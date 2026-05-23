package prog2.vista;

import prog2.adaptador.Adaptador;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;

public class FrmAfegirExemplar extends JDialog {
    private JPanel contentPane;
    private JButton btnAcceptar;
    private JButton btnTornar;
    private JTextField txtTitol;
    private JTextField txtAutor;
    private JTextField txtId;
    private JCheckBox chkAdmetPrtL;
    private JLabel labelTitol;
    private JPanel panelDatos2;
    private JLabel labelId;
    private JPanel panelDatos;
    private JLabel labelAutor;
    private Adaptador adaptador;
    //Dades per afegir l'exemplar:
    private boolean admetPrestecLlarg;

    public FrmAfegirExemplar(JDialog parent, Adaptador adaptador) {
        super(parent);
        setContentPane(contentPane);
        setTitle("Afegir Exemplar - Biblioteca UB");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setModal(true);
        getRootPane().setDefaultButton(btnAcceptar);
        estilizar();

        Color acceptarInactivo = new Color(79, 86, 83);
        Color acceptarActivo = new Color(165, 233, 198);

        //Button OK inactivo hasta que estén todos los campos rellenos
        btnAcceptar.setEnabled(false);
        btnAcceptar.setBackground(acceptarInactivo);
        btnTornar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        btnAcceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String titol = txtTitol.getText();
                String autor = txtAutor.getText();
                String id = txtId.getText();

                try{
                    adaptador.afegirExemplar(id, titol, autor, admetPrestecLlarg);

                    dispose();
                }catch(BiblioException ex){
                    JOptionPane.showMessageDialog(parent, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        chkAdmetPrtL.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                admetPrestecLlarg = chkAdmetPrtL.isSelected();
            }
        });
        txtTitol.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                btnAcceptar.setEnabled(comprovarCampsText());
                if(comprovarCampsText()){
                    btnAcceptar.setBackground(acceptarActivo);
                } else{
                    btnAcceptar.setBackground(acceptarInactivo);
                }
            }
        });
        txtAutor.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                btnAcceptar.setEnabled(comprovarCampsText());
                if(comprovarCampsText()){
                    btnAcceptar.setBackground(acceptarActivo);
                } else{
                    btnAcceptar.setBackground(acceptarInactivo);
                }
            }
        });
        txtId.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                btnAcceptar.setEnabled(comprovarCampsText());
                if(comprovarCampsText()){
                    btnAcceptar.setBackground(acceptarActivo);
                } else{
                    btnAcceptar.setBackground(acceptarInactivo);
                }
            }
        });
    }

    public boolean comprovarCampsText(){
        return !txtTitol.getText().isEmpty() && !txtAutor.getText().isEmpty() && !txtId.getText().isEmpty();
    }
    private void estilizar(){
        Color cBackground = new Color(227, 245, 235);
        Color cBotones = new Color(165, 233, 198);
        Color cAcceptar = new Color(127, 203, 97, 255);
        Color cTornar = new Color(226, 88, 88);

        Font fComponentes = new Font("DejaVu Sans Condensed", Font.PLAIN, 22);

        //Cambio de fuentes
        labelAutor.setFont(fComponentes);
        labelId.setFont(fComponentes);
        labelTitol.setFont(fComponentes);

        txtAutor.setFont(fComponentes);
        txtId.setFont(fComponentes);
        txtTitol.setFont(fComponentes);

        chkAdmetPrtL.setFont(fComponentes);
        btnAcceptar.setFont(fComponentes);
        btnTornar.setFont(fComponentes);

        //Cambio de colores
        contentPane.setBackground(cBackground);
        panelDatos.setBackground(cBackground);
        chkAdmetPrtL.setBackground(cBackground);
        panelDatos2.setBackground(cBackground);
        btnTornar.setBackground(cBotones);
        btnAcceptar.setBackground(cBotones);

        //Colores al interactuar con el cursor:
        btnAcceptar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if(comprovarCampsText()){
                    btnAcceptar.setBackground(cAcceptar);
                    btnAcceptar.setBorderPainted(false);
                    btnAcceptar.setCursor(new Cursor(Cursor.HAND_CURSOR));
                }
            }

            @Override
            public void mouseExited(MouseEvent e){
                if(comprovarCampsText()){
                    btnAcceptar.setBackground(cBotones);
                    btnAcceptar.setBorderPainted(true);
                }
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
