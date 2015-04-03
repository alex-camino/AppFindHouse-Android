package com.FindHouse;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import com.FindHouse.R;

public class PantallaBienvenida extends Activity {


    private final int DURACION_SPLASH = 3000; // 3 segundos

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_bienvenida);

        mostrarMensaje("Cargando Aplicación.....");
        new Handler().postDelayed(new Runnable(){
            public void run(){
                // Cuando pasen los 3 segundos, pasamos a la actividad principal de la aplicación
                Intent intent = new Intent(PantallaBienvenida.this, PantallaPrincipal.class);
                startActivity(intent);
                finish();
            };
        }, DURACION_SPLASH);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_pantalla_bienvenida, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void mostrarMensaje(String mensaje){

        Context context = getApplicationContext();
        CharSequence textoMensaje=mensaje;
        int duration = Toast.LENGTH_SHORT;

        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.toast_mensaje_informa,
                (ViewGroup) findViewById(R.id.toast_mensaje));

        TextView textToast = (TextView) layout.findViewById(R.id.text_toast);
        textToast.setText(textoMensaje);

        Toast alerta = new Toast(context);
        alerta.setDuration(duration);
        alerta.setView(layout);
        alerta.show();
    }
}
