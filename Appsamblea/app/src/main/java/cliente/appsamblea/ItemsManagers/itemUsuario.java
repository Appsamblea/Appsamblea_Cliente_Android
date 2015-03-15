package cliente.appsamblea.ItemsManagers;

import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by carlos on 29/01/2015.
 */
public class itemUsuario {
    private ImageView imagen;
    private TextView nombre;

    public itemUsuario() {

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
}
