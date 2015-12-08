package festival.classes;

import java.util.ArrayList;

/**
 * <b>Classe Aire de concert</b>
 * <p>
 * L'aire de concert dispose d'un temps d'attente, qui sera le temps d'attente d'un bus pour
 * la descente des festivaliers
 * D'autres fonctions non utilisées permettront de générer des statistiques
 * </p>
 *
 * @version 1.0
 */
public class AireDeConcert extends Site{
    // VARIABLE - Temps d'attente pour descendre
    private int tempsDescente;

    private int nbFestivaliersPresent;

    /**
     * Constructeur de l'aire de concert
     *
     *  @param tempsDescente
     *  Simulation
     */
    public AireDeConcert(int tempsDescente) {
        this.tempsDescente = tempsDescente;
    }

    /**
     * Getter TempsDescente
     * @return int
     *      retourne le temps d'attente
     */
    public int getTempsDescente() {
        return tempsDescente;
    }

    /**
     * Getter Nb Festivalier présent
     * @return int
     *      retourne le nombre de festivalier sur l'aire de concert
     */
    public int getNbFestivaliersPresent() {
        return nbFestivaliersPresent;
    }

    /**
     * Setter Nb Festivalier présent
     *  @param nbFestivaliersPresent
     *      instancie un nombre de festivalier
     */
    public void setNbFestivaliersPresent(int nbFestivaliersPresent) {
        this.nbFestivaliersPresent = nbFestivaliersPresent;
    }
}
