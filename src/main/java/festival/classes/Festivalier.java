package festival.classes;

import festival.simulation.Simulation;

import java.util.ArrayList;

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

    // VARIABLE - Monde dans lequel est le festivaler
    private Simulation simulation;

    // Constructeur
    public Festivalier(int idF, String nomF, String prenomF, Simulation s) {
        this.idF = idF;
        this.nomF = nomF;
        this.prenomF = prenomF;
        this.etatF = "A";
        this.simulation = s;
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
        if (!b.estComplet()) {
            b.ajoutFestivalier(this);
            return true;
        }

        return false;
    }

    public void run() {
        // Si il reste des places, le festivalier achete un billet
        if (this.acheterBillet(this.simulation.getBilleterie())) {
            System.out.println("Festivalier "+this.idF+" a acheté son billet !");
            ArrayList<Bus> busEnAttente =  this.simulation.getDepart().getBuses();
            // Seul les festivaliers aillant un billet peuvent monter
            if (this.etatF == "B") {

                /** TODO WAIT SI PAS DE BUS, ET BUS DOIT NOTIFY FESTIVALIER QUAND IL ARRIVE */

                // Tant que le festivalier n'est pas monter dans le bus
                while (this.etatF != "C") {
                    // on parcours tous les bus présent
                    for (Bus bus : busEnAttente) {
                        // Si le bus n'est pas complet, le festivalier monte dedans
                        if (this.monterDansLeBus(bus)) {
                            // Le festivalier est dedans, l'état passe à C, on sort de la boucle
                            break;
                        }
                    }
                }
            }
        }

    }
}
