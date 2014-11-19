package com.example.arango.metodos;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.arango.metodos.Metodos.Integracion;

/**
 * Created by Felipe on 18/11/2014.
 */
public class IntegracionActivity extends Activity {
    private int metodo_a_usar;
    private EditText edtxtIntervalo, edtxtIntegFuncion, edtxtLimSup, edtxtLimInf;
    private TextView txtIntegResult;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_integracion);

        metodo_a_usar = -1;
        edtxtIntegFuncion = (EditText)findViewById(R.id.edtxtIntegFuncion);
        edtxtIntervalo = (EditText)findViewById(R.id.edtxtIntervalos);
        edtxtLimSup = (EditText) findViewById(R.id.edtxtLimSup);
        edtxtLimInf = (EditText) findViewById(R.id.edtxtLimInf);

        txtIntegResult = (TextView) findViewById(R.id.txtIntegResult);

        Spinner spinner = (Spinner) findViewById(R.id.spinnerIntegracion);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.integracion, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                 metodo_a_usar = i;
                switch (i){
                    case 0:
                        edtxtIntervalo.setVisibility(View.GONE);
                        break;
                    case 1:
                        edtxtIntervalo.setVisibility(View.VISIBLE);
                        break;
                    case 2:
                        edtxtIntervalo.setVisibility(View.GONE);
                        break;
                    case 3:
                        edtxtIntervalo.setVisibility(View.VISIBLE);
                        break;
                    case 4:
                        edtxtIntervalo.setVisibility(View.GONE);
                        break;
                    case 5:
                        edtxtIntervalo.setVisibility(View.VISIBLE);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        Button btnCalcular = (Button)findViewById(R.id.btnIntegracion);
        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    String resultado ="";
                    double x0 = Double.parseDouble(edtxtLimInf.getText().toString());
                    double xn = Double.parseDouble(edtxtLimSup.getText().toString());
                    Integracion integ = new Integracion(edtxtIntegFuncion.getText().toString().trim());
                    switch (metodo_a_usar){
                        case 0:
                            integ.trapecioSimple(x0,xn);
                            break;
                        case 1:
                            integ.trapecioCompuesto(x0, xn, Integer.parseInt(edtxtIntervalo.getText().toString()));
                            break;
                        case 2:
                            integ.simpson13(x0, xn);
                            break;
                        case 3:
                            integ.simpson13Compuesto(x0, xn,  Integer.parseInt(edtxtIntervalo.getText().toString()));
                            break;
                        case 4:
                            integ.simpson38(x0, xn);
                            break;
                        case 5:
                            integ.simpson38Compuesto(x0, xn,  Integer.parseInt(edtxtIntervalo.getText().toString()));
                            break;
                    }
                    resultado = integ.getRes();
                    txtIntegResult.setText(resultado);
                }catch(Exception e){
                    Log.e("Exception ", e.toString());}
            }
        });
    }
}
