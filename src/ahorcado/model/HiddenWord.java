/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ahorcado.model;

/**
 * Clase que implementa la palabra a adivinar con la que trabajremos en el
 * juego, resolverá todo el trabajo de mantener las letras acertadas, mostrar la
 * palabra ocultando las letras no acertadas y comprobar si una letra forma
 * parte de la palabra o no.
 *
 * @author Miguel Bastos Gándara & Ainhoa Barros Queimadelos
 */
public class HiddenWord {

    private char[] characters;
    private boolean[] hits;

    /**
     * Devuelve el array de caracteres.
     *
     * @return Array de caracteres.
     */
    public char[] getCharacters() {
        return characters;
    }

    /**
     * Cambia el valor del array de caracteres.
     *
     * @param characters Array de caracteres.
     */
    public void setCharacters(char[] characters) {
        this.characters = characters;
    }

    /**
     * Devuelve el array de hits.
     *
     * @return Array de hits.
     */
    public boolean[] getHits() {
        return hits;
    }

    /**
     * Cambia el array e hits.
     *
     * @param hits Array de hits.
     */
    public void setHits(boolean[] hits) {
        this.hits = hits;
    }

    /**
     * Constructor de la clase HiddenWord.
     *
     * @param hiddenWord Palabra oculta a adivinar.
     */
    public HiddenWord(String hiddenWord) {
        this.characters = new char[hiddenWord.length()];
        this.hits = new boolean[hiddenWord.length()];
        for (int i = 0; i < characters.length; i++) {
            characters[i] = hiddenWord.charAt(i);
        }
        for (int i = 0; i < hits.length; i++) {
            hits[i] = false;
        }
    }

    /**
     * Método que comprueba si un caracter aparece en la lista de caracteres de
     * la palabra, marcando todas las apariciones de ese caracter como true.
     *
     * @param c Caracter a buscar en la lista de caracteres de la palabra.
     * @return True si aparece en la palabra, sino false.
     */
    public boolean checkChar(char c) {
        boolean charInWord = false;
        for (int i = 0; i < characters.length; i++) {
            if (c == characters[i]) {
                hits[i] = true;
                charInWord = true;
            }
        }
        return charInWord;
    }

    /**
     * Método que devuelve la palabra sustituyendo los caracteres no acertado
     * por guiones.
     *
     * @return Palabra con guiones.
     */
    public String show() {
        String b = "-";
        String result = "";
        for (int i = 0; i < characters.length; i++) {
            if (hits[i]) {
                result += characters[i];
            } else {
                result = result + b;
            }
        }
        return result;
    }

    /**
     * Devuelve la palabra completa, incluyendo las caracteres no acertados.
     *
     * @return Palabra a adivinar.
     */
    public String showFullWord() {
        String hiddenWord = "";
        for (int i = 0; i < characters.length; i++) {
            hiddenWord += characters[i];
        }
        return hiddenWord;
    }

    /**
     * Método que indica si la palabra es visible, es decir, que todos los
     * caracteres ya han sido acertados.
     *
     * @return True si es visible, False si no lo es.
     */
    public boolean isVisible() {
        for (int i = 0; i < hits.length; i++) {
            if (!hits[i]) {
                return false;
            }
        }
        return true;
    }
}
