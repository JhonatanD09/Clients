package co.edu.uniminuto.controller;

import co.edu.uniminuto.model.Customer;
import co.edu.uniminuto.model.dao.CustomerDao;
import co.edu.uniminuto.model.manager.CustomerManager;
import co.edu.uniminuto.view.MyJDialogAddEmployee;
import co.edu.uniminuto.view.MyJdialogConfirmDelete;
import co.edu.uniminuto.view.MyJpanelTarjetCustomer;
import co.edu.uniminuto.view.MyPrincipalFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Optional;

public class Controller implements ActionListener {

    private MyPrincipalFrame principalFrame;
    private MyJDialogAddEmployee myJDialogAddEmployee;
    private MyJDialogAddEmployee myJDialogUpdateEmployee;
    private CustomerManager customerManager;
    private MyJdialogConfirmDelete jdialogConfirmDelete;

    public  Controller(){
        principalFrame = new MyPrincipalFrame(this);

        myJDialogAddEmployee = new MyJDialogAddEmployee(this,null);

        customerManager = new CustomerManager();

        principalFrame.setData(customerManager.findCustomers(),this);

        principalFrame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (Options.valueOf(e.getActionCommand())){
            case ADD:
                myJDialogAddEmployee.viweDialog();
                break;
            case ACCEPT_ADD:
                String [] data = myJDialogAddEmployee.getData();
                addCustomer(data);
                myJDialogAddEmployee.resetValues();
                myJDialogAddEmployee.disposeDialog();
                principalFrame.setData(customerManager.findCustomers(),this);
                break;
            case UPDATE:
                String idCustomer = ((JButton)e.getSource()).getName();
                myJDialogUpdateEmployee =  new MyJDialogAddEmployee(this,customerManager.findCustomer(idCustomer).get(0));
                myJDialogUpdateEmployee.viweDialog();
                break;
            case ACCEPT_UPDATE:
                String idCustomerUpdate = ((JButton)e.getSource()).getName();
                String [] dataUpdate = myJDialogUpdateEmployee.getData();
                updateCustomer(idCustomerUpdate,dataUpdate);
                myJDialogUpdateEmployee.resetValues();
                myJDialogUpdateEmployee.disposeDialog();
                principalFrame.setData(customerManager.findCustomers(),this);
                break;
            case DELETE:
                String idCustomerDelete = ((JButton)e.getSource()).getName();
                Long idDelete = Long.parseLong(idCustomerDelete);
                jdialogConfirmDelete = new MyJdialogConfirmDelete(this,idDelete);
                jdialogConfirmDelete.showDialog();
                break;
            case ACCEPT_DELETE:
                String idCustomerDeleteOk = ((JButton)e.getSource()).getName();
                deleteCustomer(idCustomerDeleteOk);
                principalFrame.setData(customerManager.findCustomers(),this);
                jdialogConfirmDelete.deleteDialog();
                break;
            case CANCEL_DELETE :
                jdialogConfirmDelete.deleteDialog();
                break;
            default:
                System.out.println("Default");
                break;
        }
    }

    private void addCustomer(String[] data) {
        customerManager.addCustomer(data);
    }

    private void deleteCustomer(String id) {
        customerManager.deleteCustomer(id);
    }
    private void updateCustomer(String id,String [] data){
        String[] newData = {id,data[0],data[1],data[2],data[3]};
        customerManager.updateCustomer(newData);
    }

    public static void main(String[] args) {
        new Controller();
    }
}
