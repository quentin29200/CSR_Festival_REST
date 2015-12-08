package festival.classes;
/**
 * <b>Classe Billeterie</b>
 * <p>
 * La billeterie dispose d'un nombre de billet max, d'un nombre de billet dispo, d'une fonction pour
 * attribuer un billet à un festivalier
 *
 * </p>
 *
 * @version 1.0
 */
public class Billeterie {

    // CONSTANTE - Nombre de tickets max
    private final static int nbBilletMax = 5000;

    // VARIABLE - Nombre de billets dispo
    private int nbBilletDispo;

    /**
     * Constructeur de la billeterie
     *
     *  Billeterie : Nombre de billet dispo = Nombre de billet maximum
     */
    public Billeterie() {
        this.nbBilletDispo = nbBilletMax;
    }

    /**
     * Getter NbBilletDispo
     * @return int
     *      retourne le nombre de billet dispo
     */
    public int getNbBilletDispo() { return nbBilletDispo; }

    /**
     * Méthode AcheterBillet
     * Si l'état du festivalier est A et si il reste assez de billet dispo, alors
     * on décrémente le nombre de billet dispo
     * Met l'état du festivalier à B
     * @param f
     *
     */
    public boolean acheterBillet(Festivalier f) {
        synchronized (this) {
            // Si il reste des billets et si le festivalier n'a pas encore acheté de billet
            if (this.nbBilletDispo > 0 && f.getEtatF().equals("A")) {

                // Decrementer le nombre de billets dispo
                System.out.println("Nombre de billets avant achat : " + nbBilletDispo);
                this.nbBilletDispo--;

                // Attribution d'un billet a un festivalier = Changer etat festivalier
                f.setEtatF("B");

                // Le festivalier a bien acheté son billet
                return true;
            }
            return false;
        }
    }

}
