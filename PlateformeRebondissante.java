import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class PlateformeRebondissante extends Plateforme {
    public PlateformeRebondissante(double x, double y, double largeur) {
        super(x, y, largeur);
        this.color = color.LIGHTGREEN;
    }

    public void draw(GraphicsContext context, double score, boolean debug) {
        if (debug) {
            context.setFill(Color.YELLOW);
        } else {
            context.setFill(color);
        }
        super.draw(context, score, debug);
    }

    public void hitPlateforme(Meduse meduse) {
        //todo
    }
}
