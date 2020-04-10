import javafx.scene.canvas.GraphicsContext;

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

    public void draw(GraphicsContext context, Meduse meduse, Integer score) {
        meduse.draw(context, score);
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
        meduse.rebondir();
    }
}
