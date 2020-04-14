import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

/**
 * @author Mathilde Prouvost et Augustine Poirier
 */
public class Controleur  {

    Jeu jeu;
    Modele modele;
    Bulle bulles;
    int width = 350;
    int height = 480;
    int tailleMeduse = 50;


    public Controleur(){
        this.jeu = new Jeu();
        this.modele = jeu.getModele();
        this.bulles = new Bulle(width, height);
    }

    public void update(double dt, double deltaT){
        if (jeu.isCommence())
            jeu.setIsOnFloor(false);

        jeu.update(dt, deltaT);

        // updater les bulles
        if (jeu.isCommence())
            bulles.update(dt);

        // mourir
        if (jeu.isCommence() && jeu.getMeduse().getY() + tailleMeduse < jeu.getScore())
            this.jeu = new Jeu();
    }

    public void draw(GraphicsContext context){
        if (jeu.isCommence())
            bulles.draw(context);
        jeu.draw(context);
        context.setFont(new Font(25));
        context.setFill(Color.RED);
        context.setTextAlign(TextAlignment.CENTER);
        context.fillText(((Integer)((int) getScore())).toString() + " m", width/2, 25);
    };

    public void deboguer(){
        jeu.changeDebug();
    }

    public void sauter(){
        jeu.sauter();
    }

    public void tourner(boolean direction){ jeu.tourner(direction); }

    public void creerBulles() {
        bulles = new Bulle(width, height);
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
