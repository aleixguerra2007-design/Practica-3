package prog2.vista;

import prog2.adaptador.Adaptador;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AppBiblioUB extends JFrame {
    private JPanel panelBiblioUB;
    private JButton btnGestioUsuaris;
    private JButton btnGestioExemplars;
    private JButton btnGestioPrestecs;
    private JButton btnGuardar;
    private JButton btnCarregar;
    private Adaptador adaptador;

    public AppBiblioUB(Adaptador adaptador){
        this.adaptador = adaptador;
        setTitle("AppBiblioUB");
        setContentPane(panelBiblioUB);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1000, 600);
        setLocationRelativeTo(null);
        panelBiblioUB.setBorder(BorderFactory.createEmptyBorder(50, 150, 50, 150));

        estilizar();

        btnGestioUsuaris.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FrmGestioUsuaris gestioUsuaris = new FrmGestioUsuaris(AppBiblioUB.this, adaptador);
                gestioUsuaris.setVisible(true);
            }
        });
        btnGestioExemplars.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FrmGestioExemplars gestioExemplars = new FrmGestioExemplars(AppBiblioUB.this, adaptador);
                gestioExemplars.setVisible(true);
            }
        });
        btnGestioPrestecs.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FrmGestioPrestecs gestioPrestecs = new FrmGestioPrestecs(AppBiblioUB.this, adaptador);
                gestioPrestecs.setVisible(true);
            }
        });
        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int opcion = fileChooser.showSaveDialog(AppBiblioUB.this);
                if(opcion == JFileChooser.APPROVE_OPTION){
                    String camino = fileChooser.getSelectedFile().getAbsolutePath();

                    try{
                        adaptador.guardaDades(camino);
                        JOptionPane.showMessageDialog(AppBiblioUB.this, "Dades guardades correctament", "INFO", JOptionPane.INFORMATION_MESSAGE);
                    } catch(BiblioException ex){
                        JOptionPane.showMessageDialog(AppBiblioUB.this, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        btnCarregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();

                int opcion = fileChooser.showOpenDialog(AppBiblioUB.this);

                if(opcion == JFileChooser.APPROVE_OPTION){
                    String camino = fileChooser.getSelectedFile().getAbsolutePath();

                    try{
                        adaptador.carregaDades(camino);
                        JOptionPane.showMessageDialog(AppBiblioUB.this, "Dades carregades correctament", "INFO", JOptionPane.INFORMATION_MESSAGE);
                    }catch(BiblioException ex){
                        JOptionPane.showMessageDialog(AppBiblioUB.this, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
                    }
                }

            }
        });
    }

    private void estilizar(){
        Color cBackground = new Color(227, 245, 235);
        Color cBotones = new Color(165, 233, 198);
        Color cHover = new Color(110, 194, 150);

        Font fBotones = new Font("DejaVu Sans Condensed", Font.PLAIN, 22);
//Fuentes locales disponibles:
// [Bitstream Charter, C059, Cantarell, cmex10, cmmi10, cmr10, cmsy10, Courier 10 Pitch, D050000L,
// DejaVu Math TeX Gyre, DejaVu Sans, DejaVu Sans Condensed, DejaVu Sans Light, DejaVu Sans Mono,
// DejaVu Serif, DejaVu Serif Condensed, Dialog, DialogInput, Droid Sans Fallback, dsrom10, esint10,
// eufm10, FontAwesome, GLYPHICONS Halflings, Liberation Mono, Liberation Sans, Liberation Sans Narrow,
// Liberation Serif, MathJax_AMS, MathJax_Caligraphic, MathJax_Fraktur, MathJax_Main, MathJax_Math,
// MathJax_SansSerif, MathJax_Script, MathJax_Size1, MathJax_Size2, MathJax_Size3, MathJax_Size4,
// MathJax_Typewriter, MathJax_Vector, MathJax_Vector-Bold, MathJax_WinChrome, MathJax_WinIE6, Monospaced,
// msam10, msbm10, Nimbus Mono PS, Nimbus Roman, Nimbus Sans, Nimbus Sans Narrow, Noto Color Emoji, Noto Mono,
// Noto Sans Mono, OpenSymbol, P052, Quicksand, Quicksand Light, Quicksand Medium, rsfs10, SansSerif, Serif,
// Standard Symbols PS, stmary10, Symbola, URW Bookman, URW Gothic, wasy10, Z003]

        btnGestioExemplars.setFont(fBotones);
        btnCarregar.setFont(fBotones);
        btnGuardar.setFont(fBotones);
        btnGestioPrestecs.setFont(fBotones);
        btnGestioUsuaris.setFont(fBotones);

        panelBiblioUB.setBackground(cBackground);
        btnGestioUsuaris.setBackground(cBotones);
        btnCarregar.setBackground(cBotones);
        btnGuardar.setBackground(cBotones);
        btnGestioPrestecs.setBackground(cBotones);
        btnGestioExemplars.setBackground(cBotones);

        btnGestioUsuaris.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnGestioUsuaris.setBackground(cHover);
                btnGestioUsuaris.setBorderPainted(false);
                btnGestioUsuaris.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e){
                btnGestioUsuaris.setBackground(cBotones);
                btnGestioUsuaris.setBorderPainted(true);
            }
        });

        btnGestioExemplars.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnGestioExemplars.setBackground(cHover);
                btnGestioExemplars.setBorderPainted(false);
                btnGestioExemplars.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e){
                btnGestioExemplars.setBackground(cBotones);
                btnGestioExemplars.setBorderPainted(true);
            }
        });

        btnGestioPrestecs.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnGestioPrestecs.setBackground(cHover);
                btnGestioPrestecs.setBorderPainted(false);
                btnGestioPrestecs.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e){
                btnGestioPrestecs.setBackground(cBotones);
                btnGestioPrestecs.setBorderPainted(true);
            }
        });

        btnGuardar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnGuardar.setBackground(cHover);
                btnGuardar.setBorderPainted(false);
                btnGuardar.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e){
                btnGuardar.setBackground(cBotones);
                btnGuardar.setBorderPainted(true);
            }
        });

        btnCarregar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnCarregar.setBackground(cHover);
                btnCarregar.setBorderPainted(false);
                btnCarregar.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e){
                btnCarregar.setBackground(cBotones);
                btnCarregar.setBorderPainted(true);
            }
        });
    }

    public static void main(String[] args){
        /*
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedLookAndFeelException e) {
            throw new RuntimeException(e);
        }
        */

        SwingUtilities.invokeLater(() -> {
            Adaptador adaptador = new Adaptador();
            AppBiblioUB appBiblioUB = new AppBiblioUB(adaptador);
            appBiblioUB.setVisible(true);
        });
    }
}
