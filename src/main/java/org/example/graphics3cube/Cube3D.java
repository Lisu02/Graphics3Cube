package org.example.graphics3cube;

import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;

public class Cube3D extends Application {

    public static final int WIDTH = 1400;
    public static final int HEIGHT = 800;


    @Override
    public void start(Stage primaryStage) {
        //javafx.scene.shape.Box box = new javafx.scene.shape.Box();

        Sphere sphere = new Sphere(50);

        Camera camera = new PerspectiveCamera(true);

        Group group = new Group();
        group.getChildren().add(sphere);

        camera.setTranslateX(0);
        camera.setTranslateY(0);
        camera.setTranslateZ(-500);

        camera.setNearClip(1);
        camera.setFarClip(1000);

        primaryStage.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            switch (event.getCode()){
                case UP: camera.setTranslateZ(camera.getTranslateZ() + 100); break;
                case DOWN: camera.setTranslateZ(camera.getTranslateZ() - 100); break;
                case LEFT: camera.setTranslateX(camera.getTranslateX() - 100); break;
                case RIGHT: camera.setTranslateX(camera.getTranslateX() + 100); break;

            }
        });




        Scene scene = new Scene(group,WIDTH,HEIGHT);
        scene.setCamera(camera);
        scene.setFill(Color.DARKBLUE);

        primaryStage.setTitle("Cube 3D");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

