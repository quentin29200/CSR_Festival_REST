package festival.simulation;

import festival.classes.AireDeConcert;
import festival.classes.Bus;
import festival.classes.SiteDepart;

import java.util.ArrayList;

public class Simulation {


	public Simulation() {
        ArrayList<Bus> buses_depart = new ArrayList<>();
        ArrayList<Bus> buses_arrivee = new ArrayList<>();
        for (int i = 0; i <5; i++) {
            System.out.println("Bus "+i+" créé");
            Bus b = new Bus(i, 40);
            buses_depart.add(b);
        }

        SiteDepart depart = new SiteDepart(buses_depart, 10000);
        System.out.println("Site de départ créé : Buses ajoutés au site de départ");

        AireDeConcert sitefestival = new AireDeConcert(buses_arrivee, 1000);
        System.out.println("Aire du concert créé");

        System.out.println("Lancement de la rotation des bus");
        for(Bus b : buses_depart) {
            b.initialiserTrajet(depart, sitefestival);
            b.start();
        }
    }

	public void addPeople(int people) {
        // TODO	
    }

	public void addBuses(int buses, int seats) {
	    // TODO
    }
}
