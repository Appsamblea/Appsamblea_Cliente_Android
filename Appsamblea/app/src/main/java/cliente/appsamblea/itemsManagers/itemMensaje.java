package cliente.appsamblea.itemsManagers;
import android.widget.TextView;

/**
 * Created by carlos on 15/03/15.
 */
public class itemMensaje {

    private TextView emisor;
    //@+id/emisorMensajeItem

    private TextView mensaje;
    //@+id/textoMensajeItem

    public itemMensaje() {

    }

    public TextView getEmisor() {
        return emisor;
    }

    public void setEmisor(TextView emisor) {
        this.emisor = emisor;
    }

    public TextView getMensaje() {
        return mensaje;
    }

    public void setMensaje(TextView mensaje) {
        this.mensaje = mensaje;
    }
}
