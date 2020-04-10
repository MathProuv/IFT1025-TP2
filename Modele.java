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

    public void update(double dt, double deltaT, Meduse meduse, Integer score) {
        meduse.update(dt, deltaT);
        if (isInWall(meduse)) {
            hitWall(meduse);
        }
    }

    public void draw(GraphicsContext context, Meduse meduse, Integer score, ArrayList<Plateforme> plateformes) {
        meduse.draw(context, score);
        for (Plateforme plat : plateformes) {
            plat.draw(context, score);
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


    public boolean intersectsPlateforme(Meduse meduse, Plateforme plateforme) {
        boolean intersectOver = (meduse.getY() - plateforme.getY() < 5) //méduse et plateforme sont à la même hauteur
                && (meduse.getVy() <= 0) //méduse tombe ou ne bouge pas
                && (plateforme.getX() - plateforme.getLargeur()/2 <= meduse.getX())
                && (meduse.getX() >= plateforme.getX() + plateforme.getLargeur()/2); //méduse qqpart sur la plateforme

        if (!(plateforme instanceof PlateformeSolide))
            return intersectOver;

        boolean intersectUnder = (meduse.getY() - plateforme.getY()+plateforme.getHauteur() < 5) //méduse et plateforme sont à la même hauteur
                && (meduse.getVy() > 0)
                && (plateforme.getX() - plateforme.getLargeur()/2 <= meduse.getX())
                && (meduse.getX() >= plateforme.getX() + plateforme.getLargeur()/2); //méduse qqpart sur la plateforme

        return intersectUnder || intersectOver;
    }

    public void hitPlateforme(Meduse meduse, Plateforme plateforme) {
        if (plateforme instanceof PlateformeSimple)
            ((PlateformeSimple) plateforme).hitPlateforme(meduse);
        if (plateforme instanceof PlateformeRebondissante)
            ((PlateformeRebondissante) plateforme).hitPlateforme(meduse);
        if (plateforme instanceof PlateformeSolide)
            ((PlateformeSolide) plateforme).hitPlateforme(meduse);
        if (plateforme instanceof PlateformeAccelerante)
            ((PlateformeAccelerante) plateforme).hitPlateforme(meduse);
    }
}
