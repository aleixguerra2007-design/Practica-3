package prog2.vista;

import prog2.adaptador.Adaptador;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class AfegirExemplar extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField txtTitol;
    private JTextField txtAutor;
    private JTextField txtId;
    private JCheckBox chkAdmetPrtL;
    private JLabel labelTitol;
    private JPanel labelAutor;
    private JLabel labelId;
    private Adaptador adaptador;
    //Dades per afegir l'exemplar:
    boolean admetPrestecLlarg;

    public AfegirExemplar(JDialog parent, Adaptador adaptador) {
        setContentPane(contentPane);
        setTitle("Afegir Exemplar - Biblioteca UB");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(800, 800);
        setLocationRelativeTo(null);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        //Button OK inactivo hasta que estén todos los campos rellenos
        buttonOK.setEnabled(false);
        buttonCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        buttonOK.addActionListener(new ActionListener() {
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
                buttonOK.setEnabled(comprobarCampsText());
            }
        });

        txtAutor.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                buttonOK.setEnabled(comprobarCampsText());
            }
        });
        txtId.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                buttonOK.setEnabled(comprobarCampsText());
            }
        });
    }

    public boolean comprobarCampsText(){
        return !txtTitol.getText().isEmpty() && !txtAutor.getText().isEmpty() && !txtId.getText().isEmpty();
    }
}
