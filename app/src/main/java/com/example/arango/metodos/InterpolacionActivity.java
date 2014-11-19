package com.example.arango.metodos;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

/**
 * Created by Felipe on 18/11/2014.
 */
public class InterpolacionActivity extends Activity{
    private TableLayout tblInterpolacion;
    private double[] b;
    private double [][] matriz;
    private static int metodo_a_usar;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interpolacion);

        metodo_a_usar = -1;

        Spinner spinner = (Spinner) findViewById(R.id.spinnerInterpolacion);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.interpolacion, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                metodo_a_usar = i;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                metodo_a_usar = -1;
            }
        });

        tblInterpolacion = (TableLayout)findViewById(R.id.tblInterpolacion);
        final EditText nrofilas = (EditText)findViewById(R.id.txtInterpNumeroPuntos);
        Button btnFilas = (Button)findViewById(R.id.btnInterpFilas);
        btnFilas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    int filas = Integer.parseInt(nrofilas.getText().toString());
                    setFilas(filas);
                }catch(Exception e){
                    Toast.makeText(getApplicationContext(),"Revise filas ingresadas", Toast.LENGTH_SHORT).show();
                }
            }
        });
        Button btnCalc = (Button)findViewById(R.id.btnCalcInterpolacion);
        btnCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(metodo_a_usar != -1){
                setMatriz();
                    Intent intent = new Intent(getBaseContext(), ResultActivity.class);
                    switch (metodo_a_usar){
                        case 0:
                            new ResultActivity();
                            startActivity(intent);
                            break;
                        case 1:
                            new ResultActivity();
                            startActivity(intent);
                            break;
                    }
                }else{
                    Toast.makeText(getBaseContext(),"Seleccione un método a usar", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /*
 *Create the table View when user input the size of the matrix
  */
    public void setFilas(int filas){

        tblInterpolacion.removeAllViews();
        for (int i = 0; i < filas; i++) {
            TableRow row = new TableRow(InterpolacionActivity.this);
            row.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));

            for (int j = 0; j < 2; j++) {
                EditText edit = new EditText(InterpolacionActivity.this);
                edit.setInputType(InputType.TYPE_CLASS_NUMBER|InputType.TYPE_NUMBER_FLAG_DECIMAL|InputType.TYPE_NUMBER_FLAG_SIGNED);
                edit.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
                edit.setText("");
                row.addView(edit);
            }
            tblInterpolacion.addView(row);
        }
    }

    /*
    *Return the matrix values inside each row
    * and fill a matrix
     */
    public void setMatriz(){
        int filas = tblInterpolacion.getChildCount();
        matriz = new double[filas][2];
        for(int i = 0, c = filas; i < c; i++){
            TableRow row = (TableRow) tblInterpolacion.getChildAt(i);
            for(int j = 0; j < row.getChildCount(); j++){
                EditText editText = (EditText) row.getChildAt(j);
                matriz[i][j] = Double.parseDouble(editText.getText().toString().trim());
                //Log.e("[",editText.getText().toString()+"]");
            }
        }

        /*for(int f = 0; f<matriz.length; f++){
            for(int c = 0; c<matriz[f].length;c++){
                Log.e("Test Matriz [", matriz[f][c] + "]");
            }
        }*/
    }
    public double [][] getMatriz(){
        return this.matriz;
    }
}
