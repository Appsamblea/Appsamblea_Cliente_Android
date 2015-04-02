package cliente.appsamblea.itemsManagers;

import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by carlos on 15/03/15.
 */
public class itemOrganizacion {
    private ImageView imagen;
    private TextView nombre;
    private TextView tematica;

    public itemOrganizacion() {

    }

    public ImageView getImagen() {
        return imagen;
    }

    public void setImagen(ImageView imagen) {
        this.imagen = imagen;
    }

    public TextView getNombre() {
        return nombre;
    }

    public void setNombre(TextView nombre) {
        this.nombre = nombre;
    }

    public TextView getTematica() {
        return tematica;
    }

    public void setTematica(TextView tematica) {
        this.tematica = tematica;
    }
}
