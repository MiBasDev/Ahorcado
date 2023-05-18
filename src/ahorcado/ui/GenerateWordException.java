/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ahorcado.ui;

/**
 * Clase que define una excepción de tipo GenerateWord.
 *
 * @author Miguel Bastos Gándara & Ainhoa Barros Queimadelos.
 */
public class GenerateWordException extends Exception {

    private boolean visible;

    /**
     * Constructor de la clase GenerateWordException.
     *
     * @param visible Si la excepción es visible por el usuario o no.
     * @param message Mensaje a mostrar al usuario.
     */
    public GenerateWordException(boolean visible, String message) {
        super(message);
        this.visible = visible;
    }

    /**
     * Cambia el valor a true si es visible, false si no.
     *
     * @return True si es visible, false si no.
     */
    public boolean isVisible() {
        return visible;
    }

    /**
     * Devuelve el valor true si es visible, false si no.
     *
     * @param visible True si es visible, false si no.
     */
    public void setVisible(boolean visible) {
        this.visible = visible;
    }
}
