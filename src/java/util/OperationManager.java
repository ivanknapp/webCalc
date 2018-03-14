/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

/**
 *
 * @author Иван
 */
public class OperationManager {
    
    public String doOperation(double arg1, double arg2, OperationType type){
        double result;
        
        switch (type.getType()){
            case "+" : result = add(arg1,arg2); break;
            case "-" : result = substract(arg1, arg2); break;
            case "*" : result = multiply(arg1, arg2); break;
            case "/" : result = divide(arg1, arg2); break;
            default : result = 0;
        }
        
        return String.format("%.0f %s %.0f = %.2f", arg1, type.getType(), arg2, result);
    }
    
    private double add(double x, double y){
        return x + y;
    }
    
    private double substract(double x, double y){
        return x - y;
    }
    
    private double multiply(double x, double y){
        return x*y;
    }
    
    private double divide(double x, double y){
        return x/y;
    }
    
}
