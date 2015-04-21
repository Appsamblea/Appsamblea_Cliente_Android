package cliente.appsamblea.listAdapters;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import cliente.appsamblea.R;
import cliente.appsamblea.itemsManagers.itemInvitacion;

/**
 * Created by carlos on 21/04/15.
 */
public class InvitacionListAdapter implements ListAdapter {
    private Context context;
    protected ArrayList<itemInvitacion> items;
    private int layout;

    public InvitacionListAdapter(Context c, ArrayList<itemInvitacion> litems){
        context = c;
        layout = R.layout.item_usuario;
        items = litems;
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
       return items.size();
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

        items.get(posicion).setImagen((ImageView)row.findViewById(R.id.imagenAddUsuarioGrupoItem));
        items.get(posicion).setNombre((TextView) row.findViewById(R.id.nombreAddUsuarioGrupoItem));

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