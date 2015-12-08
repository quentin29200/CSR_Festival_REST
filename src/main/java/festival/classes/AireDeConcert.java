package festival.classes;

import java.util.ArrayList;

/**
 * Created by 13006798 on 27/11/15.
 */
public class AireDeConcert implements Site {
    // VARIABLE - Ensemble des bus présent sur le site courant
    private ArrayList<Bus> buses;

    // VARIABLE - Temps d'attente pour descendre
    private int tempsDescente;

    private int nbFestivaliersPresent;

    // Constructeur
    public AireDeConcert(ArrayList<Bus> buses, int tempsDescente) {
        this.buses = buses;
        this.tempsDescente = tempsDescente;
    }

    public void ajoutBus(Bus bus) {
        this.buses.add(bus);
    }

    public void retraitBus(Bus bus) {
        this.buses.remove(bus.getIdB());
    }

    public ArrayList<Bus> getBuses() {
        return this.buses;
    }

    // Getter tempsDescente
    public int getTempsDescente() {
        return tempsDescente;
    }

    // Getter et setter nbFestivalierPresent
    public int getNbFestivaliersPresent() {
        return nbFestivaliersPresent;
    }

    public void setNbFestivaliersPresent(int nbFestivaliersPresent) {
        this.nbFestivaliersPresent = nbFestivaliersPresent;
    }
}
