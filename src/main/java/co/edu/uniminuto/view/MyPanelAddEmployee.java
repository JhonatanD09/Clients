package co.edu.uniminuto.view;

import co.edu.uniminuto.controller.Options;
import co.edu.uniminuto.model.Customer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MyPanelAddEmployee extends JPanel {

    private  MyGridPanel gridPanel;
    private JTextField foreName;
    private JTextField lastName;
    private JTextField address;
    private JComboBox<String> role;
    private JButton add;

    public MyPanelAddEmployee(ActionListener l, Customer c){

        gridPanel = new MyGridPanel();

        JLabel labelForeName = new JLabel("Nombre(s)");
        labelForeName.setOpaque(false);

        foreName = new JTextField(c==null ? "":c.getForeName());
        setArea(foreName);

        JLabel labelLastName = new JLabel("Apellido(s)");
        labelLastName.setOpaque(false);

        lastName = new JTextField(c==null?"":c.getLastName());
        setArea(lastName);

        JLabel labelAdress = new JLabel("Direccion");
        labelAdress.setOpaque(false);

        address = new JTextField(c==null?"":c.getAddress());
        setArea(address);

        JLabel labelComboBox = new JLabel("Rol");
        labelAdress.setOpaque(false);

        role = new JComboBox<String>();
        role.addItem("Gold");
        role.addItem("Standard");
        role.addItem("Premium");

        if (c!=null){
            switch (c.getRole()) {
                case 'G' -> role.setSelectedIndex(0);
                case 'S' ->  role.setSelectedIndex(1);
                case 'P' ->  role.setSelectedIndex(2);
            }
        }

        add = new JButton(c==null?"Agregar":"Editar");
        editButton(add, c==null? Options.ACCEPT_ADD.name(): Options.ACCEPT_UPDATE.name()  ,l , c);

        this.setBackground(Color.PINK);
        this.setLayout(new BorderLayout());

        gridPanel.addComponent(labelForeName,0,1,12,0.01);
        gridPanel.addComponent(foreName,0,2,12,0.01);
        gridPanel.addComponent(labelLastName,0,3,12,0.01);
        gridPanel.addComponent(lastName,0,4,12,0.01);
        gridPanel.addComponent(labelAdress,0,5,12,0.01);
        gridPanel.addComponent(address,0,6,12,0.01);
        gridPanel.addComponent(labelComboBox,0,7,12,0.01);
        gridPanel.addComponent(role,0,8,12,0.01);
        JPanel gap = new JPanel();
        gap.setOpaque(false);
        gridPanel.addComponent(gap,0,9,12,0.01);
        gridPanel.addComponent(add,0,10,12,0.01);
        gridPanel.setBackground(Color.WHITE);
        add(gridPanel);
    }




    private void setArea(JTextField jTextArea){
        jTextArea.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black));
    }

    private void editButton(JButton jButton, String ActionCommand, ActionListener l, Customer c){
        jButton.setActionCommand(ActionCommand);
        jButton.addActionListener(l);
        jButton.setBackground(Color.decode(jButton.getText()=="Agregar"?"#18b26d":"#e8a711"));
        jButton.setForeground(Color.WHITE);
        jButton.setFont(new Font("Arial",Font.BOLD,20));
        jButton.setBorderPainted(false);
        jButton.setFocusable(false);
        jButton.setName(String.valueOf(c==null?-1:c.getId()));
    }

    public String[] getData(){
        String roleSlected = (String) role.getSelectedItem();
        return new String[]{foreName.getText(),lastName.getText(),address.getText(),String.valueOf(roleSlected.charAt(0))};
    }

    public void resetValues(){
        foreName.setText("");
        lastName.setText("");
        address.setText("");
    }
}
