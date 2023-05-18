/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ahorcado.ui;

import ahorcado.model.HangMan;
import java.util.Scanner;

/**
 * Clase con método main que saca por pantalla el juego del ahorcado.
 *
 * @author Miguel Bastos Gándara & Ainhoa Barros Queimadelos
 */
public class MenuGenerator {

    private HangMan hangman;

    /**
     * Método que inicializa el menú del jeugo.
     *
     * @return @throws GenerateWordException Excepción de tipo palabra generada.
     */
    private String showInitMenu() throws GenerateWordException {
        WordGenerator wordGen = null;
        Scanner scan = new Scanner(System.in);
        System.out.println("Escoge modo de juego: ");
        System.out.println("1. Palabra generada aleatoriamente.");
        System.out.println("2. Palabra metida por teclado.");
        System.out.print("ESCRIBE AQUI --> ");
        int option = scan.nextInt();
        switch (option) {
            case 1:
                wordGen = new ArrayWordGenerator();
                break;
            case 2:
                wordGen = new KeyboardWordGenerator();
                break;
            default:
                throw new GenerateWordException(true, "¡Modo de juego no permitido!");
        }
        return wordGen.generateWord();
    }

    /**
     * Método que saca por pantalla el juego del ahorcado.
     */
    public void showGameMenu() {
        Scanner scan = new Scanner(System.in);
        System.out.println("");
        System.out.println("La palabra a adivinar tiene [" + hangman.showHiddenWord().length() + "] caracteres: ");
        System.out.println(hangman.showHiddenWord());
        System.out.println("");
        do {
            System.out.println("Dime una letra: ");
            char attemp = scan.nextLine().charAt(0);
            hangman.tryChar(attemp);
            if (!hangman.isGameOver()) {
                System.out.println("Estado de la palabra: ");
                System.out.println(hangman.showHiddenWord());
                System.out.println("Llevas " + hangman.getFails().size() + " de " + HangMan.MAX_FAILS + " fallos posibles" + " (LETRAS FALLADAS -> " + hangman.getFails().toString() + ")");
                System.out.println("");
                System.out.println("");
            }
        } while (!hangman.isGameOver() || !hangman.maxFailsExceeded());

        if (hangman.getFails().size() == HangMan.MAX_FAILS) {
            System.out.println("");
            System.out.println("GAME OVER");
            System.out.println("La palabra era: " + hangman.showFullWord());
            System.out.println("¡Suerte en la próxima vez!");
        } else {
            System.out.println("");
            System.out.println("¡Has acertado! La palabra era: " + hangman.showFullWord());
            System.out.println("¡ENHORABUENA!");
        }
    }

    /**
     * Método que saca por pantalla el menú de salida del juego del ahorcado.
     *
     * @return True si deja de jugar, false si sigue.
     */
    public boolean showExitMenu() {
        Scanner scan = new Scanner(System.in);
        System.out.println("");
        System.out.println("¿Quieres salir de la partida o continuar jugando?");
        System.out.println("(s-> salir // c-> continuar jugando)");
        char game = scan.nextLine().charAt(0);
        System.out.println("");
        return game == 's';
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Look and Feel épico
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("GTK+".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        MenuGenerator menuGenerator = new MenuGenerator();
        do {
            try {
                menuGenerator.hangman = new HangMan(menuGenerator.showInitMenu());
                menuGenerator.showGameMenu();
            } catch (GenerateWordException e) {
                if (e.isVisible()) {
                    System.out.println("");
                    System.out.println(e.getMessage());
                }
            }
        } while (!menuGenerator.showExitMenu());
    }
}
