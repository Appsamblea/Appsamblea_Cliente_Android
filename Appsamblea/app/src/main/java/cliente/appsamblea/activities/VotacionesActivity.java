package cliente.appsamblea.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

import java.util.ArrayList;

import cliente.appsamblea.R;
import cliente.appsamblea.application.AppsambleaApplication;
import cliente.appsamblea.database.Votacion;

public class VotacionesActivity extends ActionBarActivity {

    private ArrayList <String> nombresVotaciones;
    private ArrayList <Votacion> votaciones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_votaciones);

        //Crear unas vsVotaciones de prueba.
        Votacion v1 = new Votacion("¿Deberíamos de seguir haciendo esta asamblea?");
        Votacion v2 = new Votacion("¿Quién debería de ser nuestro nuevo líder?");
        Votacion v3 = new Votacion("¿Os parece bien que cambiemos la fecha de la siguiente asamblea a Julio?");

        String si = "Si";
        String no = "No";
        String pablo = "Pablo Iglesias";
        String rajoy = "Mariano Rajoy";
        String albert = "Albert Rivera";

        v1.addOpcion(si);
        v1.addOpcion(no);
        v2.addOpcion(pablo);
        v2.addOpcion(rajoy);
        v2.addOpcion(albert);
        v3.addOpcion(si);
        v3.addOpcion(no);

        votaciones = new ArrayList<>();
        votaciones.add(v1);
        votaciones.add(v2);
        votaciones.add(v3);

        //Crear el listAdapter para strings
        nombresVotaciones = new ArrayList<>();
        nombresVotaciones.add(v1.getPregunta());
        nombresVotaciones.add(v2.getPregunta());
        nombresVotaciones.add(v3.getPregunta());

        ArrayAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, nombresVotaciones);
        ListView l = (ListView) findViewById(R.id.listaVotaciones);
        l.setAdapter(adapter);

        l.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(VotacionesActivity.this, VotacionActivity.class);
                intent.putExtra("votacion", votaciones.get(position));
                startActivity(intent);
            }
        });

        //Seguimiento de la actividad: GoogleAnalytics
        Tracker t = ((AppsambleaApplication) getApplication()).getTracker(AppsambleaApplication.TrackerName.APP_TRACKER);
        t.setScreenName("ListaDeVotaciones");
        t.send(new HitBuilders.AppViewBuilder().build());
        //
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_votaciones, menu);
        return true;
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
        else if (id == android.R.id.home){
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_votaciones, container, false);
            return rootView;
        }
    }
}
