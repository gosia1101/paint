package sample;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.ResourceBundle;

public class Kwadrat implements Initializable {

    public TextField x1;
    public TextField height;
    public TextField y1;
    public TextField width;
    public Button kwadratButon;
    public Button kwadratMouse;
    public AnchorPane pane;
    public Button clear;



    double orgX1, orgX2, orgY1, orgY2;
    double orgSceneX, orgSceneY;
    double orgTranslateX, orgTranslateY;
    int mode = 0;
    Rectangle rectangle=new Rectangle();
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void rysujKwadrat(){
        pane.getChildren().remove(rectangle);
        rectangle.setX(Double.parseDouble(x1.getText()));
        rectangle.setY(Double.parseDouble(y1.getText()));
        rectangle.setHeight(Double.parseDouble(height.getText()));
        rectangle.setWidth(Double.parseDouble(width.getText()));
        pane.getChildren().addAll(rectangle);
    }


    public void event(){
        rectangle.setOnMousePressed(event -> {
            if (event.getButton() == MouseButton.SECONDARY) {
                orgSceneX = event.getSceneX();
                orgSceneY = event.getSceneY();
                orgTranslateX = ((Rectangle) event.getSource()).getWidth();
                orgTranslateY = ((Rectangle) (event.getSource())).getHeight();
            } else if (event.getButton() == MouseButton.PRIMARY) {
                orgSceneX = event.getSceneX();
                orgSceneY = event.getSceneY();
                orgTranslateX = ((Rectangle) event.getSource()).getTranslateX();
                orgTranslateY = ((Rectangle) (event.getSource())).getTranslateY();
                orgX1 = ((Rectangle) event.getSource()).getX();
                orgY1 = ((Rectangle) event.getSource()).getY();
            }
        });
        rectangle.setOnMouseDragged(event -> {
            if (event.getButton() == MouseButton.SECONDARY) {
                double offsetX = event.getSceneX() - orgSceneX;
                double offsetY = event.getSceneY() - orgSceneY;
                double newTranslateX = orgTranslateX + offsetX;
                double newTranslateY = orgTranslateY + offsetY;

                ((Rectangle) (event.getSource())).setWidth(newTranslateX);
                ((Rectangle) (event.getSource())).setHeight(newTranslateY);
            } else if (event.getButton() == MouseButton.PRIMARY) {
                double offsetX = event.getSceneX() - orgSceneX;
                double offsetY = event.getSceneY() - orgSceneY;
                double newTranslateX = orgTranslateX + offsetX;
                double newTranslateY = orgTranslateY + offsetY;

                ((Rectangle) (event.getSource())).setX(orgX1+offsetX);
                ((Rectangle) (event.getSource())).setY(orgY1+offsetY);
                ((Rectangle) (event.getSource())).setTranslateX(newTranslateX);
                ((Rectangle) (event.getSource())).setTranslateY(newTranslateY);
            }
        });

        if (mode == 3) {
            pane.setOnMousePressed(event -> {
                orgSceneX = event.getSceneX();
                orgSceneY = event.getSceneY();
                setRectangleStart(orgSceneX, orgSceneY, rectangle);
                if (!pane.getChildren().contains(rectangle)) {
                    pane.getChildren().addAll(rectangle);
                }
            });
            pane.setOnMouseDragged(event -> {
                setRectangleEnd(event.getSceneX(), event.getSceneY(), rectangle);
            });
        }
        pane.setOnMouseReleased(event -> {
            mode = 0;
        });
        clear.setOnMouseClicked(event -> {
            pane.getChildren().remove(rectangle);
            x1.setText(Integer.toString(0));
            y1.setText(Integer.toString(0));
            height.setText(Integer.toString(0));
            width.setText(Integer.toString(0));
        });
    }
    public void kwadratByMouse() {
        mode = 3;
    }

    void setRectangleStart(double x, double y, Rectangle rectangle) {
        rectangle.setX(x);
        rectangle.setY(y);
        x1.setText(Double.toString(x));
        y1.setText(Double.toString(y));
    }

    void setRectangleEnd(double x, double y, Rectangle rectangle) {
        if(mode==3) {
            if (x < rectangle.getX()) {
                rectangle.setX(x);
                x1.setText(Double.toString(x));

            }
            if (y < rectangle.getY()) {
                rectangle.setY(y);
                y1.setText(Double.toString(y));
            }
        }
        rectangle.setHeight(y - rectangle.getY());
        rectangle.setWidth(x - rectangle.getX());
        height.setText(Double.toString(y - rectangle.getY()));
        width.setText(Double.toString(x - rectangle.getX()));
    }
}
