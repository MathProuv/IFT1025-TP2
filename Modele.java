public class Modele {
    private static int widthFenetre = 350, heightFenetre = 480;
    private static int tailleMeduse = 50;


    public Modele() { }

    public void sauter (Meduse meduse){
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
