import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import java.util.Random;

public class Plateforme {

    private double x, y;
    Random rand = new Random();
    private double hauteur = 10, largeur;
    private Color color;
    private String type;
    private boolean intersect;

    protected static int widthFenetre = 350, heightFenetre = 480;

    public Plateforme(double x, double y, double largeur, String type) {
       this.x = x;
       this.y = y;
       this.largeur = largeur;
       this.type = type;
       if (type.equals("simple"))
           this.color = Color.rgb(230, 134, 58);
       else if (type.equals("accélérante"))
           this.color = Color.LIGHTGREEN;
       else if (type.equals("solide"))
           this.color = Color.rgb(184, 15, 36);
       else if (type.equals("rebondissante"))
           this.color = Color.rgb(230, 221, 58);
       this.intersect = false;
    }



    public void draw(GraphicsContext context, double score, boolean debug) {
        double yFenetre = heightFenetre - y + score;
        double xFenetre = x - largeur/2;
        if (debug && this.intersect)
            context.setFill(Color.YELLOW);
        else
            context.setFill(this.color);
        context.clearRect(xFenetre, yFenetre, largeur, hauteur);
        context.fillRect(xFenetre, yFenetre, largeur, hauteur);
    }

    public void update(boolean intersect) {
        this.intersect = intersect;
    }

    public double getY() { return this.y; }

    public double getX() { return this.x; }

    public double getLargeur() { return this.largeur; }

    public double getHauteur() { return this.hauteur; }

    public String getType() { return this.type; }

    public void hitPlateforme(Meduse meduse){
        if (this.type.equals("simple")) {
            meduse.setVy(0);
            meduse.setY(this.y);
        }
        if (this.type.equals("rebondissante")) {
            meduse.setVy(0);
            meduse.setY(this.y);
            //todo
        }
        if (this.type.equals("accélérante")) {
            meduse.setVy(0);
            meduse.setY(this.y);
            //todo
        }
        if (this.type.equals("solide")) {
            if (meduse.getVy() < 0) {
                meduse.setVy(0);
                meduse.setY(this.y);
            } else {
                // todo : bug ici
                meduse.setVy(-meduse.getVy());
                meduse.setY(this.y - hauteur);
            }
        }
    }
}
