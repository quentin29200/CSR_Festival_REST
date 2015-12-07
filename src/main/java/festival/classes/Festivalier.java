package festival.classes;

import festival.simulation.Simulation;

import java.sql.Timestamp;
import java.util.ArrayList;

/**
 * Created by 13006798 on 25/11/15.
 */
public class Festivalier extends Thread {
    // VARIABLE - Identifiant du festivalier
    private static int nbfest = 0;

    private int idF;

    // VARIABLE - Nom et prenom du festivalier
    private String nomF;
    private String prenomF;

    // VARIABLE - Etat du festivalier
    private String etatF;

    // VARIABLE - Monde dans lequel est le festivaler
    private Simulation simulation;

    // Constructeur
    public Festivalier(String nomF, String prenomF, Simulation s) {
        this.idF = nextID();
        this.nomF = nomF;
        this.prenomF = prenomF;
        this.etatF = "A";
        this.simulation = s;
    }

    private static synchronized int nextID()
    {
        return ++nbfest;
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

    public void run() {
        // Si il reste des places, le festivalier achete un billet
        if (this.simulation.getBilleterie().acheterBillet(this)) {
            System.out.println("Festivalier "+this.idF+" a acheté son billet !");
            this.simulation.getDepart().monterDansBus(this);
        } else {
            System.out.println("Accès refusé, plus de billet");
        }

    }
}
