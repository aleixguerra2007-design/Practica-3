package prog2.vista;

import prog2.adaptador.Adaptador;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class FrmGestioExemplars extends JDialog {
    private JPanel panelGestioExemplars;
    private JButton btnTornar;
    private JList listExemplars;
    private JButton btnAfegirExemplar;
    private JPanel panelDatos1;
    private JPanel panelDatos2;
    private Adaptador adaptador;

    public FrmGestioExemplars(JFrame parent, Adaptador adaptador) {
        super(parent);
        this.adaptador = adaptador;
        setContentPane(panelGestioExemplars);
        setTitle("Gestió exemplars - Biblioteca UB");
        setModal(true);
        setSize(1000, 600);
        setLocationRelativeTo(null);
        getRootPane().setDefaultButton(btnTornar);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        listExemplars.setListData(adaptador.recuperarExemplars().toArray());

        estilizar();

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

    private void estilizar(){
        Color cBackground = new Color(227, 245, 235);
        Color cBotones = new Color(165, 233, 198);
        Color cHover = new Color(110, 194, 150);
        Color cTornar = new Color(226, 88, 88);

        Font fBotones = new Font("DejaVu Sans Condensed", Font.PLAIN, 30);
        Font fLista = new Font("DejaVu Sans Condensed", Font.PLAIN, 18);

        //Cambio de fuente
        listExemplars.setFont(fLista);
        btnTornar.setFont(fBotones);
        btnAfegirExemplar.setFont(fBotones);

        //Cambio de colores
        panelGestioExemplars.setBackground(cBackground);
        panelDatos1.setBackground(cBackground);
        panelDatos2.setBackground(cBackground);
        btnAfegirExemplar.setBackground(cBotones);
        btnTornar.setBackground(cBotones);

        //Cambio de colores al interactuar con el cursor:
        btnAfegirExemplar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnAfegirExemplar.setBackground(cHover);
                btnAfegirExemplar.setBorderPainted(false);
                btnAfegirExemplar.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e){
                btnAfegirExemplar.setBackground(cBotones);
                btnAfegirExemplar.setBorderPainted(true);
            }
        });

        btnTornar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnTornar.setBackground(cTornar);
                btnTornar.setBorderPainted(false);
                btnTornar.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e){
                btnTornar.setBackground(cBotones);
                btnTornar.setBorderPainted(true);
            }
        });
    }

}
