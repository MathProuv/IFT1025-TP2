import javafx.scene.canvas.GraphicsContext;

/**
 * @author Mathilde Prouvost et Augustine Poirier
 */
public class Controleur  {
    private Modele modele = new Modele();
    private Meduse meduse;
    private int score;
    private boolean commence; //false avant le debut de la partie et en mode debug
    private boolean debug;

    public void changeDebug(){
        this.debug = !this.debug;
    }

    public Integer getScore() {
        return score;
    }

    public Controleur(){
        this.meduse = new Meduse();
        this.score = 0;
        this.commence = false;
        this.debug = false;
    }

    public void update(double dt, double deltaT){
        meduse.update(dt, deltaT);
    }

    public void draw(GraphicsContext context){
        meduse.draw(context, score);
    };

    public void deboguer(){
        System.out.println("debug " + debug);
        this.changeDebug();
    }

    public void sauter(){
        modele.faireSauter(meduse);
    }

    public void tourner(boolean direction){
        if (direction)
            System.out.println("droite");
        else
            System.out.println("gauche");
        meduse.tourner(direction);
    }

    public void stopTourn(){
        meduse.stopTourn();
    }
}
