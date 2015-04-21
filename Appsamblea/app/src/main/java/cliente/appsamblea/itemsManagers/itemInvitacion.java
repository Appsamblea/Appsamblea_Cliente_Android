package cliente.appsamblea.itemsManagers;

import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by carlos on 21/04/15.
 */

/*Clase para gestionar el item del ListView utilizado para añadir usuarios o grupos a una conversación,
  asamblea, organización, etc.
  */
public class itemInvitacion {
    private ImageView imagen;
    private TextView nombre;
    private ImageView check;
    private boolean seleccionado;

    public itemInvitacion() {

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

    public ImageView getCheck() {
        return check;
    }

    public void setCheck(ImageView check) {
        this.check = check;
    }

    public boolean isSeleccionado() {
        return seleccionado;
    }

    public void setSeleccionado(boolean seleccionado) {
        this.seleccionado = seleccionado;
    }
}