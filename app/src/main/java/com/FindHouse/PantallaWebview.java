package com.FindHouse;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.FindHouse.R;

public class PantallaWebview extends Activity {

    private WebView webView1;
    /*
    * POSICION 0: Operacion
    * POSICION 1: Tipo Inmueble
    * POSICION 2: Localidad
    * POSICION 3: Precio
    * */
    String[] valoresRecogidos = new String[4];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);

        //Obtenemos los valores que nos llegan de la Clase Principal.
        rellenarValores();

        webView1 = (WebView) findViewById(R.id.webView);
        webView1.setWebViewClient(new WebViewClient());
        webView1.getSettings().setJavaScriptEnabled(true);

        //Obtenemos la url llamando al metodo "crearURL" y luego la cargamos en el webview.
        webView1.loadUrl(crearURL(valoresRecogidos));

    }

    private void rellenarValores() {

        valoresRecogidos[0]=getIntent().getStringExtra("operacion");
        valoresRecogidos[1]=getIntent().getStringExtra("inmueble");
        valoresRecogidos[2]=getIntent().getStringExtra("localidad");
        valoresRecogidos[3]=getIntent().getStringExtra("precio");
    }

    private String crearURL(String[] valoresParametros){

        String url= "http://miprimerapp-alexcamino.rhcloud.com/buysalerent.php?";
        url.concat("operacion="+valoresParametros[0]+",");
        url.concat("inmueble="+valoresParametros[1]+",");
        url.concat("localidad="+valoresParametros[2]+",");
        url.concat("precio="+valoresParametros[3]+",");

        for(int i=0;i<valoresParametros.length;i++){

            System.out.println(valoresParametros[i]);
        }

        return url;
    }


    /*
    @Override public void onBackPressed() {
        if(webView1.canGoBack())
            webView1.goBack();
        else
            super.onBackPressed();
    }*/

    @Override public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(event.getAction() == KeyEvent.ACTION_DOWN){
            switch(keyCode) {
                case KeyEvent.KEYCODE_BACK:
                    if(webView1.canGoBack()){
                        webView1.goBack();
                    }else{
                        finish();
                    }
                return true;
            }
        } return super.onKeyDown(keyCode, event);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_webview, menu);
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
}
