package prog2.vista;

import prog2.adaptador.Adaptador;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrmGestioUsuaris extends JDialog {
    private JPanel panelGestioUsuaris;
    private JButton btnTornar;
    private JList listUsuaris;
    private JButton btnAfegirUsuari;
    private Adaptador adaptador;


    public FrmGestioUsuaris(JFrame parent, Adaptador adaptador) {
        super(parent);
        this.adaptador = adaptador;
        setContentPane(panelGestioUsuaris);
        setModal(true);
        setTitle("Gestió Usuaris - Biblioteca UB");
        setSize(1100,700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        getRootPane().setDefaultButton(btnTornar);
        listUsuaris.setListData(adaptador.recuperarUsuaris().toArray());
        listUsuaris.setVisible(true);

        btnTornar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        btnAfegirUsuari.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FrmAfegirUsuari afegirUsuari = new FrmAfegirUsuari(FrmGestioUsuaris.this, adaptador);
                afegirUsuari.setVisible(true);
                listUsuaris.setListData(adaptador.recuperarUsuaris().toArray());
            }
        });
    }
}
