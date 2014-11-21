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
import android.widget.Toast;

import com.example.arango.metodos.Metodos.EcNoLineales;

import org.w3c.dom.Text;

/**
 * Created by Felipe on 18/11/2014.
 */
public class NoLinealesActivity extends Activity {
    private static int metodo_a_usar;
    private EditText edtxtFuncion, edtxtValorInicial, edtxtDelta, edtxtIteraciones, edtxtAd1 ,edtxtTol, edtxtAd3;
    private TextView txtResult, txtColumName;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nolineales);

        metodo_a_usar = -1;
        txtColumName = (TextView) findViewById(R.id.txtColumName);
        edtxtFuncion = (EditText) findViewById(R.id.edtxtNoLnFuncion);
        edtxtValorInicial = (EditText) findViewById(R.id.editTextValorInicial);
        edtxtDelta = (EditText) findViewById(R.id.editTextDelta);
        edtxtIteraciones = (EditText) findViewById(R.id.editTextIteraciones);
        edtxtAd1 = (EditText) findViewById(R.id.edtxtAd1);
        edtxtTol = (EditText) findViewById(R.id.edtxtAd2);
        edtxtAd3 = (EditText) findViewById(R.id.edtxtAd3);
        txtResult = (TextView) findViewById(R.id.txtNoLinealesResult);

        Spinner spinner = (Spinner) findViewById(R.id.spinnerNoLineales);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.noLineales, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                metodo_a_usar = i;
                edtxtAd1.setText("");
                edtxtTol.setText("");
                edtxtAd3.setText("");
                edtxtValorInicial.setText("");
                edtxtDelta.setText("");
                edtxtIteraciones.setText("");
                switch (i){
                    case 0:
                        txtColumName.setText(" Xi  F(Xi)");
                        edtxtAd1.setVisibility(View.GONE);
                        edtxtTol.setVisibility(View.GONE);
                        edtxtAd3.setVisibility(View.GONE);
                        edtxtDelta.setVisibility(View.VISIBLE);
                        break;
                    case 1:
                        txtColumName.setText(" N  Xinf  Xsup  Xm  F(Xm)   Error");
                        edtxtAd1.setHint("Valor Siguiente");
                        edtxtTol.setHint("Tolerancia");
                        edtxtAd3.setVisibility(View.GONE);
                        edtxtDelta.setVisibility(View.GONE);
                        edtxtAd1.setVisibility(View.VISIBLE);
                        edtxtTol.setVisibility(View.VISIBLE);
                        break;
                    case 2:
                        txtColumName.setText(" N  Xinf  Xsup  Xm  F(Xm)   Error");
                        edtxtAd1.setHint("Valor Siguiente");
                        edtxtTol.setHint("Tolerancia");
                        edtxtAd3.setVisibility(View.GONE);
                        edtxtDelta.setVisibility(View.GONE);
                        edtxtAd1.setVisibility(View.VISIBLE);
                        edtxtTol.setVisibility(View.VISIBLE);
                        break;
                    case 3:
                        txtColumName.setText(" N  Xn  F(Xn)  Error");
                        edtxtAd1.setHint("g(x)");
                        edtxtTol.setHint("Tolerancia");
                        edtxtDelta.setVisibility(View.GONE);
                        edtxtAd3.setVisibility(View.GONE);
                        edtxtAd1.setVisibility(View.VISIBLE);
                        edtxtTol.setVisibility(View.VISIBLE);
                        break;
                    case 4:
                        txtColumName.setText(" N  Xn  F(Xn)  Error");
                        edtxtAd1.setHint("f'(x)");
                        edtxtTol.setHint("Tolerancia");
                        edtxtDelta.setVisibility(View.GONE);
                        edtxtAd3.setVisibility(View.GONE);
                        edtxtAd1.setVisibility(View.VISIBLE);
                        edtxtTol.setVisibility(View.VISIBLE);
                        break;
                    case 5:
                        txtColumName.setText(" N  Xn  F(Xn)  Error");
                        edtxtAd1.setHint("Valor Siguiente");
                        edtxtTol.setHint("Tolerancia");
                        edtxtAd3.setVisibility(View.GONE);
                        edtxtDelta.setVisibility(View.GONE);
                        edtxtAd1.setVisibility(View.VISIBLE);
                        edtxtTol.setVisibility(View.VISIBLE);
                        break;
                    case 6:
                        txtColumName.setText(" N  Xn  F(Xn)  F'(Xn)  F''(Xn)  Error");
                        edtxtAd1.setHint("f'(x)");
                        edtxtAd3.setHint("f''(x)");
                        edtxtTol.setHint("Tolerancia");
                        edtxtDelta.setVisibility(View.GONE);
                        edtxtAd1.setVisibility(View.VISIBLE);
                        edtxtTol.setVisibility(View.VISIBLE);
                        edtxtAd3.setVisibility(View.VISIBLE);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        Button btnCalcular = (Button) findViewById(R.id.buttonCalcNoLin);
        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(metodo_a_usar < 0){
                    Toast.makeText(getApplicationContext(),"Seleccione un método ", Toast.LENGTH_SHORT).show();
                }else{
                    try {
                        EcNoLineales ecunl = new EcNoLineales(edtxtFuncion.getText().toString().trim());
                        double x0 = Double.parseDouble(edtxtValorInicial.getText().toString().trim());
                        int iter = Integer.parseInt(edtxtIteraciones.getText().toString().trim());
                        double[][] tabla;
                        String result;
                        switch (metodo_a_usar) {
                            case 0:
                                ecunl.busquedas(x0, Double.parseDouble(edtxtDelta.getText().toString().trim()), iter);
                                tabla = ecunl.getTabla();
                                result = getResult(tabla);
                                txtResult.setText(result);
                                break;
                            case 1:
                                ecunl.biseccion(x0, Double.parseDouble(edtxtAd1.getText().toString()), Double.parseDouble(edtxtTol.getText().toString()), iter);
                                tabla = ecunl.getTabla();
                                result = getResult(tabla);
                                txtResult.setText(result);
                                break;
                            case 2:
                                ecunl.reglaFalsa(x0, Double.parseDouble(edtxtAd1.getText().toString()), Double.parseDouble(edtxtTol.getText().toString()), iter);
                                tabla = ecunl.getTabla();
                                result = getResult(tabla);
                                txtResult.setText(result);
                                break;
                            case 3:
                                ecunl.setgx( edtxtAd1.getText().toString());
                                ecunl.puntoFijo(x0, Double.parseDouble(edtxtTol.getText().toString()), iter);
                                tabla = ecunl.getTabla();
                                result = getResult(tabla);
                                txtResult.setText(result);
                                break;
                            case 4:
                                ecunl.setdfx( edtxtAd1.getText().toString());
                                //x0 | f'(x) | tol | iter
                                ecunl.newton(x0,  Double.parseDouble(edtxtTol.getText().toString()) ,iter);
                                tabla = ecunl.getTabla();
                                result = getResult(tabla);
                                txtResult.setText(result);
                                break;
                            case 5:
                                ecunl.secante(x0,  Double.parseDouble(edtxtAd1.getText().toString()), Double.parseDouble(edtxtTol.getText().toString()), iter);
                                tabla = ecunl.getTabla();
                                result = getResult(tabla);
                                txtResult.setText(result);
                                break;
                            case 6:
                                ecunl.setdfx( edtxtAd1.getText().toString());
                                ecunl.setddfx( edtxtAd3.getText().toString());
                                ecunl.raicesMulti(x0,  Double.parseDouble(edtxtTol.getText().toString()) ,iter);
                                tabla = ecunl.getTabla();
                                result = getResult(tabla);
                                txtResult.setText(result);
                                break;
                        }
                    }catch (Exception e){
                        Log.e("Error ", e.toString());}
                }
            }
        });
    }
    public String getResult(double [][] tabla){
        String result = "";
        int counter = 0;
        for(int fila = 0; fila < tabla.length; fila++){
            for(int columna = 0; columna < tabla[0].length; columna++){
                result += "   "+String.valueOf(tabla[fila][columna]);
            }
            counter++;
            result += "\n";
        }
        Toast.makeText(getBaseContext(),"Num Filas"+counter, Toast.LENGTH_LONG).show();
        return result;
    }
}
