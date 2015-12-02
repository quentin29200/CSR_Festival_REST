package festival.resources;

/*import org.inria.restlet.mta.backend.Backend;
import org.inria.restlet.mta.internals.Tweet;
import org.inria.restlet.mta.internals.User;*/
import org.json.JSONArray;
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
public class BusesResources extends ServerResource {

    /** Backend. */
   /* private Backend backend_;

    public BusesResources()
    {
        backend_ = (Backend) getApplication().getContext().getAttributes()
                .get("backend");
    }



    @Post("json")
    public JsonRepresentation createTweet(JsonRepresentation representation) throws Exception
    {
        // Récupération du Tweet
        JSONObject object = representation.getJsonObject();
        String content = object.getString("content");
        Tweet t = new Tweet(content);

        // Récupération de l'User
        String userIdString = (String) getRequest().getAttributes().get("userId");
        int userId = Integer.valueOf(userIdString);
        User user = backend_.getDatabase().getUser(userId);

        // Ajout du Tweet
        user.addTweet(t);

        // generate result
        JSONObject resultObject = new JSONObject();
        resultObject.put("user", user.getName());
        resultObject.put("tweets ajouté", t.getContent_());
        JsonRepresentation result = new JsonRepresentation(resultObject);
        return result;
    }


    @Get("json")
    public Representation getTweets() throws Exception {
        // Récupération de l'User
        String userIdString = (String) getRequest().getAttributes().get("userId");
        int userId = Integer.valueOf(userIdString);
        User user = backend_.getDatabase().getUser(userId);

        // Récupération de la liste des Tweets
        ArrayList<Tweet> tweets = user.getTweets_();

        // Génération du JSON
        Collection<JSONObject> jsonTweets = new ArrayList<JSONObject>();

        for (Tweet t : tweets) {
            JSONObject current = new JSONObject();
            current.put("Content", t.getContent_());
            jsonTweets.add(current);
        }

        JSONArray jsonArray = new JSONArray(jsonTweets);
        JsonRepresentation result = new JsonRepresentation(jsonArray);
        result.setIndenting(true);
        return result;
    }

*/

}
