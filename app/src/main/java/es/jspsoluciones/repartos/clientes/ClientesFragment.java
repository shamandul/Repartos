package es.jspsoluciones.repartos.clientes;

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

import es.jspsoluciones.repartos.R;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ClientesFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class ClientesFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    private ImageButton botonVerCliente, botonAgregarCliente, botonEditarCliente, botonBorrarCliente;

    public ClientesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view= inflater.inflate(R.layout.fragment_clientes, container, false);

        botonVerCliente = (ImageButton) view.findViewById(R.id.botonVerClientes);
        botonAgregarCliente = (ImageButton) view.findViewById(R.id.botonAgregarClientes);
        botonEditarCliente=(ImageButton) view.findViewById(R.id.botonEditarClientes);
        botonBorrarCliente=(ImageButton) view.findViewById(R.id.botonBorrarclientes);

        botonVerCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction transation = fragmentManager.beginTransaction();
                MostrarDatosClientesFragment fragment = new MostrarDatosClientesFragment();
                transation.add(R.id.clientesfragment, fragment);
                transation.addToBackStack(null);
                transation.commit();

                botonVerCliente.setClickable(false);
                botonAgregarCliente.setEnabled(false);
                botonBorrarCliente.setEnabled(false);
                botonEditarCliente.setEnabled(false);
            }
        });

        botonAgregarCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction transation = fragmentManager.beginTransaction();
                IntroducirDatosClientesFragment fragment = new IntroducirDatosClientesFragment();
                transation.add(R.id.clientesfragment, fragment);
                transation.addToBackStack(null);
                transation.commit();

                botonAgregarCliente.setClickable(false);
                botonVerCliente.setEnabled(false);
                botonBorrarCliente.setEnabled(false);
                botonEditarCliente.setEnabled(false);
            }
        });

        botonEditarCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction transation = fragmentManager.beginTransaction();
                EditarDatosClientesFragment fragment = new EditarDatosClientesFragment();
                transation.add(R.id.clientesfragment, fragment);
                transation.addToBackStack(null);
                transation.commit();

                botonEditarCliente.setClickable(false);

                botonVerCliente.setEnabled(false);
                botonAgregarCliente.setEnabled(false);
                botonBorrarCliente.setEnabled(false);
            }
        });

        botonBorrarCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction transation = fragmentManager.beginTransaction();
                BorrarDatosClientesFragment fragment = new BorrarDatosClientesFragment();
                transation.add(R.id.clientesfragment, fragment);
                transation.addToBackStack(null);
                transation.commit();

                botonBorrarCliente.setClickable(false);

                botonVerCliente.setEnabled(false);
                botonAgregarCliente.setEnabled(false);
                botonEditarCliente.setEnabled(false);
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
