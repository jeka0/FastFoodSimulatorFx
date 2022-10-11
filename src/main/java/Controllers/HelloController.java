package Controllers;

import Models.FastFood;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;

public class HelloController implements Controller{
    private FastFood fastFood;
    @FXML
    public Text NumberOfCustomers;
    @FXML
    public Text OrderNumberPrepared;
    @FXML
    public Text CountOrders;
    @FXML
    public Text OrderNumberAvailablePickup;
    @FXML
    public Text NumberCustomersServingLine;
    @FXML
    public Text CurrentlyOrderNumber;
    @FXML
    public ListView ListOrders;
    public HelloController()
    {
        fastFood = new FastFood(this);
    }
    @FXML
    public void OnActionStart(ActionEvent event)
    {
        fastFood.Start();
    }
    public void updateTextNumberOfCustomers(String str)
    {
        NumberOfCustomers.setText("Number of customers waiting to place orders: "+str);
    }
    public void updateTextOrderNumberPrepared(String str)
    {
        OrderNumberPrepared.setText("Order number that'sbeing prepared: "+str);
    }
    public void updateTextCountOrders(String str)
    {
        CountOrders.setText("Count of the number of waiting orders: "+str);
    }
    public void updateTextOrderNumberAvailablePickup(String str)
    {
        OrderNumberAvailablePickup.setText("Order number that'scurrently available for pickup by the Customer: "+str);
    }
    public void updateTextNumberCustomersServingLine(String str)
    {
        NumberCustomersServingLine.setText("Number of Customers waiting in the serving line: "+str);
    }
    public void updateTextCurrentlyOrderNumber(String str)
    {
        CurrentlyOrderNumber.setText("Currently Order number: "+str);
    }


}