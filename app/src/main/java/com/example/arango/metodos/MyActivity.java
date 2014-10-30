package com.example.arango.metodos;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Timer;


public class MyActivity extends Activity {
    private Spinner spinnerMetodo;
    private Spinner spinnerTipo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
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
                if(spinnerTipo.getSelectedItem().toString().equals("Directos")){
                    switch(i){
                        case 0:
                            Toast.makeText(getApplicationContext(), "Direct 0 ",Toast.LENGTH_SHORT).show();
                            break;
                        case 1:
                            Toast.makeText(getApplicationContext(), "Direct 1 ",Toast.LENGTH_SHORT).show();
                            break;
                        case 2:
                            Toast.makeText(getApplicationContext(), "Direct 2 ",Toast.LENGTH_SHORT).show();
                            break;
                        case 3:
                            Toast.makeText(getApplicationContext(), "Direct 3 ",Toast.LENGTH_SHORT).show();
                            break;
                    }

                }
                if(spinnerTipo.getSelectedItem().toString().equals("Iterativos")){
                    switch(i){
                        case 0:
                            Toast.makeText(getApplicationContext(), "Iter 0 ",Toast.LENGTH_SHORT).show();
                            break;
                        case 1:
                            Toast.makeText(getApplicationContext(), "Iter 1 ",Toast.LENGTH_SHORT).show();
                            break;
                    }
                }
                if(spinnerTipo.getSelectedItem().toString().equals("Factorización")){
                    switch(i){
                        case 0:
                            Toast.makeText(getApplicationContext(), "Fact 0 ",Toast.LENGTH_SHORT).show();
                            break;
                        case 1:
                            Toast.makeText(getApplicationContext(), "Fact 1 ",Toast.LENGTH_SHORT).show();
                            break;
                        case 2:
                            Toast.makeText(getApplicationContext(), "Fact 2 ",Toast.LENGTH_SHORT).show();
                            break;
                        case 3:
                            Toast.makeText(getApplicationContext(), "Fact 3 ",Toast.LENGTH_SHORT).show();
                            break;
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

}
