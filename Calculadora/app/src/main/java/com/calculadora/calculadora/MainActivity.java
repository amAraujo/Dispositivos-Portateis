package com.calculadora.calculadora;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn0, btnDividir, btnMultiplicar,
            btnSubtrair, btnSomar, btnIgual, btnLimpar = null;

    TextView etExibicao = null;

    Integer valorInicial, valorFinal = null;
    String operador = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);
        btn4 = (Button) findViewById(R.id.btn4);
        btn5 = (Button) findViewById(R.id.btn5);
        btn6 = (Button) findViewById(R.id.btn6);
        btn7 = (Button) findViewById(R.id.btn7);
        btn8 = (Button) findViewById(R.id.btn8);
        btn9 = (Button) findViewById(R.id.btn9);
        btn0 = (Button) findViewById(R.id.btn0);
        btnDividir = (Button) findViewById(R.id.btn_dividir);
        btnSubtrair = (Button) findViewById(R.id.btn_subtrair);
        btnMultiplicar = (Button) findViewById(R.id.btn_multiplicar);
        btnSomar = (Button) findViewById(R.id.btn_somar);
        btnIgual = (Button) findViewById(R.id.btn_igual);
        btnLimpar = (Button) findViewById(R.id.btn_limpar);
        etExibicao = (TextView) findViewById(R.id.et_exibicao);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
        btn9.setOnClickListener(this);
        btn0.setOnClickListener(this);
        btnDividir.setOnClickListener(this);
        btnMultiplicar.setOnClickListener(this);
        btnSubtrair.setOnClickListener(this);
        btnSomar.setOnClickListener(this);
        btnIgual.setOnClickListener(this);
        btnLimpar.setOnClickListener(this);

    }

    public void valorInicialNull(Button btnAtual) {
        if (Util.getInstance().isOperador(btnAtual.getText().toString())) {
            return;
        } else {
            this.valorInicial = Integer.valueOf(btnAtual.getText().toString());
            this.etExibicao.setText(String.valueOf(this.valorInicial));
        }
    }

    public void valorInicialNotNull(Button btnAtual) {
        String valor = btnAtual.getText().toString();
        if (Util.getInstance().isOperador(btnAtual.getText().toString()) &&
                Util.getInstance().operadorNull(this.operador)) {
            this.etExibicao.setText(btnAtual.getText().toString());
            setOperador(btnAtual.getText().toString());
        } else {
            this.valorInicial = Integer.valueOf(String.valueOf(this.valorInicial) + btnAtual.getText().toString());
            this.etExibicao.setText(String.valueOf(this.valorInicial));
        }
    }

    public void valorFinalNull(Button btnAtual) {
        if (Util.getInstance().isOperador(btnAtual.getText().toString()) &&
                Util.getInstance().operadorNull(btnAtual.getText().toString())) {
            return;
        } else {
            this.valorFinal = Integer.valueOf(btnAtual.getText().toString());
            this.etExibicao.setText(String.valueOf(this.valorFinal));
        }
    }

    public void setOperador(String operador){
        if (operador.equals("+")) {
            this.operador = "soma";
        } else if (operador.equals("-")){
            this.operador = "subtracao";
        } else if (operador.equals("*")){
            this.operador = "multiplicacao";
        } else if (operador.equals("/")){
            this.operador = "divisao";
        }
    }

    public void valorFinalNotNull(Button btnAtual) {
        if (Util.getInstance().isOperador(btnAtual.getText().toString()) &&
                !Util.getInstance().operadorNull(btnAtual.getText().toString())) {
            return;
        } else {
            this.valorFinal = Integer.valueOf(String.valueOf(this.valorInicial) + btnAtual.getText().toString());
            this.etExibicao.setText(String.valueOf(this.valorFinal));
        }
    }

    @Override
    public void onClick(View view) {
        Button btnAtual = (Button) view;
        if (Util.getInstance().valorInicialIsNull(this.valorInicial)) {
            valorInicialNull(btnAtual);
        } else if (!Util.getInstance().valorInicialIsNull(this.valorInicial)) {
            valorInicialNotNull(btnAtual);
        } else if (Util.getInstance().valorFinalIsNull(this.valorFinal)) {
            valorFinalNull(btnAtual);
        } else if (!Util.getInstance().valorFinalIsNull(this.valorFinal)) {
            valorFinalNotNull(btnAtual);
        } else if (btnIgual == view) {
            switch (this.operador) {
                case "soma":
                    etExibicao.setText(String.valueOf(Calculo.getInstance().somar(this.valorInicial, this.valorFinal)));
                    break;
                case "subtracao":
                    etExibicao.setText(String.valueOf(Calculo.getInstance().subtrair(this.valorInicial, this.valorFinal)));
                    break;
                case "multiplicacao":
                    etExibicao.setText(String.valueOf(Calculo.getInstance().multiplicar(this.valorInicial, this.valorFinal)));
                    break;
                case "divisao":
                    etExibicao.setText(String.valueOf(Calculo.getInstance().dividir(this.valorInicial, this.valorFinal)));
                    break;
                default:
                    System.out.println("Este não é um operador válido!");
            }
        }
    }
}
