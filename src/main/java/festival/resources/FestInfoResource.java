package festival.resources;

import festival.classes.Festivalier;
import festival.simulation.Simulation;
import org.json.JSONException;
import org.json.JSONObject;
import org.restlet.data.MediaType;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.FileRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

/**
 * Created by Quentin on 07/12/2015.
 */
public class FestInfoResource extends ServerResource {

    /** Backend. */
    private Simulation simulation_;

    /** User handled by this resource. */
    private Festivalier fest_;

    /**
     * Constructor.
     * Call for every single user request.
     */
    public FestInfoResource()
    {
        simulation_ = (Simulation) getApplication().getContext().getAttributes()
                .get("simulation");
    }

    @Get("html")
    public Representation getFestivalierHtml() {
        return new FileRepresentation("templates/get-festivalier.html", MediaType.TEXT_HTML);
    }

    /**
     * Returns the user matching the id given in the URI
     *
     * @return JSON representation of a festivalier
     * @throws JSONException
     */
    @Get("json")
    public Representation getFestivalier() throws Exception
    {
        System.out.println("Choux fleur");
        String festIdString = (String) getRequest().getAttributes().get("peopleId");
        int festID = Integer.valueOf(festIdString);
        fest_ = simulation_.getFestivaliers().get(festID);
        System.out.println("Crotte");
        JSONObject festObject = new JSONObject();
        festObject.put("id", fest_.getIdF());
        festObject.put("nom", fest_.getNomF());
        festObject.put("prenom", fest_.getPrenomF());
        festObject.put("etat", fest_.getEtatF());

        JsonRepresentation result = new JsonRepresentation(festObject);

        System.out.println("festObject content : " + festObject);
        System.out.println("result content : " + result);

        result.setIndenting(true);
        return result;
    }
}
