package cliente.appsamblea.listAdapters;

import cliente.appsamblea.R;
import cliente.appsamblea.itemsManagers.itemGrupo;
import java.util.ArrayList;
import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.ImageView;

/**
 * Created by carlos on 29/01/2015.
 */
public class GrupoListAdapter implements ListAdapter {
    private Context context;
    protected ArrayList<String> referencias;
    protected ArrayList<itemGrupo> grupos;
    private int layout;

    public GrupoListAdapter(Context c, ArrayList<String> e, ArrayList<itemGrupo> litems){
        context = c;
        referencias = e;
        layout = R.layout.item_grupo;
        if(e == null){
            grupos = litems;
            referencias = null;
        }
        else{
            referencias = e;
            grupos= null;
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
        else return grupos.size();
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

        grupos.get(posicion).setImagen((ImageView)row.findViewById(R.id.imagenGrupoItem));
        grupos.get(posicion).setNombre((TextView) row.findViewById(R.id.nombreGrupoItem));

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