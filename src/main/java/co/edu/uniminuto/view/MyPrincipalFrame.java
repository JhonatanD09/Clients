package co.edu.uniminuto.view;

import co.edu.uniminuto.controller.Options;
import co.edu.uniminuto.model.Customer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MyPrincipalFrame extends JFrame {

    private JScrollPane jScrollPane;
    private MyGridPanel gridPanel;
    private JButton add;
    private JPanel panelData;

    public MyPrincipalFrame(ActionListener l){
        this.gridPanel = new MyGridPanel();
        gridPanel.setBackground(Color.WHITE);
        this.add = new JButton("+  Agregar cliente");
        editButton(add, Options.ADD.name(), l);


        this.panelData = new JPanel();
        this.panelData.setLayout(new GridLayout(10,1));
        panelData.setBackground(Color.decode("#f1f5f3"));




        jScrollPane = new JScrollPane(panelData);

        this.setSize(1100,800);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Supermercado la exitosa");
        addPanels();
        this.add(gridPanel);

    }

    public void setData(ArrayList<Customer>customers,ActionListener l){
        this.panelData.removeAll();

        JPanel header = new JPanel();
        header.setLayout(new GridLayout(1,6));
        JLabel name = new JLabel("Nombre Completo",SwingConstants.CENTER);
        editLabel(name);
        header.add(name);
        JLabel address = new JLabel("Direccion",SwingConstants.CENTER);
        editLabel(address);
        header.add(address);
        JLabel rol = new JLabel("Rol",SwingConstants.CENTER);
        editLabel(rol);
        header.add(rol);
        JLabel edit = new JLabel("",SwingConstants.CENTER);
        editLabel(edit);
        header.add(edit);



        panelData.add(header);

        for (Customer c : customers){
            panelData.add(new MyJpanelTarjetCustomer(c,l));
        }
        this.revalidate();
        this.repaint();
    }

    private void addPanels(){

        JLabel jLabel = new JLabel("SUPERMERCADO LA EXITOSA",SwingConstants.CENTER);
        jLabel.setOpaque(true);
        jLabel.setBackground(Color.WHITE);
        jLabel.setFont(new Font("Arial",Font.BOLD,30));
        gridPanel.addComponent(jLabel,0,0,12,0.03);
        gridPanel.addComponent(jScrollPane,0,1,12,0.09);
        JPanel gapTop = new JPanel();
        gapTop.setBackground(Color.WHITE);
        gridPanel.addComponent(gapTop,5,2,2,0.01);
        gridPanel.addComponent(add,5,3,2,0.01);
        JPanel gapButton = new JPanel();
        gapButton.setBackground(Color.WHITE);
        gridPanel.addComponent(gapButton,5,4,2,0.01);
    }

    private void editButton(JButton jButton, String ActionCommand, ActionListener l){
        jButton.setActionCommand(ActionCommand);
        jButton.addActionListener(l);
        jButton.setBackground(Color.decode("#18b26d"));
        jButton.setForeground(Color.WHITE);
        jButton.setFont(new Font("Arial",Font.BOLD,20));
        jButton.setBorderPainted(false);
        jButton.setFocusable(false);
    }

    private void editLabel(JLabel jLabel){
        jLabel.setOpaque(true);
        jLabel.setBackground(Color.BLACK);
        jLabel.setForeground(Color.WHITE);
        jLabel.setFont(new Font("Arial",Font.BOLD,20));
    }

}
