import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import java.util.ArrayList;
import java.util.Random;

public class Jeu {
    private int widthF = 350, heightF = 480, espacement = 100, tailleMeduse = 50;
    private double aScroll = 2;

    private Modele modele;
    private Meduse meduse;
    private double score;
    private double vScroll;
    private boolean commence;
    private boolean debug;
    private ArrayList<Plateforme> plateformes;
    boolean isRed = false;
    private double positionY;
    private boolean isOnFloor;

    public Jeu() {
        this.modele = new Modele(widthF, heightF);
        this.meduse = new Meduse();
        this.score = 0;
        this.vScroll = 50;
        this.commence = false;
        this.debug = false;
        this.positionY = 100;
        this.plateformes = new ArrayList<Plateforme>();
        this.isOnFloor = true;
    }

    public void commencer() {
        commence = true;
        meduse.setAy(-1200);
    }

    public ArrayList<Plateforme> getPlateforme() {
        return this.plateformes;
    }

    public void addPlateforme() {
        double proba = Math.random() * 100;
        Random rand = new Random();
        int largeur = rand.nextInt((175 - 80) + 1) + 80;
        int positionX = rand.nextInt((widthF-largeur/2) - largeur/2) + largeur/2;

        if (proba < 65 || (proba >= 95 && isRed)) {
            // si la plateforme précédente est solide, on crée une plateforme simple
            this.plateformes.add(new Plateforme(positionX, positionY, largeur, "simple"));
            isRed = false;
        } else if (proba < 80) {
            this.plateformes.add(new Plateforme(positionX, positionY, largeur, "rebondissante"));
            isRed = false;
        } else if (proba < 95) {
            this.plateformes.add(new Plateforme(positionX, positionY, largeur, "accélérante" ));
            isRed = false;
        } else {
            this.plateformes.add(new Plateforme(positionX, positionY, largeur, "solide"));
            isRed = true;
        }

        positionY += espacement;
    }

    public void changeDebug(){
        this.debug = !this.debug;
    }

    public void update(double dt, double deltaT) {
        modele.update(dt, deltaT, this);

        // on ajoute les plateformes au fur et à mesure de la montée
        if (positionY < score + heightF) {
            this.addPlateforme();
        }

        // on retire les plateformes qui ne sont plus à l'écran
        if (plateformes.size() > Math.floor(heightF/espacement) + 1) {
            plateformes.remove(0);
        }

        modele.scroll(dt, this);
    }

    public void draw(GraphicsContext context){
        modele.draw(context, this);

        if (debug) {
            context.setFill(Color.WHITE);
            context.setFont(Font.font(14));
            // position (x, y)
            context.fillText("Position : (" + (int) meduse.getX() + ", " + (int) meduse.getY() + ")", 0, 14);
            // v (x, y)
            context.fillText("v = (" + (int) meduse.getVx() + ", " + (int) meduse.getVy() + ")", 0, 28);
            // acceleration (x, y)
            context.fillText("a = (" + (int) meduse.getAx() + ", " + (int) meduse.getAy() + ")", 0, 42);
            // touche le sol : oui/non
            context.fillText("Touche le sol : " + (isOnFloor ? "oui" : "non"), 0, 56);
        }
    }

    public void stopTourn(){
        meduse.stopTourn();
    }

    public void tourner(boolean direction) {
        modele.tourner(meduse, direction);
    }

    public void sauter() {
        if (isOnFloor)
            modele.sauter(meduse);
    }

    public Modele getModele() {
        return modele;
    }

    public Meduse getMeduse() {
        return meduse;
    }

    public double getScore() {
        return score;
    }

    public boolean isCommence() {
        return commence;
    }

    public boolean isDebug() {
        return debug;
    }

    public void setIsOnFloor(boolean isOnFloor) { this.isOnFloor = isOnFloor; }

    public boolean getIsOnFloor() { return this.isOnFloor; }

    public double getVScroll() { return this.vScroll; }

    public double getAScroll() { return this.aScroll; }

    public void setVScroll(double vScroll) { this.vScroll = vScroll; }

    public void setScore(double score) { this.score = score; }
}
