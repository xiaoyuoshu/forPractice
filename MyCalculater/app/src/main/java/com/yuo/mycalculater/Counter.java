package com.yuo.mycalculater;


import android.util.Log;

import java.util.Stack;

public class Counter {

    public static double calculate(String expression){
        Stack<Double> operandStack = new Stack<>();
        Stack<Character> operatorStack = new Stack<>();
        Log.e("MainActivity", "calculate: "+ "#" + expression + "#");

        String[] charArr = expression.split("");
        for(int i = 0; i < charArr.length; i++){
            if(charArr[i].trim().equals("")){
                continue;
            }
            else if(charArr[i].trim().equals("+") || charArr[i].trim().equals("-")){
                while(!operatorStack.isEmpty() && (operatorStack.peek() == '+' || operatorStack.peek() == '-' || operatorStack.peek() == '*' || operatorStack.peek() == '/')){
                    operator(operandStack,operatorStack);
                }
                operatorStack.push(charArr[i].charAt(0));
            }
            else if(charArr[i].trim().equals("*") || charArr[i].trim().equals("/")){
                while(!operatorStack.isEmpty() && (operatorStack.peek() == '*' || operatorStack.peek() == '/')){
                    operator(operandStack, operatorStack);
                }
                operatorStack.push(charArr[i].charAt(0));
            }
            else if(charArr[i].trim().equals("(")){
                operatorStack.push('(');
            }
            else if(charArr[i].trim().equals(")")){
                while(operatorStack.peek() != '('){
                    operator(operandStack,operatorStack);
                }
                operatorStack.pop();
            }
            else{
                int j;
                for(j = i; j < expression.length(); j++){
                    if(expression.charAt(j) == '+' || expression.charAt(j) == '-' || expression.charAt(j) == '*' || expression.charAt(j) == '/' || expression.charAt(j) == '(' || expression.charAt(j) == ')')
                        break;
                }
                if(j == expression.length()){
                    operandStack.push(Double.parseDouble(expression.substring(i)));
                }
                else {
                    operandStack.push(Double.parseDouble(expression.substring(i, j)));
                }
                i = j - 1;
            }
        }
        while(!operatorStack.isEmpty()){
            operator(operandStack,operatorStack);
        }
        return operandStack.pop();
    }

    public static void operator(Stack<Double> operandStack,Stack<Character> operatorStack){
        char op = operatorStack.pop();
        double op1 = operandStack.pop();
        double op2 = operandStack.pop();
        if(op == '+'){
            operandStack.push(op2 + op1);
        }
        else if(op == '-'){
            operandStack.push(op2 - op1);
        }
        else if(op == '*'){
            operandStack.push(op2 * op1);
        }
        else if(op == '/'){
            operandStack.push(op2 / op1);
        }
    }
}