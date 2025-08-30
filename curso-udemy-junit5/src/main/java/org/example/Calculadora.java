package org.example;

public class Calculadora {

    public Calculadora(){
        System.out.println("Nova calculadora criada");
    }

    public int soma(int numero1, int numero2){
        return numero1 + numero2;
    }

    public float dividir(int numerador, int denominador){
        return (float) numerador / denominador ;
    }
}
