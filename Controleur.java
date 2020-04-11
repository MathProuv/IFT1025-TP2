import javafx.geometry.Pos;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * @author Mathilde Prouvost et Augustine Poirier
 */
public class Controleur  {

    Jeu jeu;
    Modele modele;
    int width = 350;
    int tailleMeduse = 50;


    public Controleur(){
        this.jeu = new Jeu();
        this.modele = jeu.getModele();
    }

    public void update(double dt, double deltaT){
        if (jeu.isCommence())
            jeu.setIsOnFloor(false);

        jeu.update(dt, deltaT);

        // mourir
        if (jeu.isCommence() && jeu.getMeduse().getY() + tailleMeduse < jeu.getScore()) {
            this.jeu = new Jeu();
        }
    }

    public void draw(GraphicsContext context){
        jeu.draw(context);
        context.setFont(new Font(25));
        context.setFill(Color.RED);
        context.fillText(((Integer)((int) getScore())).toString() + " m", width/2, 25);
    };

    public void deboguer(){
        System.out.println("debug " + jeu.isDebug());
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

    public double getScore() {
        return jeu.getScore();
    }

    public void commencer() {
        jeu.commencer();
    }
}
