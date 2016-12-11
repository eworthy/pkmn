/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Exceptions;

/**
 *
 * @author ellen
 */
public class NoSavedGameException extends RuntimeException {
    
    public NoSavedGameException(String message) {
        super(message);
    }
}
