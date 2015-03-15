package cliente.appsamblea.ItemsManagers;
import android.graphics.drawable.Drawable;
import android.widget.TextView;
/**
 * Created by carlillos on 29/01/2015.
 */
public class itemAsamblea {

    private TextView asamblea;
    //@+id/nombreProximaAsamblea"
    private TextView fecha;
    //"@+id/fechaProximaAsamblea"
    private TextView organizacion;
    //"@+id/organizacionProximaAsamblea"

    public TextView getAsamblea() {
        return asamblea;
    }

    public void setAsamblea(TextView asamblea) {
        this.asamblea = asamblea;
    }

    public TextView getFecha() {
        return fecha;
    }

    public void setFecha(TextView fecha) {
        this.fecha = fecha;
    }



    public TextView getOrganizacion() {
        return organizacion;
    }

    public void setOrganizacion(TextView organizacion) {
        this.organizacion = organizacion;
    }
}
