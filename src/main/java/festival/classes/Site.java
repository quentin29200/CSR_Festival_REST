package festival.classes;

import java.util.ArrayList;

/**
 * Created by 13006798 on 25/11/15.
 */
public interface Site {

    void ajoutBus(Bus bus);

    void retraitBus(Bus bus);

    ArrayList<Bus> getBuses();
}
