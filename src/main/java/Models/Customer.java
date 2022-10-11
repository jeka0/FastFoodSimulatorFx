package Models;

import java.util.concurrent.LinkedBlockingQueue;

public class Customer{
    private final LinkedBlockingQueue<Customer> customersToOrder, recipients;
    private String oderNumber;
    public Customer(LinkedBlockingQueue<Customer> customersToOrder, LinkedBlockingQueue<Customer> recipients)
    {
        super();
        this.customersToOrder = customersToOrder;
        this.recipients = recipients;
    }
    public String getOderNumber() {
        return oderNumber;
    }
    public void setOderNumber( String orderNumber)
    {
        this.oderNumber = orderNumber;
    }
    public void enterInRecipients()
    {
        try {
            recipients.put(this);
        }catch (InterruptedException interruptedException)
        {

        }
    }

}
