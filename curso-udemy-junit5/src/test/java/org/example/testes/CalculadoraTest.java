package org.example.testes;

import org.example.Calculadora;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CalculadoraTest {

    private static Calculadora calculadora = new Calculadora(); // se não inicializar aqui, dara NullPointerException
    private static int contador = 0;

    //executado antes de cada método de teste
    @BeforeEach
    public void setup(){
        System.out.println("^^^^ Setup ^^^");
    }

    //executado após cada método de teste é o oposto do BeforeEach
    @AfterEach
    public void tearDown(){
        System.out.println("vvvv Tear Down vvvv");
    }

    //executado uma vez antes de todos os métodos de teste - precisa ser um metodo estatico
    @BeforeAll
    public static void beforeAll(){
        System.out.println("******** Before All ********");
    }

    //executado uma vez após todos os métodos de teste - precisa ser um metodo estatico
    @AfterAll
    public static void afterAll(){
        System.out.println("******** After All ********");
    }

    @Test
    public void testeSomar(){
        System.out.println(++contador);
        System.out.println(calculadora.soma(5,3) == 8);
        assertEquals(8, calculadora.soma(5, 3));
    }

    @Test
    public void assertivas() {
        System.out.println(++contador);
        assertEquals("casa", "casa");
        assertNotEquals("Casa", "casa");
        assertTrue("casa".equalsIgnoreCase("CASA"));
        assertTrue("casa".endsWith("sa"));
        assertTrue("Casa".startsWith("Ca"));

        List<String> lista1 = new ArrayList<>();
        List<String> lista2 = new ArrayList<>();
        List<String> lista3 = null;

        assertEquals(lista1, lista2);
//        assertSame(lista1, lista2);
//        assertEquals(lista1, lista3);
        assertNull(lista3);
        assertNotNull(lista1);
//        Assertions.fail("Teste falhou!!");
    }

    @Test
    public void deveRetornarNumeroInteiroNaDivisao(){
        System.out.println(++contador);
        float resultado = calculadora.dividir(6, 2);
        assertEquals(3, resultado);
    }

    @Test
    public void deveRetornarNumeroNegativoNaDivisao(){
        System.out.println(++contador);
        float resultado = calculadora.dividir(12, -6);
        assertEquals(-2, resultado);
    }

    @Test
    public void deveRetornarNumeroDecimalNaDivisao(){
        System.out.println(++contador);
        float resultado = calculadora.dividir(10, 3);
        assertEquals(3.33, resultado, 0.01);
    }

    @Test
    public void deveRetornarZeroComNumeradorZeroNaDivisao(){
        System.out.println(++contador);
        float resultado = calculadora.dividir(0, 6);
        assertEquals(0, resultado);
    }

    @Test
    public void deveLancarExcecaoQuandoDividirPorZero_Junit4(){
        try{
            float resultado = 10 / 0;
            Assertions.fail("Deveria ter lancado uma excecao");
        } catch(ArithmeticException e){
            assertEquals("/ by zero", e.getMessage());
        }
    }

    @Test
    public void deveLancarExcecaoQuandoDividirPorZero_Junit5(){
        ArithmeticException exception = Assertions.assertThrows(ArithmeticException.class, ()-> {
           float resultado = 10 / 0;
        });
        Assertions.assertEquals("/ by zero", exception.getMessage());
    }

    //parametized tests -> serve para rodar o mesmo teste com varios parametros diferentes
    @ParameterizedTest
    @ValueSource(strings = {"Teste1", "Teste2"})
    public void testString(String param){
        System.out.println(param);
        assertNotNull(param);
    }
    
    @ParameterizedTest
    @CsvSource(value = {
            "6, 2, 3",
            "12, -6, -2",
            "10, 3, 3.3333332538604736",
            "0, 6, 0"
    })
    public void deveDividirCorretamente(int numerador, int denominador, double res){
        float resultado = calculadora.dividir(numerador, denominador);
        assertEquals(res, resultado);
    }

}

