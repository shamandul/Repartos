package es.jspsoluciones.repartos.zonas;

/**
 * Created by jesus on 13/02/16.
 */
public class Zonas {
    private int _idZona;
    private String nombreZona;

    /**
     * Obtener _idZona
     * @return String
     */
    public int get_idZona() {
        return _idZona;
    }

    /**
     * Agregar el _idZona
     * @param _idZona tipo int
     */
    public void set_idZona(int _idZona) {
        this._idZona = _idZona;
    }

    /**
     * Obtener el nombreZona
     * @return String
     */
    public String getNombreZona() {
        return nombreZona;
    }

    /**
     * Agregar el nombreZona
     * @param nombreZona tipo String
     */
    public void setNombreZona(String nombreZona) {
        this.nombreZona = nombreZona;
    }
}
