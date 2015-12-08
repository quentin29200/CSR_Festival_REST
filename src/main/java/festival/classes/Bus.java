package festival.classes;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 13006798 on 25/11/15.
 */
public class Bus extends Thread {

    // VARIABLE - Identifiant du bus
    private static int nbbus = 0;
    private int idB = 0;

    // VARIABLE - Capacité du bus
    private int capa;

    // VARIABLE - Collection de Festivalier
    private ArrayList<Festivalier> festivaliers;

    // VARIABLE - Site courant du bus
    private Site current_site;
    private Site depart;
    private Site arrivee;

    // Constructeur
    public Bus(int capa) {
        this.idB = nextID();
        this.capa = capa;
        this.festivaliers = new ArrayList<>();
    }

    private static synchronized int nextID()
    {
        return nbbus++;
    }

    public int getIdB() {
        return idB;
    }

    public boolean estComplet() {
        return festivaliers.size() >= this.capa;
    }


    public void ajoutFestivalier(Festivalier f) {
        System.out.println("Festivalier " + f.getIdF() + " monte dans le bus " + this.idB);
        this.festivaliers.add(f);
        f.setEtatF("C");
    }

        public void initialiserTrajet(SiteDepart depart, AireDeConcert arrivee) {
        this.depart = depart;
        this.arrivee = arrivee;
    }

    // Faire arriver un bus sur un site
    public void arriver( Site site ) {
        this.current_site = site;
        // Si il s'agit de l'aire de concert
        if (site instanceof AireDeConcert) {
            //site.ajoutBus(this);
            System.out.println("Bus "+this.idB+" arrive sur "+this.current_site.toString());
            System.out.println("Bus "+this.idB+" débarquement des passagers...");
            // On fait descendre tout le monde du bus (passage du festivalier à l'état D et on vide la liste des festivaliers du bus
            for(Festivalier f : this.festivaliers) {
                f.setEtatF("D");
            }

            this.festivaliers.clear();
        }
        if (site instanceof SiteDepart) {
            ((SiteDepart)site).ajoutBus(this);
            System.out.println("Bus "+this.idB+" arrive sur "+this.current_site.toString());
            System.out.println("Bus "+this.idB+" embarquement des passagers...");
        }


    }

    public void attendre() {
        synchronized (this) {
            if (this.current_site instanceof SiteDepart) {
                // Le bus part du site de depart
                SiteDepart current_site = (SiteDepart) this.current_site;
                try {
                    this.wait(current_site.getTempsAttente());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else if (this.current_site instanceof AireDeConcert) {
                // Le bus est a l'aire de concert
                AireDeConcert current_site = (AireDeConcert) this.current_site;
                try {
                    this.wait(current_site.getTempsDescente());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // Le bus part du site courant
    private void partir() {
        // Retirer le bus du site courant
        if (this.current_site instanceof SiteDepart) {
            ((SiteDepart)this.current_site).retraitBus(this);
        }
        System.out.println("Bus "+this.idB+" départ de "+this.current_site.toString());

        // Trajet
        try {
            this.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String toString() {
        String rslt = "Bus "+idB+"\n";
        for(Festivalier f : this.festivaliers) {
            rslt += "Festivalier "+f.getIdF()+"\n";
        }
        rslt  += "\n";
        return rslt;
    }

    public void run() {

        System.out.println("On lance un thread Bus !");

        // Chaque bus arrive, attend puis part
        while( true ) {
            this.arriver(this.depart);
            this.attendre();
            this.partir();
            System.out.println(this.toString());
            this.arriver(this.arrivee);
            this.attendre();
            this.partir();
        }

    }
}
