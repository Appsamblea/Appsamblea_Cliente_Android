package cliente.appsamblea.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

import cliente.appsamblea.R;
import cliente.appsamblea.application.AppsambleaApplication;
import cliente.appsamblea.database.Votacion;

public class VotacionActivity extends ActionBarActivity {
    private Votacion votacion;

    //Elementos de la UI
    private TextView mNombreVotacionTextView;
    private RadioGroup mOpcionesRadioGroup;
    private Button mVotarButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_votacion);

        //Cargar la votación del intent.
        votacion = (Votacion) getIntent().getSerializableExtra("votacion");

        //Asignar elementos de la UI
        mNombreVotacionTextView = (TextView) findViewById(R.id.nombreVotacion);
        mOpcionesRadioGroup = (RadioGroup) findViewById(R.id.opciones);
        mVotarButton = (Button) findViewById(R.id.votar);

        //Mostrar el nombre
        mNombreVotacionTextView.setText(votacion.getPregunta());

        //Rellenar las opciones
        for (int i = 0; i < votacion.getOpciones().size(); i++){
            //Crear un radiobutton y meterle text
            RadioButton rb = new RadioButton(this);
            rb.setText((String)votacion.getOpciones().get(i));

            //Añadirlo al radiobutton
            mOpcionesRadioGroup.addView(rb);
        }

        //Asignar callback
        mVotarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        //Seguimiento de la actividad: GoogleAnalytics
        Tracker t = ((AppsambleaApplication) getApplication()).getTracker(AppsambleaApplication.TrackerName.APP_TRACKER);
        t.setScreenName("Votación");
        t.send(new HitBuilders.AppViewBuilder().build());
        //
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_votacion, menu);
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
}
