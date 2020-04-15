import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import java.util.ArrayList;

/**
 * @author Mathilde Prouvost et Augustine Poirier
 */

public class Jeu {
    private int widthF;
    private int heightF;
    private int espacement = 100;
    private double aScroll = 2;

    private Modele modele;
    private Meduse meduse;
    private double score; // correspond à la position y du bas de l'écran
    private double vScroll;
    private boolean commence;
    private boolean debug;
    private ArrayList<Plateforme> plateformes;
    private boolean isRed = false; // indique si la dernière plateforme est rouge pour ne pas en avoir 2 de suite
    private double positionY; // correspond à la hauteur de la prochaine plateforme
    private boolean onFloor;
    private Bulles bulles;

    /**
     * Constructeur
     */
    public Jeu(int widthF, int heightF) {
        this.widthF = widthF;
        this.heightF = heightF;
        this.meduse = new Meduse(widthF, heightF);
        this.modele = new Modele(widthF, heightF, meduse.getTailleMeduse());
        this.score = 0;
        this.vScroll = 50;
        this.commence = false;
        this.debug = false;
        this.positionY = 100;
        this.plateformes = new ArrayList<Plateforme>();
        this.isRed = true; // pour empêcher de commencer avec une plateforme solide
        this.onFloor = true;
        this.bulles = new Bulles(widthF, heightF);
    }


    /**
     * Fonction qui crée une instance de Blles
     */
    public void creerBulles() {
        bulles = new Bulles(widthF, heightF);
    }

    /**
     * Fonction qui ajoute les différents types de plateformes selon leurs probabilités d'apparition
     * 65% pour une simple, 15% pour une rebondissante, 10% pour une accélérante et 5% pour une solide
     */
    public void addPlateforme() {

        double proba = Math.random() * 100;

        if (proba < 65 || (proba >= 95 && isRed)) {
            // si la plateforme précédente est solide, on crée une plateforme simple
            this.plateformes.add(new Plateforme(positionY, "simple"));
            isRed = false;
        } else if (proba < 80) {
            this.plateformes.add(new Plateforme(positionY, "rebondissante"));
            isRed = false;
        } else if (proba < 95) {
            this.plateformes.add(new Plateforme(positionY, "accélérante" ));
            isRed = false;
        } else {
            this.plateformes.add(new Plateforme(positionY, "solide"));
            isRed = true;
        }
        positionY += espacement;
    }


    /**
     * Fonction update du jeu
     * @param dt temps entre 2 frames
     * @param deltaT temps depuis le début de la partie
     */
    public void update(double dt, double deltaT) {
        if (isCommence())
            onFloor = false;

        modele.update(dt, deltaT, this);

        // on ajoute les plateformes au fur et à mesure de la montée
        if (positionY < score + heightF) {
            this.addPlateforme();
        }

        // on retire les plateformes qui ne sont plus à l'écran
        if (plateformes.size() > heightF/espacement + 1) {
            plateformes.remove(0);
        }

        modele.scroll(dt,this, this.vScroll);

        // updater les bulles
        if (isCommence())
            this.bulles.update(dt);

        // mourir
        // si la partie est commencée et que la méduse se trouve en dessous de l'écran, on meurt
        if (isCommence() && this.meduse.getY() + this.meduse.getTailleMeduse() < score)
            mourir();
    }

    /**
     * Fonction draw du jeu
     * @param context context du canvas
     */
    public void draw(GraphicsContext context){

        if (isCommence()) {
            bulles.draw(context);
        }

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
            context.fillText("Touche le sol : " + (onFloor ? "oui" : "non"), 0, 56);
        }

        context.setFont(new Font(25));
        context.setFill(Color.RED);
        context.fillText(((Integer)((int) score)).toString() + " m", widthF/2, 25);
    }

    /**
     * Fonction qui set les paramètres à leur valeur durant une partie : commence à true et l'accélération
     * à -1200 px/s
     */
    public void commencer() {
        commence = true;
        meduse.setAy(-1200);
    }

    /**
     * Fonction pour mourir, qui réinitialise le jeu
     */
    public void mourir() {
        this.meduse = new Meduse(widthF, heightF);
        this.modele = new Modele(widthF, heightF, meduse.getTailleMeduse());
        this.score = 0;
        this.vScroll = 50;
        this.commence = false;
        this.debug = false;
        this.positionY = 100;
        this.plateformes = new ArrayList<Plateforme>();
        this.isRed = true;
        this.onFloor = true;
        this.bulles = new Bulles(widthF, heightF);
    }

    /**
     * Fonction pour tourner
     * @param direction (true pour droite, false pour gauche)
     */
    public void tourner(boolean direction) {
        modele.tourner(meduse, direction);
    }

    /**
     * Fonction pour arrêter de tourner
     */
    public void stopTourn(){
        meduse.stopTourn();
    }

    /**
     * Fonction pour sauter
     */
    public void sauter() {
        if (onFloor)
            modele.sauter(meduse);
    }

    /**
     * Fonction pour entrer ou sortir du mode debug
     */
    public void changeDebug(){
        this.debug = !this.debug;
    }

    public ArrayList<Plateforme> getPlateforme() { return this.plateformes; }

    public Modele getModele() { return modele; }

    public Meduse getMeduse() { return meduse; }

    public double getScore() { return score; }

    public boolean isCommence() { return commence; }

    public boolean isDebug() { return debug; }

    public void setIsOnFloor(boolean isOnFloor) { this.onFloor = isOnFloor; }

    public double getVScroll() { return this.vScroll; }

    public double getAScroll() { return this.aScroll; }

    public void setVScroll(double vScroll) { this.vScroll = vScroll; }

    public void setScore(double score) { this.score = score; }
}