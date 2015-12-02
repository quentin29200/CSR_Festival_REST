package festival.classes;

import java.util.ArrayList;

/**
 * Created by 13006798 on 27/11/15.
 */
public class SiteDepart extends Site {

    // VARIABLE - Temps d'attente pour descendre
    private int tempsAttente;

    // Constructeur
    public SiteDepart(ArrayList<Bus> buses, int tempsAttente) {
        super(buses);
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
                for (Bus bus : super.getBuses()) {
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

    public synchronized void ajouterBus(Bus b) {
        super.ajoutBus(b);
        this.notifyAll();
    }


    // Getter et Setters
    public int getTempsAttente() {
        return tempsAttente;
    }
}
