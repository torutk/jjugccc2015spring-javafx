/*
 * Copyright (C) by TAKAHASHI,Toru
 */
package analogclock.imaging;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author toru
 */
public class AnalogClockImaging extends Application {

    @Override
    public void start(Stage stage) {
        StackPane root = new StackPane();
        ImageView clockDial = new ImageView(new Image("file:clockDial.png"));
        ImageView hourHand = new ImageView(new Image("file:clockHourHand.png"));
        ImageView minuteHand = new ImageView(new Image("file:clockMinuteHand.png"));
        ImageView secondHand = new ImageView(new Image("file:clockSecondHand.png"));
        ImageView centerPoint = new ImageView(new Image("file:clockCenterPoint.png"));
        
        root.getChildren().addAll(clockDial, hourHand, minuteHand, secondHand, centerPoint);
        
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    
}
