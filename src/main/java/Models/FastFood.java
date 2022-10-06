package Models;

import java.util.concurrent.LinkedBlockingQueue;

public class FastFood {
    private final Cook cook;
    private final LinkedBlockingQueue<Customer> customersToOrder, recipients;
    private final LinkedBlockingQueue<Order> ordersToKitchen, ordersToServer;
    private final OrderTaker orderTaker;
    private final Server server;
    private final VisitorGenerator visitorGenerator;
    public FastFood()
    {
        customersToOrder = new LinkedBlockingQueue<>();
        recipients = new LinkedBlockingQueue<>();
        ordersToKitchen = new LinkedBlockingQueue<>();
        ordersToServer = new LinkedBlockingQueue<>();
        cook = new Cook(ordersToKitchen,ordersToServer,2000);
        orderTaker = new OrderTaker(ordersToKitchen,customersToOrder);
        server = new Server(recipients,ordersToServer);
        visitorGenerator = new VisitorGenerator(customersToOrder, recipients,500);
    }
    public void Start()
    {
        orderTaker.start();
        cook.start();
        server.start();
        visitorGenerator.start();
    }
    public void Stop()
    {
        orderTaker.interrupt();
        cook.interrupt();
        server.interrupt();
        visitorGenerator.interrupt();
    }

}
