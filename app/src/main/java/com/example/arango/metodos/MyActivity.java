package com.example.arango.metodos;

import android.app.Activity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.arango.metodos.Metodos.SistemasDeEcuaciones;

public class MyActivity extends Activity {
    private Spinner spinnerMetodo;
    private Spinner spinnerTipo;
    private SistemasDeEcuaciones sistemasDeEcuaciones;
    private TableLayout tableLayout;
    private Button button;
    private EditText txtNrofilas;
    private static int metodo_a_usar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        metodo_a_usar = 0;
        tableLayout = (TableLayout) findViewById(R.id.tblLayout);
        button = (Button) findViewById(R.id.btnNroFilas);
        txtNrofilas = (EditText) findViewById(R.id.editTextNroFilas);
        spinnerTipo = (Spinner) findViewById(R.id.spinnerTipo);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.sis_lineales, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinnerTipo.setAdapter(adapter);

        spinnerMetodo = (Spinner) findViewById(R.id.spinnerMetodo);

        spinnerTipo.setOnItemSelectedListener( new AdapterView.OnItemSelectedListener() {
                                                   @Override
                                                   public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                                       ArrayAdapter<CharSequence> adapter;
                                                        switch(i){
                                                           case 0:
                                                               adapter = ArrayAdapter.createFromResource(getApplicationContext(),
                                                                       R.array.sisLinealesDirector, android.R.layout.simple_spinner_item);
                                                               adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                                               spinnerMetodo.setAdapter(adapter);
                                                               break;
                                                           case 1:
                                                               adapter = ArrayAdapter.createFromResource(getApplicationContext(),
                                                                       R.array.sisLinealesIterativos, android.R.layout.simple_spinner_item);
                                                               adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                                               spinnerMetodo.setAdapter(adapter);
                                                               break;
                                                           case 2:
                                                               adapter = ArrayAdapter.createFromResource(getApplicationContext(),
                                                                       R.array.sisLinealesFactorizacion, android.R.layout.simple_spinner_item);
                                                               adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                                               spinnerMetodo.setAdapter(adapter);
                                                                break;
                                                           default:
                                                               break;
                                                       }
                                                   }

                                                   @Override
                                                   public void onNothingSelected(AdapterView<?> adapterView) {

                                                   }
                                               }
        );


        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.sisLinealesDirector, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinnerMetodo.setAdapter(adapter2);
        spinnerMetodo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                /*sistemasDeEcuaciones  = new SistemasDeEcuaciones();
                int n= tabla.getRowCount();
                double[][] A = new double[n][n];
                double[] b = new double[n];*/
                if(spinnerTipo.getSelectedItem().toString().equals("Directos")){
                    switch(i){
                        case 0:
                            metodo_a_usar = 0;
                            Toast.makeText(getApplicationContext(), "Elm Gaussiana",Toast.LENGTH_SHORT).show();
                            break;
                        case 1:
                            metodo_a_usar = 1;
                            //sistemasDeEcuaciones.eliminacionGauss(A, b, 0);
                            Toast.makeText(getApplicationContext(), "Pivoteo Parcial",Toast.LENGTH_SHORT).show();
                            break;
                        case 2:
                            metodo_a_usar = 2;
                            //sistemasDeEcuaciones.eliminacionGauss(A, b, 1);
                            Toast.makeText(getApplicationContext(), "Pivoteo Total",Toast.LENGTH_SHORT).show();
                            break;
                        case 3:
                            metodo_a_usar = 3;
                            //sistemasDeEcuaciones.eliminacionGauss(A, b, 2);
                            Toast.makeText(getApplicationContext(), "Pivoteo Escalonado",Toast.LENGTH_SHORT).show();
                            break;
                        default:
                            break;
                    }

                }
                if(spinnerTipo.getSelectedItem().toString().equals("Iterativos")){
                    switch(i){
                        case 0:
                            metodo_a_usar = 4;
                            /*double lamda = Double.parseDouble(principalFrm.getLamda());
                            int iter = Integer.parseInt(principalFrm.getIteracion());
                            double tol = Double.parseDouble(principalFrm.getToler());

                            sistemasDeEcuaciones.Jacobi(A,b,tol,iter,val,lamda);*/
                            Toast.makeText(getApplicationContext(), "Jacobi",Toast.LENGTH_SHORT).show();
                            break;
                        case 1:
                            metodo_a_usar = 5;
                            /*double lamda = Double.parseDouble(principalFrm.getLamda());
                            int iter = Integer.parseInt(principalFrm.getIteracion());
                            double tol = Double.parseDouble(principalFrm.getToler());

                            sistemasDeEcuaciones.Seidel(A,b,tol,iter,val,lamda);*/
                            Toast.makeText(getApplicationContext(), "Seidel",Toast.LENGTH_SHORT).show();
                            break;
                        default:
                            break;
                    }
                }
                if(spinnerTipo.getSelectedItem().toString().equals("Factorización")){
                    switch(i){
                        case 0:
                            metodo_a_usar = 6;
                            //sistemasDeEcuaciones.LUeliminacionGauss(A, b);
                            Toast.makeText(getApplicationContext(), "LU Gauss",Toast.LENGTH_SHORT).show();
                            break;
                        case 1:
                            metodo_a_usar = 7;
                            //sistemasDeEcuaciones.cholesky(A, b);
                            Toast.makeText(getApplicationContext(), "LU Cholesky",Toast.LENGTH_SHORT).show();
                            break;
                        case 2:
                            metodo_a_usar = 8;
                            //sistemasDeEcuaciones.crout(A, b);
                            Toast.makeText(getApplicationContext(), "LU Crout",Toast.LENGTH_SHORT).show();
                            break;
                        case 3:
                            metodo_a_usar = 9;
                            //sistemasDeEcuaciones.doolittle(A, b);
                            Toast.makeText(getApplicationContext(), "LU Doolittle",Toast.LENGTH_SHORT).show();
                            break;
                        default:
                            break;
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getApplicationContext(),txtNrofilas.getText().toString(),Toast.LENGTH_SHORT).show();
                int filas = Integer.parseInt(txtNrofilas.getText().toString());
                setFilas(filas);
            }
        });
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.action_calc:
                switch (metodo_a_usar){
                    case 0:
                        getMatriz();
                        //sistemasDeEcuaciones.eliminacionGauss(A, b, -1);
                        break;
                    case 1:
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                    case 4:
                        break;
                    case 5:
                        break;
                    case 6:
                        break;
                    case 7:
                        break;
                    case 8:
                        break;
                    case 9:
                        break;
                }
                return true;
            case R.id.action_settings:

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    public void setFilas(int filas){

        tableLayout.removeAllViews();
        for (int i = 0; i < filas; i++) {
            TableRow row = new TableRow(MyActivity.this);
            row.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));

            for (int j = 0; j < filas; j++) {
                EditText edit = new EditText(MyActivity.this);
                edit.setInputType(InputType.TYPE_CLASS_NUMBER|InputType.TYPE_NUMBER_FLAG_DECIMAL|InputType.TYPE_NUMBER_FLAG_SIGNED);
                edit.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
                edit.setText("0");

                //edit.setKeyListener();
                row.addView(edit);
            }
            tableLayout.addView(row);
        }
    }

    public void getMatriz(){
        int filas = tableLayout.getChildCount();
        Double [][] matriz = new Double[filas][filas];
        for(int i = 0, c = filas; i < c; i++){
            // then, you can remove the the row you want...
            // for instance...
            TableRow row = (TableRow) tableLayout.getChildAt(i);
            for(int j = 0; j < row.getChildCount(); j++){
                EditText editText = (EditText) row.getChildAt(j);
                matriz[i][j] = Double.parseDouble(editText.getText().toString().trim());
                //Log.e("[",editText.getText().toString()+"]");
            }
        }

        for(int f = 0; f<matriz.length; f++){
            for(int c = 0; c<matriz[f].length;c++){
                Log.e("Test Matriz [",matriz[f][c]+"]");
            }
        }
    }
}
