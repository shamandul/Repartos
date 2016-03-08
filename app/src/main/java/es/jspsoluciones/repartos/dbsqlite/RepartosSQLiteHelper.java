package es.jspsoluciones.repartos.dbsqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by jesus on 11/02/16.
 */
public class RepartosSQLiteHelper extends SQLiteOpenHelper {

    private static final String DB_NOMBRE = "RepartosDB.db";
    private static final int DB_VERSION = 1;
    private static final String BD_CREAR_ZONAS = "CREATE TABLE zonas(_idZona INTEGER PRIMARY KEY AUTOINCREMENT, nombreZona TEXT NOT NULL)";
    private static final String BD_CREAR_CLIENTES = "CREATE TABLE clientes(_idCliente integer primary key AUTOINCREMENT," +
            "nombreCliente varchar(100), contacto varchar(200)," +
            "direccionCliente varchar(200),telefono varchar(45)," +
            "email varchar(150))";

    private static final String BD_CREAR_PEDIDOS="CREATE TABLE pedidos(_idpedido integer primary key AUTOINCREMENT," +
            "fechaEntrega date, fechaCobro date," +
            "is_pagado boolean, precioTotal decimal, firma blob)";
    private static final String BD_CREAR_PRODUCTOS="CREATE TABLE productos(_idproducto integer primary key AUTOINCREMENT," +
            "nombreProducto varchar(200), descripcion varchar(250)," +
            "cantidad integer, precio decimal)";
    private static final String BD_CREAR_TIENE="";
    private static final String BD_CREAR_TRIGGERS_INSERCCION_ZONAS="CREATE TRIGGER fk_zonas_idzona before insert on clientes " +
            "for each row" +
            "begin" +
            "select raise(rollback,'Insercción en Clientes no permitida')" +
            "where(select _idZona from zonas where _idZona=new._idClientes) is null;" +
            "end;";

    public RepartosSQLiteHelper(Context context) {
        super(context, DB_NOMBRE, null, DB_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(BD_CREAR_ZONAS);
        db.execSQL(BD_CREAR_CLIENTES);
        db.execSQL(BD_CREAR_PEDIDOS);
        db.execSQL(BD_CREAR_PRODUCTOS);
        //db.execSQL(BD_CREAR_TIENE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Se elimina la versión anterior de la tabla
        db.execSQL("DROP TABLE IF EXISTS zonas");
        db.execSQL("DROP TABLE IF EXISTS clientes");
        db.execSQL("DROP TABLE IF EXISTS pedidos");
        db.execSQL("DROP TABLE IF EXISTS productos");
        // Se crea la nueva versión de la tabla
        onCreate(db);
    }
}
