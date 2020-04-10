import javafx.scene.canvas.GraphicsContext;
import java.util.ArrayList;
import java.util.Random;

public class Jeu {
    private int widthF = 350, heightF = 480, espacement = 100;

    private Modele modele;
    private Meduse meduse;
    private int score;
    private boolean commence;
    private boolean debug;
    private ArrayList<Plateforme> plateformes;
    private boolean isRed = true;
    private double positionY;

    public Jeu() {
        this.modele = new Modele(widthF, heightF);
        this.meduse = new Meduse();
        this.score = 0;
        this.commence = false;
        this.debug = false;
        this.plateformes = new ArrayList<Plateforme>();
        this.positionY = 0;
        
        for (int i = 0; i < heightF/espacement; i ++)
            this.addPlateforme();
    }

    public ArrayList<Plateforme> getPlateforme() {
        return this.plateformes;
    }

    public void changeDebug(){
        this.debug = !this.debug;
    }
    
    public void addPlateforme() {
        double proba = Math.random() * 100;
        Random rand = new Random();
        int largeur = rand.nextInt((175 - 80) + 1) + 80;
        int positionX = rand.nextInt((widthF-largeur/2) - largeur/2) + largeur/2;

        if (proba < 65 || (proba >= 95 && isRed)) { // si la plateforme précédente est solide, on crée une plateforme simple
            this.plateformes.add(new PlateformeSimple(positionX, positionY, largeur));
            isRed = false;
        } else if (proba < 80) {
            this.plateformes.add(new PlateformeRebondissante(positionX, positionY, largeur));
            isRed = false;
        } else if (proba < 95) {
            this.plateformes.add(new PlateformeAccelerante(positionX, positionY, largeur));
            isRed = false;
        } else {
            this.plateformes.add(new PlateformeSolide(positionX, positionY, largeur));
            isRed = true;
        }
        positionY += espacement;
    }

    public void update(double dt, double deltaT) {
        modele.update(dt, deltaT, meduse, score);
        if (positionY - score < heightF - 100) {
            this.addPlateforme();
        }
        score += 1;
    }

    public void draw(GraphicsContext context){
        modele.draw(context, meduse, score, plateformes);
    }

    public void stopTourn(){
        meduse.stopTourn();
    }

    public void tourner(boolean direction) {
        modele.tourner(meduse, direction);
    }

    public void sauter() {
        modele.sauter(meduse);
    }

    public Modele getModele() {
        return modele;
    }

    public Meduse getMeduse() {
        return meduse;
    }

    public Integer getScore() {
        return score;
    }

    public boolean isCommence() {
        return commence;
    }

    public boolean isDebug() {
        return debug;
    }
}
