package com.calculadora.calculadora;

/**
 * Created by Arthur on 05/04/2017.
 */

public class Calculo {

    private static Calculo instance;

    private Calculo(){

    }

    public static synchronized Calculo getInstance(){
        if (instance == null) {
            instance = new Calculo();
        }
        return instance;
    }

    public Integer somar(Integer valor1, Integer valor2){
        return valor1 + valor2;
    }
    public Integer subtrair(Integer valor1, Integer valor2){
        return valor1 - valor2;
    }
    public Integer multiplicar(Integer valor1, Integer valor2){
        return valor1 * valor2;
    }
    public Integer dividir(Integer valor1, Integer valor2){
        return valor1 / valor2;
    }


}
