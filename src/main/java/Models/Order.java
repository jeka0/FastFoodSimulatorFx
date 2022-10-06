package Models;

public class Order {
    private final String oderNumber;
    public Order(String orderNumber)
    {
        this.oderNumber = orderNumber;
    }

    public String getOderNumber() {
        return oderNumber;
    }
}
