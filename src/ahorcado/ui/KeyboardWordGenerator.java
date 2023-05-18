/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ahorcado.ui;

import java.util.Scanner;

/**
 * Clase que implementa la interfaz WordGenerator y que pedirá la palabra a
 * adivinar por teclado.
 *
 * @author Miguel Bastos Gándara & Ainhoa Barros Queimadelos
 */
public class KeyboardWordGenerator implements WordGenerator {

    /**
     * Método que pide por teclado la palabra a adivinar.
     *
     * @return @throws GenerateWordException Excepción de tipo palabra generada.
     */
    @Override
    public String generateWord() throws GenerateWordException {
        Scanner scan = new Scanner(System.in);
        String word;
        System.out.println("");
        System.out.println("Pon la palabra a adivinar:");
        if (System.console() == null) {
            word = scan.nextLine();
        } else {
            word = new String(System.console().readPassword());
        }
        return word;
    }
}
