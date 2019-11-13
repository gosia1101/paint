package sample;

public class CMYK {
    private int cyan;
    private int magenta;
    private int yellow;
    private int black;

    public int getMagenta() {
        return magenta;
    }

    public void setMagenta(int magenta) {
        this.magenta = magenta;
    }

    public int getYellow() {
        return yellow;
    }

    public void setYellow(int yellow) {
        this.yellow = yellow;
    }

    public int getBlack() {
        return black;
    }

    public void setBlack(int black) {
        this.black = black;
    }

    public int getCyan() {
        return cyan;
    }

    public void setCyan(int cyan) {
        this.cyan = cyan;
    }

    public CMYK() {
    }

    public CMYK(int cyan, int magenta, int yellow, int black) {
        this.cyan = cyan;
        this.magenta = magenta;
        this.yellow = yellow;
        this.black = black;
    }
    public RGB toRGB() {
        double R = 255 * (1 - cyan/100.) * (1 - black/100.);
        double G = 255 * (1 - magenta/100.) * (1 - black/100.);
        double B = 255 * (1 - yellow/100.) * (1 - black/100.);
        return new RGB((int) Math.round(R), (int) Math.round(G), (int) Math.round(B));
    }
}
