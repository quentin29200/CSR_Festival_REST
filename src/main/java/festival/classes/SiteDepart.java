package festival.classes;

import java.util.ArrayList;

/**
 * Created by 13006798 on 27/11/15.
 */
public class SiteDepart extends Site {
    // VARIABLE - Ensemble des bus présent sur le site courant
    private ArrayList<Bus> buses;

    // VARIABLE - Temps d'attente pour descendre
    private int tempsAttente;

    // Constructeur
    public SiteDepart(ArrayList<Bus> buses, int tempsAttente) {
        this.buses = buses;
        this.tempsAttente = tempsAttente;
    }

    /**
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

    // Getter et Setters
    public int getTempsAttente() {
        return tempsAttente;
    }

    synchronized public void ajoutBus(Bus bus) {
        this.buses.add(bus);
        this.notifyAll();
    }

    synchronized public void retraitBus(Bus bus) {
        this.buses.remove(bus);
    }

    public ArrayList<Bus> getBuses() {
        return this.buses;
    }
}
