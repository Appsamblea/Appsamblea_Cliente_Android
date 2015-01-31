package cliente.appsamblea.ListAdapters;

import cliente.appsamblea.R;
import cliente.appsamblea.ItemsManagers.itemAsamblea;
import java.util.ArrayList;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.ImageView;

/**
 * Created by carlillos on 29/01/2015.
 */
public class UsuarioListAdapter {
    private Context context;
    protected ArrayList<String> elementos;
    protected ArrayList<itemAsamblea> items;
    private int layout;

    public UsuarioListAdapter(Context c, int l, ArrayList<String> e, ArrayList<EjercicioRutina> er){
        context = c;
        elementos = e;
        layout = l;
        if(e == null){
            ejerciciosrutina = er;
            elementos = null;
        }
        else{
            elementos = e;
            ejerciciosrutina = null;
        }
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public int getCount() {
        if(elementos!=null )return elementos.size();
        else return ejerciciosrutina.size();
    }

    @Override
    public Object getItem(int arg0) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View row;
        row = inflater.inflate(layout, parent, false);
        /*Asginar los atributos a los elementos del layot a partir de su ID
        Ejemplo:
            TextView texto;
        	texto = (TextView) row.findViewById(R.id.nombreElemento);
        */

        return (row);
    }
}