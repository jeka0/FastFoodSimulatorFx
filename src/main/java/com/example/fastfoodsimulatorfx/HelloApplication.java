package com.example.fastfoodsimulatorfx;

import Controllers.Controller;
import Controllers.HelloController;
import Models.FastFood;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.setOnCloseRequest((WindowEvent windowEvent) ->{
            if(FastFood.fastFood!=null)FastFood.fastFood.Stop();
        });
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}