import javafx.scene.canvas.GraphicsContext;

/**
 * @author Mathilde Prouvost et Augustine Poirier
 */
public interface Controleur {

    public Integer getScore();

    public void update(double dt, double deltaT);
    public void draw(GraphicsContext context);

    public void deboguer();

    public void sauter();
    public void stopSaut();

    public void tourner(boolean direction);
    public void stopTourn();
}
