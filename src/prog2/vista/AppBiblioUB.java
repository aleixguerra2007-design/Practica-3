package prog2.vista;

import prog2.adaptador.Adaptador;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        setSize(900, 800);
        setLocationRelativeTo(null);

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

    public static void main(String[] args){
        SwingUtilities.invokeLater(() -> {
            Adaptador adaptador = new Adaptador();
            AppBiblioUB appBiblioUB = new AppBiblioUB(adaptador);
            appBiblioUB.setVisible(true);
        });
    }
}
