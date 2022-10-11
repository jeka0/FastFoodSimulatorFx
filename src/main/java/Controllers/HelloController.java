package Controllers;

import Models.FastFood;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class HelloController implements Controller{
    private FastFood fastFood;
    private int timeCook, timeVisitor;
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
    public ListView<String> ListOrders;
    @FXML
    public TextField VisitorTimeLabel;
    @FXML
    public TextField CookTimeLabel;
    @FXML
    public ListView<String> TagList;
    public HelloController()
    {
        timeCook = 1000;
        timeVisitor = 500;
    }

    @FXML
    public void ActionAssignVisitorTime()
    {
        try
        {
            int time = Integer.parseInt(VisitorTimeLabel.getText());
            if(time <=0)throw new NumberFormatException();
            timeVisitor = time*100;
            if(fastFood!=null)fastFood.setVisitorGeneratorTime(timeVisitor);
        }catch (NumberFormatException e){ShowError();}
    }

    private void ShowError()
    {
        Alert error = new Alert(Alert.AlertType.ERROR);
        error.setTitle("Error!");
        error.setHeaderText(null);
        error.setContentText("Invalid value entered!!!!");
        error.show();
    }
    @FXML
    public void ActionAssignCookTime()
    {
        try
        {
            int time = Integer.parseInt(CookTimeLabel.getText());
            if(time <=0)throw new NumberFormatException();
            timeCook = time*100;
            if(fastFood!=null)fastFood.setCookTime(timeCook);
        }catch (NumberFormatException e){ShowError();}
    }
    @FXML
    public void OnActionStart(ActionEvent event)
    {
        if(fastFood==null) {
            Platform.runLater(()->{
                TagList.getItems().clear();
                ListOrders.getItems().clear();
            });
            fastFood = new FastFood(this,timeCook,timeVisitor);
            fastFood.Start();
        }
    }
    @FXML
    public void OnActionStop(ActionEvent event)
    {
        if(fastFood!=null) {
            fastFood.Stop();
            fastFood = null;
        }
    }
    public synchronized void AddTag(String str)
    {
            Platform.runLater(() -> {
                try {
                    TagList.getItems().add(str);
                    int size = TagList.getItems().size();
                    if (size > 1)
                        TagList.scrollTo(size - 1);
                    if (size > 100) TagList.getItems().remove(0, 10);
                }catch (Exception e){}
            });
    }
    public synchronized void AddOrder(String orderNum)
    {
        Platform.runLater(()-> {
            try {ListOrders.getItems().add(orderNum);}catch (Exception e){}
        });
    }
    public synchronized void RemoveOrder(String orderNum)
    {
        Platform.runLater(()-> {
           try{ListOrders.getItems().remove(orderNum);}catch (Exception e){}
        });
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