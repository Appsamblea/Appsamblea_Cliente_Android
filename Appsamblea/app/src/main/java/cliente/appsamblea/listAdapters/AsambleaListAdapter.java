package cliente.appsamblea.listAdapters;

import android.content.Context;
import android.content.Intent;
import android.database.DataSetObserver;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import cliente.appsamblea.R;
import cliente.appsamblea.activities.AsambleaActivity;
import cliente.appsamblea.itemsManagers.itemAsamblea;

/**
 * Created by carlos on 29/01/2015.
 */
public class AsambleaListAdapter implements ListAdapter{
    private Context context;
    //protected ArrayList<Long> referencias;
    protected ArrayList<itemAsamblea> listaAsambleas;
    private int layout;
    private static LayoutInflater inflater = null;

    public AsambleaListAdapter(Context c, ArrayList<itemAsamblea> litems){
        //Asignar argumentos
        context = c;
        listaAsambleas = new ArrayList<>();

        layout = R.layout.item_proximaasamblea;

        //Llamar a al layout xml externo
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);


        //Controlar la lista de itemAsamblea y copiarla.
        if(litems == null || litems.size() == 0){
            listaAsambleas = null;
        }
        else{
            for(itemAsamblea item: litems){
                listaAsambleas.add(item);
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
        return listaAsambleas.size();
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

        final itemAsamblea item;
        View vi = convertView;

        //Comprobar si convertView es nulo
        if (convertView == null){
            //Añadir el xml del ítem. Se hace con el inflater.
            vi = inflater.inflate(R.layout.item_proximaasamblea, null);

            //Rellenar la vista
            item = listaAsambleas.get(posicion);//Rellenar el ítem.
            item.setNombreAsamblea((TextView)vi.findViewById(R.id.nombreProximaAsamblea));
            item.setFecha((TextView) vi.findViewById(R.id.fechaProximaAsamblea));
            item.setOrganizacion((TextView) vi.findViewById(R.id.organizacionProximaAsamblea));

            //Darle layout a la vista
            vi.setTag(item);

            vi.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, AsambleaActivity.class);
                    intent.putExtra("Asamblea", item.getAsamblea());
                    context.startActivity(intent);
                }
            });

        }else{
            item = (itemAsamblea)vi.getTag();
        }

        if (listaAsambleas.size() <= 0){
            //TODO cambiarlo por un mensaje de error del diccionario
            Log.i ("AsambleaListAdapter", "Sin datos");
        }
        else{
            //Rellenar la vista
            item.actualizarValores();
        }

        //Lo pongo aquí por si acaso TODO borrar
        /*vi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("ListAdapter", "Click! pre return");
            }
        });*/

        return (vi);
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
