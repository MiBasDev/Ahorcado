/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ahorcado.model;

import java.util.ArrayList;

/**
 * Clase que implementa el estado de una partida del ahorcado, manteniendo una
 * referencia a un objeto de la clase HiddenWord con la palabra oculta a
 * adivinar, y almacenando las letras introducidas por el usuario que no estén
 * en la palabra, para is mostrándolas y no se introduzcan de nuevo.
 *
 * @author Miguel Bastos Gándara & Ainhoa Barros Queimadelos
 */
public class HangMan {

    private HiddenWord word;
    private ArrayList<Character> fails;

    /**
     * Constante que referencia el número de errores máximo posible.
     */
    public static final int MAX_FAILS = 6;

    /**
     * Constructor de la clase HangMan.
     *
     * @param hiddenWord Palabra oculta.
     */
    public HangMan(String hiddenWord) {
        word = new HiddenWord(hiddenWord);
        fails = new ArrayList();
    }

    /**
     * Devuelve la palabra oculta.
     *
     * @return Palabra oculta.
     */
    public HiddenWord getWord() {
        return word;
    }

    /**
     * Cambai el valor de la palabra oculta.
     *
     * @param word Palabra oculta.
     */
    public void setWord(HiddenWord word) {
        this.word = word;
    }

    /**
     * Devuelve los fallos acumulados de la partida.
     *
     * @return Array del número de fallos.
     */
    public ArrayList<Character> getFails() {
        return fails;
    }

    /**
     * Método que devuelve un String con la lista de caracteres fallados
     * acumulados separados por espacios en blanco.
     *
     * @return String con la lista de caracteres fallados acumulados.
     */
    public String getStringFails() {
        String totalFails = "";
        for (int i = 0; i < MAX_FAILS; i++) {
            totalFails += fails.get(i).toString();
        }
        return totalFails;
    }

    /**
     * Método que muestra la palabra oculta con guiones en las letras no
     * acertadas.
     *
     * @return Palabra oculta con guiones en las letras no acertadas.
     */
    public String showHiddenWord() {
        return word.show();
    }

    /**
     * Método que devuelve la palabra oculta mostrando todas sus letras.
     *
     * @return Palabra oculta.
     */
    public String showFullWord() {
        return word.showFullWord();
    }

    /**
     * Método que comprueba si el caracter pasado como parámetro forma parte de
     * la palabra oculta, mostrándola si aparece, y si no, añadiendo un fallo.
     *
     * @param c Caracter a buscar en la palabra oculta.
     */
    public void tryChar(char c) {
        if (word.checkChar(c)) {
            word.show();
        } else {
            fails.add(c);
        }
    }

    /**
     * Método que comprueba si acabó la partida.
     *
     * @return True si terminó la partida, false si no.
     */
    public boolean isGameOver() {
        return fails.size() >= MAX_FAILS || word.isVisible();
    }

    /**
     * Método que comprueba si se llegó al número máximo de falos permitido.
     *
     * @return True si se llegó, false si no.
     */
    public boolean maxFailsExceeded() {
        return fails.size() >= MAX_FAILS;
    }
}
