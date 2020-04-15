import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.Random;

/**
 * @author Mathilde Prouvost et Augustine Poirier
 */
public class Plateforme {

    private double x, y; // x est au milieu de la plateforme, y est sur le dessus
    private int hauteur = 10, largeur;
    private Color color;
    private String type;
    private boolean intersect;

    protected static int widthFenetre = 350, heightFenetre = 480;

    /**
     * Constructeur
     * @param y la position y de la plateforme
     * @param type le type ("simple", "rebondissante", "accélérante" ou "solide")
     */
    public Plateforme(double y, String type) {

        Random rand = new Random();
        // largeur aléatoire entre 80 et 175
        this.largeur = rand.nextInt((175 - 80) + 1) + 80;
        // position x aléatoire sur l'écran
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

    /**
     * Fonction draw de la plateforme
     * @param context context du canvas
     * @param score score de la partie
     * @param debug booléen qui détermine si on est en mode debug ou non
     */
    public void draw(GraphicsContext context, double score, boolean debug) {
        // on détermine la position y dans la fenêtre (entre 0 et heightFenetre) en fonction du score
        double yFenetre = heightFenetre - y + score;
        double xFenetre = x - largeur/2;
        if (debug && this.intersect)
            context.setFill(Color.YELLOW);
        else
            context.setFill(this.color);
        context.clearRect(xFenetre, yFenetre, largeur, hauteur);
        context.fillRect(xFenetre, yFenetre, largeur, hauteur);
    }

    /**
     * Fonction update des plateformes
     * @param intersect booléen qui détermine si la méduse est sur la plateforme
     */
    public void update(boolean intersect) {
        this.intersect = intersect;
    }

    /**
     * Fonction qui gère l'intersection de la plateforme selon son type et son influence sur la méduse
     * @param meduse l'instance de la méduse qui joue
     * @param jeu l'instance actuelle du jeu
     * @param dt le temps entre 2 frames
     */
    public void hitPlateforme(Meduse meduse, Jeu jeu, double dt){


        if (this.type.equals("rebondissante")) {
            // la vitesse de la méduse est multipliée par -1.5 et garde un minimum de 100 px/s
            meduse.setVy(-meduse.getVy()*1.5);
            if (meduse.getVy() < 100) {
                meduse.setVy(100);
            }
        }

        else if (this.type.equals("accélérante")) {
            // la méduse s'arrête sur la plateforme et la fenêtre scroll 3 fois plus vite
            meduse.setVy(0);
            meduse.setY(this.y);
            jeu.getModele().scroll(dt, jeu, jeu.getVScroll()*2);
        }

        else if (this.type.equals("solide")) {
            // si la méduse arrive par au dessus, elle s'arrête sur la plateforme
            if (meduse.getVy() < 0) {
                meduse.setVy(0);
                meduse.setY(this.y);

            } else {  // si la méduse arrive par en dessous, elle rebondit dessus et retombe
                meduse.setVy(-meduse.getVy());
                meduse.setY(this.y - hauteur - meduse.getTailleMeduse());
            }
        }

        else { // si on a une plateforme simple ou une plateforme de type inconnu
            // la méduse s'arrête sur la plateforme
            meduse.setVy(0);
            meduse.setY(this.y);
        }

    }

    public double getY() { return this.y; }

    public double getX() { return this.x; }

    public int getLargeur() { return this.largeur; }

    public int getHauteur() { return this.hauteur; }

    public String getType() { return this.type; }
}