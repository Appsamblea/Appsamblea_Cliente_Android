package cliente.appsamblea.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by carlillos on 29/01/2015.
 */

public class database extends SQLiteOpenHelper{

    protected ContentValues registro;

    public BaseDeDatos(Context c){
        super(c, "database.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase bd){

        bd.execSQL("CREATE TABLE IF NOT EXISTS Usuario ("+
                "usuario VARCHAR(20), pass VARCHAR(20), PRIMARY KEY (usuario, pass))");

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

}