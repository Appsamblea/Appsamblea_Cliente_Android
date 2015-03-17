package cliente.appsamblea.ListAdapters;

import cliente.appsamblea.R;
import cliente.appsamblea.ItemsManagers.itemAsamblea;
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
public class AsambleaListAdapter implements ListAdapter {
    private Context context;
    //protected ArrayList<Long> referencias;
    protected ArrayList<itemAsamblea> asambleas;
    private int layout;

    public AsambleaListAdapter(Context c, ArrayList<itemAsamblea> litems){
        context = c;
        asambleas = new ArrayList<>();
        //referencias = e;

        layout = R.layout.item_proximaasamblea;
        if(litems == null){
            asambleas = null;
            //referencias = null;
        }
        else{
            for(itemAsamblea item: litems){
                asambleas.add(item);
            }
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
        return asambleas.size();
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

        asambleas.get(posicion).setNombreAsamblea((TextView)row.findViewById(R.id.nombreProximaAsamblea));
        asambleas.get(posicion).setFecha((TextView) row.findViewById(R.id.fechaProximaAsamblea));
        asambleas.get(posicion).setOrganizacion((TextView) row.findViewById(R.id.organizacionProximaAsamblea));

        return (row);
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
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
