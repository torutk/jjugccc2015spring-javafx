/*
 * Copyright (C) by TAKAHASHI,Toru
 */
package analogclock.drawing;

import java.util.List;
import static java.util.stream.Collectors.toList;
import java.util.stream.IntStream;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

/**
 * アナログ時計（Javaの2DグラフィックスAPIで）
 * @author toru
 */
public class AnalogClockDrawing extends Application {

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
        Pane pane = new Pane();
        pane.getChildren().addAll(createCircle(), createTickMarks());
        return pane;
    }
    
    // 時計の文字盤（背景）を作成する。
    private Node createCircle() {
        RadialGradient gradient = new RadialGradient(
                0, 0, 0.5, 0.5, 0.5, true, CycleMethod.NO_CYCLE,
                new Stop(0.8, Color.WHITE), new Stop(0.9, Color.BLACK),
                new Stop(0.95, Color.WHITE), new Stop(1.0, Color.BLACK)
        );
        Circle circle = new Circle(100, 100, 100, gradient);
        return circle;
    }
    
    // 時計の文字盤（分刻み）を作成する。
    private Node createTickMarks() {
        Group tickMarkGroup = new Group();
        List<Node> tickMarks = IntStream.range(0, 60)
                .mapToObj(this::createTickMark)
                .collect(toList());
        tickMarkGroup.getChildren().addAll(tickMarks);
        return tickMarkGroup;
    }
    
    // 時計の文字盤の分刻みの1つを作成する。
    private Node createTickMark(int n) {
        Line line = (n % 5 == 0) ? new Line(100, 100 * 0.12, 100, 100 * 0.2)
                                 : new Line(100, 100 * 0.15, 100, 100 * 0.16);
        line.getTransforms().add(new Rotate(360 / 60 * n, 100, 100));
        line.setStrokeWidth(2);
        return line;
    }
    
    // 時計の短針を作成する。
    private Node createHourHand() {
        Node hourHand = createHourOrMinuteHand(100 * 0.4, Color.BLACK);
        Pane pane = new Pane(hourHand);
        pane.setPrefSize(100 * 2, 100 * 2);
        return pane;
    }
    
    // 時計の長針を作成する。
    private Node createMinuteHand() {
        Node minuteHand = createHourOrMinuteHand(100 * 0.2, Color.BLACK);
        Pane pane = new Pane(minuteHand);
        pane.setPrefSize(100 * 2, 100 * 2);
        return pane;
    }

    // 時計の針（長針または短針）を作成する。
    private Node createHourOrMinuteHand(double stretchRelativeToRim, Color color) {
        Path path = new Path(
                new MoveTo(100,100),
                new LineTo(100 * 0.9, 100 * 0.9),
                new LineTo(100, stretchRelativeToRim),
                new LineTo(100 * 1.1, 100 * 0.9),
                new LineTo(100, 100)
        );
        path.setFill(color);
        path.setStroke(Color.TRANSPARENT);
        return path;
    }

    // 時計の秒針を作成する。
    private Node createSecondHand() {
        Line line = new Line(100, 100 * 1.1, 100, 100 * 0.2);
        Pane pane = new Pane(line);
        pane.setPrefSize(100 * 2, 100 * 2);
        return pane;
    }

    // 時計の中心点を作成する。
    private Node createCenterPoint() {
        return new Circle(100, 100, 100 * 0.05, Color.BLACK);
    }

}
