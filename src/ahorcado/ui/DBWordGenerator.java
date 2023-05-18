/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ahorcado.ui;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Clase que consulta la base de datos y elige una palabra aleatoria.
 *
 * @author Miguel Bastos Gándara & Ainhoa Barros Queimadelos.
 */
public class DBWordGenerator implements WordGenerator {

    private Connection c;

    /**
     * Constructor de la clase DBWordGenerator.
     *
     * @throws GenerateWordException Excepción al generar una palabra.
     */
    public DBWordGenerator() throws GenerateWordException {
        try {
            c = DriverManager.getConnection("jdbc:sqlite://home/mbasgan/Documentos/1º DAW/Programación/ahorcado.db/ahorcado.db", "", "");
        } catch (SQLException e) {
            throw new GenerateWordException(true, e.getMessage());
        }
    }

    /**
     * Método que consulta la base de datos y elige una palabra aleatoria de la
     * misma.
     *
     * @return @throws GenerateWordException Excepción al generar una palabra.
     */
    @Override
    public String generateWord() throws GenerateWordException {
        ArrayList<String> words = new ArrayList();
        // Hacemos la consulta en la BD
        try {
            String sql = "SELECT * FROM word";
            Statement st = c.createStatement();
            ResultSet rst = st.executeQuery(sql);
            while (rst.next()) {
                // Agregamos las palabras al ArrayList
                words.add(rst.getString("word"));
            }
            rst.close();
            st.close();
            c.close();
        } catch (SQLException e) {
            throw new GenerateWordException(true, e.getMessage());
        }
        // Elegimos una palabra random del ArrayList con la que jugar
        int random = new java.util.Random().nextInt(words.size());
        String word = words.get(random);
        return word;
    }
}
