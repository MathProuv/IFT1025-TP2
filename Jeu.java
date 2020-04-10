import javafx.scene.canvas.GraphicsContext;

public class Jeu {

    private Modele modele;
    private Meduse meduse;
    private int score;
    private boolean commence; //false avant le debut de la partie et en mode debug
    private boolean debug;

    public Jeu() {
        this.modele = new Modele();
        this.meduse = new Meduse();
        this.score = 0;
        this.commence = false;
        this.debug = false;
    }

    public void changeDebug(){
        this.debug = !this.debug;
    }

    public void update(double dt, double deltaT) {
        meduse.update(dt, deltaT);

        if (modele.isInWall(meduse)) {
            modele.hitWall(meduse);
        }
    }

    public void draw(GraphicsContext context){
        meduse.draw(context, score);
    };

    public void deboguer(){
        System.out.println("debug " + debug);
        this.changeDebug();
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
