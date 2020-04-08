public class Controleur {
    
    Vue vue;
    Modele modele;

    public Controleur(Vue vue){
        this.vue = vue;
        this.modele = new Modele();
    }
    
    //if "esc" Platform.exit
    //if "t" debug
}
