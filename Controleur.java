import javafx.scene.canvas.GraphicsContext;

/**
 * @author Mathilde Prouvost et Augustine Poirier
 */
public class Controleur  {

    Jeu jeu;
    Modele modele;


    public Controleur(){
       this.jeu = new Jeu();
       this.modele = new Modele();
    }

    public void update(double dt, double deltaT){
        jeu.update(dt, deltaT);
    }

    public void draw(GraphicsContext context){
        jeu.draw(context);
    };

    public void deboguer(){
        //System.out.println("debug " + jeu.isDebug());
        jeu.changeDebug();
    }

    public void sauter(){
       jeu.sauter();
    }

    public void tourner(boolean direction){
        //if (direction)
           //System.out.println("droite");
        //else
           //System.out.println("gauche");
        jeu.tourner(direction);
    }

    public void stopTourn(){
        jeu.stopTourn();
    }

    public Integer getScore() {
        return jeu.getScore();
    }
}
