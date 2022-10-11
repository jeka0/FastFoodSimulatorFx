package Models;

import Controllers.Controller;

import java.util.concurrent.LinkedBlockingQueue;

public class Server extends Thread{
    private final Controller controller;
    private final LinkedBlockingQueue<Customer> recipients;
    private final LinkedBlockingQueue<Order> ordersToServer;
    private int count;
    public Server(Controller controller,LinkedBlockingQueue<Customer> recipients, LinkedBlockingQueue<Order> ordersToServer)
    {
        super();
        this.controller=controller;
        this.recipients=recipients;
        this.ordersToServer=ordersToServer;
    }

    public void run()
    {
        try {
            while(true) {
                controller.AddTag("Server is start");
                controller.updateTextOrderNumberAvailablePickup("NaN");
                Order nowOrder = ordersToServer.take();
                controller.updateTextOrderNumberAvailablePickup(String.valueOf(nowOrder.getOderNumber()));
                controller.AddTag("Server Order: take order");
                sleep(200);
                count++;
                Customer customer=recipients.take();
                controller.AddTag("Customer leve from recipients");
                controller.AddTag("Server is end");
            }
        }catch(InterruptedException interruptedException)
        {

        }
    }

}
