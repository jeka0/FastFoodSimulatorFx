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
                controller.AddTag("OrderTaker: start");
                String id = createUniqueId();
                controller.updateTextCurrentlyOrderNumber(id);
                sleep(300);
                controller.AddTag("OrderTaker: " + id);
                ordersToKitchen.put(new Order(id));
                controller.AddOrder(id);
                controller.AddTag("OrderTaker Order: send order");
                Customer customer = customersToOrder.take();
                controller.AddTag("Customer leve from customersToOrder");
                customer.setOderNumber(id);
                customer.enterInRecipients();
                controller.AddTag("Customer enter in recipients");
                controller.AddTag("OrderTaker: end");
            }
        }catch (InterruptedException interruptedException)
        {

        }
    }

    private String createUniqueId()
    {
        return "#"+ id++;
    }

}
