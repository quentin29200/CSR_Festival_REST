package festival.simulation;

import festival.classes.*;

import java.util.ArrayList;

public class Simulation {
    private ArrayList<Bus> buses_depart = new ArrayList<>();
    private ArrayList<Bus> buses_arrivee = new ArrayList<>();
    private ArrayList<Festivalier> festivaliers = new ArrayList<>();
    private SiteDepart depart;
    private Billeterie billeterie;
    private AireDeConcert sitefestival;

    public Simulation() {
        this.depart = new SiteDepart(buses_depart, 15000);
        System.out.println("Site de départ créé : Buses ajoutés au site de départ");

        this.billeterie = new Billeterie();

        this.sitefestival = new AireDeConcert(buses_arrivee, 1000);
        System.out.println("Aire du concert créé");
    }

	public void addPeoples(int people) {
        for (int i = 0; i <people; i++) {
            Festivalier f = new Festivalier("Festi"+i,"Festi"+i, this);
            festivaliers.add(f);
            System.out.println("Festi"+i);
            f.start();
        }
    }

    public void addPeople(int people) {
        for (int i = 0; i <people; i++) {
            Festivalier f = new Festivalier("Festi"+i,"Festi"+i, this);
            festivaliers.add(f);
            System.out.println("Festi"+i);
            f.start();
        }
    }

	public void addBuses(int buses, int seats) {
        for (int i = 0; i <buses; i++) {
            System.out.println("Bus "+i+" créé");
            Bus b = new Bus(i, seats);
            b.initialiserTrajet(this.depart, this.sitefestival);
            this.buses_depart.add(b);
            b.start();
        }
    }

    // Getter and Setter
    public ArrayList<Bus> getBuses_depart() {
        return buses_depart;
    }

    public SiteDepart getDepart() {
        return depart;
    }

    public Billeterie getBilleterie() {
        return billeterie;
    }

    public ArrayList<Festivalier> getFestivaliers() {
        return festivaliers;
    }
}
