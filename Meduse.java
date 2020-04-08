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

    /**
     * Constructeur
     */
    public Meduse(){
        this.partieCommencee = false;
        this.direction = true;
        this.image = new Image("/images/jellyfish1.png");
    }

    public Image getImage() {
        return this.image;
    }
}
