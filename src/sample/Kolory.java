package sample;

import javafx.beans.value.ChangeListener;
import javafx.fxml.Initializable;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;



import javafx.scene.shape.Rectangle;


import java.net.URL;
import java.text.NumberFormat;
import java.util.ResourceBundle;

public class Kolory implements Initializable {
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Red.setMax(255);
        Red.setMin(0);
        Blue.setMax(255);
        Blue.setMin(0);
        Green.setMax(255);
        Green.setMin(0);
        Red.setMin(0);
        Red.setValue(0);
        RedText.setText(Integer.toString(0));
        RedText.textProperty().bindBidirectional(Red.valueProperty(), NumberFormat.getIntegerInstance());
        Green.setValue(0);
        GreenText.setText(Integer.toString(0));
        GreenText.textProperty().bindBidirectional(Green.valueProperty(), NumberFormat.getIntegerInstance());
        Blue.setValue(0);
        BlueText.setText(Integer.toString(0));
        BlueText.textProperty().bindBidirectional(Blue.valueProperty(), NumberFormat.getIntegerInstance());

        Cyan.setValue(0);
        CyanText.setText(Integer.toString(0));
        CyanText.textProperty().bindBidirectional(Cyan.valueProperty(), NumberFormat.getIntegerInstance());
        Magenta.setValue(0);
        MagentaText.setText(Integer.toString(0));
        MagentaText.textProperty().bindBidirectional(Magenta.valueProperty(), NumberFormat.getIntegerInstance());
        Yellow.setValue(0);
        YellowText.setText(Integer.toString(0));
        YellowText.textProperty().bindBidirectional(Yellow.valueProperty(), NumberFormat.getIntegerInstance());
        KBlack.setValue(0);
        KBlackText.setText(Integer.toString(0));
        KBlackText.textProperty().bindBidirectional(KBlack.valueProperty(), NumberFormat.getIntegerInstance());

        Red.valueProperty().addListener(changeListenerRGB);
        Green.valueProperty().addListener(changeListenerRGB);
        Blue.valueProperty().addListener(changeListenerRGB);

        Cyan.valueProperty().addListener(changeListenerCMYK);
        Magenta.valueProperty().addListener(changeListenerCMYK);
        Yellow.valueProperty().addListener(changeListenerCMYK);
        KBlack.valueProperty().addListener(changeListenerCMYK);
    }

    public Slider Red;
    public Slider Green;
    public Slider Blue;
    public Slider Cyan;
    public Slider Magenta;
    public Slider Yellow;
    public Slider KBlack;

    public TextField RedText;
    public TextField GreenText;
    public TextField BlueText;
    public TextField CyanText;
    public TextField MagentaText;
    public TextField YellowText;
    public TextField KBlackText;

    public Rectangle rectangle;
    boolean colorChanging = false;

    ChangeListener changeListenerRGB = (observable, oldValue, newValue) -> {
        if (colorChanging) {
            return;
        }

        colorChanging = true;

        int R = Integer.parseInt(RedText.getText());
        int G = Integer.parseInt(GreenText.getText());
        int B = Integer.parseInt(BlueText.getText());
        RGB rgb = new RGB(R, G, B);
        CMYK cmyk = rgb.toCMYK();

        rectangle.setFill(Color.rgb(R, G, B));
        CyanText.setText(String.valueOf(cmyk.getCyan()));
        MagentaText.setText(String.valueOf(cmyk.getMagenta()));
        YellowText.setText(String.valueOf(cmyk.getYellow()));
        KBlackText.setText(String.valueOf(cmyk.getBlack()));
        colorChanging = false;


    };
    ChangeListener changeListenerCMYK = (observable, oldValue, newValue) -> {
        Red.valueProperty().addListener(changeListenerRGB);
        if (colorChanging) {
            return;
        }

        colorChanging = true;
        int C = Integer.parseInt(CyanText.getText());
        int M = Integer.parseInt(MagentaText.getText());
        int Y = Integer.parseInt(YellowText.getText());
        int K = Integer.parseInt(KBlackText.getText());
        CMYK cmyk = new CMYK(C, M, Y, K);
        RGB rgb = cmyk.toRGB();
        rectangle.setFill(Color.rgb(rgb.getRed(), rgb.getGreen(), rgb.getBlue()));
        RedText.setText(String.valueOf(rgb.getRed()));
        GreenText.setText(String.valueOf(rgb.getGreen()));
        BlueText.setText(String.valueOf(rgb.getBlue()));
        colorChanging = false;
    };

}
