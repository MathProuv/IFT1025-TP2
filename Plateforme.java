import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import java.util.Random;

public abstract class Plateforme {

    protected double x, y, vy, ay;
    Random rand = new Random();
    protected double hauteur = 10, largeur;
    Color color = new Color(0, 0, 1, 1.0);

    protected static int widthFenetre = 350, heightFenetre = 480;

    public Plateforme(double x, double y, double largeur) {
       this.x = x;
       this.y = y;
       this.vy = 50;
       this.ay = 2;
       this.largeur = largeur;
    }


    public void update(double dt) {
        this.vy += ay * dt - vy*dt;
        this.y += vy * dt;
    }

    public void draw(GraphicsContext context, double score) {
        double yFenetre = heightFenetre - hauteur - y + score;
        double xFenetre = x - largeur/2;
        context.clearRect(xFenetre, yFenetre, largeur, hauteur);
        context.fillRect(xFenetre, yFenetre, largeur, hauteur);
    }

}
