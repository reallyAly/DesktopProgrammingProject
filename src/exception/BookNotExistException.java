/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package exception;

/**
 *
 * @author alysson
 */
public class BookNotExistException extends Exception {
    public BookNotExistException(String errorMessage) {
        super(errorMessage);
    }
}
