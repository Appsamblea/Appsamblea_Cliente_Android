package cliente.appsamblea.database;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * Created by carlos on 15/03/15.
 */
public class Asamblea implements Serializable{
    private long id;

    private String nombre;
    private Date fecha;
    private String fechaString;
    private String horaString;
    private String lugar;
    private String descripcion;
    private boolean es_abierta;
    private String url_streaming;
    //TODO el creador deberá de ser un usuario.
    private String creador;
    private String organizacion;
    private String participantes;

    /**
     * Constructor vacío
     */
    public Asamblea() {

    }

    /*
     * //TODO este método tendrá que ser cambiado cuando la organizacion se cambie por una clase.
     * Constructor muy básico.
     * @param nombre el nombre de la asamblea.
     * @param organizacion el nombre de la organizacion que convocó la asamblea.
     * @param fecha la fecha en la que se celebrará la asamblea.
     * @param descripcion la descripcion de la asamblea.
     * @param lugar lugar donde se celebrará la asamblea.
     * @param id el id de la asamblea.
     */
    public Asamblea (String nombre, String organizacion, Date fecha, int id, String descripcion, String lugar){
        this.nombre = nombre;
        this.organizacion = organizacion;
        this.fecha = fecha;
        this.id = id;
        this.descripcion = descripcion;
        this.lugar = lugar;

        //Cadenas de fecha y hora
        SimpleDateFormat parserFecha = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat parserHora = new SimpleDateFormat("HH:mm");

        fechaString = parserFecha.format(fecha);
        horaString = parserHora.format(fecha);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFecha() {return fechaString;}

    public String getHora() {return horaString;}

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isEs_abierta() {
        return es_abierta;
    }

    public void setEs_abierta(boolean es_abierta) {
        this.es_abierta = es_abierta;
    }

    public String getUrl_streaming() {
        return url_streaming;
    }

    public void setUrl_streaming(String url_streaming) {
        this.url_streaming = url_streaming;
    }

    public String getCreador() {
        return creador;
    }

    public void setCreador(String creador) {
        this.creador = creador;
    }

    public String getOrganizacion() {
        return organizacion;
    }

    public void setOrganizacion(String organizacion) {
        this.organizacion = organizacion;
    }

    public String getParticipantes() {
        return participantes;
    }

    public void setParticipantes(String participantes) {
        this.participantes = participantes;
    }
}
