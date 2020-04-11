import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class PlateformeAccelerante extends Plateforme {
    public PlateformeAccelerante(double x, double y, double largeur) {
        super(x, y, largeur);
        this.color = color.rgb(230, 221, 58);
    }

    public void draw(GraphicsContext context, double score, boolean debug) {
        if (debug && ) {
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
