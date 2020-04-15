import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

/**
 *@author Mathilde Prouvost et Augustine Poirier
 */

public class Meduse{
    private Image image;
    private boolean direction;
    private double x, y, vx, vy, ax, ay;

    private double coefFrotY = 0;
    private double coefFrotX = 5;

    private int widthF, heightF;
    private static int tailleMeduse = 50;

    private static double frameRate = 8 * 1e-9;
    private static Image[] framesDroite = new Image[]{
            new Image("/images/jellyfish1.png"),
            new Image("/images/jellyfish2.png"),
            new Image("/images/jellyfish3.png"),
            new Image("/images/jellyfish4.png"),
            new Image("/images/jellyfish5.png"),
            new Image("/images/jellyfish6.png")
    };
    private static Image[] framesGauche = new Image[]{
            new Image("/images/jellyfish1g.png"),
            new Image("/images/jellyfish2g.png"),
            new Image("/images/jellyfish3g.png"),
            new Image("/images/jellyfish4g.png"),
            new Image("/images/jellyfish5g.png"),
            new Image("/images/jellyfish6g.png")
    };

    /**
     * Constructeur
     * @param widthF largeur de la fenêtre en px
     * @param heightF hauteur de la fenêtre en px
     */
    public Meduse(int widthF, int heightF){
        this.direction = true;
        this.image = framesDroite[0];
        this.x = (double)(widthF-tailleMeduse)/2; //cast facultatif
        this.y = 0;
        this.vx = 0;
        this.vy = 0;
        this.ax = 0;
        this.ay = 0;
        this.widthF = widthF;
        this.heightF = heightF;
    }

    /**
     * Fonction update de la méduse
     * @param dt temps entre 2 frames
     * @param deltaT temps depuis le début de la partie
     */
    public void update(double dt, double deltaT){
        int frame = (int) (frameRate*deltaT);
        // true = droite
        if (direction)
            this.image = framesDroite[frame%framesDroite.length];
        else // false = gauche
            this.image = framesGauche[frame%framesGauche.length];

        this.x += vx * dt;
        this.y += vy * dt;
        this.vx += ax * dt - coefFrotX*vx*dt;
        this.vy += ay * dt - coefFrotY*vy*dt;
    }

    /**
     * Fonction draw de la méduse
     * @param context context du canvas
     * @param score score actuel (position du bas de l'écran)
     * @param debug booléen qui détermine si on est en mode debug
     */
    public void draw(GraphicsContext context, double score, boolean debug){
        // on calcule la position y dans la fenêtre selon le score
        double yFenetre = heightF - tailleMeduse - this.y + score;
        context.clearRect(x, yFenetre, tailleMeduse, tailleMeduse);
        if (debug) { // en mode debug, la méduse est dans un carré rouge
            context.setFill(Color.rgb(255, 0, 0, 0.5));
            context.fillRect(x, yFenetre, tailleMeduse, tailleMeduse);
        }
        context.drawImage(image, x, yFenetre, tailleMeduse, tailleMeduse);
    }

    /**
     * Fonction qui fait sauter la méduse
     */
    public void sauter(){
        this.vy = 600;
    }

    /**
     * Fonction qui fait rebondir la méduse sur le mur
     */
    public void rebondirMur() {
        this.vx *= -1;
        this.direction = !this.direction;

        if (this.x <= 0) {
            this.x *= -1;
        } else {
            this.x = 2*widthF - this.x - 2*tailleMeduse ;
        }
    }

    /**
     * Fonction qui fait tourner la méduse
     * @param direction direction où tourner, true = droite, false = gauche
     */
    public void tourner(boolean direction){
        if (direction != this.direction) {
            this.direction = direction;
        }
        ax = direction ? 1200 : -1200;
    }

    /**
     * Fonction pour arrêter de tourner
     */
    public void stopTourn(){
        ax = 0;
    }

    public double getX() { return x; }

    public double getY() { return y; }

    public void setY(double y) { this.y = y; }

    public double getVy() { return this.vy; }

    public double getVx() { return this.vx; }

    public double getAx() { return this.ax; }

    public double getAy() { return this.ay; }

    public void setVy(double y) { this.vy = y; }

    public void setAy(double y) { this.ay = y; }

    public int getTailleMeduse() {return this.tailleMeduse; }

}