package com.calculadora.calculadora;

/**
 * Created by Arthur on 05/04/2017.
 */

public class Util {

    private static Util instance;

    private Util() {

    }

    public static synchronized Util getInstance() {
        if (instance == null) {
            instance = new Util();
        }
        return instance;
    }

    public boolean operadorNull(String operador) {
        if (operador == null) {
            return true;
        } else {
            return false;
        }
    }

    public boolean valorInicialIsNull(Integer valorInicial) {
        if (valorInicial == null) {
            return true;
        } else {
            return false;
        }
    }

    public boolean valorFinalIsNull(Integer valorFinal) {
        if (valorFinal == null) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isOperador(String operador) {
        if (operador.equals("+") || operador.equals("-") || operador.equals("*") || operador.equals("/")) {
            return true;
        } else {
            return false;
        }
    }
}
