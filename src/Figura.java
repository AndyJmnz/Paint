import java.awt.*;

public class Figura {
    int tipo;
    int x1, y1, x2, y2;
    Color color;

    public Figura(int tipo, int x1, int y1, int x2, int y2, Color color) {
        this.tipo = tipo;
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.color = color;
    }
}
