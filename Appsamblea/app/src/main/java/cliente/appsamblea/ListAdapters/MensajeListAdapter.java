package cliente.appsamblea.ListAdapters;

import cliente.appsamblea.R;
import cliente.appsamblea.ItemsManagers.itemMensaje;
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
public class MensajeListAdapter {
    private Context context;
    protected ArrayList<String> referencias;
    protected ArrayList<itemMensaje> mensajes;
    private int layout;

    public AsambleaListAdapter(Context c, ArrayList<String> e, ArrayList<itemMensaje> litems){
        context = c;
        referencias = e;
        layout = R.layout.item_mensaje;
        if(e == null){
            mensajes = litems;
            referencias = null;
        }
        else{
            referencias = e;
            mensajes= null;
        }
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public int getCount() {
        if(referencias!=null )return referencias.size();
        else return mensajes.size();
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
    public View getView(int posicion, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(layout, parent, false);

        mensajes.get(posicion).setEmisor((TextView)row.findViewById(R.id.emisorMensajeItem));
        mensajes.get(posicion).setMensaje((TextView)row.findViewById(R.id.textoMensajeItem));
        return (row);
    }
}
