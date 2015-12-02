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
    public int getNbBilletDispo() {
        return nbBilletDispo;
    }
    public int retirerUnBilletDispo() {
        return nbBilletDispo--;
    }
}
