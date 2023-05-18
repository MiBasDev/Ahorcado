/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ahorcado.ui;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Clase que consulta un fichero y elige una palabra aleatoria de él.
 *
 * @author Miguel Bastos Gándara.
 */
public class FileWordGenerator implements WordGenerator {

    /**
     * Método que consulta un fichero y elige una palabra aleatoria del mismo.
     *
     * @return @throws GenerateWordException Excepción al generar una palabra.
     */
    @Override
    public String generateWord() throws GenerateWordException {
        ArrayList<String> words = new ArrayList();
        // Declaramos o scanner
        Scanner in = null;
        try {
            // Abrimos o scanner sobre un reader con buffer
            in = new Scanner(new BufferedReader(new FileReader("wordGenerator")));
            // Establecemos como delimitador ", " ou espazo en branco
            in.useDelimiter(", ");
            // Lemos todos os tokens e os mostramos
            while (in.hasNext()) {
                words.add(in.next());
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Error al intentar leer del fichero: " + ex.getMessage());
        } finally {
            // En calquera caso, producirase ou non unha excepción, pechamos o
            // scanner se está aberto
            if (in != null) {
                in.close();
            }
        }
        // Elegimos una palabra random del ArrayList con la que jugar
        int random = new java.util.Random().nextInt(words.size());
        String word = words.get(random);
        return word;
    }
}
