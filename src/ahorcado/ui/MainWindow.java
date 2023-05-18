/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ahorcado.ui;

import ahorcado.model.HangMan;
import java.awt.CardLayout;
import javax.swing.JOptionPane;

/**
 * Clase que crea la ventana del juego del ahorcado.
 *
 * @author Miguel Bastos Gándara & Ainhoa Barros Queimadelos.
 */
public class MainWindow extends javax.swing.JFrame {

    /**
     * Un obxecto da clase HangMan que xestionará o funcionamento da partida
     */
    private HangMan hangman;

    /**
     * Introducirán as etiquetas que representan o número de fallos acumulados
     */
    private CardLayout cardLayout;

    /**
     * Creates new form MainWindow
     */
    public MainWindow() {
        initComponents();
        jDialogChooseGameLevel.setVisible(false);
        jTextFieldTry.setEnabled(false);
        jBtnProbar.setEnabled(false);
        cardLayout = (CardLayout) (jPanelMidRight.getLayout());
        cardLayout.show(jPanelMidRight, "img6");
        this.setLocationRelativeTo(null);
        jDialogChooseGameLevel.setLocationRelativeTo(jPanelMIdMid);
    }

    /**
     * Comeza unha nova partida, escollendo un modo de xogo e mostrando o estado
     * inicial. Como a chamada ao método para xerar a palabra secreta pode xerar
     * unha excepción (GenerateWordException), englobaremos o código deste
     * método nun bloque "try-catch" que no "catch" ostrará un cadro de diálogo
     * coa mensaxe da excepción se o atributo visible vale true.
     */
    private void startNewGame() throws GenerateWordException {
        jDialogChooseGameLevel.setVisible(false);
        jTextFieldTry.setEnabled(true);
        jBtnProbar.setEnabled(true);
        jLblLetrasFalladas.setText("Letras falladas:");
        jLblArrayLetrasFalladas.setText("");
        WordGenerator wordGen;
        switch (jComboBoxJDChoose.getSelectedIndex()) {
            case 0:
                wordGen = new ArrayWordGenerator();
                break;
            case 1:
                wordGen = new GUIKeyboardWordGenerator(this);
                break;
            case 2:
                wordGen = new DBWordGenerator();
                break;
            default:
                wordGen = new FileWordGenerator();
                break;
        }

        try {
            this.hangman = new HangMan(wordGen.generateWord());
        } catch (GenerateWordException e) {
            if (e.isVisible()) {
                JOptionPane.showMessageDialog(this, "Non se puido xerar a palabra ",
                        "ERROR", JOptionPane.OK_OPTION, new javax.swing.ImageIcon(getClass().getResource("/ahorcado/imagenes/badHand.png")));
            }
            jDialogChooseGameLevel.setVisible(true);
            jTextFieldTry.setEnabled(false);
            jBtnProbar.setEnabled(false);
        }

        if ((hangman != null) && (hangman.showHiddenWord() != null)) {
            jLblPalabraConGuiones.setText(hangman.showHiddenWord());
            cardLayout.show(jPanelMidRight, "img" + hangman.getFails().size());
        }
    }

    /**
     * Mostra o estado da partida co estado da palabra oculta, letras falladas e
     * estado do aforcado. Tamén comproba se o xogo remata, informando ao
     * usuario do fin da partida e desactivando os controis do xogo.
     */
    private void showGameStatus() {
        jLblPalabraConGuiones.setText(hangman.showHiddenWord());
        jLblLetrasFalladas.setText("Letras falladas (" + hangman.getFails().size() + "/" + HangMan.MAX_FAILS + "):");
        cardLayout.show(jPanelMidRight, "img" + hangman.getFails().size());
        jTextFieldTry.setText("");
        jTextFieldTry.requestFocus();
        if (hangman.getFails().isEmpty()) {
            jLblArrayLetrasFalladas.setText("");
        } else {
            String fails = "";
            for (int i = 0; i < hangman.getFails().size(); i++) {
                fails += hangman.getFails().get(i);
                if (i < hangman.getFails().size() - 1) {
                    fails += ", ";
                }
            }
            jLblArrayLetrasFalladas.setText(fails);
        }
        if (hangman.maxFailsExceeded()) {
            JOptionPane.showMessageDialog(this, "Fin del juego... !Has sido ahorcado! La palabra oculta era: " + hangman.getWord().showFullWord(),
                    "GAME OVER", JOptionPane.OK_OPTION, new javax.swing.ImageIcon(getClass().getResource("/ahorcado/imagenes/badHand.png")));
            jTextFieldTry.setEnabled(false);
            jBtnProbar.setEnabled(false);
        } else if (hangman.isGameOver()) {
            JOptionPane.showMessageDialog(this, "!Enhorabuena, te has salvado! Has acertado la palabra oculta, que era: " + hangman.getWord().showFullWord(),
                    "GAME OVER", JOptionPane.OK_OPTION, new javax.swing.ImageIcon(getClass().getResource("/ahorcado/imagenes/okeyHand.png")));
            jTextFieldTry.setEnabled(false);
            jBtnProbar.setEnabled(false);
        }
    }

    /**
     * Proba se o caracter introducido na caixa de texto está na palabra oculta
     * e mostra o novo estado do xogo. Comproba que o texto non sexa baleiro e
     * que o primeiro caracter sexa unha letra
     */
    private void tryChar() {
        if (!jTextFieldTry.getText().isEmpty()) {
            hangman.tryChar(jTextFieldTry.getText().charAt(0));
            showGameStatus();
        } else if (jTextFieldTry.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "O cadro de texto non pode estar vacío ",
                    "Palabra incorrecta", JOptionPane.OK_OPTION, new javax.swing.ImageIcon(getClass().getResource("/ahorcado/imagenes/badHand.png")));
        } else {
            JOptionPane.showMessageDialog(this, "A palabra só pode conter letras minúculas da a á z!",
                    "Palabra incorrecta", JOptionPane.OK_OPTION, new javax.swing.ImageIcon(getClass().getResource("/ahorcado/imagenes/badHand.png")));
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDialogChooseGameLevel = new javax.swing.JDialog();
        jComboBoxJDChoose = new javax.swing.JComboBox<>();
        jBtnJDCancel = new javax.swing.JButton();
        jBtnJDOk = new javax.swing.JButton();
        jLblJDIcon = new javax.swing.JLabel();
        jLblJDSeleccionLvl = new javax.swing.JLabel();
        jPanelTitle = new javax.swing.JPanel();
        jPanelTopLeft = new javax.swing.JPanel();
        jPanelTopMid = new javax.swing.JPanel();
        jLlblTitle = new javax.swing.JLabel();
        jPanelTopRight = new javax.swing.JPanel();
        jPanelBody = new javax.swing.JPanel();
        jPanelMidLeft = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLblPalabraAAdivinar = new javax.swing.JLabel();
        jLblLetrasFalladas = new javax.swing.JLabel();
        jLblIntroduceLetra = new javax.swing.JLabel();
        jTextFieldTry = new javax.swing.JTextField();
        jBtnProbar = new javax.swing.JButton();
        jLblPalabraConGuiones = new javax.swing.JLabel();
        jLblArrayLetrasFalladas = new javax.swing.JLabel();
        jLblIntroduceLetra1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanelMIdMid = new javax.swing.JPanel();
        jPanelMidRight = new javax.swing.JPanel();
        jLblImage1 = new javax.swing.JLabel();
        jLblImage2 = new javax.swing.JLabel();
        jLblImage3 = new javax.swing.JLabel();
        jLblImage4 = new javax.swing.JLabel();
        jLblImage5 = new javax.swing.JLabel();
        jLblImage6 = new javax.swing.JLabel();
        jLblImage0 = new javax.swing.JLabel();
        jPanelFooter = new javax.swing.JPanel();
        jPanelBottomLeft = new javax.swing.JPanel();
        jPanelBottomMid = new javax.swing.JPanel();
        jBtnNewGame = new javax.swing.JButton();
        jBtnExit = new javax.swing.JButton();
        jPanelBottomRight = new javax.swing.JPanel();

        jDialogChooseGameLevel.setTitle("Modo de juego");
        jDialogChooseGameLevel.setLocation(new java.awt.Point(200, 200));
        jDialogChooseGameLevel.setMaximumSize(new java.awt.Dimension(554, 180));
        jDialogChooseGameLevel.setMinimumSize(new java.awt.Dimension(554, 180));
        jDialogChooseGameLevel.setModal(true);
        jDialogChooseGameLevel.setPreferredSize(new java.awt.Dimension(554, 180));
        jDialogChooseGameLevel.setResizable(false);

        jComboBoxJDChoose.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Un jugador, generando la palabra al azar", "Dos jugadores, escribiendo la palabra a adivinar", "Dos jugadores, eligiendo la palabra de una BD", "Dos jugadores, sacando la palabra de un fichero" }));
        jComboBoxJDChoose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxJDChooseActionPerformed(evt);
            }
        });

        jBtnJDCancel.setText("Cancel");
        jBtnJDCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnJDCancelActionPerformed(evt);
            }
        });

        jBtnJDOk.setText("OK");
        jBtnJDOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnJDOkActionPerformed(evt);
            }
        });

        jLblJDIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ahorcado/imagenes/QuestionMark.png"))); // NOI18N

        jLblJDSeleccionLvl.setText("Selecciona un modo de juego:");

        javax.swing.GroupLayout jDialogChooseGameLevelLayout = new javax.swing.GroupLayout(jDialogChooseGameLevel.getContentPane());
        jDialogChooseGameLevel.getContentPane().setLayout(jDialogChooseGameLevelLayout);
        jDialogChooseGameLevelLayout.setHorizontalGroup(
            jDialogChooseGameLevelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDialogChooseGameLevelLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLblJDIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jDialogChooseGameLevelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDialogChooseGameLevelLayout.createSequentialGroup()
                        .addGap(0, 260, Short.MAX_VALUE)
                        .addComponent(jBtnJDCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jBtnJDOk, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jComboBoxJDChoose, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jDialogChooseGameLevelLayout.createSequentialGroup()
                        .addComponent(jLblJDSeleccionLvl)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(27, 27, 27))
        );
        jDialogChooseGameLevelLayout.setVerticalGroup(
            jDialogChooseGameLevelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogChooseGameLevelLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jDialogChooseGameLevelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jDialogChooseGameLevelLayout.createSequentialGroup()
                        .addComponent(jLblJDSeleccionLvl, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBoxJDChoose, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLblJDIcon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jDialogChooseGameLevelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBtnJDCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBtnJDOk, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("El ahorcado");
        setMinimumSize(new java.awt.Dimension(800, 710));

        jPanelTitle.setLayout(new java.awt.GridLayout(1, 0));

        javax.swing.GroupLayout jPanelTopLeftLayout = new javax.swing.GroupLayout(jPanelTopLeft);
        jPanelTopLeft.setLayout(jPanelTopLeftLayout);
        jPanelTopLeftLayout.setHorizontalGroup(
            jPanelTopLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 271, Short.MAX_VALUE)
        );
        jPanelTopLeftLayout.setVerticalGroup(
            jPanelTopLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 82, Short.MAX_VALUE)
        );

        jPanelTitle.add(jPanelTopLeft);

        jPanelTopMid.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 30));

        jLlblTitle.setFont(new java.awt.Font("Yu Gothic Medium", 1, 18)); // NOI18N
        jLlblTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLlblTitle.setText("EL JUEGO DEL AHORCADO");
        jPanelTopMid.add(jLlblTitle);

        jPanelTitle.add(jPanelTopMid);

        javax.swing.GroupLayout jPanelTopRightLayout = new javax.swing.GroupLayout(jPanelTopRight);
        jPanelTopRight.setLayout(jPanelTopRightLayout);
        jPanelTopRightLayout.setHorizontalGroup(
            jPanelTopRightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 271, Short.MAX_VALUE)
        );
        jPanelTopRightLayout.setVerticalGroup(
            jPanelTopRightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 82, Short.MAX_VALUE)
        );

        jPanelTitle.add(jPanelTopRight);

        getContentPane().add(jPanelTitle, java.awt.BorderLayout.NORTH);

        jPanelBody.setLayout(new java.awt.BorderLayout());

        jPanelMidLeft.setLayout(new java.awt.GridLayout(3, 1));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 405, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 190, Short.MAX_VALUE)
        );

        jPanelMidLeft.add(jPanel1);

        jLblPalabraAAdivinar.setText("Palabra a adivinar:");

        jLblLetrasFalladas.setText("Letras falladas:");

        jLblIntroduceLetra.setText("Introduce una letra: ");

        jTextFieldTry.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldTryActionPerformed(evt);
            }
        });

        jBtnProbar.setText("PROBAR");
        jBtnProbar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnProbarActionPerformed(evt);
            }
        });

        jLblPalabraConGuiones.setFont(new java.awt.Font("Yu Gothic Medium", 1, 14)); // NOI18N
        jLblPalabraConGuiones.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLblArrayLetrasFalladas.setFont(new java.awt.Font("Yu Gothic Medium", 1, 14)); // NOI18N
        jLblArrayLetrasFalladas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLblPalabraAAdivinar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLblIntroduceLetra1)
                    .addComponent(jLblIntroduceLetra)
                    .addComponent(jLblLetrasFalladas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(28, 28, 28)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jBtnProbar, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLblArrayLetrasFalladas, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLblPalabraConGuiones, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldTry, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(74, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLblPalabraAAdivinar, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLblPalabraConGuiones, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLblLetrasFalladas, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLblArrayLetrasFalladas, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldTry, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLblIntroduceLetra, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBtnProbar)
                    .addComponent(jLblIntroduceLetra1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanelMidLeft.add(jPanel2);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 405, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 190, Short.MAX_VALUE)
        );

        jPanelMidLeft.add(jPanel3);

        jPanelBody.add(jPanelMidLeft, java.awt.BorderLayout.WEST);

        jPanelMIdMid.setPreferredSize(new java.awt.Dimension(266, 200));

        javax.swing.GroupLayout jPanelMIdMidLayout = new javax.swing.GroupLayout(jPanelMIdMid);
        jPanelMIdMid.setLayout(jPanelMIdMidLayout);
        jPanelMIdMidLayout.setHorizontalGroup(
            jPanelMIdMidLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 154, Short.MAX_VALUE)
        );
        jPanelMIdMidLayout.setVerticalGroup(
            jPanelMIdMidLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 571, Short.MAX_VALUE)
        );

        jPanelBody.add(jPanelMIdMid, java.awt.BorderLayout.CENTER);

        jPanelMidRight.setLayout(new java.awt.CardLayout());

        jLblImage1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ahorcado/imagenes/Hangman-1.png"))); // NOI18N
        jPanelMidRight.add(jLblImage1, "img1");

        jLblImage2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ahorcado/imagenes/Hangman-2.png"))); // NOI18N
        jPanelMidRight.add(jLblImage2, "img2");

        jLblImage3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ahorcado/imagenes/Hangman-3.png"))); // NOI18N
        jPanelMidRight.add(jLblImage3, "img3");

        jLblImage4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ahorcado/imagenes/Hangman-4.png"))); // NOI18N
        jPanelMidRight.add(jLblImage4, "img4");

        jLblImage5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ahorcado/imagenes/Hangman-5.png"))); // NOI18N
        jPanelMidRight.add(jLblImage5, "img5");

        jLblImage6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ahorcado/imagenes/Hangman-6.png"))); // NOI18N
        jPanelMidRight.add(jLblImage6, "img6");

        jLblImage0.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ahorcado/imagenes/Hangman-0.png"))); // NOI18N
        jPanelMidRight.add(jLblImage0, "img0");

        jPanelBody.add(jPanelMidRight, java.awt.BorderLayout.EAST);

        getContentPane().add(jPanelBody, java.awt.BorderLayout.CENTER);

        jPanelFooter.setLayout(new java.awt.GridLayout(1, 0));

        javax.swing.GroupLayout jPanelBottomLeftLayout = new javax.swing.GroupLayout(jPanelBottomLeft);
        jPanelBottomLeft.setLayout(jPanelBottomLeftLayout);
        jPanelBottomLeftLayout.setHorizontalGroup(
            jPanelBottomLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 271, Short.MAX_VALUE)
        );
        jPanelBottomLeftLayout.setVerticalGroup(
            jPanelBottomLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 90, Short.MAX_VALUE)
        );

        jPanelFooter.add(jPanelBottomLeft);

        jPanelBottomMid.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 30));

        jBtnNewGame.setText("Nueva Partida");
        jBtnNewGame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnNewGameActionPerformed(evt);
            }
        });
        jPanelBottomMid.add(jBtnNewGame);

        jBtnExit.setText("Salir");
        jBtnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnExitActionPerformed(evt);
            }
        });
        jPanelBottomMid.add(jBtnExit);

        jPanelFooter.add(jPanelBottomMid);

        javax.swing.GroupLayout jPanelBottomRightLayout = new javax.swing.GroupLayout(jPanelBottomRight);
        jPanelBottomRight.setLayout(jPanelBottomRightLayout);
        jPanelBottomRightLayout.setHorizontalGroup(
            jPanelBottomRightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 271, Short.MAX_VALUE)
        );
        jPanelBottomRightLayout.setVerticalGroup(
            jPanelBottomRightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 90, Short.MAX_VALUE)
        );

        jPanelFooter.add(jPanelBottomRight);

        getContentPane().add(jPanelFooter, java.awt.BorderLayout.SOUTH);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBtnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnExitActionPerformed
        dispose();
    }//GEN-LAST:event_jBtnExitActionPerformed

    private void jBtnNewGameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnNewGameActionPerformed
        jDialogChooseGameLevel.setVisible(true);
    }//GEN-LAST:event_jBtnNewGameActionPerformed

    private void jBtnJDCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnJDCancelActionPerformed
        jDialogChooseGameLevel.dispose();
    }//GEN-LAST:event_jBtnJDCancelActionPerformed

    private void jBtnJDOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnJDOkActionPerformed
        try {
            startNewGame();
        } catch (GenerateWordException e) {
            e.getMessage();
        }
    }//GEN-LAST:event_jBtnJDOkActionPerformed

    private void jBtnProbarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnProbarActionPerformed
        tryChar();
    }//GEN-LAST:event_jBtnProbarActionPerformed

    private void jTextFieldTryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldTryActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldTryActionPerformed

    private void jComboBoxJDChooseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxJDChooseActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxJDChooseActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
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
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainWindow().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBtnExit;
    private javax.swing.JButton jBtnJDCancel;
    private javax.swing.JButton jBtnJDOk;
    private javax.swing.JButton jBtnNewGame;
    private javax.swing.JButton jBtnProbar;
    private javax.swing.JComboBox<String> jComboBoxJDChoose;
    private javax.swing.JDialog jDialogChooseGameLevel;
    private javax.swing.JLabel jLblArrayLetrasFalladas;
    private javax.swing.JLabel jLblImage0;
    private javax.swing.JLabel jLblImage1;
    private javax.swing.JLabel jLblImage2;
    private javax.swing.JLabel jLblImage3;
    private javax.swing.JLabel jLblImage4;
    private javax.swing.JLabel jLblImage5;
    private javax.swing.JLabel jLblImage6;
    private javax.swing.JLabel jLblIntroduceLetra;
    private javax.swing.JLabel jLblIntroduceLetra1;
    private javax.swing.JLabel jLblJDIcon;
    private javax.swing.JLabel jLblJDSeleccionLvl;
    private javax.swing.JLabel jLblLetrasFalladas;
    private javax.swing.JLabel jLblPalabraAAdivinar;
    private javax.swing.JLabel jLblPalabraConGuiones;
    private javax.swing.JLabel jLlblTitle;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanelBody;
    private javax.swing.JPanel jPanelBottomLeft;
    private javax.swing.JPanel jPanelBottomMid;
    private javax.swing.JPanel jPanelBottomRight;
    private javax.swing.JPanel jPanelFooter;
    private javax.swing.JPanel jPanelMIdMid;
    private javax.swing.JPanel jPanelMidLeft;
    private javax.swing.JPanel jPanelMidRight;
    private javax.swing.JPanel jPanelTitle;
    private javax.swing.JPanel jPanelTopLeft;
    private javax.swing.JPanel jPanelTopMid;
    private javax.swing.JPanel jPanelTopRight;
    private javax.swing.JTextField jTextFieldTry;
    // End of variables declaration//GEN-END:variables
}
