public class Controleur {
    Vue vue;
    Modele modele;

    public Controleur(Vue vue){
        this.vue = vue;
        this.modele = new Modele();
    }

    public void deboguer(){
        System.out.println("debug");
    }

    public void sauter(){
        System.out.println("Saute !");
    }
    public void bouger(boolean direction){
        if (direction)
            System.out.println("droite");
        else
            System.out.println("gauche");
    }
}
