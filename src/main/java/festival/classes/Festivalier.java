package festival.classes;

/**
 * Created by 13006798 on 25/11/15.
 */
public class Festivalier extends Thread {

    // VARIABLE - Identifiant du festivalier
    private int idF = 0;

    // VARIABLE - Nom et prenom du festivalier
    private String nomF;
    private String prenomF;

    // VARIABLE - Etat du festivalier
    private String etatF;

    // Constructeur
    public Festivalier(int idF, String nomF, String prenomF) {
        this.idF = idF;
        this.nomF = nomF;
        this.prenomF = prenomF;
        this.etatF = "A";
    }

    // Getters et setters
    public String getEtatF() {
        return etatF;
    }

    public void setEtatF(String etatF) {
        this.etatF = etatF;
    }

    public int getIdF() {
        return idF;
    }

    // Acheter un billet
    public boolean acheterBillet(Billeterie b) {
        // Un seul achat de billet en meme temps
        synchronized (this) {
            // Si il reste des billets et si le festivalier n'a pas encore acheté de billet
            if (b.getNbBilletDispo() > 0 && this.getEtatF().equals("A"))
            {
                // Decrementer le nombre de billets dispo
                b.retirerUnBilletDispo();

                // Attribution d'un billet a un festivalier = Changer etat festivalier
                this.setEtatF("B");

                // Le festivalier a bien acheté son billet
                return true;
            }
            return false;
        }
    }

    // Monter dans le bus
    public boolean monterDansLeBus(Bus b)
    {
        // Un seul festivalier monte a la fois dans le bus b
        // Pour verifier le nombre de place
        return false;
    }
}
