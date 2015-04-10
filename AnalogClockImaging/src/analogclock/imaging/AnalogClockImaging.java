/*
 * Copyright (C) by TAKAHASHI,Toru
 */
package analogclock.imaging;

import java.time.LocalTime;
import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * アナログ時計（画像を重ねて）
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
        
        LocalTime now = LocalTime.now();
        RotateTransition secondHandTransition = createRotateTransition(Duration.seconds(60d), secondHand, getAngleOfSecond(now));
        secondHandTransition.play();
        RotateTransition minuteHandTransition = createRotateTransition(Duration.minutes(60d), minuteHand, getAngleOfMinute(now));
        minuteHandTransition.play();
        RotateTransition hourHandTransition = createRotateTransition(Duration.hours(12d), hourHand, getAngleOfHour(now));
        hourHandTransition.play();
        
        root.getChildren().addAll(
                clockDial, hourHand, minuteHand, secondHand, centerPoint
        );
        
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    /**
     * 360度回転を繰り返すアニメーションを作成する。
     * @param duration １回転に要する時間
     * @param node 回転させるノード
     * @param startAngle 回転開始角度（度）
     * @return 指定したパラメータで初期化したRotateTransitionインスタンス
     */
    private RotateTransition createRotateTransition(
            Duration duration, Node node, double startAngle
    ) {
        RotateTransition rt = new RotateTransition(duration, node);
        rt.setFromAngle(startAngle);
        rt.setByAngle(360d);
        rt.setCycleCount(Animation.INDEFINITE);
        rt.setInterpolator(Interpolator.LINEAR);
        return rt;
    }

    /**
     * 指定した時刻における秒針の角度（0秒を0度とし時計回りを正）を返却する。
     * @param time 時刻
     * @return 指定した時刻における秒針の角度
     */
    private double getAngleOfSecond(LocalTime time) {
        return time.getSecond() * 360 / 60;
    }

    /**
     * 指定した時刻における長針（分針）の角度（0分を0秒とし時計回りを正）を返却する。
     * @param time 時刻
     * @return 指定した時刻における長針の角度
     */
    private double getAngleOfMinute(LocalTime time) {
        return (time.getMinute() + time.getSecond() / 60d) * 360 / 60;
    }

    /**
     * 指定した時刻における短針（時針）の角度（0時/12時を0度とし時計回りを正）を返却する。
     * @param time 時刻
     * @return 指定した時刻における短針の角度
     */
    private double getAngleOfHour(LocalTime time) {
        return (time.getHour() % 12 + time.getMinute() / 60d + time.getSecond() / (60d * 60d)) * 360 / 12;
    }
}
