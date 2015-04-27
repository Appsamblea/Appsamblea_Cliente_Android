package cliente.appsamblea.activities;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

import java.util.ArrayList;

import cliente.appsamblea.R;
import cliente.appsamblea.application.AppsambleaApplication;
import cliente.appsamblea.itemsManagers.itemInvitacion;
import cliente.appsamblea.listAdapters.InvitacionListAdapter;

public class InvitarAsistentesActivity extends ActionBarActivity{

    private ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invitar_asistentes);
        lista = (ListView)this.findViewById(R.id.listaInvitados);
        ArrayList<itemInvitacion> arrayList = new ArrayList();
        InvitacionListAdapter listAdapter = new InvitacionListAdapter(this, arrayList);
        lista.setAdapter(listAdapter);

        //Seguimiento de la actividad: GoogleAnalytics
        Tracker t = ((AppsambleaApplication) getApplication()).getTracker(AppsambleaApplication.TrackerName.APP_TRACKER);
        t.setScreenName("Invitar asistentes");
        t.send(new HitBuilders.AppViewBuilder().build());
        //
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
