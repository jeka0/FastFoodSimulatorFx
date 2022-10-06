package Models;

import Controllers.Controller;

import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;

public class OrderTaker extends Thread{
    private final Controller controller;
    private final LinkedBlockingQueue<Order> ordersToKitchen;
    private final LinkedBlockingQueue<Customer> customersToOrder;
    private int id = 1;
    public OrderTaker(Controller controller,LinkedBlockingQueue<Order> ordersToKitchen, LinkedBlockingQueue<Customer> customersToOrder)
    {
        super();
        this.controller=controller;
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
                controller.updateTextCurrentlyOrderNumber(id);
                sleep(200);
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
