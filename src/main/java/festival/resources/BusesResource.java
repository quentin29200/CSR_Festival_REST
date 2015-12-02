package festival.resources;

/*import org.inria.restlet.mta.backend.Backend;
import org.inria.restlet.mta.internals.Tweet;
import org.inria.restlet.mta.internals.User;*/
import festival.classes.Festivalier;
import festival.simulation.Simulation;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.restlet.ext.json.JsonRepresentation;
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

    @Post("json")
    public void createFest(JsonRepresentation representation) throws Exception
    {
        JSONObject object = representation.getJsonObject();

        simulation_.addBuses(object.length(), 50);
    }

}
