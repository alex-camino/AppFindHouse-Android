package com.FindHouse;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.concurrent.ThreadPoolExecutor;

public class PantallaPrincipal extends Activity {
	
	Spinner ListaOperaciones, ListaLocalidad, ListaPrecio, ListaTipo;
    Button botonBuscar;
    TextView minPrice, maxPrice;
    ToggleButton toggleOperacion;
    RangeSeekBar<Integer> seekBar;

    /*
    * POSICION 0: Operacion
    * POSICION 1: Tipo Inmueble
    * POSICION 2: Localidad
    * POSICION 3: Precio Mínimo
    * POSICION 4: Precio Máximo
    * */
    String[] valoresSeleccionados = new String[5];

    @Override
    public void onCreate(Bundle savedInstanceState) {
        
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        minPrice = (TextView) findViewById(R.id.minPrice);
        maxPrice = (TextView) findViewById(R.id.maxPrice);

        toggleOperacion = (ToggleButton) findViewById(R.id.toggleButtonOperacion);
        if(!toggleOperacion.isChecked())
            valoresSeleccionados[0]="alquiler";

        //Llamada a los metodos para crear los componentes.
        crearRangoPrecio();
        crearSpinners();

        botonBuscar=(Button) findViewById(R.id.btnBuscar);
    }

    public void onClickBuscar(View view){

        if(valoresSeleccionados[1].equals("Seleccione el Inmueble")&&valoresSeleccionados[2].equals("Seleccione la localidad")){

            mostrarMensaje("Seleccione los paramatros de búsqueda!");

        }else if(valoresSeleccionados[1].equals("Seleccione el Inmueble")) {

            mostrarMensaje("Seleccione el inmueble!");

        }else if(valoresSeleccionados[2].equals("Seleccione la localidad")) {

            mostrarMensaje("Seleccione la localidad!");

        }else{

            if(verificarConexion(this)){

                /*
                Intent i = new Intent(PantallaPrincipal.this,PantallaWebview.class);
                i.putExtra("operacion",valoresSeleccionados[0]);
                i.putExtra("inmueble",valoresSeleccionados[1]);
                i.putExtra("localidad",valoresSeleccionados[2]);
                i.putExtra("precioMin",valoresSeleccionados[3]);
                i.putExtra("precioMax",valoresSeleccionados[4]);
                startActivity(i);
                */
                mostrarMensaje(valoresSeleccionados[0]+valoresSeleccionados[1]+
                               valoresSeleccionados[2]+valoresSeleccionados[3]+
                               valoresSeleccionados[4]);
            }else{

                mostrarMensaje("No tienes conexión a Internet!");
            }


        }

    }

    public void mostrarMensaje(String mensaje){

        Context context = getApplicationContext();
        CharSequence textoMensaje=mensaje;
        int duration = Toast.LENGTH_SHORT;

        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.custom_toast,
                (ViewGroup) findViewById(R.id.toast_mensaje));

        TextView textToast = (TextView) layout.findViewById(R.id.text_toast);
        textToast.setText(textoMensaje);

        Toast alerta = new Toast(context);
        alerta.setDuration(duration);
        alerta.setView(layout);
        alerta.show();
    }

    public boolean verificarConexion(Context ctx) {

        boolean conectado = false;
        ConnectivityManager connec = (ConnectivityManager) ctx
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        // Verificar tanto wifi como GPRS
        NetworkInfo[] redes = connec.getAllNetworkInfo();

        for (int i = 0; i < 2; i++) {


            if (redes[i].getState() == NetworkInfo.State.CONNECTED) {
                conectado = true;
            }
        }

        return conectado;
    }

    public void crearSpinners(){

        //ESPINER TIPOS
        ListaTipo = (Spinner) findViewById(R.id.spinTipo);
        ListaTipo.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                // TODO Auto-generated method stub

                //Toast.makeText(PantallaPrincipal.this, ListaTipo.getSelectedItem().toString(), Toast.LENGTH_LONG).show();
                valoresSeleccionados[1]=ListaTipo.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
          }
        );

        //ESPINER LOCALIDADES
        ListaLocalidad = (Spinner) findViewById(R.id.spinLocalidad);
        ListaLocalidad.setOnItemSelectedListener(new OnItemSelectedListener() {
             @Override
             public void onItemSelected(AdapterView<?> arg0, View arg1,
                                        int arg2, long arg3) {
                 // TODO Auto-generated method stub

                 //Toast.makeText(PantallaPrincipal.this, ListaLocalidad.getSelectedItem().toString(), Toast.LENGTH_LONG).show();
                 valoresSeleccionados[2]=ListaLocalidad.getSelectedItem().toString();
             }

             @Override
             public void onNothingSelected(AdapterView<?> arg0) {
                 // TODO Auto-generated method stub
             }
           }
        );
    }

    public void onToggleClicked(View v){

        if(toggleOperacion.isChecked())
            valoresSeleccionados[0]="venta";
        else
            valoresSeleccionados[0]="alquiler";

    }

    public void crearRangoPrecio(){

        int startValue = 1000;
        int endValue = 500000;
        final int factor = 1000;
        RangeSeekBar<Integer> seekBar = new RangeSeekBar<Integer>(startValue/factor, endValue/factor, this);
        seekBar.setOnRangeSeekBarChangeListener(new RangeSeekBar.OnRangeSeekBarChangeListener<Integer>() {
            @Override
            public void onRangeSeekBarValuesChanged(RangeSeekBar<?> bar, Integer minValue, Integer maxValue) {

                minValue = minValue*factor;
                maxValue *= factor;


                if(minValue==1000){
                    minPrice.setText("-"+minValue+" €");
                    valoresSeleccionados[3]=Integer.toString(minValue);
                }else{
                    minPrice.setText(minValue+" €");
                    valoresSeleccionados[3]=Integer.toString(minValue);
                }


                if(maxValue==500000) {
                    maxPrice.setText("+" + maxValue + " €");
                    valoresSeleccionados[4]=Integer.toString(maxValue);
                }else{

                    maxPrice.setText(maxValue + " €");
                    valoresSeleccionados[4]=Integer.toString(maxValue);

                }





                // mostrarMensaje("MIN=" + minValue + ", MAX=" + maxValue);
            }
        });

        seekBar.setNotifyWhileDragging(true);
        // add RangeSeekBar to pre-defined layout
        ViewGroup layout = (ViewGroup) findViewById(R.id.layoutRange);
        layout.addView(seekBar);

    }
}