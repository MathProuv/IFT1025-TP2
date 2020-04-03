/**
 *@author Augustine Poirier et Mathilde Prouvost
 */
public class Meduse{ //extend Modele

	private boolean partieCommencee;
	/**
	 * indique true=vers la droite, flase=vers la gauche
	 */
	private boolean direction;
	/**
	 * entier entre 1 et 6 inclus qui indique quelle image est en cours
	 */
	private int image;

	/** 
	 * Constructeur
	 */
	public Meduse(){
		this.partieCommencee = false;
		this.direction = true;
		this.image = 1;
	}
}
