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

    // Getter tempsDescente
    public int getTempsAttente() {
        return tempsAttente;
    }
}
