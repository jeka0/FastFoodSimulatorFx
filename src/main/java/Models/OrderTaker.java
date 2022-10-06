package Models;

import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;

public class OrderTaker extends Thread{
    private final LinkedBlockingQueue<Order> ordersToKitchen;
    private final LinkedBlockingQueue<Customer> customersToOrder;
    private int id = 1;
    public OrderTaker(LinkedBlockingQueue<Order> ordersToKitchen, LinkedBlockingQueue<Customer> customersToOrder)
    {
        super();
        this.ordersToKitchen = ordersToKitchen;
        this.customersToOrder = customersToOrder;
    }
    public void run()
    {
        try
        {
            while(true) {
                System.out.println("OrderTaker: start");
                String id = createUniqueId();
                System.out.println("OrderTaker: " + id);
                ordersToKitchen.put(new Order(id));
                System.out.println("OrderTaker Order: -->");
                Customer customer = customersToOrder.take();
                System.out.println("Customer leve from customersToOrder");
                customer.setOderNumber(id);
                customer.enterInRecipients();
                System.out.println("OrderTaker: end");
            }
        }catch (InterruptedException interruptedException)
        {

        }
    }

    private String createUniqueId()
    {
        //Random random = new Random();
        return /*Integer.toHexString(random.nextInt(900)+100) + */"#"+ id++;
    }

}
