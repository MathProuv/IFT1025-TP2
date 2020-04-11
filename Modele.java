import javafx.scene.canvas.GraphicsContext;
import java.sql.SQLOutput;
import java.util.ArrayList;

public class Modele {
    private static int widthFenetre = 350, heightFenetre = 480;
    private static int tailleMeduse = 50;


    public Modele(int width, int height) {
        this.widthFenetre = width;
        this.heightFenetre = height;
    }

    public void update(double dt, double deltaT, Jeu jeu) { //meduse, score, plateformes
        Meduse meduse = jeu.getMeduse();
        double score = jeu.getScore();
        ArrayList<Plateforme> plateformes = jeu.getPlateforme();

        meduse.update(dt, deltaT);
        if (isInWall(meduse)) {
            hitWall(meduse);
        }

        for (int i=0; i<plateformes.size(); i++) {
            if (this.intersectsPlateforme(meduse, plateformes.get(i), dt)) {
                jeu.setIsOnFloor(true);
                plateformes.get(i).update(true);
                this.hitPlateforme(meduse, plateformes.get(i));
            } else {
                plateformes.get(i).update(false);
            }
        }
    }

    public void scroll(double dt, Jeu jeu) {
        boolean debug = jeu.isDebug();
        double vScroll = jeu.getVScroll();
        double aScroll = jeu.getAScroll();
        double score = jeu.getScore();
        Meduse meduse = jeu.getMeduse();


        if (!debug && jeu.isCommence()) {
            jeu.setVScroll(vScroll + aScroll * dt);
            score += (int) (vScroll * dt);
            jeu.setScore(score);
        }
        // si la méduse dépasse 75% de la hauteur, l'écran remonte
        if (meduse.getY() > score + 0.75*heightFenetre - tailleMeduse) {
            score = meduse.getY() - 0.75*heightFenetre + tailleMeduse ;
            jeu.setScore(score);
        }
    }

    public void draw(GraphicsContext context, Jeu jeu) {
        Meduse meduse = jeu.getMeduse();
        double score = jeu.getScore();
        ArrayList<Plateforme> plateformes = jeu.getPlateforme();
        boolean debug = jeu.isDebug();

        meduse.draw(context, score, debug);
        for (Plateforme plat : plateformes) {
            plat.draw(context, score, debug);
        }
    }

    public void sauter (Meduse meduse) {
        meduse.sauter();
    }

    public void tourner(Meduse meduse, boolean direction) {
        meduse.tourner(direction);
    }

    public boolean isInWall(Meduse meduse) {
        return ((meduse.getX() <= 0) || (meduse.getX() >= widthFenetre - tailleMeduse));
    }

    public void hitWall(Meduse meduse) {
        meduse.rebondirMur();
    }


    public boolean intersectsPlateforme(Meduse meduse, Plateforme plateforme, double dt) {

        // si la méduse et la plateforme ont des coordonnées X compatibles
        if ((meduse.getX() < plateforme.getX() + plateforme.getLargeur()/2)
            && (meduse.getX() + tailleMeduse > plateforme.getX() - plateforme.getLargeur()/2)) {

            // si la méduse est au dessus de la plateforme et va la traverser pendant l'instant dt
            if ((meduse.getY() >= plateforme.getY()) && (meduse.getY() + meduse.getVy()*dt < plateforme.getY())) {
                return true;
            }

            // si la plateforme est solide
            if (plateforme.getType().equals("solide")) {
                // si la méduse la traverse par en dessous
                if ((meduse.getY() + tailleMeduse <= plateforme.getY() - plateforme.getHauteur())
                    && (meduse.getY() + tailleMeduse + meduse.getVy()*dt > plateforme.getY() - plateforme.getHauteur()))
                    return true;
            }
        }
        return false;
    }

    public void hitPlateforme(Meduse meduse, Plateforme plateforme) {
        plateforme.hitPlateforme(meduse);
    }
}
