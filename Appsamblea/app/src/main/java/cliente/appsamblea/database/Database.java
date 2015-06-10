package cliente.appsamblea.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;

/**
 * Created by carlos on 29/01/2015.
 */

public class Database extends SQLiteOpenHelper{

    protected ContentValues registro;
    private Cursor cursor;
    private SQLiteDatabase db;

    public Database(Context c){
        super(c, "databaseAppsamblea.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase bd){
        db = bd;
        db.execSQL("CREATE TABLE IF NOT EXISTS Usuario (" +
                "usuario VARCHAR(20), pass VARCHAR(20), id INT, PRIMARY KEY (id))");

        //Comentario
        //Otro comentario
        /*
        *
        * Por cada tabla:
        bd.execSQL("CREATE TABLE IF NOT EXISTS Usuario ("+
                "nombre VARCHAR(20), altura INT, peso INT, fechaNacimiento VARCHAR(10), sexo VARCHAR(1), mail VARCHAR(30), "+
                "sonido VARCHAR, PRIMARY KEY (nombre))");
        * */

        /*
        * Las inserciones iniciales de los datos de la BD:
        *
            registro = new ContentValues();
            registro.put("nombre", "Biceps "+ Integer.valueOf(i).toString());
            registro.put("musculoPrincipal", "Bíceps");
            registro.put("path", "bic"+ Integer.valueOf(i).toString());
            bd.insert("Ejercicio", null, registro);
        * */
    }



    @Override
    public void onUpgrade(SQLiteDatabase bd, int oldVersion, int newVersion){
        if(newVersion > oldVersion){
            /*Si se desea añadir alguna tabla o realizar algún cambio en la base
            de datos en una versión posterior, los cambios se han de definir en este
            método
             */
        }
    }

    public ArrayList<Asamblea> proximasAsambleas(String usuario){
        ArrayList<Asamblea> resultados = new ArrayList();
        return resultados;
    }

    public ArrayList<Asamblea> todasAsambleas(String usuario){
        ArrayList<Asamblea> resultados = new ArrayList();
        return resultados;
    }

    public String getIdUsuario(){
        cursor = this.getReadableDatabase().rawQuery("SELECT id FROM Usuario", null);
        if(cursor.moveToFirst()) {
          return cursor.getString(0);
        }
        else{
            return null;
        }
    }

    /*Registra las características del usuario en la base de datos local.
    * En caso de que ya se haya creado otro usuario con anterioridad, se elimina.
    */
    public boolean insertarUsuario(Usuario user){
        db.delete("Usuario", "", new String[]{});
        registro = new ContentValues();
        registro.put("id", user.getId() );
        if(user.getNombre() != null) {
            registro.put("usuario", user.getNombre());
        }
        if(user.getPassword() != null) {
            registro.put("password", user.getPassword());
        }
        if(db.insert("Usuario", null, registro) == -1){
            return false;
        }
        else{
            return true;
        }
    }

}