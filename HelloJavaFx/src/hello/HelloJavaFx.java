/*
 * Copyright (C) by TAKAHASHI,Toru
 */
package hello;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 *
 * @author toru
 */
public class HelloJavaFx extends Application {

    @Override
    public void start(Stage stage) {
        Group root = new Group();
        Label label = new Label("Hello, JavaFX world.");
        root.getChildren().add(label);
        
        Scene scene = new Scene(root, 320, 200);
        stage.setScene(scene);
        stage.setTitle("HelloJavaFx");
        stage.show();
    }
    
}
