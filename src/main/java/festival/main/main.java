package festival.main;


import festival.Application.FestApp;
import festival.simulation.Simulation;
import org.restlet.Application;
import org.restlet.Component;
import org.restlet.Context;
import org.restlet.Restlet;
import org.restlet.data.Protocol;
import org.restlet.data.Reference;
import org.restlet.resource.Directory;

/**
 * Main RESTlet minimal example
 *
 */
public final class Main
{

    /** Hide constructor. */
    private Main()
    {
        throw new UnsupportedOperationException();
    }

    /**
     * Main method.
     *
     * @param args  The arguments of the command line
     * @throws Exception
     */
    public static void main(String[] args) throws Exception
    {
        // Create a component
        Component component = new Component();
        Context context = component.getContext().createChildContext();
        component.getServers().add(Protocol.HTTP, 5000);
        component.getClients().add(Protocol.FILE);

        // Create an application
        Application application = new FestApp(context);

        // Add the backend into component's context
        Simulation simulation = new Simulation();
        context.getAttributes().put("simulation", simulation);
        component.getDefaultHost().attach(application);

        // Start the component
        component.start();
    }

}
