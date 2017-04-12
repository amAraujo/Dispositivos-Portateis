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

    //region Variáveis

    Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn0, btnDividir, btnMultiplicar,
            btnSubtrair, btnSomar, btnIgual, btnLimpar = null;

    TextView etExibicao = null;

    Integer valorInicial, valorFinal = null;
    String operador = null;
    Boolean hasValueTotal = false;

    //endregion

    //region Método onCreate

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

    //endregion

    //region Método onclick

    @Override
    public void onClick(View view) {
        Button btnAtual = (Button) view;
        if (view == btnLimpar) {
            etExibicao.setText("");
            if (this.valorInicial != null) {
                this.valorInicial = null;
            } if (this.valorFinal != null) {
                this.valorFinal = null;
            }
            if (this.operador != null) {
                this.operador = null;
            }
            this.hasValueTotal = false;
        } else if (Util.getInstance().operadorNull(this.operador)) {
            if (Util.getInstance().valorInicialIsNull(this.valorInicial)) {
                valorInicialNull(btnAtual);
            } else if (!Util.getInstance().valorInicialIsNull(this.valorInicial)) {
                valorInicialNotNull(btnAtual);
            }
        } else {
            if (Util.getInstance().valorFinalIsNull(this.valorFinal)) {
                valorFinalNull(btnAtual);
            } else if (!Util.getInstance().valorFinalIsNull(this.valorFinal)) {
                if (!this.hasValueTotal) {
                    if (btnIgual == view) {
                        calcular();
                    } else {
                        valorFinalNotNull(btnAtual);
                    }
                }
            }
        }
    }


    //endregion

    //region Métodos de vericação de variável

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
            this.etExibicao.setText(String.valueOf(this.valorInicial) + btnAtual.getText().toString());
            setOperador(btnAtual.getText().toString());
        } else {
            this.valorInicial = Integer.valueOf(String.valueOf(this.valorInicial) + btnAtual.getText().toString());
            this.etExibicao.setText(String.valueOf(this.valorInicial));
        }
    }

    public void valorFinalNull(Button btnAtual) {
        if (Util.getInstance().isOperador(btnAtual.getText().toString()) &&
                !Util.getInstance().operadorNull(btnAtual.getText().toString())) {
            return;
        } else {
            this.valorFinal = Integer.valueOf(btnAtual.getText().toString());
            this.etExibicao.setText(String.valueOf(String.valueOf(this.valorInicial)) + this.operador + String.valueOf(this.valorFinal));
        }
    }

    public void valorFinalNotNull(Button btnAtual) {
        if (Util.getInstance().isOperador(btnAtual.getText().toString()) &&
                !Util.getInstance().operadorNull(btnAtual.getText().toString())) {
            return;
        } else {
            this.valorFinal = Integer.valueOf(String.valueOf(this.valorInicial) + btnAtual.getText().toString());
            this.etExibicao.setText(String.valueOf(String.valueOf(this.valorInicial)) + this.operador + String.valueOf(this.valorFinal));
        }
    }

    public void setOperador(String operador) {
        if (operador.equals("+")) {
            this.operador = "+";
        } else if (operador.equals("-")) {
            this.operador = "-";
        } else if (operador.equals("*")) {
            this.operador = "*";
        } else if (operador.equals("/")) {
            this.operador = "/";
        }
    }

    //endregion

    //region Método calcular

    private void calcular() {
        switch (this.operador) {
            case "+":
                etExibicao.setText(String.valueOf(String.valueOf(this.valorInicial)) + this.operador + String.valueOf(this.valorFinal) + " = " + String.valueOf(Calculo.getInstance().somar(this.valorInicial, this.valorFinal)));
                hasValueTotal = true;
                break;
            case "-":
                etExibicao.setText(String.valueOf(String.valueOf(this.valorInicial)) + this.operador + String.valueOf(this.valorFinal) + " = " + String.valueOf(Calculo.getInstance().subtrair(this.valorInicial, this.valorFinal)));
                hasValueTotal = true;
                break;
            case "*":
                etExibicao.setText(String.valueOf(String.valueOf(this.valorInicial)) + this.operador + String.valueOf(this.valorFinal) + " = " + String.valueOf(Calculo.getInstance().multiplicar(this.valorInicial, this.valorFinal)));
                hasValueTotal = true;
                break;
            case "/":
                etExibicao.setText(String.valueOf(String.valueOf(this.valorInicial)) + this.operador + String.valueOf(this.valorFinal) + " = " + String.valueOf(Calculo.getInstance().dividir(this.valorInicial, this.valorFinal)));
                hasValueTotal = true;
                break;
            default:
                System.out.println("Este não é um operador válido!");
        }
    }

    //endregion

}
