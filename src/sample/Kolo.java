package sample;

import javafx.fxml.Initializable;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;

import java.net.URL;
import java.util.ResourceBundle;

public class Kolo implements Initializable {

    public TextField x1;
    public TextField y1;
    public TextField r;
    public Button circleButton;
    public Button circleMouse;
    public Button clear;
    public AnchorPane pane;
    Circle circle = new Circle();

    Canvas canvas = new Canvas(900, 480);

    double orgX1,  orgY1;
    double orgSceneX, orgSceneY;
    double orgTranslateX, orgTranslateY, originalRadius;
    int mode = 0;
    Point2D pointC1;
    Point2D pointC2;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


    public void rysujKolo() {
        pane.getChildren().remove(circle);
        circle.setCenterX(Double.parseDouble(x1.getText()));
        circle.setCenterY(Double.parseDouble(y1.getText()));
        circle.setRadius(Double.parseDouble(r.getText()));
        pane.getChildren().addAll(circle);
    }

    public void circleByButton() {
        mode = 2;
    }

    public void event() {



/////////////////////////////////////////////////////////////////////// kolo ////////////////
        //zmiana kształtu i położenia
        circle.setOnMousePressed(event -> {
            if (event.getButton() == MouseButton.SECONDARY) {
                orgSceneX = event.getSceneX();
                orgTranslateX = ((Circle) (event.getSource())).getRadius();
            } else if (event.getButton() == MouseButton.PRIMARY) {
                orgSceneX = event.getSceneX();
                orgSceneY = event.getSceneY();
                orgTranslateX = ((Circle) (event.getSource())).getTranslateX();
                orgTranslateY = ((Circle) (event.getSource())).getTranslateY();
                orgX1 = ((Circle) event.getSource()).getCenterX();
                orgY1 = ((Circle) event.getSource()).getCenterY();
            }
        });
        circle.setOnMouseDragged(event -> {
            if (event.getButton() == MouseButton.SECONDARY) {
                double offsetX = event.getSceneX() - orgSceneX;
                double newTranslateX = orgTranslateX + offsetX;

                ((Circle) (event.getSource())).setRadius(newTranslateX);
            } else if (event.getButton() == MouseButton.PRIMARY) {
                double offsetX = event.getSceneX() - orgSceneX;
                double offsetY = event.getSceneY() - orgSceneY;
                double newTranslateX = orgTranslateX + offsetX;
                double newTranslateY = orgTranslateY + offsetY;

                ((Circle) (event.getSource())).setCenterX(orgX1 + offsetX);
                ((Circle) (event.getSource())).setCenterY(orgY1 + offsetY);
                ((Circle) (event.getSource())).setTranslateX(newTranslateX);
                ((Circle) (event.getSource())).setTranslateY(newTranslateY);
            }
        });


        if (mode == 2) {
            pane.setOnMouseClicked(event -> {
              //  pane.getChildren().remove(circle);
                if (pointC1 == null) {
                    pointC1 = new Point2D(event.getX(), event.getY());
                } else if (pointC2 == null) {
                    pointC2 = new Point2D(event.getX(), event.getY());
                }
                if (pointC1 != null && pointC2 != null) {
                    circle.setCenterX(pointC1.getX());
                    circle.setCenterY(pointC1.getY());
                    double offsetX = event.getSceneX() - orgSceneX;
                    double newTranslateX = orgTranslateX + offsetX;
                    //  double r = Math.sqrt(Math.pow(pointC2.getX() - pointC1.getX(), 2) + Math.pow(pointC2.getY() - pointC1.getY(), 2));
                    circle.setRadius(newTranslateX);
                    if (!pane.getChildren().contains(circle)) {
                        pane.getChildren().addAll(circle);
                    }
                }
            });
        }
        pane.setOnMouseReleased(event -> {
            mode = 0;
        });
        clear.setOnMouseClicked(event -> {
            pane.getChildren().remove(circle);
            x1.setText(Integer.toString(0));
            y1.setText(Integer.toString(0));
            r.setText(Integer.toString(0));
        });

    }
}
