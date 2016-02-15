package es.jspsoluciones.repartos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import java.sql.SQLException;

/**
 * Created by jesus on 11/02/16.
 */
public class RepartosDBAdapter {
    public final String CAMPO_IDZONA = "_idZona";
    public final String CAMPO_NOMBREZONA = "nombreZona";
    private static final String TABLA_BD = "zonas";
    private final Context context;
    private SQLiteDatabase basedatos;
    private RepartosSQLiteHelper bdHelper;

    /**
     * Constructor
     * @param context
     */
    public RepartosDBAdapter(Context context) {
        this.context=context;
    }



    /**
     * Método que abre la base de datos
     * @return this
     * @throws SQLException
     */
    public RepartosDBAdapter abrir()throws SQLException{
        // Abrimos la base de datos en modo escritura
        bdHelper = new RepartosSQLiteHelper(context);
        basedatos = bdHelper.getReadableDatabase();
        return this;
    }

    /**
     * Método que cierra la base de datos
     */
    public void cerrar(){
        bdHelper.close();
    }

    /**
     * Método que nos permite obtener los registro de la tabla y nos devuelve
     * un cursor
     * @return
     */
    public Cursor obtenerZonas(){

        return basedatos.query(TABLA_BD, new String[]{getCampoId(), CAMPO_NOMBREZONA}, null, null, null, null, null);

    }

    /**
     * Método que nos devuelve la id de la zona
     * @return
     */
    public  String getCampoId() {
        return CAMPO_IDZONA;
    }

    public void insertarZona(String nombreZona){

        String sentencia="INSERT INTO zonas(_idZona,nombreZona) VALUES('"+getCampoId()+"','" + nombreZona + "')";
        ContentValues datos = new ContentValues();
        datos.put(CAMPO_NOMBREZONA, nombreZona);
        basedatos.insert(TABLA_BD, null, datos);

    }

    public static boolean existeBD(){
        SQLiteDatabase sqliteDB = null;
        String myPath ="/data/data/es.jspsoluciones.repartos/databases/RepartosDB.db";
        try{
            sqliteDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        }catch(SQLiteException e){
            //si llegamos aqui es porque la base de datos no existe todavía.
        }
        if(sqliteDB != null){
            sqliteDB.close();
        }
        return sqliteDB != null ? true : false;
    }
    /**
     * Metodo que nos permite borrar una Zona
     * @param id
     */
    public void borrarZona(int id){

        basedatos.delete(TABLA_BD,"_idZona="+id, null);

    }
}
