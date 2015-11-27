package festival.classes;

import java.util.ArrayList;

/**
 * Created by 13006798 on 27/11/15.
 */
public class AireDeConcert extends Site {

    // VARIABLE - Temps d'attente pour descendre
    private int tempsDescente;

    private int nbFestivaliersPresent;

    // Constructeur
    public AireDeConcert(ArrayList<Bus> buses, int tempsDescente) {
        super(buses);
        this.tempsDescente = tempsDescente;
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
