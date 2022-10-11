package Models;

import Controllers.Controller;

import java.util.concurrent.LinkedBlockingQueue;

import static java.lang.Thread.sleep;

public class FastFood {
    public static FastFood fastFood;
    private final Cook cook;
    private final LinkedBlockingQueue<Customer> customersToOrder, recipients;
    private final LinkedBlockingQueue<Order> ordersToKitchen, ordersToServer;
    private final OrderTaker orderTaker;
    private final Server server;
    private final VisitorGenerator visitorGenerator;
    private final Controller controller;
    private final Thread thread;
    public FastFood(Controller controller, int timeCook, int timeVisitorGenerator)
    {
        fastFood = this;
        this.controller = controller;
        customersToOrder = new LinkedBlockingQueue<>();
        recipients = new LinkedBlockingQueue<>();
        ordersToKitchen = new LinkedBlockingQueue<>();
        ordersToServer = new LinkedBlockingQueue<>();
        cook = new Cook(controller,ordersToKitchen,ordersToServer,timeCook);
        orderTaker = new OrderTaker(controller,ordersToKitchen,customersToOrder);
        server = new Server(controller,recipients,ordersToServer);
        visitorGenerator = new VisitorGenerator(controller,customersToOrder, recipients,timeVisitorGenerator);
        thread = new Thread(this::UpdateQueue);
    }
    public void setCookTime(int time)
    {
        cook.setTime(time);
    }
    public void setVisitorGeneratorTime(int time)
    {
        visitorGenerator.setTime(time);
    }

    public void UpdateQueue()
    {
        try
        {
            while(true) {
                controller.updateTextNumberOfCustomers(String.valueOf(customersToOrder.size()));
                controller.updateTextCountOrders(String.valueOf(ordersToKitchen.size()));
                controller.updateTextNumberCustomersServingLine(String.valueOf(recipients.size()));
                sleep(1);
            }
        }catch (InterruptedException interruptedException){}
    }
    public void Start()
    {
        orderTaker.start();
        cook.start();
        server.start();
        visitorGenerator.start();
        thread.start();
    }
    public void Stop()
    {
        orderTaker.interrupt();
        cook.interrupt();
        server.interrupt();
        visitorGenerator.interrupt();
        thread.interrupt();
    }

}
