package festival.classes;

/**
 * Created by 13006798 on 25/11/15.
 */
public class Billeterie {

    // CONSTANTE - Nombre de tickets max
    private final static int nbBilletMax = 100;

    // VARIABLE - Nombre de billets dispo
    private int nbBilletDispo;

    // Constructeur
    public Billeterie() {
        this.nbBilletDispo = nbBilletMax;
    }

    // Getters et setters
    public int getNbBilletDispo() { return nbBilletDispo; }

    // Acheter un billet
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
