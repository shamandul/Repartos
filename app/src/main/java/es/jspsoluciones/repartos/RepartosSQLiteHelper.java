package es.jspsoluciones.repartos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by jesus on 11/02/16.
 */
public class RepartosSQLiteHelper extends SQLiteOpenHelper {

    private static final String DB_NOMBRE = "RepartosDB.db";
    private static final int DB_VERSION = 1;
    private static final String BD_CREAR = "CREATE TABLE zonas(_idZona INTEGER PRIMARY KEY AUTOINCREMENT, nombreZona TEXT NOT NULL)";

    public RepartosSQLiteHelper(Context context) {
        super(context, DB_NOMBRE, null, DB_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(BD_CREAR);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Se elimina la versión anterior de la tabla
        db.execSQL("DROP TABLE IF EXISTS zonas");
        // Se crea la nueva versión de la tabla
        onCreate(db);
    }
}
