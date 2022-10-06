package Models;

import java.util.concurrent.LinkedBlockingQueue;

public class VisitorGenerator extends Thread{
    private final LinkedBlockingQueue<Customer> customersToOrder, recipients;
    private int time;
    public VisitorGenerator(LinkedBlockingQueue<Customer> customersToOrder,LinkedBlockingQueue<Customer> recipients, int time)
    {
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
