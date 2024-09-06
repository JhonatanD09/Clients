package co.edu.uniminuto.model.manager;

import co.edu.uniminuto.model.Customer;
import co.edu.uniminuto.model.TrasactionEnum;
import co.edu.uniminuto.model.dao.CustomerDao;

import java.text.MessageFormat;
import java.util.ArrayList;

public class CustomerManager {

    public static final String QUOTE = "'";

    private CustomerDao customerDao;

    public CustomerManager() {
        customerDao = new CustomerDao();
    }

    public void addCustomer(String[] data) {
        Customer customer = new Customer(data[0], data[1], data[2], data[3].charAt(0));
        customerDao.conectDB(TrasactionEnum.INSERT.toString(), customer);
    }

    public void deleteCustomer(String id) {
        Customer customer = new Customer();
        customer.setId(Long.parseLong(id));
        customerDao.conectDB(TrasactionEnum.DELETE.toString(), customer);
    }

    public void updateCustomer(String[] data) {
        Customer customer = new Customer(Long.valueOf(data[0]), data[1], data[2], data[3], (data[4] != null) ? data[4].charAt(0) : ' ');
        customerDao.conectDB(TrasactionEnum.UPDATE.toString(), customer);
    }

    public ArrayList<Customer> findCustomer(String id) {
        Customer customer = new Customer();
        customer.setId(Long.parseLong(id));
        return customerDao.conectDB(TrasactionEnum.SELECT.toString(), customer);
    }

    public ArrayList<Customer> findCustomers() {
        return customerDao.conectDB(TrasactionEnum.SELECT_ALL.toString(), null);
    }
}
