package model.manager;

import co.edu.uniminuto.model.TrasactionEnum;
import co.edu.uniminuto.model.dao.CustomerDao;
import co.edu.uniminuto.model.manager.CustomerManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;

public class CustomerManagerTest {

    @Mock CustomerDao customerDao;

    @InjectMocks CustomerManager customerManager;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addCustomer(){
        String[] dummyCustomer = getCustomer();

        customerManager.addCustomer(dummyCustomer);
        verify(customerDao).conectDB(anyString(), any());
    }

    @Test
    void deleteCustomer(){
        customerManager.deleteCustomer("1");
        verify(customerDao).conectDB(anyString(), any());
    }

    @Test
    void updateCustomer(){
        customerManager.updateCustomer(getCustomer());
        verify(customerDao).conectDB(anyString(), any());
    }

    @Test
    void findCustomer(){
        customerManager.findCustomer("1");
        verify(customerDao).conectDB(anyString(), any());
    }

    @Test
    void findCustomers(){
        customerManager.findCustomers();
        verify(customerDao).conectDB(anyString(), any());
    }

    private static String[] getCustomer() {
        return new String[]{ "1", "Dummy nombre", "Dummy apellido", "Dummy dirección", "S" };
    }

}
