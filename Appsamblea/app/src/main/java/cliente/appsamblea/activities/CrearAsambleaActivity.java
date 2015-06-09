package cliente.appsamblea.activities;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.text.format.Time;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TimePicker;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;

import cliente.appsamblea.R;
import cliente.appsamblea.database.Database;
import cliente.appsamblea.utils.ComunicadorServidor;

public class CrearAsambleaActivity extends ActionBarActivity {
    private Database db;
    private Context contexto;
    private EditText nombreAsamblea;
    private EditText lugarAsamblea;
    private static EditText fechaAsamblea;
    private static EditText horaAsamblea;
    private EditText descripcionAsamblea;
    private CheckBox abiertaAsamblea;

    private static int hora, minuto;
    private static int dia, mes, anio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_asamblea);
        db = new Database(contexto);
        contexto = this;
        nombreAsamblea = (EditText) findViewById(R.id.nombreCrearAsamblea);
        lugarAsamblea = (EditText) findViewById(R.id.lugarCrearAsamblea);
        fechaAsamblea = (EditText) findViewById(R.id.fechaCrearAsamblea);

        fechaAsamblea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment fragment = new DatePickerFragment();
                fragment.show(getFragmentManager(), "DatePicker");
            }
        });

        horaAsamblea = (EditText) findViewById(R.id.horaCrearAsamblea);

        horaAsamblea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment fragment = new TimePickerFragment();
                fragment.show(getFragmentManager(), "TimePicker");
            }
        });
        descripcionAsamblea = (EditText) findViewById(R.id.descripcionCrearAsamblea);
        abiertaAsamblea = (CheckBox) findViewById(R.id.abiertaCrearAsamblea);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_crear_asamblea, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id){
            case R.id.action_done:
                //Se envía la asamblea al servidor
                if(nombreAsamblea.getText().toString().isEmpty() ||
                        lugarAsamblea.getText().toString().isEmpty() ||
                        fechaAsamblea.getText().toString().isEmpty() ||
                        horaAsamblea.getText().toString().isEmpty() ||
                        descripcionAsamblea.getText().toString().isEmpty()){
                    Toast.makeText(contexto, "Rellene todos los campos para crear una asamblea", Toast.LENGTH_SHORT).show();
                }
                else if(!crearAsamblea()){
                    Toast.makeText(contexto, "No se ha podido crear la asamblea, compruebe su conexión a Internet e inténtelo de nuevo", Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(contexto, "Asamblea creada correctamente", Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    //Este método se encarga de enviar la información recogida en el Layout y de enviarla al servidor
    //Hay que lanzar una hebra para que no bloquee la interfaz de usuario.
    private boolean crearAsamblea(){
        SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.preference_key_file), Context.MODE_PRIVATE);

        return ComunicadorServidor.CrearAsamblea(
                sharedPreferences.getString(getString(R.string.usuario_id), ""),
                nombreAsamblea.getText().toString(),
                lugarAsamblea.getText().toString(),
                dia, mes, anio,
                hora, minuto,
                descripcionAsamblea.getText().toString(),
                abiertaAsamblea.isChecked());
    }

    public static class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            return new TimePickerDialog(getActivity(), this, hora, minuto,
                    DateFormat.is24HourFormat(getActivity()));
        }
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            hora = hourOfDay;
            minuto = minute;
            horaAsamblea.setText(String.valueOf(hora) + ":" + String.valueOf(minuto));
        }
    }

    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            Time ahora = new Time();
            ahora.setToNow();
            return new DatePickerDialog(getActivity(), this, ahora.year, ahora.month, ahora.monthDay);
        }
        public void onDateSet(DatePicker view, int year, int monthOfYear, int
                dayOfMonth) {
            anio = year;
            mes = monthOfYear;
            dia = dayOfMonth;
            fechaAsamblea.setText(String.valueOf(dia) + "/" + String.valueOf(mes) + '/' + String.valueOf(anio));
        }
    }
}
