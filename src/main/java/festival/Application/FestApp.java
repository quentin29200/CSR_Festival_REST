package festival.Application;
import festival.resources.FestivalierResource;
import festival.resources.RootResource;
import org.restlet.Application;
import org.restlet.Context;
import org.restlet.Restlet;
import org.restlet.resource.Directory;
import org.restlet.routing.Router;

import java.io.File;

/**
 * Created by Quentin on 02/12/2015.
 */
public class FestApp extends Application{

    public FestApp(Context context)
    {
        super(context);
    }

    @Override
    public Restlet createInboundRoot()
    {
        File staticDirectory = new File("static/");
        Directory directory = new Directory(getContext(), "file:///" + staticDirectory.getAbsolutePath() + "/");
        directory.isDeeplyAccessible();
        directory.isListingAllowed();

        Router router = new Router(getContext());
        router.attach("/", RootResource.class);
        router.attach("/people", FestivalierResource.class);
        //router.attach("/buses/", BusesResource.class);
        router.attach("/people/{people-id}", FestivalierResource.class);
        router.attach("/people/{people-id}/stats", FestivalierResource.class);
        return router;
    }
}
