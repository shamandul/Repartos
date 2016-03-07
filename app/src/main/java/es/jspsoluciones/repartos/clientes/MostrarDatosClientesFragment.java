package es.jspsoluciones.repartos.clientes;

import android.app.Fragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import es.jspsoluciones.repartos.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MostrarDatosClientesFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class MostrarDatosClientesFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    private FloatingActionButton volver;
    private ImageButton botonVerCliente, botonAgregarCliente, botonEditarCliente, botonBorrarCliente;

    public MostrarDatosClientesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mostrar_datos_clientes, container, false);
        volver = (FloatingActionButton) getActivity().findViewById(R.id.fab);
        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                botonVerCliente=(ImageButton) getActivity().findViewById(R.id.botonVerClientes);
                botonAgregarCliente=(ImageButton) getActivity().findViewById(R.id.botonAgregarClientes);
                botonEditarCliente=(ImageButton) getActivity().findViewById(R.id.botonEditarClientes);
                botonBorrarCliente=(ImageButton) getActivity().findViewById(R.id.botonBorrarclientes);

                botonVerCliente.setClickable(true);
                botonAgregarCliente.setEnabled(true);
                botonEditarCliente.setEnabled(true);
                botonBorrarCliente.setEnabled(true);
                getActivity().getFragmentManager().popBackStack();
            }
        });
        return  view;
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
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {

        void onFragmentInteraction(Uri uri);
    }
}
