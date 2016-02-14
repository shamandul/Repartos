package es.jspsoluciones.repartos;

import android.app.Fragment;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;

import java.sql.SQLException;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link EditarDatosZonasFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class EditarDatosZonasFragment extends Fragment {

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

    public EditarDatosZonasFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_editar_datos_zonas, container, false);
        volver = (FloatingActionButton) getActivity().findViewById(R.id.fab);
        listaElement = (ListView) view.findViewById(R.id.listaElementoZonaEditar);
        context=view.getContext();
        leerBD(context);
        //Llenamos el adaptador
        adaptador = new AdaptadorZonas(context,listaRegistros);
        listaElement.setAdapter(adaptador);
        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                botonVerZonas=(ImageButton) getActivity().findViewById(R.id.botonVerZonas);
                botonEditarZona=(ImageButton) getActivity().findViewById(R.id.botonEditarZonas);
                botonBorrarZona=(ImageButton) getActivity().findViewById(R.id.botonBorrarZonas);
                botonAgregarZona=(ImageButton) getActivity().findViewById(R.id.botonAgregarZonas);

                botonEditarZona.setClickable(true);
                botonVerZonas.setEnabled(true);
                botonAgregarZona.setEnabled(true);
                botonBorrarZona.setEnabled(true);

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
            db.cerrar();
        } catch (SQLException e) {
            e.printStackTrace();
        }

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
                //zonas.set_idZona(cursor.getInt(cursor.getColumnIndex(db.)));
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
}
