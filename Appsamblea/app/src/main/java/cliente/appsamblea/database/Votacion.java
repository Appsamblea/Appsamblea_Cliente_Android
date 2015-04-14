package cliente.appsamblea.database;

import java.util.ArrayList;

/**
 * Created by carlos on 15/03/15.
 */
public class Votacion {
    public ArrayList getOpciones() {
        return opciones;
    }

    public void setOpciones(ArrayList opciones) {
        this.opciones = opciones;
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    private String pregunta;
    private ArrayList opciones;

    public Votacion (String pregunta){
        this.pregunta = pregunta;
        opciones = new ArrayList();
    }

    /**
     * Añade una opción a la votación.
     * @param opcion opción a añadir.
     */
    public void addOpcion (String opcion){
        opciones.add(opcion);
    }

    /**
     * Elimina una opción de la votación.
     * @param posicion posición de la opción a eliminar.
     * @return si se ha eliminado o no.
     */
    public boolean quitarOpcion (int posicion){
        if (posicion >= opciones.size())
            return false;
        else opciones.remove(posicion);
        return true;
    }


}
