package Controllers;

public interface Controller {
    void updateTextNumberOfCustomers(String str);
    void updateTextOrderNumberPrepared(String str);
    void updateTextCountOrders(String str);
    void updateTextOrderNumberAvailablePickup(String str);
    void updateTextNumberCustomersServingLine(String str);
    void updateTextCurrentlyOrderNumber(String str);
    void AddTag(String str);
    void AddOrder(String orderNum);
    void RemoveOrder(String orderNum);
}
