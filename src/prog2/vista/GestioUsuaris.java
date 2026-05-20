package prog2.vista;

import prog2.adaptador.Adaptador;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GestioUsuaris extends JDialog {
    private JPanel panelGestioUsuaris;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JList listUsuaris;
    private JButton btnAfegirUsuari;
    private Adaptador adaptador;


    public GestioUsuaris(JFrame parent, Adaptador adaptador) {
        super(parent);
        this.adaptador = adaptador;
        setContentPane(panelGestioUsuaris);
        setModal(true);
        setTitle("Gestió Usuaris - Biblioteca UB");
        setSize(1100,700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        getRootPane().setDefaultButton(buttonOK);
        listUsuaris.setListData(adaptador.recuperarUsuaris().toArray());
        listUsuaris.setVisible(true);

        buttonOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        buttonCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        btnAfegirUsuari.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AfegirUsuari afegirUsuari = new AfegirUsuari(GestioUsuaris.this, adaptador);
                afegirUsuari.setVisible(true);
                listUsuaris.setListData(adaptador.recuperarUsuaris().toArray());
            }
        });
    }
}
