package Models;

import Controllers.Controller;

import java.util.concurrent.LinkedBlockingQueue;

public class Cook extends Thread{
    private final Controller controller;
    private final LinkedBlockingQueue<Order> ordersToKitchen, ordersToServer;
    private int time;

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public Cook(Controller controller,LinkedBlockingQueue<Order> ordersToKitchen, LinkedBlockingQueue<Order> ordersToServer, int time)
    {
        super();
        this.controller=controller;
        this.ordersToKitchen = ordersToKitchen;
        this.ordersToServer = ordersToServer;
        this.time = time;
    }
    public void run()
    {
        try {
            while (true) {
                controller.AddTag("Cook is start");
                Order nowOrder = ordersToKitchen.take();
                controller.updateTextOrderNumberPrepared(nowOrder.getOderNumber());
                controller.RemoveOrder(nowOrder.getOderNumber());
                controller.AddTag("Cook Order: take order");
                sleep(time);
                ordersToServer.put(nowOrder);
                controller.AddTag("Cook Order: send order");
                controller.AddTag("Cook is end");
            }
        }catch(InterruptedException interruptedException)
        {

        }

    }

}
