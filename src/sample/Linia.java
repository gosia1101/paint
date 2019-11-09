package sample;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

import java.net.URL;
import java.util.ResourceBundle;

public class Linia implements Initializable {
    public TextField x1;
    public TextField x2;
    public TextField y1;
    public TextField y2;
    public Button liniaButon;
    public Button lineMouse;
    public AnchorPane pane;
    Line line = new Line();

   // Canvas canvas = new Canvas(900, 480);

    double orgX1, orgX2, orgY1, orgY2;
    double orgSceneX, orgSceneY;
    double orgTranslateX, orgTranslateY;
    int mode = 0;



    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void rysujLinie() {
        line.setStartX(Double.parseDouble(x1.getText()));
        line.setStartY(Double.parseDouble(y1.getText()));
        line.setEndX(Double.parseDouble(x2.getText()));
        line.setEndY(Double.parseDouble(y2.getText()));
        line.setStrokeWidth(10);
        pane.getChildren().addAll(line);
    }

    public void event() {
        //zmiany wymiarów i położenia lini
        line.setOnMousePressed(event -> {
            if (event.getButton() == MouseButton.SECONDARY) {
                orgSceneX = event.getSceneX();
                orgSceneY = event.getSceneY();
                orgTranslateX = ((Line) event.getSource()).getEndX();
                orgTranslateY = ((Line) (event.getSource())).getEndY();
            } else if (event.getButton() == MouseButton.PRIMARY) {
                orgSceneX = event.getSceneX();
                orgSceneY = event.getSceneY();
                orgTranslateX = ((Line) event.getSource()).getTranslateX();
                orgTranslateY = ((Line) (event.getSource())).getTranslateY();
                orgX1 = ((Line) event.getSource()).getStartX();
                orgY1 = ((Line) event.getSource()).getStartY();
                orgX2 = ((Line) event.getSource()).getEndX();
                orgY2 = ((Line) event.getSource()).getEndY();
            }
        });
        line.setOnMouseDragged(event -> {
            if (event.getButton() == MouseButton.SECONDARY) {
                double offsetX = event.getSceneX() - orgSceneX;
                double offsetY = event.getSceneY() - orgSceneY;
                double newTranslateX = orgTranslateX + offsetX;
                double newTranslateY = orgTranslateY + offsetY;

                ((Line) (event.getSource())).setEndX(newTranslateX);
                ((Line) (event.getSource())).setEndY(newTranslateY);
            } else if (event.getButton() == MouseButton.PRIMARY) {
                double offsetX = event.getSceneX() - orgSceneX;
                double offsetY = event.getSceneY() - orgSceneY;
                double newTranslateX = orgTranslateX + offsetX;
                double newTranslateY = orgTranslateY + offsetY;

                ((Line) (event.getSource())).setStartX(orgX1 + offsetX);
                ((Line) (event.getSource())).setStartY(orgY1 + offsetY);
                ((Line) (event.getSource())).setEndX(orgX2 + offsetX);
                ((Line) (event.getSource())).setEndY(orgY2 + offsetY);
                ((Line) (event.getSource())).setTranslateX(newTranslateX);
                ((Line) (event.getSource())).setTranslateY(newTranslateY);
            }
        });
        //rysowanie lini mysza
        if (mode == 1) {
            pane.setOnMousePressed(event -> {
                orgSceneX = event.getSceneX();
                orgSceneY = event.getSceneY();
                line.setStrokeWidth(10);
                setLineStart(orgSceneX, orgSceneY, line);
                setLineEnd(orgSceneX, orgSceneY, line);
                if (!pane.getChildren().contains(line)) {
                    pane.getChildren().addAll(line);
                }

            });
            pane.setOnMouseDragged(event -> {
                setLineEnd(event.getSceneX(), event.getSceneY(), line);
            });
        }


        pane.setOnMouseReleased(event -> {
            mode = 0;
        });
    }

    public void lineByMouse() {
        mode = 1;
    }

    void setLineStart(double tempX1, double tempY1, Line line) {
        line.setStartX(tempX1);
        line.setStartY(tempY1);
        x1.setText(Double.toString(tempX1));
        y1.setText(Double.toString(tempY1));
    }

    void setLineEnd(double tempX2, double tempY2, Line line) {
        line.setEndX(tempX2);
        line.setEndY(tempY2);
        x2.setText(Double.toString(tempX2));
        y2.setText(Double.toString(tempY2));
    }
}