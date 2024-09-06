package co.edu.uniminuto.view;

import co.edu.uniminuto.controller.Options;
import co.edu.uniminuto.model.Customer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.net.URL;

public class MyJpanelTarjetCustomer extends JPanel {


    private static final String RELATIVE_URL = "src/main/java/co/edu/uniminuto/view/resources/";
    private JButton edit,delete;

    public MyJpanelTarjetCustomer(Customer customer, ActionListener l){

        this.setLayout(new BorderLayout());
        this.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, Color.black));
        this.setPreferredSize(new Dimension(100,50));
        this.setBackground(Color.WHITE);

        JLabel name = new JLabel(customer.getForeName()+" "+ customer.getLastName(), SwingConstants.CENTER);
        editLabel(name);

        JLabel adress = new JLabel(customer.getAddress(),SwingConstants.CENTER);
        editLabel(adress);

        JLabel role = new JLabel(role(customer.getRole()),SwingConstants.CENTER);
        editLabel(role);

        ImageIcon iconEdit = new ImageIcon(RELATIVE_URL + "edit.png");
        Image image = iconEdit.getImage(); // Get the image
        Image newimg = image.getScaledInstance(50, 35, java.awt.Image.SCALE_SMOOTH); // Scale it smoothly
        iconEdit = new ImageIcon(newimg);
        edit = new JButton(iconEdit);
        editButton(edit, Options.UPDATE.name(),l,customer.getId());
        ImageIcon iconDelete = new ImageIcon(RELATIVE_URL+"delete.png");
        Image imageDel = iconDelete.getImage(); // Get the image
        Image newimgDel = imageDel.getScaledInstance(35, 35, java.awt.Image.SCALE_SMOOTH); // Scale it smoothly
        iconDelete = new ImageIcon(newimgDel);
        delete = new JButton(iconDelete);
        editButton(delete, Options.DELETE.name(),l,customer.getId());

        JPanel data = new JPanel();
        data.setLayout(new GridLayout(1,3));
        data.add(name);
        data.add(adress);
        data.add(role);
        data.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.black));

        JPanel buttons = new JPanel();
        buttons.setPreferredSize(new Dimension(270,100));
        buttons.setLayout(new GridLayout(1,2));
        buttons.add(edit);
        buttons.add(delete);

        add(data,BorderLayout.CENTER);
        add(buttons, BorderLayout.EAST);
    }

    private void editButton(JButton jButton, String ActionCommand, ActionListener l, long id){
        jButton.setActionCommand(ActionCommand);
        jButton.addActionListener(l);
        jButton.setOpaque(true);
        jButton.setBorderPainted(false);
        jButton.setFocusable(false);
        jButton.setBackground(Color.WHITE);
        jButton.setName(String.valueOf(id));
    }
    
    private void editLabel(JLabel jLabel){
        jLabel.setOpaque(true);
        jLabel.setBackground(Color.WHITE);
        jLabel.setFont(new Font("Arial",Font.BOLD,20));
    }

    private  String role (char option){
        return switch (option) {
            case 'G' -> "Gold";
            case 'S' -> "Standard";
            case 'P' -> "Premium";
            default -> "";
        };
    }


}


