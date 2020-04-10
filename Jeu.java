import javafx.scene.canvas.GraphicsContext;

public class Jeu {
    private int widthF = 350, heightF = 480;

    private Modele modele;
    private Meduse meduse;
    private int score;
    private boolean commence;
    private boolean debug;

    public Jeu() {
        this.modele = new Modele(widthF, heightF);
        this.meduse = new Meduse();
        this.score = 0;
        this.commence = false;
        this.debug = false;
    }

    public void changeDebug(){
        this.debug = !this.debug;
    }

    public void update(double dt, double deltaT) {
        modele.update(dt, deltaT, meduse, score);
        score += 1;
    }

    public void draw(GraphicsContext context){
        modele.draw(context, meduse, score);
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
