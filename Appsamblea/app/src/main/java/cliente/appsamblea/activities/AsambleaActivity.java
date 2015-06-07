package cliente.appsamblea.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

import cliente.appsamblea.R;
import cliente.appsamblea.application.AppsambleaApplication;
import cliente.appsamblea.database.Asamblea;
import cliente.appsamblea.database.Database;
import cliente.appsamblea.utils.ComunicadorServidor;

public class AsambleaActivity extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    private Context contexto;
    // Elementos de la UI
    private TextView mOrganizacionView;
    private TextView mLugarView;
    private TextView mFechaView;
    private TextView mHoraView;
    private TextView mDescripcionView;
    private String idAsamblea;
    private String idUsuario;
    private Database db;


    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asamblea);
        contexto = this;
        db = new Database(contexto);
        idUsuario = db.getIdUsuario();

        //Cargar la asamblea del intent.
        Asamblea asamblea = (Asamblea) getIntent().getSerializableExtra("Asamblea");

        //Cargar la UI en los atributos.
        mOrganizacionView = (TextView) findViewById(R.id.organizacion);
        mLugarView = (TextView) findViewById(R.id.lugar);
        mFechaView = (TextView) findViewById(R.id.fecha);
        mHoraView = (TextView) findViewById(R.id.hora);
        mDescripcionView = (TextView) findViewById(R.id.descripcion);

        //Rellenar la UI con los datos de la asamblea
        mOrganizacionView.setText("Organizada por " + asamblea.getOrganizacion());
        mLugarView.setText(asamblea.getLugar());
        mFechaView.setText(asamblea.getFecha());
        mHoraView.setText(asamblea.getHora());
        mDescripcionView.setText(asamblea.getDescripcion());

        //Cambiar el título
        getSupportActionBar().setTitle(asamblea.getNombre());

        /*mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);*/
        mTitle = asamblea.getNombre();

        // Set up the drawer.
        /*mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));
        */
        //Seguimiento de la actividad: GoogleAnalytics
        Tracker t = ((AppsambleaApplication) getApplication()).getTracker(AppsambleaApplication.TrackerName.APP_TRACKER);
        t.setScreenName("Asamblea");
        t.send(new HitBuilders.AppViewBuilder().build());
        //
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        //TODO cambiar de activity.
        /*// update the main content by replacing fragments
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, PlaceholderFragment.newInstance(position + 1))
                .commit();
                */
    }

    public void onSectionAttached(int number) {
        /*switch (number) {
            case 1:
                mTitle = getString(R.string.title_section1);
                break;
            case 2:
                mTitle = getString(R.string.title_section2);
                break;
            case 3:
                mTitle = getString(R.string.title_section3);
                break;
        }*/
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Only show items in the action bar relevant to this screen
        // if the drawer is not showing. Otherwise, let the drawer
        // decide what to show in the action bar.
        getMenuInflater().inflate(R.menu.asamblea, menu);
        restoreActionBar();
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        Intent intent;
        switch (id){
            case R.id.orden_del_dia:
                intent = new Intent (AsambleaActivity.this, OrdenDiaActivity.class);
                startActivity(intent);
                //Importante devolver true cuando se lanza una actividad.
                return true;
            case R.id.crear_votacion:
                intent = new Intent (AsambleaActivity.this, VotacionesActivity.class);
                startActivity(intent);
                return true;
            case R.id.turnos_de_palabra:
                intent = new Intent (AsambleaActivity.this, TurnosPalabraActivity.class);
                startActivity(intent);
                return true;
            case R.id.eliminar_asamblea:
                //TODO obetener el id de la asamblea
                if(ComunicadorServidor.EliminarAsamblea(idUsuario, idAsamblea)) {
                    return true;
                }
                else{
                    Toast.makeText(contexto, "No se ha podido eliminar la asamblea. Quizás usted no sea el creador. En caso contrario, compruebe su conexión a Internet.", Toast.LENGTH_LONG).show();
                    return true;
                }
            default:
                return super.onOptionsItemSelected(item);
        }
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
            View rootView = inflater.inflate(R.layout.fragment_asamblea, container, false);
            return rootView;
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((AsambleaActivity) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }
    }

}
