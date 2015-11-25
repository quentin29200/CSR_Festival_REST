package festival.classes;

import java.util.List;

/**
 * Created by 13006798 on 25/11/15.
 */
public class Bus {

    // VARIABLE - Identifiant du bus
    private int idB = 0;

    // VARIABLE - Capacité du bus
    private int capa;

    // VARIABLE - Collection de Festivalier
    private Festivalier[] festivaliers;

    // Constructeur
    public Bus(int idB, int capa) {
        this.idB = idB;
        this.capa = capa;
        this.festivaliers = new Festivalier[capa];
    }
}
