import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class PlateformeSimple extends Plateforme {
    public PlateformeSimple(double x, double y, double largeur) {
        super(x, y, largeur);
        this.color = color.rgb(230, 134, 58);
    }

    public void draw(GraphicsContext context, double score, boolean debug) {
        if (debug) {
            context.setFill(Color.YELLOW);
        } else {
            context.setFill(color);
        }
        super.draw(context, score, debug);
    }

}
