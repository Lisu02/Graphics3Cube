package org.example.graphics3cube;

import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.*;
import javafx.scene.control.ColorPicker;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Transform;
import javafx.stage.Stage;

public class Chap2 extends Application {

    public static final int WIDTH = 1400;
    public static final int HEIGHT = 800;

    private double anchorX, anchorY;
    private double anchorAngleX = 0;
    private double anchorAngleY = 0;
    private final DoubleProperty angleX = new SimpleDoubleProperty(0);
    private final DoubleProperty angleY = new SimpleDoubleProperty(0);


    @Override
    public void start(Stage primaryStage) {
        //javafx.scene.shape.Box box = new javafx.scene.shape.Box();

        Box box = prepareBox();


//        camera.setTranslateX(WIDTH/2);
//        camera.setTranslateY(HEIGHT/2);

        SmartGroup group = new SmartGroup();
        group.getChildren().add(box);

        Camera camera = new PerspectiveCamera();
        Scene scene = new Scene(group,WIDTH,HEIGHT);
        scene.setCamera(camera);
        scene.setFill(Color.DARKBLUE);

        group.setTranslateX(WIDTH/2);
        group.setTranslateY(HEIGHT/2);
        group   .setTranslateZ(-1200);


        initMouseControll(group,scene);

//        Transform transform = new Rotate(65,Rotate.X_AXIS);
//        box.getTransforms().add(transform);

        ColorPicker colorPicker = new ColorPicker(Color.LIGHTGREEN);
        colorPicker.setOnAction(actionEvent -> {
            Color selectedColor = colorPicker.getValue();
            PhongMaterial phongMaterial = new PhongMaterial();
            phongMaterial.setDiffuseColor(selectedColor);
            box.setMaterial(phongMaterial);
        });
        //VBox vBox = new VBox(colorPicker);
        //vBox.setSpacing(100);
        colorPicker.setTranslateX(50);

        group.getChildren().add(colorPicker);


        primaryStage.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            switch (event.getCode()){
                case UP: group.setTranslateZ(group.getTranslateZ() + 100); break;
                case DOWN: group.setTranslateZ(group.getTranslateZ() - 100); break;
                case W:
                    group.rotateByX(10);
                    break;
                case S:
                    group.rotateByX(-10);
                    break;
                case D:
                    group.rotateByY(10);
                    break;
                case A:
                    group.rotateByY(-10);
                    break;
            }
        });






        primaryStage.setTitle("Cube 3D");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void initMouseControll(SmartGroup group, Scene scene) {
        Rotate xRotate;
        Rotate yRotate;
        group.getTransforms().addAll(
                xRotate = new Rotate(0,Rotate.X_AXIS),
                yRotate = new Rotate(0,Rotate.Y_AXIS)
        );
        xRotate.angleProperty().bind(angleX);
        yRotate.angleProperty().bind(angleY);

        scene.setOnMousePressed(event -> {
            anchorX = event.getSceneX();
            anchorY = event.getSceneY();
            anchorAngleX = angleX.get();
            anchorAngleY = angleY.get();

        });

        scene.setOnMouseDragged(event -> {
            angleX.set(anchorAngleX - (anchorY - event.getSceneY()));
            angleY.set(anchorAngleY - (anchorX - event.getSceneX()));
        });
    }

    private Box prepareBox(){

        PhongMaterial phongMaterial = new PhongMaterial(new Color(0.5,0.4,1,1));

        Box box = new Box(50,50,50);
        box.setMaterial(phongMaterial);
        return box;
    }

    public static void main(String[] args) {
        launch(args);
    }

    class SmartGroup extends Group {
        Rotate r;
        Transform t = new Rotate();

        public void rotateByX(int ang){
            r = new Rotate(ang,Rotate.X_AXIS);
            t = t.createConcatenation(r);
            this.getTransforms().clear();
            this.getTransforms().addAll(t);
        }
        public void rotateByY(int ang){
            r = new Rotate(ang,Rotate.Y_AXIS);
            t = t.createConcatenation(r);
            this.getTransforms().clear();
            this.getTransforms().addAll(t);
        }
        public void rotateByZ(int ang){
            r = new Rotate(ang,Rotate.Z_AXIS);
            t = t.createConcatenation(r);
            this.getTransforms().clear();
            this.getTransforms().addAll(t);
        }

    }
}


