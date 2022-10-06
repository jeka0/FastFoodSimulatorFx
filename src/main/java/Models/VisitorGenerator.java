package Models;

import Controllers.Controller;

import java.util.concurrent.LinkedBlockingQueue;

public class VisitorGenerator extends Thread{
    private final Controller controller;
    private final LinkedBlockingQueue<Customer> customersToOrder, recipients;
    private int time;
    public VisitorGenerator(Controller controller,LinkedBlockingQueue<Customer> customersToOrder,LinkedBlockingQueue<Customer> recipients, int time)
    {
        this.controller=controller;
        this.customersToOrder = customersToOrder;
        this.recipients=recipients;
        this.time = time;
    }
    public void run()
    {
        try
        {
            while(true) {
                customersToOrder.put(new Customer(customersToOrder, recipients));
                System.out.println("Customer enter in customersToOrder");
                sleep(time);
            }
        }catch (InterruptedException interruptedException){}
    }
}
