package es.jspsoluciones.repartos;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link zonasFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class zonasFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    ImageButton botonVerZonas, botonAgregarZonas, botonEditarZona, botonBorrarZona;

    public zonasFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_zonas, container, false);

        botonVerZonas = (ImageButton) view.findViewById(R.id.botonVerZonas);
        botonAgregarZonas = (ImageButton) view.findViewById(R.id.botonAgregarZonas);
        botonEditarZona=(ImageButton) view.findViewById(R.id.botonEditarZonas);
        botonBorrarZona=(ImageButton) view.findViewById(R.id.botonBorrarZonas);

        botonVerZonas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction transation = fragmentManager.beginTransaction();
                MostrarDatosZonasFragment fragment = new MostrarDatosZonasFragment();
                transation.add(R.id.zonasfragment, fragment);
                transation.addToBackStack(null);
                transation.commit();

                botonVerZonas.setClickable(false);

                botonAgregarZonas.setEnabled(false);
                botonBorrarZona.setEnabled(false);
                botonEditarZona.setEnabled(false);
            }
        });

        botonAgregarZonas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction transation = fragmentManager.beginTransaction();
                IntroducirDatosZonasFragment fragment = new IntroducirDatosZonasFragment();
                transation.add(R.id.zonasfragment, fragment);
                transation.addToBackStack(null);
                transation.commit();

                botonAgregarZonas.setClickable(false);

                botonVerZonas.setEnabled(false);
                botonBorrarZona.setEnabled(false);
                botonEditarZona.setEnabled(false);
            }
        });

        botonEditarZona.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction transation = fragmentManager.beginTransaction();
                EditarDatosZonasFragment fragment = new EditarDatosZonasFragment();
                transation.add(R.id.zonasfragment, fragment);
                transation.addToBackStack(null);
                transation.commit();

                botonEditarZona.setClickable(false);

                botonVerZonas.setEnabled(false);
                botonAgregarZonas.setEnabled(false);
                botonBorrarZona.setEnabled(false);
            }
        });

        botonBorrarZona.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction transation = fragmentManager.beginTransaction();
                BorrarDatosZonasFragment fragment = new BorrarDatosZonasFragment ();
                transation.add(R.id.zonasfragment, fragment);
                transation.addToBackStack(null);
                transation.commit();

                botonBorrarZona.setClickable(false);

                botonVerZonas.setEnabled(false);
                botonAgregarZonas.setEnabled(false);
                botonEditarZona.setEnabled(false);
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
