package festival.classes;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 13006798 on 25/11/15.
 */
public class Bus extends Thread {

    // VARIABLE - Identifiant du bus
    private int idB = 0;

    // VARIABLE - Capacité du bus
    private int capa;

    // VARIABLE - Collection de Festivalier
    private Festivalier[] festivaliers;

    // VARIABLE - Site courant du bus
    private Site current_site;
    private Site depart;
    private Site arrivee;

    // Constructeur
    public Bus(int idB, int capa) {
        this.idB = idB;
        this.capa = capa;
        this.festivaliers = new Festivalier[capa];
    }

    public void initialiserTrajet(SiteDepart depart, AireDeConcert arrivee) {
        this.depart = depart;
        this.arrivee = arrivee;
    }

    // Faire arriver un bus sur un site
    public void arriver( Site site ) {
        this.current_site = site;
        site.ajoutBus(this);
    }

    public void attendre() {
        if( this.current_site instanceof SiteDepart ) {
            // Le bus part du site de depart
            SiteDepart current_site = (SiteDepart) this.current_site;
            try {
                this.wait( current_site.getTempsAttente() );
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        else if ( this.current_site instanceof AireDeConcert ) {
            // Le bus est a l'aire de concert
            AireDeConcert current_site = (AireDeConcert) this.current_site;
            try {
                this.wait( current_site.getTempsDescente() );
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // Le bus part du site courant
    private void partir() {
        // Retirer le bus du site courant
        this.current_site.retraitBus(this);

        // Trajet
        try {
            this.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void run() {

        System.out.println("On lance un thread Bus !");

        // Chaque bus arrive, attend puis part
        while( true ) {
            this.arriver(this.depart);
            this.attendre();
            this.partir();
            this.arriver(this.arrivee);
            this.attendre();
            this.partir();
        }

    }
}
