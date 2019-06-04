package lab;


import javafx.animation.*;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Penguin extends Application {

    public static void main(String args[]) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Group root = new Group();
        Scene scene = new Scene(root, 1200, 600);

        // head
        var head = new Ellipse(254, 123, 80, 75);
        head.setFill(Color.BLUE);
        head.setStroke(Color.BLUE);
        head.setStrokeWidth(1);

        root.getChildren().add(head);

        //body

        var body = new Ellipse(252, 255, 100, 100);
        body.setStrokeWidth(0);
        body.setFill(Color.BLUE);

        root.getChildren().add(body);

        var innerBody = new Ellipse(252, 255, 70, 100);
        innerBody.setStrokeWidth(0);
        innerBody.setFill(Color.LIGHTBLUE);

        root.getChildren().add(innerBody);

        //hand 1
        var hand1 = new Ellipse(154, 213, 15, 40);
        hand1.setRotate(45);
        hand1.setStrokeWidth(0);
        hand1.setFill(Color.BLUE);

        root.getChildren().add(hand1);

        //hand 2
        var hand2 = new Ellipse(352, 214, 15, 40);
        hand2.setRotate( -45);
        hand2.setStrokeWidth(0);
        hand2.setFill(Color.BLUE);

        root.getChildren().add(hand2);

        //nose
        var nose1 = new MoveTo(254, 172);
        var nose2 = new LineTo(221, 123);
        var nose3 = new LineTo(283, 123);

        var nose = new Path(nose1, nose2, nose3);
        nose.setFill(Color.ORANGE);
        nose.setStrokeWidth(0);
        root.getChildren().add(nose);
        // eye 1
        var eye1 = new Ellipse(229, 113, 20, 30);
        eye1.setStrokeWidth(0);
        eye1.setFill(Color.WHITE);

        root.getChildren().add(eye1);

        // eye 1
        var eye2 = new Ellipse(285, 120, 30, 20);
        eye2.setStrokeWidth(0);
        eye2.setFill(Color.WHITE);

        root.getChildren().add(eye2);

        // eye dot
        var eyeDot1 = new Ellipse(243, 121, 5 , 5);
        eyeDot1.setStrokeWidth(0);
        eyeDot1.setFill(Color.BLACK);

        root.getChildren().add(eyeDot1);

        // eye dot
        var eyeDot2 = new Ellipse(260, 121, 5 , 5);
        eyeDot2.setStrokeWidth(0);
        eyeDot2.setFill(Color.BLACK);

        root.getChildren().add(eyeDot2);

        //leg1
        var leg1 = new MoveTo(237, 326);
        var leg2 = new LineTo(240, 355);
        var leg3 = new QuadCurveTo(195, 370, 154, 353);
        var leg4 = new LineTo(155, 326);
        var leg5 = new QuadCurveTo(198, 296, 237, 326);

        var legLeft = new Path(leg1, leg2, leg3, leg4, leg5);
        legLeft.setStrokeWidth(0);
        legLeft.setFill(Color.ORANGE);
        root.getChildren().add(legLeft);

        //leg2
        var leg6 = new MoveTo(345, 326);
        var leg7 = new LineTo(350, 345);
        var leg8 = new QuadCurveTo(315, 370, 264, 353);
        var leg9 = new LineTo(264, 326);
        var leg10 = new QuadCurveTo(308, 296, 347, 326);

        var legRight = new Path(leg6, leg7, leg8, leg9, leg10);
        legRight.setStrokeWidth(0);
        legRight.setFill(Color.ORANGE);
        root.getChildren().add(legRight);







        // Animation
        int cycleCount = 2;
        int time = 2000;

        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(time), root);
        scaleTransition.setToX(2);
        scaleTransition.setToY(2);
        scaleTransition.setAutoReverse(true);

        RotateTransition rotateTransition = new RotateTransition(Duration.millis(time), root);
        rotateTransition.setByAngle(360f);
        rotateTransition.setCycleCount(cycleCount);
        rotateTransition.setAutoReverse(true);

        TranslateTransition translateTransition = new TranslateTransition(Duration.millis(time), root);
        translateTransition.setFromX(150);
        translateTransition.setToX(50);
        translateTransition.setCycleCount(cycleCount + 1);
        translateTransition.setAutoReverse(true);

        TranslateTransition translateTransition2 = new TranslateTransition(Duration.millis(time), root);
        translateTransition2.setFromX(50);
        translateTransition2.setToX(150);
        translateTransition2.setCycleCount(cycleCount + 1);
        translateTransition2.setAutoReverse(true);

        ScaleTransition scaleTransition2 = new ScaleTransition(Duration.millis(time), root);
        scaleTransition2.setToX(0.1);
        scaleTransition2.setToY(0.1);
        scaleTransition2.setCycleCount(cycleCount);
        scaleTransition2.setAutoReverse(true);

        ParallelTransition parallelTransition = new ParallelTransition();
        parallelTransition.getChildren().addAll(
                rotateTransition,
                scaleTransition,
                scaleTransition2,
                translateTransition
        );
        parallelTransition.setCycleCount(Timeline.INDEFINITE);
        parallelTransition.play();
//        // End of animation

        primaryStage.setResizable(false);
        primaryStage.setTitle("Lab 3");
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
