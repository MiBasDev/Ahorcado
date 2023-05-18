/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ahorcado.ui;

/**
 * Interfaz que define la palabra generada.
 *
 * @author Miguel Bastos Gándara & Ainhoa Barros Queimadelos
 */
public interface WordGenerator {

    /**
     * Método que devuelve la palabra generada.
     *
     * @return @throws GenerateWordException Excepción de tipo palabra generada.
     */
    public String generateWord() throws GenerateWordException;
}
