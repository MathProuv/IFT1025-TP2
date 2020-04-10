import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 *@author Mathilde Prouvost et Augustine Poirier
 */
public class Meduse{
    private Image image;
    private boolean direction;
    private double x, y, vx, vy, ax, ay;
    private double coefFrot = 1;

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
        this.ay = -1200;
    }

    public void update(double dt, double deltaT){
        int frame = (int) (frameRate*deltaT);
        if (direction)
            this.image = framesDroite[frame%framesDroite.length];
        else
            this.image = framesGauche[frame%framesGauche.length];

        this.vx += ax * dt - coefFrot*vx*dt;
        this.vy += ay * dt - coefFrot*vy*dt;
        this.x += vx * dt;
        this.y += vy * dt;
        if (this.y<0)
            this.y = 0; //partie pas encore commencée
    }

    public void draw(GraphicsContext context, double score){
        double yFenetre = heightFenetre - tailleMeduse - this.y;
        context.clearRect(x, yFenetre, tailleMeduse, tailleMeduse);
        context.drawImage(image, x, yFenetre, tailleMeduse, tailleMeduse);
    }

    public void sauter(){
        this.vy = 600;
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
}
