package prog2.vista;

import prog2.adaptador.Adaptador;

import javax.swing.*;
import java.awt.event.*;

public class GestioExemplars extends JDialog {
    private JPanel panelGestioExemplars;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JList listExemplars;
    private JButton btnAfegirExemplar;
    private Adaptador adaptador;

    public GestioExemplars(JFrame parent, Adaptador adaptador) {
        super(parent);
        this.adaptador = adaptador;
        setContentPane(panelGestioExemplars);
        setTitle("Gestió exemplars - Biblioteca UB");
        setModal(true);
        setSize(700, 700);
        setLocationRelativeTo(null);
        getRootPane().setDefaultButton(buttonOK);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        listExemplars.setListData(adaptador.recuperarExemplars().toArray());

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        btnAfegirExemplar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AfegirExemplar afegirExemplar = new AfegirExemplar(GestioExemplars.this, adaptador);
                afegirExemplar.setVisible(true);
                listExemplars.setListData(adaptador.recuperarExemplars().toArray());
            }
        });
    }

}
