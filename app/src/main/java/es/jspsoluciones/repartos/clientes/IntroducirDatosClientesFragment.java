package es.jspsoluciones.repartos.clientes;

import android.app.Fragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import es.jspsoluciones.repartos.R;
import es.jspsoluciones.repartos.dbsqlite.RepartosDBAdapter;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link IntroducirDatosClientesFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class IntroducirDatosClientesFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    ImageButton botonVerCliente, botonAgregarCliente, botonEditarCliente, botonBorrarCliente;
    FloatingActionButton volver;
    Button guardar;
    EditText nombreZona;
    Context context;
    RepartosDBAdapter db;

    public IntroducirDatosClientesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        final View view=inflater.inflate(R.layout.fragment_introducir_datos_clientes, container, false);
        volver = (FloatingActionButton) getActivity().findViewById(R.id.fab);
        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                botonVerCliente=(ImageButton) getActivity().findViewById(R.id.botonVerClientes);
                botonEditarCliente=(ImageButton) getActivity().findViewById(R.id.botonEditarClientes);
                botonBorrarCliente=(ImageButton) getActivity().findViewById(R.id.botonBorrarclientes);
                botonAgregarCliente=(ImageButton) getActivity().findViewById(R.id.botonAgregarClientes);

                botonAgregarCliente.setClickable(true);
                botonVerCliente.setEnabled(true);
                botonEditarCliente.setEnabled(true);
                botonBorrarCliente.setEnabled(true);

                getActivity().getFragmentManager().popBackStack();
            }
        });
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
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
}
