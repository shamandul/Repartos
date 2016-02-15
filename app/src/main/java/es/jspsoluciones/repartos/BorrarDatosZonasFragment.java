package es.jspsoluciones.repartos;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.sql.SQLException;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link BorrarDatosZonasFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class BorrarDatosZonasFragment extends Fragment implements AdapterView.OnItemLongClickListener {

    private OnFragmentInteractionListener mListener;
    private ImageButton botonVerZonas, botonAgregarZona, botonEditarZona, botonBorrarZona;
    private FloatingActionButton volver;
    private ListView listaElement;
    private RepartosDBAdapter db;
    private Cursor cursor;
    private ArrayList<Zonas> listaRegistros= new ArrayList<>();
    private Zonas zonas;
    private AdaptadorZonas adaptador=null;
    private Context context;
    private int idElemento;

    public BorrarDatosZonasFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_borrar_datos_zonas, container, false);

        volver = (FloatingActionButton) getActivity().findViewById(R.id.fab);
        listaElement = (ListView) view.findViewById(R.id.listaElementoZonaBorrar);
        context=view.getContext();
        leerBD(context);
        //Llenamos el adaptador
        adaptador = new AdaptadorZonas(context,listaRegistros);
        listaElement.setAdapter(adaptador);
        listaElement.setOnItemLongClickListener(this);
        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                botonVerZonas=(ImageButton) getActivity().findViewById(R.id.botonVerZonas);
                botonEditarZona=(ImageButton) getActivity().findViewById(R.id.botonEditarZonas);
                botonBorrarZona=(ImageButton) getActivity().findViewById(R.id.botonBorrarZonas);
                botonAgregarZona=(ImageButton) getActivity().findViewById(R.id.botonAgregarZonas);

                botonBorrarZona.setClickable(true);
                botonVerZonas.setEnabled(true);
                botonAgregarZona.setEnabled(true);
                botonEditarZona.setEnabled(true);

                getActivity().getFragmentManager().popBackStack();
            }
        });

        return view;
    }

    /**
     * Método que nos permite leer de la base de datos
     * @param context
     */
    private void leerBD(Context context){
        db = new RepartosDBAdapter(context);
        try {
            db.abrir();
            rellenarDatos();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        db.cerrar();
    }

    /**
     * Método que nos permite rellenar los datos en el arrayList
     */
    private void rellenarDatos(){
        cursor=db.obtenerZonas();
        listaRegistros.clear();

        getLoaderManager();
        //startManagingCursor(cursor);// con esto decimos que cierre el Cursor cuando cierre la app
        if(cursor.moveToFirst()){ //vemos si hay algun elemento el la tabla
            // Si hay elementos  en la tabla guarda el Campo Nombre en el arrayList listado
            do{
                zonas = new Zonas();
                zonas.set_idZona(cursor.getInt(cursor.getColumnIndex(db.CAMPO_IDZONA)));
                zonas.setNombreZona(cursor.getString(cursor.getColumnIndex(db.CAMPO_NOMBREZONA)));
                listaRegistros.add(zonas);
            }while(cursor.moveToNext());
        }
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {

        void onFragmentInteraction(Uri uri);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(db!=null){
            db.cerrar();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        leerBD(context);
        adaptador.notifyDataSetChanged();
    }
    @Override
    /**
     * Método que nos permite controlar el click Largo en un elemento de la lista
     */
    public boolean onItemLongClick(AdapterView<?> arg0, View vista, int posicion,
                                   long arg3) {
        idElemento = listaRegistros.get(posicion).get_idZona();
        Dialog dialogoBorrar = null;
        dialogoBorrar = dialogo(context);
        dialogoBorrar.show();

        return true;
    }
    private void borrarElemento(Context context){
        db = new RepartosDBAdapter(context);
        try {
            db.abrir();
            db.borrarZona(idElemento);
        }catch (SQLException e){
            e.printStackTrace();
        }
        db.cerrar();

        //Toast.makeText(context, R.string.txt_item_borrado, Toast.LENGTH_SHORT).show();
        Snackbar.make(getView(), "Se ha borrado la Zona correctamente", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
        leerBD(context);
        adaptador.notifyDataSetChanged();
    }
    private Dialog dialogo(final Context context){

        AlertDialog.Builder alerta = new AlertDialog.Builder(context);
        alerta.setTitle(R.string.txt_titulo_alerta);
        alerta.setMessage(R.string.txt_texto_alerta);
        alerta.setPositiveButton(R.string.txt_btn_aceptar, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                borrarElemento(context);
            }
        });
        alerta.setNegativeButton(R.string.txt_boton_no, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {


            }
        });
        return alerta.create();
    }

}
