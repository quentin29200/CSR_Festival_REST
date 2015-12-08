package festival.simulation;

public class MainSimu {

	public static void MainSimu(String[] args) {

		Simulation simulation = new Simulation();
		// Ajout de 5 bus de capacité 40 places
		simulation.addBuses(2,9);

		simulation.addPeoples(20);

	}

}
