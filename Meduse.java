import javafx.scene.image.Image;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 *@author Augustine Poirier et Mathilde Prouvost
 */
public class Meduse{ //extend Modele

    private boolean partieCommencee;
    /**
     * true=vers la droite, flase=vers la gauche
     */
    private boolean direction;
    private Image image;

    private double vx, vy, ax, ay;

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
        this.partieCommencee = false;
        this.direction = true;
        this.image = new Image("/images/jellyfish1.png");
    }

    public void draw(GraphicsContext context){
        //context.drawImage(image, 60, 430, 50, 50);
    }
}
