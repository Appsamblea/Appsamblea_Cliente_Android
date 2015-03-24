package cliente.appsamblea.ItemsManagers;
import android.widget.TextView;
import cliente.appsamblea.Database.Asamblea;

/**
 * Created by carlos on 29/01/2015.
 */
public class itemAsamblea {

    private TextView nombreAsamblea;
    //@+id/nombreProximaAsamblea"
    private TextView fecha;
    //"@+id/fechaProximaAsamblea"
    private TextView organizacion;
    //"@+id/organizacionProximaAsamblea"
    private Asamblea asamblea;

    public itemAsamblea(Asamblea a){
        asamblea = a;
    }

    public Asamblea getAsamblea() {
        return asamblea;
    }

    public void setAsamblea(Asamblea asamblea) {
        this.asamblea = asamblea;
    }

    public TextView getNombreAsamblea() {
        return nombreAsamblea;
    }

    public void setNombreAsamblea(TextView nombreAsamblea) {
        this.nombreAsamblea = nombreAsamblea;
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

    public void actualizarValores (){
        nombreAsamblea.setText(asamblea.getNombre());
        fecha.setText(asamblea.getFecha().toString());
        organizacion.setText(asamblea.getOrganizacion());
    }
}
