package cliente.appsamblea.activities;

import android.app.Activity;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cliente.appsamblea.R;
import cliente.appsamblea.application.AppsambleaApplication;
import cliente.appsamblea.utils.ComunicadorServidor;


public class LoginActivity extends Activity implements LoaderCallbacks<Cursor> {

    /**
     * Credenciales falsas temporales.
     * TODO eliminarlas cuando esté la comunicación con el servidor.
     */
    private Map <String, String> credencialesFalsas = new HashMap < >();


    // Elementos de la UI
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private LoginButton mFacebookLoginButton;

    //Callback manager, necesario para el login con Facebook
    private CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Inicializar el SDK de Facebook, es necesario hacerlo antes del setContentView
        FacebookSdk.sdkInitialize(getApplicationContext());

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Sacar la key hash de Facebook por log para ponerla en Facebook developers.
        //TODO esto va fuera en producción.
        try {
            PackageInfo info = getPackageManager().getPackageInfo("cliente.appsamblea", PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.e("MY KEY HASH:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }


        //TODO eliminar cuando esté la comunicación con el servidor.
        credencialesFalsas.put("prueba@appsamblea.com", "1234");
        credencialesFalsas.put("a@a.com", "q");
        //Añadir a las credenciales falsas lo que haya creado el usuario anteriormente
        SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.preference_key_file), Context.MODE_PRIVATE);
        String emailSharedPreferences = sharedPreferences.getString(getString(R.string.saved_email), "");
        String passwordSharedPreferences = sharedPreferences.getString(getString(R.string.saved_password), "");

        if (!emailSharedPreferences.isEmpty() && !passwordSharedPreferences.isEmpty())
            credencialesFalsas.put(emailSharedPreferences, passwordSharedPreferences);


        // Montar el formulario de activity_login.
        mEmailView = (AutoCompleteTextView) findViewById(R.id.email);
        //getLoaderManager().initLoader(0, null, this);

        mPasswordView = (EditText) findViewById(R.id.password);
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    login();
                    return true;
                }
                return false;
            }
        });

        //Botón de login
        Button mEmailSignInButton = (Button) findViewById(R.id.loginB);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });

        //Botón de registrarse
        TextView mRegistroButton = (TextView) findViewById(R.id.registro);
        mRegistroButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegistroActivity.class);
                startActivity(intent);
            }
        });

        //Botón de Login con Facebook
        mFacebookLoginButton = (LoginButton) findViewById(R.id.facebook_login);
        callbackManager = CallbackManager.Factory.create();
        mFacebookLoginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                AccessToken token = loginResult.getAccessToken();

                //Crear una llamada asincrona con Graph
                GraphRequest request = GraphRequest.newMeRequest(token, new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject jsonObject, GraphResponse graphResponse) {
                        String id, nombre, apellidos, email;

                        try {
                            id = jsonObject.getString("id");
                            nombre = jsonObject.getString("first_name");
                            apellidos = jsonObject.getString("last_name");
                            email = jsonObject.getString("email");

                            //TODO ver lo que devuelve el método y actuar en consecuencia.
                            ComunicadorServidor.registrarConFacebook(id, nombre, apellidos, email);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });

                Bundle parametros = new Bundle();
                parametros.putString("fields", "id, first_name, last_name, email");
                request.setParameters(parametros);
                request.executeAsync();
            }

            @Override
            public void onCancel() {
                Log.e("Facebook Login", "cancelado");
            }

            @Override
            public void onError(FacebookException e) {
                Log.e("Facebook Login", "Error!");
            }
        });


        Tracker t = ((AppsambleaApplication) getApplication()).getTracker(AppsambleaApplication.TrackerName.APP_TRACKER);
        t.setScreenName("Login en onCreate");
        t.send(new HitBuilders.AppViewBuilder().build());

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }


    @Override
    protected void onStart(){
        super.onStart();
        Tracker t = ((AppsambleaApplication) getApplication()).getTracker(AppsambleaApplication.TrackerName.APP_TRACKER);
        t.setScreenName("Se ha entrado en Login en onStart");
        t.send(new HitBuilders.AppViewBuilder().build());
        //GoogleAnalytics.getInstance(LoginActivity.this).reportActivityStart(this);
    }

    @Override
    protected void onStop(){
        super.onStop();
        GoogleAnalytics.getInstance(LoginActivity.this).reportActivityStop(this);
    }

    /**
     * Método para identificarse.
     * Si hay errores se muestran al usuario.
     * Si no hay errores se pasa a la pantalla de "próximas asambleas".
     */
    public void login() {
        // Eliminar errores
        mEmailView.setError(null);
        mPasswordView.setError(null);

        // Guardar valores de entrada.
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();

        boolean cancelar = false;
        View llamarAtencion = null;

        // Ver si la contraseña es válida.
        if (!TextUtils.isEmpty(password) && !validarPassword(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            llamarAtencion = mPasswordView;
            cancelar = true;
        }

        // Ver si el email es válido
        if (TextUtils.isEmpty(email)) {
            mEmailView.setError(getString(R.string.error_field_required));
            llamarAtencion = mEmailView;
            cancelar = true;
        } else if (!validarEmail(email)) {
            mEmailView.setError(getString(R.string.error_invalid_email));
            llamarAtencion = mEmailView;
            cancelar = true;
        }

        //Ver si la contraseña es buena
        if (!comprobarPassword(email, password)){
            // Los datos introducidos no son válidos.
            mPasswordView.setError(getString(R.string.error_incorrect_password));
            llamarAtencion = mPasswordView;
            cancelar = true;
        }
        //TODO Obtener el ID del usuario del servidor (Hay que hacer que, en caso de que el Login salga bien, el servidor devuelva el ID)

        if (cancelar) {
            // Ha habido un error. Se muestra el error y se cambia llama la atención del usuario.
            llamarAtencion.requestFocus();
        } else {
            //Lanzar proximas asambleas
            Intent intent = new Intent(LoginActivity.this, ProximasAsambleasActivity.class);
            startActivity(intent);
        }
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
     * Comprueba que una contraseña se corresponda a un email.
     * @param email email a comprobar
     * @param password contraseña a comprobar
     * @return si la contraseña corresponde al email.
     */
    private boolean comprobarPassword(String email, String password) {
        //TODO conexión con el servidor, ahora mismo solo comprueba los datos tontos.
        return credencialesFalsas.containsKey(email) && credencialesFalsas.get(email).equals(password);

    }
    /**
     * Método para comprobar que una contraseña sea válida.
     * @param password la contraseña
     * @return si la contraseña es válida o no.
     */
    private boolean validarPassword(String password) {
        return password.length() > 0;
    }

    //TODO investigar qué hace todo lo que hay de aquí para abajo.
    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        return new CursorLoader(this,
                // Retrieve data rows for the device user's 'profile' contact.
                Uri.withAppendedPath(ContactsContract.Profile.CONTENT_URI,
                        ContactsContract.Contacts.Data.CONTENT_DIRECTORY), ProfileQuery.PROJECTION,

                // Select only email addresses.
                ContactsContract.Contacts.Data.MIMETYPE +
                        " = ?", new String[]{ContactsContract.CommonDataKinds.Email
                .CONTENT_ITEM_TYPE},

                // Show primary email addresses first. Note that there won't be
                // a primary email address if the user hasn't specified one.
                ContactsContract.Contacts.Data.IS_PRIMARY + " DESC");
    }

    @Override
    public void onLoadFinished(Loader<Cursor> cursorLoader, Cursor cursor) {
        List<String> emails = new ArrayList<>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            emails.add(cursor.getString(ProfileQuery.ADDRESS));
            cursor.moveToNext();
        }

        addEmailsToAutoComplete(emails);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> cursorLoader) {

    }

    private interface ProfileQuery {
        String[] PROJECTION = {
                ContactsContract.CommonDataKinds.Email.ADDRESS,
                ContactsContract.CommonDataKinds.Email.IS_PRIMARY,
        };

        int ADDRESS = 0;
    }


    private void addEmailsToAutoComplete(List<String> emailAddressCollection) {
        //Create adapter to tell the AutoCompleteTextView what to show in its dropdown list.
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(LoginActivity.this,
                        android.R.layout.simple_dropdown_item_1line, emailAddressCollection);

        mEmailView.setAdapter(adapter);
    }
}



