package cliente.appsamblea.ListAdapters;

import cliente.appsamblea.R;
import cliente.appsamblea.ItemsManagers.itemMensaje;
import java.util.ArrayList;
import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.ImageView;

/**
 * Created by carlos on 29/01/2015.
 */
public class MensajeListAdapter implements ListAdapter{
    private Context context;
    protected ArrayList<String> referencias;
    protected ArrayList<itemMensaje> mensajes;
    private int layout;

    public MensajeListAdapter(Context c, ArrayList<String> e, ArrayList<itemMensaje> litems){
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
    public void registerDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {

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

        mensajes.get(posicion).setEmisor((TextView) row.findViewById(R.id.emisorMensajeItem));
        mensajes.get(posicion).setMensaje((TextView) row.findViewById(R.id.textoMensajeItem));
        return (row);
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEnabled(int position) {
        return false;
    }
}
