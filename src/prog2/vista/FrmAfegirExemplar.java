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
    private JPanel labelAutor;
    private JLabel labelId;
    private Adaptador adaptador;
    //Dades per afegir l'exemplar:
    private boolean admetPrestecLlarg;

    public FrmAfegirExemplar(JDialog parent, Adaptador adaptador) {
        setContentPane(contentPane);
        setTitle("Afegir Exemplar - Biblioteca UB");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(800, 800);
        setLocationRelativeTo(null);
        setModal(true);
        getRootPane().setDefaultButton(btnAcceptar);

        //Button OK inactivo hasta que estén todos los campos rellenos
        btnAcceptar.setEnabled(false);
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
                btnAcceptar.setEnabled(comprobarCampsText());
            }
        });
        txtAutor.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                btnAcceptar.setEnabled(comprobarCampsText());
            }
        });
        txtId.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                btnAcceptar.setEnabled(comprobarCampsText());
            }
        });
        btnTornar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnTornar.setBackground(Color.RED);
            }
        });
    }

    public boolean comprobarCampsText(){
        return !txtTitol.getText().isEmpty() && !txtAutor.getText().isEmpty() && !txtId.getText().isEmpty();
    }
}
