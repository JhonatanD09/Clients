package co.edu.uniminuto.view;

import co.edu.uniminuto.controller.Options;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MyJdialogConfirmDelete extends JDialog {

    public  MyJdialogConfirmDelete(ActionListener l, long id){
        this.setModal(true);
        this.setUndecorated(true);
        this.setSize(new Dimension(300,150));
        this.setLocationRelativeTo(null);


        this.setLayout(new BorderLayout());

        JLabel message = new JLabel("<html>Seguro que quieres <br>eliminar el registro?</html>",SwingConstants.CENTER);
        editLabel(message);

        add(message, BorderLayout.CENTER);

        JButton accept = new JButton("Eliminar");
        editButton(accept, Options.ACCEPT_DELETE.name(), l,"#f11e1e",id);
        JButton cancel = new JButton("Cancelar");
        editButton(cancel, Options.CANCEL_DELETE.name(), l,"#166caf",id);

        JPanel buttons = new JPanel();
        buttons.setLayout(new GridLayout(1,2,10,10));

        buttons.add(accept);
        buttons.add(cancel);

        add(buttons,BorderLayout.SOUTH);

    }

    public void showDialog(){
        this.setVisible(true);
    }

    public void deleteDialog(){
        this.dispose();
    }

    private void editLabel(JLabel jLabel){
        jLabel.setOpaque(true);
        jLabel.setBackground(Color.WHITE);
        jLabel.setForeground(Color.BLACK);
        jLabel.setFont(new Font("Arial",Font.BOLD,20));
    }
    private void editButton(JButton jButton, String ActionCommand, ActionListener l, String color,long id){
        jButton.setActionCommand(ActionCommand);
        jButton.addActionListener(l);
        jButton.setOpaque(true);
        jButton.setBorderPainted(false);
        jButton.setFocusable(false);
        jButton.setBackground(Color.decode(color));
        jButton.setForeground(Color.white);
        jButton.setFont(new Font("Arial",Font.BOLD,15));
        jButton.setName(String.valueOf(id));
    }

}
