package cliente.appsamblea.application;
import android.app.Application;
import java.util.HashMap;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;
import cliente.appsamblea.R;
/**
 * Created by carlos on 24/03/15.
 */
public class AppsambleaApplication extends Application{

    //Rastreadores para realizar el seguimiento con Google Analytics
    public enum TrackerName {
        APP_TRACKER, // Rastreador utilizado por Appsamblea.
    }

    //Método para obtener los rastreadores que utilicemos
    public synchronized Tracker getTracker(TrackerName trackerId) {
        if (!mTrackers.containsKey(trackerId)) {

            GoogleAnalytics analytics = GoogleAnalytics.getInstance(this);
           // Tracker t = (trackerId == TrackerName.APP_TRACKER) ? analytics.newTracker(PROPERTY_ID)
            //        : (trackerId == TrackerName.GLOBAL_TRACKER) ? analytics.newTracker(R.xml.tracker)
            Tracker t = analytics.newTracker(R.xml.tracker);
            mTrackers.put(trackerId, t);

        }
        return mTrackers.get(trackerId);
    }

    HashMap<TrackerName, Tracker> mTrackers = new HashMap<TrackerName, Tracker>();
}
