package festival.simulation;

import festival.classes.*;

import java.util.ArrayList;

public class Simulation {
    private ArrayList<Bus> buses_depart = new ArrayList<>();
    private ArrayList<Festivalier> festivaliers = new ArrayList<>();
    private SiteDepart depart;
    private Billeterie billeterie;
    private AireDeConcert sitefestival;

    public Simulation() {
        this.depart = new SiteDepart(buses_depart, 15000);
        System.out.println("Site de départ créé : Buses ajoutés au site de départ");

        this.billeterie = new Billeterie();

        this.sitefestival = new AireDeConcert(1000);
        System.out.println("Aire du concert créé");
    }

	public void addPeoples(int people) {
        for (int i = 0; i <people; i++) {
            Festivalier f = new Festivalier("Festi","Festi", this);
            festivaliers.add(f);
            System.out.println("Festi"+f.getIdF()+"ajouté à la liste en position"+festivaliers.size());
            f.start();
        }
    }

	public void addBuses(int buses, int seats) {
        for (int i = 0; i <buses; i++) {
            Bus b = new Bus(seats);
            System.out.println("Bus "+b.getIdB()+" créé");
            b.initialiserTrajet(this.depart, this.sitefestival);
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
