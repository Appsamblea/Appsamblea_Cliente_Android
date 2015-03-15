package cliente.appsamblea.ListAdapters;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import cliente.appsamblea.ItemsManagers.itemOrganizacion;
import cliente.appsamblea.R;

/**
 * Created by carlos on 15/03/15.
 */
public class OrganizacionListAdapter implements ListAdapter{
    private Context context;
    protected ArrayList<String> referencias;
    protected ArrayList<itemOrganizacion> organizaciones;
    private int layout;

    public OrganizacionListAdapter(Context c, ArrayList<String> e, ArrayList<itemOrganizacion> litems){
        context = c;
        referencias = e;
        layout = R.layout.item_organizacion;
        if(e == null){
            organizaciones = litems;
            referencias = null;
        }
        else{
            referencias = e;
            organizaciones= null;
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
        else return organizaciones.size();
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

        organizaciones.get(posicion).setImagen((ImageView)row.findViewById(R.id.imagenOrganizacionItem));
        organizaciones.get(posicion).setNombre((TextView) row.findViewById(R.id.nombreOrganizacionItem));
        organizaciones.get(posicion).setTematica((TextView) row.findViewById(R.id.tematicaOrganizacionItem));

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
