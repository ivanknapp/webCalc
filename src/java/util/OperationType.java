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
public enum OperationType {
    ADD ("+"),
    SUBSTRACT ("-"),
    MULTIPLY ("*"),
    DIVIDE ("/");
    
    private String type;

    private OperationType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "OperationType{" + "type=" + type + '}';
    }
    
    
    
}
