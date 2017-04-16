package testCase;

import com.dev4dan.dao.IOrderDao;
import com.dev4dan.model.Order;
import org.junit.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

/**
 * Created by danielwood on 25/03/2017.
 */
public class AssertControllerTest {


    @Mock
    private IOrderDao orderDao;

    @Test
    public void testQueryByID(){
        List<Date> dates = new ArrayList();
        Date date = new Date();
        dates.add(date);

        IOrderDao orderDao = mock(IOrderDao.class);
        when(orderDao.getOrderByDates(dates)).thenReturn(new ArrayList<Order>());

        when(orderDao.getOrderById(10)).thenReturn(new Order());
    }
}
