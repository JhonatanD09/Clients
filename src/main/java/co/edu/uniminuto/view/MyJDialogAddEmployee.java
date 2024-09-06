package co.edu.uniminuto.view;

import co.edu.uniminuto.model.Customer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyJDialogAddEmployee extends JDialog {

    private MyGridPanel gridPanel;
    private MyPanelAddEmployee panelAddEmployee;

    public MyJDialogAddEmployee(ActionListener listener, Customer c){
            this.setSize(370,450);
            this.setLocationRelativeTo(null);
            this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

            this.gridPanel = new MyGridPanel();
            JLabel jLabel = selectTitle(c);
            jLabel.setOpaque(true);
            jLabel.setBackground(Color.decode(
                    c==null?"#0fa15f":"#d09a1d"
            ));
            jLabel.setFont(new Font("Arial",Font.BOLD,20));
            jLabel.setForeground(Color.WHITE);

            gridPanel.addComponent(jLabel,0,0,12,0.1);

            panelAddEmployee = new MyPanelAddEmployee(listener,c);

            gridPanel.addComponent(panelAddEmployee,0,1,12,0.9);

            add(gridPanel);
    }

    private JLabel selectTitle (Customer c){
        return c==null ?  new JLabel("Agregar cliente",SwingConstants.CENTER) :  new JLabel("Editar cliente",SwingConstants.CENTER);
    }
    public  void viweDialog(){
        this.setVisible(true);
    }

    public void disposeDialog(){
        this.dispose();
    }

    public String [] getData(){
       return  this.panelAddEmployee.getData();
    }

    public void resetValues(){
        this.panelAddEmployee.resetValues();
    }

}
