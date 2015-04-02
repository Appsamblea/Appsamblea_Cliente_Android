package cliente.appsamblea.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import cliente.appsamblea.R;

public class RegistroActivity extends ActionBarActivity {

    // Elementos de la UI
    private EditText mEmailEditText;
    private EditText mPasswordEditText;
    private EditText mConfirmarPasswordEditText;
    private Button mRegistrarButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        //Ponerle la flechita hacia atrás
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Asignar elementos del layout a atributos de la clase
        mEmailEditText = (EditText) findViewById(R.id.email);
        mPasswordEditText = (EditText) findViewById(R.id.password);
        mConfirmarPasswordEditText = (EditText) findViewById(R.id.confirm_password);
        mRegistrarButton = (Button) findViewById(R.id.confirmar_registro);



        //Registrar nos lleva a la pantalla de login.
        mRegistrarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Extraer valores de inputs
                String email = mEmailEditText.getText().toString();
                String password = mPasswordEditText.getText().toString();
                String confirmarPassword = mConfirmarPasswordEditText.getText().toString();

                //Comprobación de campos
                boolean camposCorrectos = true;

                //Foco tras la comprobación
                View llamarAtencion = null;

                //Comprobar que email no esté vacío y sea válido
                if (TextUtils.isEmpty(email)) {
                    mEmailEditText.setError(getString(R.string.error_field_required));
                    llamarAtencion = mEmailEditText;
                    camposCorrectos = false;
                } else if (!validarEmail(email)) {
                    mEmailEditText.setError(getString(R.string.error_invalid_email));
                    llamarAtencion = mEmailEditText;
                    camposCorrectos = false;
                }
                //Comprobar que la contraseña no esté vacía y sea válida
                else if (TextUtils.isEmpty(password)){
                    mPasswordEditText.setError(getString(R.string.error_field_required));
                    llamarAtencion = mPasswordEditText;
                    camposCorrectos = false;
                }
                else if (!validarPassword(password)){
                    mPasswordEditText.setError(getString(R.string.error_password_must_be_8_characters_long));
                    llamarAtencion = mPasswordEditText;
                    camposCorrectos = false;
                }
                //Comprobar que el campo "repetir contraseña" no esté vacío y sea igual que la contraseña
                else if (TextUtils.isEmpty(confirmarPassword)){
                    mConfirmarPasswordEditText.setError(getString(R.string.error_field_required));
                    llamarAtencion = mConfirmarPasswordEditText;
                    camposCorrectos = false;
                }else if (!TextUtils.equals(password, confirmarPassword)){
                    mConfirmarPasswordEditText.setError(getString(R.string.error_passwords_are_not_the_same));
                    llamarAtencion = mConfirmarPasswordEditText;
                    camposCorrectos = false;
                }

                //Si ha habido un fallo, centrar la acción en el foco
                if (!camposCorrectos){
                    llamarAtencion.requestFocus();
                }else{
                    //Crear intent para lanzarlo
                    Intent intent = new Intent(RegistroActivity.this, LoginActivity.class);

                    //Meter los datos del usuario en el intent
                    //TODO en lugar de esto mandarle info a la base de datos
                    intent.putExtra("email", email);
                    intent.putExtra("password", password);

                    //Lanzar el intent
                    startActivity(intent);
                }
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_registro, menu);
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
     * Valida un email
     * @param email email a validar
     * @return si el email es válido
     */
    private boolean validarEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    /**
     * Valida una contraseña.
     * @param password contraseña a validar
     * @return si la contraseña es válida
     */
    private boolean validarPassword (String password){
        return password.length() >= 8;
    }
}
