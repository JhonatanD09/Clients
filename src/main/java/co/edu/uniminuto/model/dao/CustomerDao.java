package co.edu.uniminuto.model.dao;

import co.edu.uniminuto.model.Customer;
import co.edu.uniminuto.model.TrasactionEnum;

import java.sql.*;
import java.text.MessageFormat;
import java.util.ArrayList;

public class CustomerDao {

    public static final String SPACE = " ";
    public static final String AND = " AND ";

    public ArrayList<Customer> conectDB(String transactionType, Customer customer) {
        String url = "jdbc:mysql://mysql-dsotofuya.alwaysdata.net/dsotofuya_actividad_s3";
        String username = "dsotofuya";
        String password = "robot2300";

        System.out.println("Init transaction " + transactionType);
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            switch (TrasactionEnum.valueOf(transactionType)){
                case INSERT:
                    insert(connection, customer);
                    break;
                case UPDATE:
                    update(connection, customer);
                    break;
                case DELETE:
                    delete(connection, customer);
                    break;
                case SELECT:
                    return select(connection, customer);
                case SELECT_ALL:
                    return selectAll(connection);
                default:
                    break;
            }
            System.out.println("Transaction Complete!");
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
        return null;
    }

    private void insert(Connection connection, Customer customer) throws SQLException {
        String query = "INSERT INTO " +
                "`CLIENTS`(`fore_name`, `last_name`, `address`, `role`)" +
                " VALUES (?,?,?,?)";
        PreparedStatement preparedStmt = connection.prepareStatement(query);
        preparedStmt.setString (1, customer.getForeName());
        preparedStmt.setString (2, customer.getLastName());
        preparedStmt.setString (3, customer.getAddress());
        preparedStmt.setString(4, String.valueOf(customer.getRole()));

        try{
            preparedStmt.execute();
            connection.close();
        } catch (Exception e) {
            System.err.println("Error al ejecutar el insert");
            e.printStackTrace();
        }
    }

    private ArrayList<Customer> select(Connection connection, Customer customer) throws SQLException {
        ArrayList<Customer> customers = new ArrayList<>();
        Statement statement = connection.createStatement();

        String filters = buidFilters(customer, AND);
        //String filters = buidFilters(new Customer(Long.valueOf(1), null, "Test", null, 'S'), AND);

        String queryFormated = MessageFormat.format("SELECT * FROM CLIENTS WHERE {0}{1}", validateId(customer.getId()), (!filters.isEmpty() ? AND + filters : filters) );
        System.out.println(queryFormated);
        ResultSet rs = statement.executeQuery(queryFormated);

        while (rs.next()) {
            customers.add(
                    new Customer(rs.getLong("clients_id"),
                            rs.getString("fore_name"), rs.getString("last_name"),
                            rs.getString("address"), rs.getString("role").charAt(0)));
        }
        return customers;
    }

    private String validateId(Long id) {
        if (id != null){
            return "`clients_id` = " + String.valueOf(id);
        }
        return "";
    }

    private ArrayList<Customer> selectAll(Connection connection) throws SQLException {
        ArrayList<Customer> customers = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("SELECT * FROM CLIENTS WHERE 1 = 1");
        while (rs.next()) {
            customers.add(
                    new Customer(rs.getLong("clients_id"),
                            rs.getString("fore_name"), rs.getString("last_name"),
                            rs.getString("address"), rs.getString("role").charAt(0)));
        }
        return customers;
    }

    private void update(Connection connection, Customer customer) throws SQLException {
        String query = "UPDATE `CLIENTS` " +
                "SET {0}" +
                "WHERE clients_id = ?;";
        String queryFormated = MessageFormat.format(query, buidFilters(customer, ","));
        System.out.println(queryFormated);
        PreparedStatement preparedStmt = connection.prepareStatement(queryFormated);
        preparedStmt.setString (1, String.valueOf(customer.getId()));

        try{
           preparedStmt.execute();
            connection.close();
        } catch (Exception e) {
            System.err.println("Error al ejecutar el insert");
            e.printStackTrace();
        }
    }

    private void delete(Connection connection, Customer customer) throws SQLException {
        String query = "DELETE FROM `CLIENTS`" +
                        "WHERE clients_id = ?;";
        PreparedStatement preparedStmt = connection.prepareStatement(query);
        preparedStmt.setString (1, String.valueOf(customer.getId()));

        try{
            preparedStmt.execute();
            connection.close();
        } catch (Exception e) {
            System.err.println("Error in delete id " + customer.getId());
            e.printStackTrace();
        }
    }

    private String buidFilters(Customer customer, String conector) {
        StringBuilder builder = new StringBuilder();
        if (customer.getForeName() != null){
            builder.append(" `fore_name` = '").append(customer.getForeName()).append("' ");
        }
        if (customer.getLastName() != null){
            isAddAnd(builder, conector);
            builder.append(" `last_name` = '").append(customer.getLastName()).append("' ");
        }
        if (customer.getAddress() != null){
            isAddAnd(builder, conector);
            builder.append(" `address` = '").append(customer.getAddress()).append("' ");
        }
        if (!String.valueOf(customer.getRole()).equals(SPACE)){
            isAddAnd(builder, conector);
            builder.append(" `role` =  '").append(customer.getRole()).append("' ");
        }
        return builder.toString();
    }

    private void isAddAnd(StringBuilder builder, String conector) {
        if (builder.toString().endsWith("' ")){
            builder.append(conector);
        }
    }
}
