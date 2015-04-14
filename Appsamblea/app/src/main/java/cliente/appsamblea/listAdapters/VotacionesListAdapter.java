package cliente.appsamblea.listAdapters;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.HashMap;

import cliente.appsamblea.database.Votacion;

/**
 * Created by Daniel on 14/04/2015.
 */
public class VotacionesListAdapter extends ArrayAdapter<String> {

    private HashMap <String, Integer> map;

    public VotacionesListAdapter(Context context, int resource, ArrayList<Votacion> votaciones) {
        super(context, resource);

        map = new HashMap<>();

        //Pasar los nombres de las votaciones al map
        for (int i = 0; i < votaciones.size(); i++){
            map.put(votaciones.get(i).getPregunta(), i);
        }
    }

    @Override
    public long getItemId(int posicion) {
        String item = getItem(posicion);
        return map.get(item);
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }


}
