package festival.classes;

import java.util.ArrayList;
import java.util.List;

/**
 * <b>Classe Bus - THREAD</b>
 * <p>
 * Simule le comportement d'un bus
 *  Le bus arrive sur un site
 *  le bus attend
 *  le bus repart du site
 * </p>
 *
 * @version 1.0
 */
public class Bus extends Thread {

    // VARIABLE - Identifiant du bus
    private static int nbbus = 0;
    private int idB = 0;

    // VARIABLE - Capacité du bus
    private int capa;

    // VARIABLE - Collection de Festivalier
    private ArrayList<Festivalier> festivaliers;

    // VARIABLE - Site courant du bus
    private Site current_site;
    private Site depart;
    private Site arrivee;

    /**
     *  Constructeur du Bus
     *  Attribue un ID, une capacité et une liste de festivalier à bord
     *  @param capa
     *      capacité du bus
     */
    public Bus(int capa) {
        this.idB = nextID();
        this.capa = capa;
        this.festivaliers = new ArrayList<>();
    }

    /**
     * Méthode nextID
     * Génère un identifiant unique
     * @return int
     *      retourne l'id généré
     */
    private static synchronized int nextID()
    {
        return nbbus++;
    }

    /**
     * Getter IdB
     *
     * @return int
     *      retourne l'identifiant du bus
     */
    public int getIdB() {
        return idB;
    }

    /**
     * Methode estComplet
     * Indique si le bus est complet ou non
     * @return boolean
     *      retourne vrai si complet, faux sinon
     */
    public boolean estComplet() {
        return festivaliers.size() >= this.capa;
    }

    /**
     * Methode ajoutFestivalier
     * Ajoute un festivalier dans la collection de festivalier du bus
     * @param f
     */
    public void ajoutFestivalier(Festivalier f) {
        System.out.println("Festivalier " + f.getIdF() + " monte dans le bus " + this.idB);
        this.festivaliers.add(f);
        f.setEtatF("C");
    }

    /**
     * Methode initialiserTrajet
     * Renseigne le trajet du bus en lui indiquant le site de départ et l'aire de concert d'arrivée
     * @param depart
     * @param arrivee
     */
    public void initialiserTrajet(SiteDepart depart, AireDeConcert arrivee) {
        this.depart = depart;
        this.arrivee = arrivee;
    }

    /**
     * Methode arriver
     * Plusieurs cas possible
     *      Le bus arrive sur l'aire de concert :
     *          - Passage à l'état D de l'ensemble des festivaliers du bus
     *          - Vider la collection des festivaliers du bus
     *      Le bus arrive sur le site de départ ;
     *          - Ajoute le bus à la collection de bus présent sur le site de départ
     * @param site
     */
    public void arriver( Site site ) {
        this.current_site = site;
        // Si il s'agit de l'aire de concert
        if (site instanceof AireDeConcert) {
            //site.ajoutBus(this);
            System.out.println("Bus "+this.idB+" arrive sur "+this.current_site.toString());
            System.out.println("Bus "+this.idB+" débarquement des passagers...");
            // On fait descendre tout le monde du bus (passage du festivalier à l'état D et on vide la liste des festivaliers du bus
            for(Festivalier f : this.festivaliers) {
                f.setEtatF("D");
            }

            this.festivaliers.clear();
        }
        if (site instanceof SiteDepart) {
            ((SiteDepart)site).ajoutBus(this);
            System.out.println("Bus "+this.idB+" arrive sur "+this.current_site.toString());
            System.out.println("Bus "+this.idB+" embarquement des passagers...");
        }


    }

    /**
     * Methode attendre
     * Wait le bus (thread) du temps d'attente du site courant
     */
    public void attendre() {
        synchronized (this) {
            if (this.current_site instanceof SiteDepart) {
                // Le bus part du site de depart
                SiteDepart current_site = (SiteDepart) this.current_site;
                try {
                    this.wait(current_site.getTempsAttente());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else if (this.current_site instanceof AireDeConcert) {
                // Le bus est a l'aire de concert
                AireDeConcert current_site = (AireDeConcert) this.current_site;
                try {
                    this.wait(current_site.getTempsDescente());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Methode partir
     * Si le bus est sur un site de départ,
     * retire le bus de la collection des bus du site
     *
     * Lance le trajet d'une durée de 5secondes
     *
     */
    private void partir() {
        // Retirer le bus du site courant
        if (this.current_site instanceof SiteDepart) {
            ((SiteDepart)this.current_site).retraitBus(this);
        }
        System.out.println("Bus "+this.idB+" départ de "+this.current_site.toString());

        // Trajet
        try {
            this.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Methode ToString
     * Affiche l'ID du bus et les festivaliers à bord
     * @return string
     */
    public String toString() {
        String rslt = "Bus "+idB+"\n";
        for(Festivalier f : this.festivaliers) {
            rslt += "Festivalier "+f.getIdF()+"\n";
        }
        rslt  += "\n";
        return rslt;
    }

    /**
     * Methode run
     * Lance le bus (thread) et execute en boucle la simulation arriver - attendre -partir
     */
    public void run() {

        System.out.println("On lance un thread Bus !");

        // Chaque bus arrive, attend puis part
        while( true ) {
            this.arriver(this.depart);
            this.attendre();
            this.partir();
            System.out.println(this.toString());
            this.arriver(this.arrivee);
            this.attendre();
            this.partir();
        }

    }
}
