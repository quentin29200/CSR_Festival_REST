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

    public void monterDansBus(Festivalier f) {
        synchronized (this) {
            // Seul les festivaliers aillant un billet peuvent monter
            if (f.getEtatF() == "B") {
                // Tant que le festivalier n'est pas dans un bus
                while (f.getEtatF() != "C") {
                    // Si il n'a pas r�ussi a monter dans un bus
                    for (Bus bus : super.getBuses()) {
                        // Si le bus n'est pas complet, le festivalier monte dedans
                        if (!bus.estComplet() && f.getEtatF() == "B") {
                            bus.ajoutFestivalier(f);
                            // Le festivalier est dedans, l'�tat passe � C, on sort de la boucle
                            break;
                        }
                    }
                    if (f.getEtatF() != "C") {
                        try {
                            // Il attend d'�tre notifi� par l'arriv�e d'un bus
                            this.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    public void ajouterBus(Bus b) {
        synchronized (this) {
            super.ajoutBus(b);
            this.notifyAll();
        }
    }


    // Getter et Setters
    public int getTempsAttente() {
        return tempsAttente;
    }
}
