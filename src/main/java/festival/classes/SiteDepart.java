package festival.classes;

import java.util.ArrayList;

/**
 * <b>Classe SiteDépart</b>
 * <p>
 *     Le site de départ dispose d'une collection de bus présent et
 *     d'un temps d'attente
 * </p>
 *
 * @version 1.0
 */
public class SiteDepart extends Site {
    // VARIABLE - Ensemble des bus présent sur le site courant
    private ArrayList<Bus> buses;

    // VARIABLE - Temps d'attente pour descendre
    private int tempsAttente;

    /**
     * Constructeur de SiteDépart
     * Initialise la liste des bus présent sur le site et
     * le temps d'attente d'un bus
     * @param buses
     * @param tempsAttente
     */
    public SiteDepart(ArrayList<Bus> buses, int tempsAttente) {
        this.buses = buses;
        this.tempsAttente = tempsAttente;
    }

    /**
     * Méthode MonterDansBus
     *
     * Vérifie l'état du festivalier
     * Si festivalier dans l'état B
     * Alors tant qu'il n'est pas dans l'état C il tente de monter dans un bus (parcours des bus présent)
     * Si pas de bus ou bus tout complet, attente d'être notifié par l'arrivée d'un bus
     * Si le festivalier rentre dans le bus, il est ajouté dans la liste des festivalier du bus
     * et son état passe à C
     *
     * @param f
     *  Festivalier - Le festivalier qui monte dans le bus
     */
    public synchronized void monterDansBus(Festivalier f) {
        // Seul les festivaliers aillant un billet peuvent monter
        if (f.getEtatF().equals("B")) {
            // Tant que le festivalier n'est pas dans un bus
            while (!f.getEtatF().equals("C")) {
                // Si il n'a pas reussi a monter dans un bus
                for (Bus bus : this.buses) {
                    // Si le bus n'est pas complet, le festivalier monte dedans
                    if (!bus.estComplet() && f.getEtatF().equals("B")) {
                        bus.ajoutFestivalier(f);
                        // Le festivalier est dedans, l'etat passe a C, on sort de la boucle
                        break;
                    }
                }
                // Si il a vérifié tous les bus et qu'ils sont tous pleins
                if (f.getEtatF().equals("B")) {
                    try {
                        // Il attend d'etre notifie par l'arrivee d'un bus
                        this.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     * Getter TempsDescente
     * @return int
     *      retourne le temps d'attente
     */
    public int getTempsAttente() {
        return tempsAttente;
    }


    /**
     * Méthode ajoutBus
     * Ajoute un bus à la collection des bus présent
     * Notifie tous les festivaliers en attente de l'arrivée d'un bus
     * @param bus
     */
    synchronized public void ajoutBus(Bus bus) {
        this.buses.add(bus);
        this.notifyAll();
    }

    /**
     * Méthode retraitBus
     * Retire le bus de la collection des bus courant
     * @param bus
     */
    synchronized public void retraitBus(Bus bus) {
        this.buses.remove(bus);
    }

    /**
     * Méthode GetBuses
     * Retourne la liste des bus présent sur le site de départ
     * @return ArrayList
     */
    public ArrayList<Bus> getBuses() {
        return this.buses;
    }
}
