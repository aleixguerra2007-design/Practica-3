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
        setLocationRelativeTo(null);
        setSize(900, 800);
        btnGestioUsuaris.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GestioUsuaris gestioUsuaris = new GestioUsuaris(AppBiblioUB.this, adaptador);
                gestioUsuaris.setVisible(true);
            }
        });
        btnGestioExemplars.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GestioExemplars gestioExemplars = new GestioExemplars(AppBiblioUB.this, adaptador);
                gestioExemplars.setVisible(true);
            }
        });
        btnGestioPrestecs.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GestioPrestecs gestioPrestecs = new GestioPrestecs(AppBiblioUB.this, adaptador);
                gestioPrestecs.setVisible(true);
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
