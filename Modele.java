import javafx.scene.canvas.GraphicsContext;
import java.util.ArrayList;

/**
 * @author Mathilde Prouvost et Augustine Poirier
 */

public class Modele {
    private int tailleMeduse, widthFenetre, heightFenetre;


    /**
     * Constructeur
     * @param width largeur de la fenêtre en px
     * @param height hauteur de la fenêtre en px
     * @param tailleMeduse taille de la méduse en px
     */
    public Modele(int width, int height, int tailleMeduse) {
        this.widthFenetre = width;
        this.heightFenetre = height;
        this.tailleMeduse = tailleMeduse;
    }

    /**
     * Fonction update du modèle
     * @param dt temps entre 2 frames
     * @param deltaT temps depuis le début de la partie
     * @param jeu instance actuelle du jeu
     */
    public void update(double dt, double deltaT, Jeu jeu) {
        Meduse meduse = jeu.getMeduse();
        double score = jeu.getScore();
        ArrayList<Plateforme> plateformes = jeu.getPlateforme();

        meduse.update(dt, deltaT);

        // si la méduse touche le mur, elle rebondit
        if (isInWall(meduse)) {
            hitWall(meduse);
        }

        for (int i=0; i<plateformes.size(); i++) {
            // si la méduse touche une plateforme, la méthode hitPlateforme est appelée
            if (this.intersectsPlateforme(meduse, plateformes.get(i), dt)) {
                jeu.setIsOnFloor(true);
                plateformes.get(i).update(true);
                this.hitPlateforme(plateformes.get(i), jeu, dt);
            } else {
                // sinon, la plateforme s'update normalement
                plateformes.get(i).update(false);
            }
        }

        // update vScroll
        double aScroll = jeu.getAScroll();
        double vScroll = jeu.getVScroll();

        // si le jeu est en mode debug, il n'y a pas de scroll
        if (!jeu.isDebug() && jeu.isCommence()) {
            jeu.setVScroll(vScroll + aScroll * dt);
        }
    }


    /**
     * Fonction draw du modele
     * @param context context du canvas
     * @param jeu instance actuelle du jeu
     */
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

    /**
     * Fonction qui fait scroller l'écran
     * @param dt temps entre 2 frames
     * @param jeu instance actuelle du jeu
     * @param vScroll vitesse verticale de scroll
     */
    public void scroll(double dt, Jeu jeu, double vScroll) {
        double score = jeu.getScore(); // position du bas de la fenêtre
        Meduse meduse = jeu.getMeduse();

        // si le jeu est commencé et n'est pas en mode debug, le score augmente
        if (!jeu.isDebug() && jeu.isCommence()) {
            score += (int) (vScroll * dt);
            jeu.setScore(score);
        }

        // si la méduse dépasse 75% de la hauteur, l'écran remonte
        if (meduse.getY() > score + 0.75*heightFenetre - tailleMeduse) {
            score = meduse.getY() - 0.75*heightFenetre + tailleMeduse ;
            jeu.setScore(score);
        }
    }

    /**
     * Fonction qui fait sauter la méduse
     * @param meduse instance actuelle de la méduse
     */
    public void sauter (Meduse meduse) {
        meduse.sauter();
    }

    /**
     * Fonction qui fait tourner la méduse
     * @param meduse instance actuelle de la méduse
     * @param direction boolen qui détermine la direction où tourner : true pour droite, false pour gauche
     */
    public void tourner(Meduse meduse, boolean direction) {
        meduse.tourner(direction);
    }

    /**
     * Fonction qui détermine si la méduse touche le mur
     * @param meduse instance actuelle de la méduse
     */
    public boolean isInWall(Meduse meduse) {
        return ((meduse.getX() <= 0) || (meduse.getX() >= widthFenetre - tailleMeduse));
    }

    /**
     * Fonction qui fait rebondir la méduse
     * @param meduse instance actuelle de la méduse
     */
    public void hitWall(Meduse meduse) {
        meduse.rebondirMur();
    }


    /**
     * Fonction qui détermine si la méduse touche à la plateforme
     * @param meduse instance actuelle de la méduse
     * @param plateforme instance de la plateforme testée
     * @param dt temps entre 2 frames
     * @return true si la méduse touche, false sinon
     */
    public boolean intersectsPlateforme(Meduse meduse, Plateforme plateforme, double dt) {

        // si la méduse et la plateforme ont des coordonnées X compatibles
        if ((meduse.getX() < plateforme.getX() + plateforme.getLargeur()/2)
            && (meduse.getX() + tailleMeduse > plateforme.getX() - plateforme.getLargeur()/2)) {

            // si la méduse est au dessus de la plateforme et va la traverser pendant l'instant dt
            if ((meduse.getY() >= plateforme.getY()) && (meduse.getY() + meduse.getVy()*dt <= plateforme.getY())) {
                return true;
            }

            // si la plateforme est solide
            if (plateforme.getType().equals("solide")) {
                // si la méduse la traverse par en dessous
                if ((meduse.getY() + tailleMeduse <= plateforme.getY() - plateforme.getHauteur())
                    && (meduse.getY() + tailleMeduse + meduse.getVy()*dt >= plateforme.getY() - plateforme.getHauteur()))
                    return true;
            }
        }
        return false;
    }

    /**
     * Fonction qui fait réagir le jeu lorsque la méduse touche la plateforme
     * @param plateforme instance de plateforme touchée
     * @param jeu instance actuelle du jeu
     * @param dt temps entre 2 frames
     */
    public void hitPlateforme(Plateforme plateforme, Jeu jeu, double dt) {
        plateforme.hitPlateforme(jeu.getMeduse(), jeu, dt);
    }
}