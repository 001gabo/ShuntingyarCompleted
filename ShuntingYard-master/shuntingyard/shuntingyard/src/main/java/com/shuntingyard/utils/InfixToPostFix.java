package com.shuntingyard.utils;

import com.shuntingyard.pojo.evaluate.request.EvaluateRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.Stack;

@Component
public class InfixToPostFix {
    private Logger log;

    public InfixToPostFix(){
        this.log =  LoggerFactory.getLogger(getClass());
    }
    public LinkedList infixToPostfixEquation(EvaluateRequest evaluateRequest){

        LinkedList colaNumeros = new LinkedList();
        Stack pilaOperadores = new Stack();

        try {
            for (char ch : evaluateRequest.getExp().toCharArray()) {
                if (Character.isDigit(ch)) { //chequeando si el caracter es un digito
                    colaNumeros.offer(ch);
                } else {
                    int auxPrecedence = 0;
                    int actualPrecedence = 0;
                    int precedenceResult = 0;
                    char characterAux;


                    //Si la pila de operadores esta vacia
                    if (pilaOperadores.empty()) {
                        pilaOperadores.push(ch);
                    } else if (ch == '.') { //si el caracter es el punto decimal
                        colaNumeros.offer(ch);
                    } else { //La pila de operadores no esta vacia

                        characterAux = pilaOperadores.peek().toString().charAt(0); //Obteniendo el caracter al tope de la pila
                        auxPrecedence = precedence(characterAux); // / -> 2
                        actualPrecedence = precedence(ch); // + ->1

                        precedenceResult = auxPrecedence + actualPrecedence;

                        if (auxPrecedence >= actualPrecedence) {
                            characterAux = pilaOperadores.pop().toString().charAt(0); //Sacando el elemento del tope de la pila
                            colaNumeros.offer(characterAux); //Metiendo el elemento sacado de la pila de operadores en la pila de numeros
                            pilaOperadores.push(ch);
                        } else {
                            pilaOperadores.push(ch);
                        }
                    }
                }
            }

            //Sacando los elementos restantes en la pila de operadores y metiendolos en la cola de numeros
            for (int i = 0; i <= pilaOperadores.size(); i++) {
                colaNumeros.offer(pilaOperadores.pop());
            }
        }catch (Exception e){
            log.error("InfixToPostFix - Error: {}",e.getMessage());
        }

        return colaNumeros;
    }

    private int precedence(char character){
        int precedenceValue = 0;

        if(character == '+' || character == '-'){
            precedenceValue = 1;
        }else if(character == '*' || character == '/'){
            precedenceValue = 2;
        }

        return precedenceValue;
    }
}
