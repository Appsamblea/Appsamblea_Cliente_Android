package cliente.appsamblea.activities;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.ListView;

import java.util.ArrayList;

import cliente.appsamblea.R;
import cliente.appsamblea.database.Votacion;
import cliente.appsamblea.listAdapters.VotacionesListAdapter;

public class VotacionesActivity extends ActionBarActivity {

    private Votacion v1, v2, v3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_votaciones);

        //Crear unas votaciones de prueba.
        v1 = new Votacion("¿Deberíamos de seguir haciendo esta asamblea?");
        v2 = new Votacion("¿Quién debería de ser nuestro nuevo líder?");
        v3 = new Votacion("¿Os parece bien que cambiemos la fecha de la siguiente asamblea a Julio?");

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
        v3.addOpcion(si);

        //Crear el listAdapter
        ArrayList <Votacion> votaciones = new ArrayList<Votacion>();
        votaciones.add(v1);
        votaciones.add(v2);
        votaciones.add(v3);

        final VotacionesListAdapter adapter = new VotacionesListAdapter(this, android.R.layout.simple_list_item_1, votaciones);
        ListView l = (ListView) findViewById(R.id.listaVotaciones);
        l.setAdapter(adapter);
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
