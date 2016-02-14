package es.jspsoluciones.repartos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by jesus on 13/02/16.
 */
public class AdaptadorZonas extends BaseAdapter{
    Context context;
    ArrayList<Zonas> zonas;

    public AdaptadorZonas(Context context,ArrayList<Zonas> zonas) {
        this.context = context;
        this.zonas = zonas;
    }

    @Override
    public int getCount() {
        return zonas.size();
    }

    @Override
    public Object getItem(int position) {
        return zonas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // creamos nuestra vista y la ponemos a null
        View vista= convertView;
        VistaTagZonas vistaTag;
		/*
		 * comprobamos si la vista es null en caso de que sea así nos estará diciendo que
		 * es la primera vez que creamos la vista con lo cual tendremos que inflarla
		 * en caso contrario quiere decir que la vista ya ha sido inflada con lo cual solo
		 * tendremos que pasarle el valor de view a nuestra vista
		 */
        if(vista==null){

            String infService= Context.LAYOUT_INFLATER_SERVICE;
            LayoutInflater li= (LayoutInflater) context.getSystemService(infService);
            vista = li.inflate(R.layout.elemento_zonas, null);
            // definimos el objeto que vamos a utilizar en nuestra vista
            vistaTag =new VistaTagZonas();
            // obtenemos el puntero de la etiqueta recien inflada
            vistaTag.nombreZona= (TextView) vista.findViewById(R.id.nombreZona);
            // guardamos el objeto
            vista.setTag(vistaTag);


        }else{
            //recuperamos el objeto
            vistaTag = (VistaTagZonas) vista.getTag();
        }

        vistaTag.nombreZona.setText(zonas.get(position).getNombreZona());
        // Devolvemos nuestra vista
        return vista;
    }
}
