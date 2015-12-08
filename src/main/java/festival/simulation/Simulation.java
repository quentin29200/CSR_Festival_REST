package festival.simulation;

import festival.classes.*;

import java.util.ArrayList;
/**
 * <b>Classe Simulation</b>
 * <p>
 * Créé un site de départ, une billeterie, une aire de concert et permet d'ajouter
 * des festivaliers et des bus à la simulation
 * </p>
 *
 * @version 1.0
 */
public class Simulation {
    private ArrayList<Bus> buses_depart = new ArrayList<>();
    private ArrayList<Festivalier> festivaliers = new ArrayList<>();
    private SiteDepart depart;
    private Billeterie billeterie;
    private AireDeConcert sitefestival;

    /**
     * Constructeur de la simulation
     *
     *  Simulation
     */
    public Simulation() {
        this.depart = new SiteDepart(buses_depart, 15000);
        System.out.println("Site de départ créé : Buses ajoutés au site de départ");

        this.billeterie = new Billeterie();

        this.sitefestival = new AireDeConcert(1000);
        System.out.println("Aire du concert créé");
    }

    /**
     * Méthode addpeoples
     * Créé n festivaliers et les lance
     * @param people
     */
	public void addPeoples(int people) {
        for (int i = 0; i <people; i++) {
            Festivalier f = new Festivalier("Festi","Festi", this);
            festivaliers.add(f);
            System.out.println("Festi"+f.getIdF()+"ajouté à la liste en position"+festivaliers.size());
            f.start();
        }
    }

    /**
     * Méthode addBuses
     * Créé n bus et les lance
     * @param buses
     * @param seats
     */
	public void addBuses(int buses, int seats) {
        for (int i = 0; i <buses; i++) {
            Bus b = new Bus(seats);
            System.out.println("Bus "+b.getIdB()+" créé");
            b.initialiserTrajet(this.depart, this.sitefestival);
            b.start();
        }
    }


    // Getter and Setter
    /**
     * Getter SiteDepart
     * @return SiteDepart
     *      retourne le site de départ
     */
    public SiteDepart getDepart() {
        return depart;
    }

    /**
     * Getter Billeterie
     * @return Billeterie
     *      retourne la billeterie
     */
    public Billeterie getBilleterie() {
        return billeterie;
    }

    /**
     * Getter Festivalier
     * @return Festivalier
     *      retourne la liste des festivaliers
     */
    public ArrayList<Festivalier> getFestivaliers() {
        return festivaliers;
    }
}
