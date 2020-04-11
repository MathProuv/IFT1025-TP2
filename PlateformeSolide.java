import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class PlateformeSolide extends Plateforme {

    public PlateformeSolide(double x, double y, double largeur) {
        super(x, y, largeur);
        this.color = color.rgb(184, 15, 36);
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
