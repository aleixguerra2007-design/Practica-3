package prog2.vista;

import prog2.adaptador.Adaptador;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FrmGestioUsuaris extends JDialog {
    private JPanel panelGestioUsuaris;
    private JButton btnTornar;
    private JList listUsuaris;
    private JButton btnAfegirUsuari;
    private JPanel panelDatos1;
    private JPanel panelDatos2;
    private Adaptador adaptador;


    public FrmGestioUsuaris(JFrame parent, Adaptador adaptador) {
        super(parent);
        //Ajustes principales
        this.adaptador = adaptador;
        setContentPane(panelGestioUsuaris);
        setModal(true);
        setTitle("Gestió Usuaris - Biblioteca UB");
        setSize(1000,600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        getRootPane().setDefaultButton(btnTornar);

        //Mostramos los usuarios registrados
        listUsuaris.setListData(adaptador.recuperarUsuaris().toArray());
        listUsuaris.setVisible(true);

        //Estética
        estilizar();

        //Salir
        btnTornar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        //Abrimos la ventana para añadir un usuario
        btnAfegirUsuari.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FrmAfegirUsuari afegirUsuari = new FrmAfegirUsuari(FrmGestioUsuaris.this, adaptador);
                afegirUsuari.setVisible(true);
                listUsuaris.setListData(adaptador.recuperarUsuaris().toArray());
            }
        });
    }

    /**
     * Estiliza la interfaz
     */
    private void estilizar(){
        Color cBackground = new Color(227, 245, 235);
        Color cBotones = new Color(165, 233, 198);
        Color cHover = new Color(110, 194, 150);
        Color cTornar = new Color(226, 88, 88);

        Font fBotones = new Font("DejaVu Sans Condensed", Font.PLAIN, 30);
        Font fLista = new Font("DejaVu Sans Condensed", Font.PLAIN, 18);

        //Cambio de fuente
        listUsuaris.setFont(fLista);
        btnTornar.setFont(fBotones);
        btnAfegirUsuari.setFont(fBotones);

        //Cambio de colores
        panelGestioUsuaris.setBackground(cBackground);
        panelDatos1.setBackground(cBackground);
        panelDatos2.setBackground(cBackground);
        btnAfegirUsuari.setBackground(cBotones);
        btnTornar.setBackground(cBotones);

        //Cambio de colores al interactuar con el cursor:
        btnAfegirUsuari.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnAfegirUsuari.setBackground(cHover);
                btnAfegirUsuari.setBorderPainted(false);
                btnAfegirUsuari.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e){
                btnAfegirUsuari.setBackground(cBotones);
                btnAfegirUsuari.setBorderPainted(true);
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
