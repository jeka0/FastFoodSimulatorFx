package Models;

import java.util.concurrent.LinkedBlockingQueue;

public class Server extends Thread{
    private final LinkedBlockingQueue<Customer> recipients;
    private final LinkedBlockingQueue<Order> ordersToServer;
    private int count;
    public Server(LinkedBlockingQueue<Customer> recipients, LinkedBlockingQueue<Order> ordersToServer)
    {
        super();
        this.recipients=recipients;
        this.ordersToServer=ordersToServer;
    }

    public void run()
    {
        try {
            while(true) {
                System.out.println("Server is start");
                Order nowOrder = ordersToServer.take();
                System.out.println("Server Order: <--");
                count++;
                Customer customer=recipients.take();
                System.out.println("Customer leve from recipients");
                System.out.println("Server is end");
            }
        }catch(InterruptedException interruptedException)
        {

        }
    }

}
