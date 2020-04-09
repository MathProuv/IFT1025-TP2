import javafx.scene.canvas.GraphicsContext;

/**
 * @author Mathilde Prouvost et Augustine Poirier
 */
public class Jeu implements Controleur {
    private Meduse meduse;
    private int score;
    private boolean scroll; //false avant le debut de la partie et en mode debug

    public void changeScroll(){
        this.scroll = !this.scroll;
    }

    public Integer getScore() {
        return score;
    }

    public Jeu(){
        this.meduse = new Meduse();
        this.score = 0;
        this.scroll = false;
    }

    public void update(double dt, double deltaT){
        meduse.update(dt, deltaT);
    }

    public void draw(GraphicsContext context){
        meduse.draw(context, score);
    };


    public void deboguer(){
        System.out.println("debug " + this.scroll);
        this.changeScroll();
    }

    public void sauter(){
        System.out.println("Saute !");
    }
    public void stopSaut(){}

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
