/*
 * Copyright (C) by TAKAHASHI,Toru
 */
package analogclock.svg;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.SVGPath;
import javafx.stage.Stage;

/**
 * アナログ時計（SVGで）
 * @author toru
 */
public class AnalogClockSvg extends Application {

    @Override
    public void start(Stage stage) {
        StackPane root = new StackPane();
        Node clockDial = createClockDial();
        Node hourHand = createHourHand();
        Node minuteHand = createMinuteHand();
        Node secondHand = createSecondHand();
        Node centerPoint = createCenterPoint();
        
        root.getChildren().addAll(
                clockDial, hourHand, minuteHand, secondHand, centerPoint
        );
        
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    private Node createClockDial() {
        Pane pane = new Pane(createCircle(), createTickMarks());
        pane.setPrefSize(100 * 2, 100 * 2);
        return pane;
    }
    
    private Node createCircle() {
        RadialGradient gradient = new RadialGradient(
                0, 0, 0.5, 0.5, 0.5, true, CycleMethod.NO_CYCLE,
                new Stop(0.8, Color.WHITE), new Stop(0.9, Color.BLACK),
                new Stop(0.95, Color.WHITE), new Stop(1.0, Color.BLACK)
        );
        SVGPath svg = new SVGPath();
        svg.setContent("M 100 0 A 100,100 0 1 1 99 0");
        svg.setFill(gradient);
        return svg;
    }
    
    private Node createTickMarks() {
        SVGPath svg = new SVGPath();
        svg.setContent("M 100.0,12.0 L 100.0,20.0 M 108.9,15.5 L 108.8,16.5 M 117.7,16.9 L 117.5,17.8 M 126.3,19.2 L 126.0,20.1 M 134.6,22.3 L 134.2,23.3"
                + "M 144.0,23.8 L 140.0,30.7 M 150.0,31.2 L 149.4,32.0 M 156.9,36.8 L 156.2,37.6 M 163.2,43.1 L 162.4,43.8 M 168.8,50.0 L 168.0,50.6"
                + "M 176.2,56.0 L 169.3,60.0 M 177.7,65.4 L 176.7,65.8 M 180.8,73.7 L 179.9,74.0 M 183.1,82.3 L 182.2,82.5 M 184.5,91.1 L 183.5,91.2"
                + "M 188.0,100.0 L 180.0,100.0 M 184.5,108.9 L 183.5,108.8 M 183.1,117.7 L 182.2,117.5 M 180.8,126.3 L 179.9,126.0 M 177.7,134.6 L 176.7,134.2"
                + "M 176.2,144.0 L 169.3,140.0 M 168.8,150.0 L 168.0,149.4 M 163.2,156.9 L 162.4,156.2 M 156.9,163.2 L 156.2,162.4 M 150.0,168.8 L 149.4,168.0"
                + "M 144.0,176.2 L 140.0,169.3 M 134.6,177.7 L 134.2,176.7 M 126.3,180.8 L 126.0,179.9 M 117.7,183.1 L 117.5,182.2 M 108.9,184.5 L 108.8,183.5"
                + "M 100.0,188.0 L 100.0,180.0 M 91.1,184.5 L 91.2,183.5 M 82.3,183.1 L 82.5,182.2 M 73.7,180.8 L 74.0,179.9 M 65.4,177.7 L 65.8,176.7"
                + "M 56.0,176.2 L 60.0,169.3 M 50.0,168.8 L 50.6,168.0 M 43.1,163.2 L 43.8,162.4 M 36.8,156.9 L 37.6,156.2 M 31.2,150.0 L 32.0,149.4"
                + "M 23.8,144.0 L 30.7,140.0 M 22.3,134.6 L 23.3,134.2 M 19.2,126.3 L 20.1,126.0 M 16.9,117.7 L 17.8,117.5 M 15.5,108.9 L 16.5,108.8"
                + "M 12.0,100.0 L 20.0,100.0 M 15.5,91.1 L 16.5,91.2 M 16.9,82.3 L 17.8,82.5 M 19.2,73.7 L 20.1,74.0 M 22.3,65.4 L 23.3,65.8"
                + "M 23.8,56.0 L 30.7,60.0 M 31.2,50.0 L 32.0,50.6 M 36.8,43.1 L 37.6,43.8 M 43.1,36.8 L 43.8,37.6 M 50.0,31.2 L 50.6,32.0"
                + "M 56.0,23.8 L 60.0,30.7 M 65.4,22.3 L 65.8,23.3 M 73.7,19.2 L 74.0,20.1 M 82.3,16.9 L 82.5,17.8 M 91.1,15.5 L 91.2,16.5"
        );
        svg.setStroke(Color.BLACK);
        svg.setStrokeWidth(2);
        return svg;
    }
    
    private Node createHourHand() {
        SVGPath svg = new SVGPath();
        svg.setContent("M 100,100 L 90,90 L 100,40 L 110,90 Z");
        Pane pane = new  Pane(svg);
        pane.setPrefSize(100 * 2, 100 * 2);
        return pane;
    }

    private Node createMinuteHand() {
        SVGPath svg = new SVGPath();
        svg.setContent("M 100,100 L 90,90 L 100,20 L 110,90 Z");
        Pane pane = new  Pane(svg);
        pane.setPrefSize(100 * 2, 100 * 2);
        return pane;
    }

    private Node createSecondHand() {
        SVGPath svg = new SVGPath();
        svg.setContent("M 100,110 L 100,20");
        svg.setStroke(Color.GRAY);
        Pane pane = new  Pane(svg);
        pane.setPrefSize(100 * 2, 100 * 2);
        return pane;
    }

    private Node createCenterPoint() {
        SVGPath svg = new SVGPath();
        svg.setContent("M 100,95 A 5,5 0 1 1 99 95");
        Pane pane = new  Pane(svg);
        pane.setPrefSize(100 * 2, 100 * 2);
        return pane;
    }

}
