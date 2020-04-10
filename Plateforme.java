import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import java.util.Random;

public abstract class Plateforme {

    protected double x, y;
    Random rand = new Random();
    protected double hauteur = 10, largeur;
    Color color = new Color(0, 0, 1, 1.0);

    protected static int widthFenetre = 350, heightFenetre = 480;

    public Plateforme(double x, double y, double largeur) {
       this.x = x;
       this.y = y;
       this.largeur = largeur;
    }



    public void draw(GraphicsContext context, double score) {
        double yFenetre = heightFenetre - hauteur - y + score;
        double xFenetre = x - largeur/2;
        context.clearRect(xFenetre, yFenetre, largeur, hauteur);
        context.fillRect(xFenetre, yFenetre, largeur, hauteur);
    }

    public double getY() { return this.y; }

    public double getX() { return this.x; }

    public double getLargeur() { return this.largeur; }

    public double getHauteur() { return this.hauteur; }

    public void hitPlateforme(Meduse meduse){
        if (this instanceof PlateformeSimple)
            meduse.setVy(0);
        if (this instanceof PlateformeRebondissante);
            //todo
        if (this instanceof PlateformeSolide);
            //todo
        if (this instanceof PlateformeAccelerante);
           //todo
    }
}
