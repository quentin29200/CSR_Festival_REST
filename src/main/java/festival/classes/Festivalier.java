package festival.classes;

import festival.simulation.Simulation;

import java.sql.Timestamp;
import java.util.ArrayList;
/**
 * <b>Classe Festivalier</b>
 * <p>
 * Simule le comportement d'un festivalier
 *  Le festivalier est créé (A)
 *  Le festivalier a acheté son billet (B)
 *  Le festivalier est dans le bus (C)
 *  Le festivalier est arrivé (D) *
 * </p>
 *
 * @version 1.0
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

    /**
     * Constructeur du Festivalier
     * Attribue un ID, un nom, un prénom et une simulation
     * @param nomF
     * @param prenomF
     * @param s
     */
    public Festivalier(String nomF, String prenomF, Simulation s) {
        this.idF = nextID();
        this.nomF = nomF+idF;
        this.prenomF = prenomF+idF;
        this.etatF = "A";
        this.simulation = s;
    }

    /**
     * Méthode nextID
     * Génère un identifiant unique
     * @return int
     *      retourne l'id généré
     */
    private static synchronized int nextID() {
        return nbfest++;
    }

    /**
     * Méthode getEtat F
     * Retourne l'état d'un festivalier
     * @return String
     */
    public String getEtatF() {
        return etatF;
    }

    /**
     * Méthode setEtatF
     * Attribue un état à un festivalier (A, B, C ou D)
     * @param etatF
     */
    public void setEtatF(String etatF) {
        this.etatF = etatF;
    }

    /**
     * Méthode getIdF
     * Retourne l'identifiant du festivalier
     * @return int
     */
    public int getIdF() {
        return idF;
    }

    /**
     * Méthode getNomF
     * @return String
     */
    public String getNomF() { return nomF; }

    /**
     * Méthode getPrenomF
     * @return String
     */
    public String getPrenomF() { return prenomF; }

    /**
     * Méthode run
     * Lance l'execution d'un festivalier Achat Billet - Monter dans le bus
     */
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
