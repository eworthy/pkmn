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
public class NoFileException extends RuntimeException {
    
    public NoFileException(String message) {
        super(message);
    }
}
