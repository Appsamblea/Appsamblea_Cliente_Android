package cliente.appsamblea.Database;

import java.util.Date;
/**
 * Created by carlos on 15/03/15.
 */
public class Asamblea {
    /*
    nombre = models.CharField(max_length = 256)
	fecha = models.DateField()
	lugar = models.CharField(max_length = 256)
	descripcion = models.TextField()
	es_abierta = models.BooleanField(default = True)
	url_streaming = models.URLField(null = True)
	urlasamblea = models.URLField(null = True)
	usuario = models.ForeignKey(Usuario, related_name="asamblea_usuario")
	organizacion = models.ForeignKey(Organizacion)
	participantes = models.ManyToManyField(Usuario, through='Participa')
    * */
    private String nombre;
    private Date fecha;
    private String lugar;
    private String descripcion;
    private boolean es_abierta;
    private String url_streaming;
    private String creador;
    private String organizacion;
    private String participantes;
    private long id;

    public Asamblea() {
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

    public Date getFecha() {
        return fecha;
    }

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
