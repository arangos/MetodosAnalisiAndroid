package com.example.arango.metodos;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by Felipe on 01/11/2014.
 */
public class MainActivity extends Activity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnNoLineales = (Button) findViewById(R.id.btnNoLineales);
        btnNoLineales.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //new Ec_NoLinealesActivity();
                //Intent intent = new Intent(this, Ec_NoLinealesActivity);
                //startActivity(intent);
            }
        });
        Button btnLineales = (Button) findViewById(R.id.btnlineales);
        btnLineales.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    new Ec_LinealesActivity();
                    Intent intent = new Intent(getBaseContext(), Ec_LinealesActivity.class);
                    startActivity(intent);
            }
        });
        Button btnInterpolacion = (Button) findViewById(R.id.btnInterpolacion);
        btnInterpolacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        Button btnIntegraionNum = (Button) findViewById(R.id.btnIntegracionNum);
        btnIntegraionNum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

}
