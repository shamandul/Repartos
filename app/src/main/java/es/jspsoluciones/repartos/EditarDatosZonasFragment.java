package es.jspsoluciones.repartos;

import android.app.Fragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link EditarDatosZonasFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class EditarDatosZonasFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    ImageButton botonVerZonas, botonAgregarZona, botonEditarZona, botonBorrarZona;
    FloatingActionButton volver;

    public EditarDatosZonasFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_editar_datos_zonas, container, false);
        volver = (FloatingActionButton) getActivity().findViewById(R.id.fab);
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