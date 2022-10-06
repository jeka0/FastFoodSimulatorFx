package Models;

import java.util.concurrent.LinkedBlockingQueue;

public class Cook extends Thread{
    private final LinkedBlockingQueue<Order> ordersToKitchen, ordersToServer;
    private int time=2000;

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public Cook(LinkedBlockingQueue<Order> ordersToKitchen, LinkedBlockingQueue<Order> ordersToServer, int time)
    {
        super();
        this.ordersToKitchen = ordersToKitchen;
        this.ordersToServer = ordersToServer;
        this.time = time;
    }
    public void run()
    {
        try {
            while (true) {
                System.out.println("Cook is start");
                Order nowOrder = ordersToKitchen.take();
                System.out.println("Cook Order: <--");
                sleep(time);
                ordersToServer.put(nowOrder);
                System.out.println("Cook Order: -->");
                System.out.println("Cook is end");
            }
        }catch(InterruptedException interruptedException)
        {

        }

    }

}
