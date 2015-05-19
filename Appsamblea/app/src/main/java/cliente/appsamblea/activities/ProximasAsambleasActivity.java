package cliente.appsamblea.activities;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.internal.view.menu.ActionMenuItemView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.widget.DrawerLayout;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

import java.util.ArrayList;
import java.util.Date;

import cliente.appsamblea.application.AppsambleaApplication;
import cliente.appsamblea.database.Asamblea;
import cliente.appsamblea.itemsManagers.itemAsamblea;
import cliente.appsamblea.listAdapters.AsambleaListAdapter;

import cliente.appsamblea.R;

public class ProximasAsambleasActivity extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks{

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;
    private ListView listView;
    private AsambleaListAdapter adapter;
    private ArrayList<itemAsamblea> proximasAsambleas;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proximas_asambleas);

        /*
        * proximasAsambleas = LISTA DE LAS PRÓXIMAS ASAMBLEAS OBTENIDA DE LA BASE DE DATOS*/

        /**/
        //Listado de prueba

        proximasAsambleas = new ArrayList <itemAsamblea>();

        //Crear 3 asambleas de prueba
        Asamblea pruebaAsamblea1, pruebaAsamblea2, pruebaAsamblea3;
        Date fecha = new Date();
        pruebaAsamblea1 = new Asamblea("Asamblea 1", "Organizacion 1", fecha, 0, "Una asamblea de prueba", "Palacio de Congresos de Madrid");
        pruebaAsamblea2 = new Asamblea("Asamblea 2", "Organizacion 2", fecha, 1, "Otra asamblea de prueba", "Palacio de Congresos de Granada");
        pruebaAsamblea3 = new Asamblea("Asamblea 3", "Organizacion 3", fecha, 2, "Y otra asamblea de prueba", "La casa de Paco");

        //Crear 3 items de asamblea
        itemAsamblea ia1, ia2, ia3;
        ia1 = new itemAsamblea(pruebaAsamblea1);
        ia2 = new itemAsamblea(pruebaAsamblea2);
        ia3 = new itemAsamblea(pruebaAsamblea3);

        //Añadir los items a la lista de asambleas
        proximasAsambleas.add (ia1);
        proximasAsambleas.add (ia2);
        proximasAsambleas.add (ia3);

        //Poner un adaptador a la lista
        listView = (ListView) findViewById(R.id.listaProximasAsambleas);
        adapter = new AsambleaListAdapter(this ,proximasAsambleas);
        listView.setAdapter(adapter);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();


        // Set up the drawer.
        /*mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));*/
        //Seguimiento de la actividad: GoogleAnalytics
        Tracker t = ((AppsambleaApplication) getApplication()).getTracker(AppsambleaApplication.TrackerName.APP_TRACKER);
        t.setScreenName("PróximasAsambleas");
        t.send(new HitBuilders.AppViewBuilder().build());
        //
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, PlaceholderFragment.newInstance(position + 1))
                .commit();
    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                mTitle = getString(R.string.title_section1);
                break;
            case 2:
                mTitle = getString(R.string.title_section2);
                break;
            case 3:
                mTitle = getString(R.string.title_section3);
                break;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.proximas_asambleas, menu);
            //Hay que crear un objeto "final" para poder utilizarlo en el "onFocusChange"
            final Menu menuFinal = menu;
            SearchManager searchManager =
                    (SearchManager) getSystemService(Context.SEARCH_SERVICE);
            SearchView searchView =
                    (SearchView) menu.findItem(R.id.action_search).getActionView();
            searchView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if(hasFocus){
                        menuFinal.findItem(R.id.action_add).setVisible(false);
                    }
                    else{
                        menuFinal.findItem(R.id.action_add).setVisible(true);
                    }
                }
            });
            //searchView.setMinimumWidth();
            searchView.setSearchableInfo(
                    searchManager.getSearchableInfo(getComponentName()));

            //restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        Context context = this;
        //noinspection SimplifiableIfStatement
        switch(id){
            case R.id.action_add:
                //Cambiar a Crear asamblea
                //Intent intent = new Intent(context, CrearAsambleaActivity.class);
                // intent.putExtra("Asamblea", item.getAsamblea());
                //context.startActivity(intent);
                break;
            case R.id.action_search:
                //Cambiar la Actionbar a un buscador para buscar la asamblea
                break;
        }
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_proximas_asambleas, container, false);
            return rootView;
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((ProximasAsambleasActivity) activity).onSectionAttached(getArguments().getInt(ARG_SECTION_NUMBER));
        }
    }
}
