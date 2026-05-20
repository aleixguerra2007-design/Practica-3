package prog2.vista;

import prog2.adaptador.Adaptador;

import javax.swing.*;
import java.awt.event.*;

public class FrmGestioExemplars extends JDialog {
    private JPanel panelGestioExemplars;
    private JButton btnTornar;
    private JList listExemplars;
    private JButton btnAfegirExemplar;
    private Adaptador adaptador;

    public FrmGestioExemplars(JFrame parent, Adaptador adaptador) {
        super(parent);
        this.adaptador = adaptador;
        setContentPane(panelGestioExemplars);
        setTitle("Gestió exemplars - Biblioteca UB");
        setModal(true);
        setSize(700, 700);
        setLocationRelativeTo(null);
        getRootPane().setDefaultButton(btnTornar);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        listExemplars.setListData(adaptador.recuperarExemplars().toArray());

        btnTornar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        btnAfegirExemplar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FrmAfegirExemplar afegirExemplar = new FrmAfegirExemplar(FrmGestioExemplars.this, adaptador);
                afegirExemplar.setVisible(true);
                listExemplars.setListData(adaptador.recuperarExemplars().toArray());
            }
        });
    }

}
