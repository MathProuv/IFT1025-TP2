import javafx.scene.canvas.GraphicsContext;

/**
 * @author Mathilde Prouvost et Augustine Poirier
 */
public class Controleur {
    Modele modele;
    Jeu jeu;

    public Controleur(){
        this.modele = new Modele();
        this.jeu = new Jeu();
    }

    public void update(double dt, double deltaT){
        jeu.update(dt, deltaT);
    }
    public void draw(GraphicsContext context){
        jeu.draw(context);
    }

    public void deboguer(){
        System.out.println("debug " + jeu.isScroll());
        jeu.changeScroll();
    }

    public void sauter(){
        System.out.println("Saute !");
    }

    public void bouger(boolean direction){
        if (direction)
            System.out.println("droite");
        else
            System.out.println("gauche");
    }
}
