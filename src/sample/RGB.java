package sample;

public class RGB {
    private int red;
    private int green;
    private int blue;

    public RGB() {
    }

    public RGB(int red, int green, int blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }
    public CMYK toCMYK() {
        double K = 1 - Math.max(Math.max(red / 255., green / 255.), blue / 255.);
        double C = (1 - red / 255. - K) / (1 - K);
        double M = (1 - green / 255. - K) / (1 - K);
        double Y = (1 - blue / 255. - K) / (1 - K);
        return new CMYK((int) Math.round(C*100.), (int) Math.round(M*100.), (int) Math.round(Y*100.), (int) Math.round(K*100.));
    }

    public int getRed() {
        return red;
    }

    public void setRed(int red) {
        this.red = red;
    }

    public int getGreen() {
        return green;
    }

    public void setGreen(int green) {
        this.green = green;
    }

    public int getBlue() {
        return blue;
    }

    public void setBlue(int blue) {
        this.blue = blue;
    }
}
