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

public class PantallaPrincipal extends Activity {
	
	Spinner ListaOperaciones, ListaLocalidad, ListaPrecio, ListaTipo;
    Button botonBuscar;
    /*
    * POSICION 0: Operacion
    * POSICION 1: Tipo Inmueble
    * POSICION 2: Localidad
    * POSICION 3: Precio
    * */
    String[] valoresSeleccionados = new String[4];

    @Override
    public void onCreate(Bundle savedInstanceState) {
        
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.main);


        //ESPINER OPERACIONES
        ListaOperaciones = (Spinner) findViewById(R.id.spinOperacion);
        ListaOperaciones.setOnItemSelectedListener(new OnItemSelectedListener() {

                @Override
                public void onItemSelected(AdapterView<?> arg0, View arg1,
                                           int arg2, long arg3) {
                    // TODO Auto-generated method stub

                    //Toast.makeText(PantallaPrincipal.this, ListaOperaciones.getSelectedItem().toString(), Toast.LENGTH_LONG).show();
                    valoresSeleccionados[0]=ListaOperaciones.getSelectedItem().toString();
                }

                @Override
                public void onNothingSelected(AdapterView<?> arg0) {
                    // TODO Auto-generated method stub
                }

            }

        );


        //ESPINER OPERACIONES
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

        //ESPINER PRECIO
        ListaPrecio = (Spinner) findViewById(R.id.spinPrecio);
        ListaPrecio.setOnItemSelectedListener(new OnItemSelectedListener() {

               @Override
               public void onItemSelected(AdapterView<?> arg0, View arg1,
                                          int arg2, long arg3) {
                   // TODO Auto-generated method stub

                   //Toast.makeText(PantallaPrincipal.this, ListaPrecio.getSelectedItem().toString(), Toast.LENGTH_LONG).show();
                   valoresSeleccionados[3]=ListaPrecio.getSelectedItem().toString();

               }

               @Override
               public void onNothingSelected(AdapterView<?> arg0) {
                   // TODO Auto-generated method stub
               }

           }

        );


        botonBuscar=(Button) findViewById(R.id.btnBuscar);
    }

    public void onClickBuscar(View view){

        if(valoresSeleccionados[0].equals("Seleccione la operación")&&valoresSeleccionados[1].equals("Seleccione el Inmueble")
           &&valoresSeleccionados[2].equals("Seleccione la localidad")&&valoresSeleccionados[3].equals("Seleccione el rango de precios")){

            mostrarMensaje("Seleccione los paramatros de búsqueda!");

        }else if(valoresSeleccionados[0].equals("Seleccione la operación")) {

            mostrarMensaje("Seleccione la operacion!");

        }else if(valoresSeleccionados[1].equals("Seleccione el Inmueble")) {

            mostrarMensaje("Seleccione el inmueble!");

        }else if(valoresSeleccionados[2].equals("Seleccione la localidad")) {

            mostrarMensaje("Seleccione la localidad!");

        }else if(valoresSeleccionados[3].equals("Seleccione el rango de precios")) {

            mostrarMensaje("Seleccione el precio!");

        }else{

            if(verificarConexion(this)){

                Intent i = new Intent(PantallaPrincipal.this,PantallaWebview.class);
                i.putExtra("operacion",valoresSeleccionados[0]);
                i.putExtra("inmueble",valoresSeleccionados[1]);
                i.putExtra("localidad",valoresSeleccionados[2]);
                i.putExtra("precio",valoresSeleccionados[3]);
                startActivity(i);

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

    public static boolean verificarConexion(Context ctx) {

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
}