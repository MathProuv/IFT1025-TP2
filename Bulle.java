import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import java.util.ArrayList;
import java.util.Random;

/**
 * @author Mathilde Prouvost et Augustine Poirier
 */
public class Bulle {
    private ArrayList<Double> y;
    private ArrayList<Integer> rayon, vy, x;
    private static Color color = Color.rgb(0, 0, 255, 0.4);
    private static int nbBaseX = 3, nbBulles = 5, heightFenetre;

    public Bulle(int widthFenetre, int heightFenetre) {
        Random rand = new Random();
        rayon = new ArrayList<>();
        vy = new ArrayList<>();
        x = new ArrayList<>();
        y = new ArrayList<>();
        Bulle.heightFenetre = heightFenetre;

        // basex
        for (int i = 0; i<nbBaseX; i++) {
            int basexi = rand.nextInt(widthFenetre+1);
            for (int j = 0; j < nbBulles; j++) {
                // positions en x
                int signe = rand.nextInt(3);
                this.x.add(signe == 0 ? basexi - 20 : signe == 1 ? basexi + 20 : basexi);
                // rayons
                int rayon = rand.nextInt(31) + 10;
                this.rayon.add(rayon);
                // positions en y
                this.y.add(-40.0);
                // vitesse
                int vy = rand.nextInt(101) + 350;
                this.vy.add(vy);
            }
        }
    }

    public void draw(GraphicsContext context) {
        context.setFill(color);
        for (int i=0; i<nbBaseX*nbBulles; i++) {
            int x = this.x.get(i);
            double y = this.y.get(i);
            int rayon = this.rayon.get(i);
            context.fillOval(x, heightFenetre - y, rayon, rayon);
        }
    }

    public void update(double dt) {
        for (int i=0; i < nbBaseX*nbBulles; i++) {
            int vy = this.vy.get(i);
            double y = this.y.get(i);
            y += vy*dt;
            this.y.set(i, y);
        }
    }
}
