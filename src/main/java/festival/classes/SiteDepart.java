package festival.classes;

import java.util.ArrayList;

/**
 * Created by 13006798 on 27/11/15.
 */
public class SiteDepart extends Site {

    // VARIABLE - Temps d'attente pour descendre
    private int tempsAttente;

    // VARIABLE - Liste de festivalier en attente de prendre un bus
    private ArrayList<Festivalier> festivaliers;

    // Constructeur
    public SiteDepart(ArrayList<Bus> buses, int tempsAttente) {
        super(buses);
        this.tempsAttente = tempsAttente;
        this.festivaliers = new ArrayList<>();
    }

    // Getter tempsDescente
    public int getTempsAttente() {
        return tempsAttente;
    }


}
