package festival.classes;

import java.util.ArrayList;

/**
 * Created by 13006798 on 27/11/15.
 */
public class SiteDepart implements Site {
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
        System.out.println(this.buses.toString());
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

    public void ajoutBus(Bus bus) {
        synchronized (this) {
            this.buses.add(bus.getIdB(), bus);
            this.notifyAll();
        }
    }

    public synchronized void retraitBus(Bus bus) {
        synchronized (this) {
            Bus b = this.buses.remove(2);
            System.out.println(b.getIdB());
            System.out.println("Bus" + bus.getIdB() + " removed of list");
            System.out.println(this.buses.toString());
        }

    }

    public ArrayList<Bus> getBuses() {
        return this.buses;
    }
}
