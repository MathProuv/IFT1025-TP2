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

    private static int widthFenetre = 350, heightFenetre = 480;
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
     */
    public Meduse(){
        this.direction = true;
        this.image = framesDroite[0];
        this.x = (double)(widthFenetre-tailleMeduse)/2; //cast facultatif
        this.y = 0;
        this.vx = 0;
        this.vy = 0;
        this.ax = 0;
        this.ay = 0;
    }

    public void update(double dt, double deltaT){
        int frame = (int) (frameRate*deltaT);
        if (direction)
            this.image = framesDroite[frame%framesDroite.length];
        else
            this.image = framesGauche[frame%framesGauche.length];

        this.x += vx * dt;
        this.y += vy * dt;
        this.vx += ax * dt - coefFrotX*vx*dt;
        this.vy += ay * dt - coefFrotY*vy*dt;
    }

    public void draw(GraphicsContext context, double score, boolean debug){
        double yFenetre = heightFenetre - tailleMeduse - this.y + score;
        context.clearRect(x, yFenetre, tailleMeduse, tailleMeduse);
        if (debug) {
            context.setFill(Color.rgb(255, 0, 0, 0.5));
            context.fillRect(x, yFenetre, tailleMeduse, tailleMeduse);
        }
        context.drawImage(image, x, yFenetre, tailleMeduse, tailleMeduse);
    }

    public void sauter(){
        this.vy = 600;
    }

    public void rebondirMur() {
        this.vx *= -1;
        this.direction = !this.direction;

        if (this.x <= 0) {
            this.x *= -1;
        } else {
            this.x = 2*widthFenetre - this.x - 2*tailleMeduse ;
        }
    }

    public void tourner(boolean direction){
        if (direction != this.direction) {
            this.direction = direction;
        }
        ax = direction ? 1200 : -1200;
    }
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

}
