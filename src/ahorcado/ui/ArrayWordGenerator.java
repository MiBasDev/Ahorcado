/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ahorcado.ui;

/**
 * Clase que guarda el array de palabras a adivinar y escoge una aleatoria para
 * hacerlo.
 *
 * @author Miguel Bastos Gándara & Ainhoa Barros Queimadelos.
 */
public class ArrayWordGenerator implements WordGenerator{

    /**
     * Array de posibles palabras a adivinar.
     */
    public static final String[] WORDLIST = {"hola", "que", "tal", "estas", "tontito", "murcielago"};

    /**
     * Método que elige una palabra aleatoria del array para adivinarla.
     *
     * @return Palabra del array a adivinar
     */
    @Override
    public String generateWord() {
        int random = new java.util.Random().nextInt(WORDLIST.length);
        String word = WORDLIST[random];
        return word;
    }
}
