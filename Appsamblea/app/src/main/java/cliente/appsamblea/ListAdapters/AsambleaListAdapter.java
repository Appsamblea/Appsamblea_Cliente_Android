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
public class AsambleaListAdapter {
    private Context context;
    protected ArrayList<String> referencias;
    protected ArrayList<itemAsamblea> asambleas;
    private int layout;

    public AsambleaListAdapter(Context c, ArrayList<String> e, ArrayList<itemAsamblea> litems){
        context = c;
        referencias = e;
        layout = R.layout.item_proximaasamblea;
        if(e == null){
            asambleas = litems;
            referencias = null;
        }
        else{
            referencias = e;
            asambleas= null;
        }
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public int getCount() {
        if(referencias!=null )return referencias.size();
        else return asambleas.size();
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

        asambleas.get(posicion).setAsamblea((TextView)row.findViewById(R.id.nombreProximaAsamblea));
        asambleas.get(posicion).setFecha((TextView)row.findViewById(R.id.fechaProximaAsamblea));
        asambleas.get(posicion).setOrganizacion((TextView)row.findViewById(R.id.organizacionProximaAsamblea));

        return (row);
    }
}
