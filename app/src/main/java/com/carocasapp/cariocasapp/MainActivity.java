package com.carocasapp.cariocasapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView quant = (TextView) findViewById(R.id.txt_quant);
        Button btnMaisUm = (Button) findViewById(R.id.btn_mais_uma_vez);

        btnMaisUm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int quantidade = Integer.parseInt(quant.getText().toString());
                quantidade++;
                quant.setText(String.valueOf(quantidade));
            }
        });
    }
}
