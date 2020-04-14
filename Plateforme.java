import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.Random;

/**
 * @author Mathilde Prouvost et Augustine Poirier
 */
public class Plateforme {

    private double x, y;
    private int hauteur = 10, largeur;
    private Color color;
    private String type;
    private boolean intersect;

    protected static int widthFenetre = 350, heightFenetre = 480;

    public Plateforme(double y, String type) {

        Random rand = new Random();
        this.largeur = rand.nextInt((175 - 80) + 1) + 80;
        this.x = rand.nextInt((widthFenetre-largeur/2) - largeur/2) + largeur/2;
        this.y = y;
        this.type = type;
        if (type.equals("simple"))
           this.color = Color.rgb(230, 134, 58);
        else if (type.equals("accélérante"))
           this.color = Color.rgb(230, 221, 58);
        else if (type.equals("solide"))
           this.color = Color.rgb(184, 15, 36);
        else if (type.equals("rebondissante"))
           this.color = Color.LIGHTGREEN;
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

    public void hitPlateforme(Meduse meduse, Jeu jeu, double dt){
        if (this.type.equals("simple")) {
            meduse.setVy(0);
            meduse.setY(this.y);
        }
        if (this.type.equals("rebondissante")) {
            meduse.setVy(-meduse.getVy()*1.5);
            if (meduse.getVy() < 100) {
                meduse.setVy(100);
            }
        }
        if (this.type.equals("accélérante")) {
            meduse.setVy(0);
            meduse.setY(this.y);
            jeu.getModele().scroll(dt, jeu, jeu.getVScroll()*2);
        }
        if (this.type.equals("solide")) {
            if (meduse.getVy() < 0) {
                meduse.setVy(0);
                meduse.setY(this.y);
            } else {
                meduse.setVy(-meduse.getVy());
                meduse.setY(this.y - hauteur - meduse.getTailleMeduse());
            }
        }
    }

    public double getY() { return this.y; }

    public double getX() { return this.x; }

    public int getLargeur() { return this.largeur; }

    public int getHauteur() { return this.hauteur; }

    public String getType() { return this.type; }
}
