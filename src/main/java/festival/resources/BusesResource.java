package festival.resources;

/*import org.inria.restlet.mta.backend.Backend;
import org.inria.restlet.mta.internals.Tweet;
import org.inria.restlet.mta.internals.User;*/
import festival.classes.Festivalier;
import festival.simulation.Simulation;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.restlet.data.MediaType;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.FileRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by 13006798 on 18/11/15.
 */
public class BusesResource extends ServerResource {

    /** Backend. */
    private Simulation simulation_;

    /** User handled by this resource. */
    private Festivalier fest_;

    /**
     * Constructor.
     * Call for every single user request.
     */
    public BusesResource()
    {
        simulation_ = (Simulation) getApplication().getContext().getAttributes()
                .get("simulation");
    }

    /**
     * Méthode getBusesHtml
     * Affiche la template des buses
     * @return Representation
     *      Fichier HTML
     */
    @Get("html")
    public Representation getBusesHtml() {
        return new FileRepresentation("templates/add-buses.html", MediaType.TEXT_HTML);
    }

    
    /**
     * Méthode CreateBuses
     * Récupère un JSON pour connaître le nombre de bus à ajouter
     * Puis fait appel à la fonction d'ajout de la simulation
     * @param representation
     * @throws Exception
     */

    @Post("json")
    public void createBuses(JsonRepresentation representation) throws Exception
    {
        JSONObject object = representation.getJsonObject();

        simulation_.addBuses(object.getInt("nbbus"), 50);
    }

}
