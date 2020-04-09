import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 *@author Mathilde Prouvost et Augustine Poirier
 */
public class Meduse{
    private int widthFenetre = 350, heightFenetre = 480;
    private int tailleMeduse = 50;

    private double frameRate = 8 * 1e-9;
    private boolean direction; //je sais pas si on en a besoin cf signe de vx
    private Image image;
    private double x, y, vx, vy, ax, ay;

    private static Image[] frames = new Image[]{
            new Image("/images/jellyfish1.png"),
            new Image("/images/jellyfish2.png"),
            new Image("/images/jellyfish3.png"),
            new Image("/images/jellyfish4.png"),
            new Image("/images/jellyfish5.png"),
            new Image("/images/jellyfish6.png")
    };

    /**
     * Constructeur
     */
    public Meduse(){
        this.direction = true;
        this.image = new Image("/images/jellyfish1.png");
        this.x = (double)(widthFenetre-tailleMeduse)/2; //cast facultatif
        this.y = 0;
        this.vx = 0;
        this.vy = 0;
        this.ax = 0;
        this.ay = 0;
    }

    public void update(double dt, double deltaT){
        int frame = (int) (frameRate*deltaT);
        this.image = frames[frame%frames.length];
        this.vx += ax * dt;
        this.vy += ay * dt;
        this.x += vx * dt;
        this.y += vy * dt;
    }

    public void draw(GraphicsContext context, double score){
        double yFenetre = heightFenetre - tailleMeduse - this.y;
        context.clearRect(x, yFenetre, tailleMeduse, tailleMeduse);
        context.drawImage(image, x, yFenetre, tailleMeduse, tailleMeduse);
    }
}
