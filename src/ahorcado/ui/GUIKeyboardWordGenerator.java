/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ahorcado.ui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

/**
 * Clase que implementará a interface WordGenerator e será a que se utilice
 * dende a clase MainWindow en lugar de KeybooardWordGenerator.
 *
 * @author Miguel Bastos Gándara & Ainhoa Barros Queimadelos.
 */
public class GUIKeyboardWordGenerator implements WordGenerator {

    private JFrame mainWindow;

    /**
     * Constructor de la clase GUIKeyboardWordGenerator.
     *
     * @param mainWindow Ventana del juego.
     */
    public GUIKeyboardWordGenerator(JFrame mainWindow) {
        this.mainWindow = mainWindow;
    }

    /**
     * Devolve true só se todas as letras que teña o String que se recibe como
     * parámetro son letras minúsculas , da "a" a "z". Este método usarase dende
     * o código co método generateWord().
     *
     * @param word palabra introducida
     * @return true se as letras recibidas no String son minúsculas
     */
    private boolean isValid(String word) {
        return word.matches("^[a-z]*$");
    }

    /**
     * Método que pide al usuario una palabra para jugar al juego.
     *
     * @return @throws GenerateWordException Excepción al generar una palabra.
     */
    @Override
    public String generateWord() throws GenerateWordException {
        // Creamos el panel que contendrá los componentes Label y Password
        JPanel panel = new JPanel();
        JLabel label = new JLabel("Introduce a palabra secreta:");
        // Definimos el largo de la casilla para la contraseña
        JPasswordField passwordField = new JPasswordField(15);
        // Agregamos los componentes al panel
        panel.add(label);
        panel.add(passwordField);

        // Definimos el texto de las opciones para aceptar o cancelar
        String[] options = new String[]{"Cancelar", "Aceptar"};

        // Agregamos el panel y las opciones al dialogo
        int option;
        String pass;
        do {
            option = JOptionPane.showOptionDialog(null, panel, "Ingreso de palabra secreta:",
                    JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE,
                    null, options, options[1]);

            if ((option == 0) || (option == JOptionPane.CLOSED_OPTION)) // pressing Cancelar button
            {
                GenerateWordException exception = new GenerateWordException(false, "aparcao");
                throw exception;
            } else {
                pass = "";
                char[] password = passwordField.getPassword();
                for (int i = 0; i < password.length; i++) {
                    pass += password[i];
                }
            }
            if (pass.equals("")) {
                JOptionPane.showMessageDialog(mainWindow,
                        "O cadro de texto non pode estar vacío",
                        "Palabra incorrecta", JOptionPane.OK_OPTION, new javax.swing.ImageIcon(getClass().getResource("/ahorcado/imagenes/badHand.png")));
            } else if (!isValid(pass)) {
                JOptionPane.showMessageDialog(mainWindow,
                        "A palabra só pode conter letras minúsculas da a á z!",
                        "Palabra incorrecta", JOptionPane.OK_OPTION, new javax.swing.ImageIcon(getClass().getResource("/ahorcado/imagenes/badHand.png")));
            }
            passwordField.setText("");
        } while (!isValid(pass));
        return pass;
    }

}
