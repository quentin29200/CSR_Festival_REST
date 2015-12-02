package festival.simulation;

public class Main {

	public static void main(String[] args) {
		
		Simulation simulation = new Simulation();
		// Ajout de 5 bus de capacité 40 places
		simulation.addBuses(2,4);

		simulation.addPeople(20);

	}

}
