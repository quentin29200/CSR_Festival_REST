package festival.classes;

import java.util.ArrayList;

/**
 * Created by 13006798 on 25/11/15.
 */
public abstract class Site {

    // VARIABLE - Ensemble des bus présent sur le site courant
    private ArrayList<Bus> buses;

    // Constructeur sur classe abstraite
    public Site(ArrayList<Bus> buses) {
        this.buses = buses;
    }

    // Setters
    public void ajoutBus(Bus bus) {
        // Ajout d'un bus a liste des bus present sur le site
        this.buses.add(bus);
    }

    public void retraitBus(Bus bus) {
        // Retrait d'un bus a liste des bus present sur le site
        this.buses.remove(bus);
    }

    public ArrayList<Bus> getBuses() {
        return buses;
    }
}
