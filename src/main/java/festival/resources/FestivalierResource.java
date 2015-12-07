package festival.resources;

import festival.classes.Festivalier;
import festival.simulation.Simulation;
import org.json.JSONArray;
import org.restlet.data.MediaType;
import org.restlet.representation.FileRepresentation;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

import org.json.JSONException;
import org.json.JSONObject;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

import java.util.Iterator;

/**
 * Resource exposing a user
 *
 * @author msimonin
 * @author ctedeschi
 *
 */
public class FestivalierResource extends ServerResource
{

    /** Backend. */
    private Simulation simulation_;

    /** User handled by this resource. */
    private Festivalier fest_;

    /**
     * Constructor.
     * Call for every single user request.
     */
    public FestivalierResource()
    {
        simulation_ = (Simulation) getApplication().getContext().getAttributes()
                .get("simulation");
    }

    @Get("html")
    public Representation getFestivalierHtml() {
        return new FileRepresentation("templates/add-festivalier.html", MediaType.TEXT_HTML);
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
        String festIdString = (String) getRequest().getAttributes().get("people-id");
        int festID = Integer.valueOf(festIdString);
        fest_ = simulation_.getFestivaliers().get(festID);

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

    @Post("json")
    public void createFest(JsonRepresentation representation) throws Exception
    {
        JSONObject object = representation.getJsonObject();
        simulation_.addPeople(object.getInt("length"));
    }

}
