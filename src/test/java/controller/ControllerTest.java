package controller;

import co.edu.uniminuto.controller.Controller;
import co.edu.uniminuto.model.Customer;
import co.edu.uniminuto.model.manager.CustomerManager;
import co.edu.uniminuto.view.MyJDialogAddEmployee;
import co.edu.uniminuto.view.MyJdialogConfirmDelete;
import co.edu.uniminuto.view.MyPrincipalFrame;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ControllerTest {

    @Mock
    private MyPrincipalFrame principalFrame;

    @Mock
    private MyJDialogAddEmployee myJDialogAddEmployee;

    @Mock
    private MyJDialogAddEmployee myJDialogUpdateEmployee;

    @Mock
    private CustomerManager customerManager;

    @Mock
    private MyJdialogConfirmDelete jdialogConfirmDelete;

    @InjectMocks
    private Controller controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testActionPerformedAdd() {
        List<Customer> customers = new ArrayList<>();
        customers.add(new Customer());

        when(customerManager.findCustomers()).thenReturn((ArrayList<Customer>) customers);

        ActionEvent event = new ActionEvent(new JButton(), 1, "ADD");
        controller.actionPerformed(event);

        verify(myJDialogAddEmployee).viweDialog();
    }

    @Test
    void testActionPerformedAcceptAdd() {
        String[] customerData = {"John", "Doe", "john@example.com", "123456789"};
        when(myJDialogAddEmployee.getData()).thenReturn(customerData);
        when(customerManager.findCustomers()).thenReturn(new ArrayList<>());

        ActionEvent event = new ActionEvent(new JButton(), 1, "ACCEPT_ADD");
        controller.actionPerformed(event);

        verify(customerManager).addCustomer(customerData);
        verify(myJDialogAddEmployee).resetValues();
        verify(myJDialogAddEmployee).disposeDialog();
        verify(principalFrame).setData(any(), eq(controller));
    }

    @Test
    void testActionPerformedUpdate() {
        String idCustomer = "1";
        Customer customer = new Customer();
        customer.setId(Long.valueOf(idCustomer));
        ArrayList<Customer> customers = new ArrayList<>();
        customers.add(customer);
        when(customerManager.findCustomer(idCustomer)).thenReturn(customers);

        JButton sourceButton = new JButton();
        sourceButton.setName(idCustomer);
        ActionEvent event = new ActionEvent(sourceButton, 1, "UPDATE");
        controller.actionPerformed(event);

        verify(customerManager).findCustomer(idCustomer);
    }

    @Test
    void testActionPerformedAcceptUpdate() {
        String idCustomerUpdate = "1";
        String[] customerDataUpdate = {"John", "Doe", "john@example.com", "123456789"};
        when(myJDialogUpdateEmployee.getData()).thenReturn(customerDataUpdate);
        when(customerManager.findCustomers()).thenReturn(new ArrayList<>());

        JButton sourceButton = new JButton();
        sourceButton.setName(idCustomerUpdate);
        ActionEvent event = new ActionEvent(sourceButton, 1, "ACCEPT_UPDATE");
        controller.actionPerformed(event);

        verify(customerManager).updateCustomer(eq(new String[]{idCustomerUpdate, customerDataUpdate[0], customerDataUpdate[1], customerDataUpdate[2], customerDataUpdate[3]}));
        verify(myJDialogUpdateEmployee).resetValues();
        verify(myJDialogUpdateEmployee).disposeDialog();
        verify(principalFrame).setData(any(), eq(controller));
    }

    @Test
    void testActionPerformedAcceptDelete() {
        String idCustomerDeleteOk = "1";
        when(customerManager.findCustomers()).thenReturn(new ArrayList<>());

        JButton sourceButton = new JButton();
        sourceButton.setName(idCustomerDeleteOk);
        ActionEvent event = new ActionEvent(sourceButton, 1, "ACCEPT_DELETE");
        controller.actionPerformed(event);

        verify(customerManager).deleteCustomer(idCustomerDeleteOk);
        verify(principalFrame).setData(any(), eq(controller));
        verify(jdialogConfirmDelete).deleteDialog();
    }

    @Test
    void testActionPerformedCancelDelete() {
        ActionEvent event = new ActionEvent(new JButton(), 1, "CANCEL_DELETE");
        controller.actionPerformed(event);

        verify(jdialogConfirmDelete).deleteDialog();
    }
}