package festival.resources;

import festival.classes.Festivalier;
import festival.simulation.Simulation;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.restlet.data.MediaType;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.representation.FileRepresentation;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Quentin on 02/12/2015.
 */
public class FestivaliersResource extends ServerResource {

    /** Backend. */
    private Simulation simulation_;

    /**
     * Constructor.
     * Call for every single user request.
     */
    public FestivaliersResource()
    {
        simulation_ = (Simulation) getApplication().getContext().getAttributes()
                .get("simulation");
    }

    @Get("html")
    public Representation getFestivaliersHtml() {
        return new FileRepresentation("templates/festivaliers/list-festivaliers.html", MediaType.TEXT_HTML);
    }

    @Get("json")
    public Representation getFestivaliers() throws JSONException
    {
        Collection<Festivalier> festivaliers = simulation_.getFestivaliers();
        Collection<JSONObject> jsonUsers = new ArrayList<JSONObject>();

        for (Festivalier festivalier : festivaliers)
        {
            JSONObject current = new JSONObject();
            current.put("id", festivalier.getIdF());
            current.put("url", getReference().toString() +  festivalier.getIdF());
            jsonUsers.add(current);

        }
        JSONArray jsonArray = new JSONArray(jsonUsers);
        JsonRepresentation result = new JsonRepresentation(jsonArray);
        result.setIndenting(true);
        return result;
    }

}


