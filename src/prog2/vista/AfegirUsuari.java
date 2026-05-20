package prog2.vista;

import prog2.adaptador.Adaptador;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class AfegirUsuari extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField txtNom;
    private JTextField txtEmail;
    private JTextField txtAdreca;
    private JCheckBox chkEsEstudiant;
    private JLabel labelAdreca;
    private JLabel labelEmail;
    private JLabel labelNom;
    private Adaptador adaptador;
    //Dades per afegir l'usuari:
    boolean esEstudiant;

    public AfegirUsuari(JDialog parent, Adaptador adaptador) {
        super(parent);
        this.adaptador = adaptador;
        setContentPane(contentPane);
        setSize(700, 700);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        //El botón OK no está disponible hasta que estén todos los campos rellenos
        buttonOK.setEnabled(false);
        chkEsEstudiant.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                esEstudiant = chkEsEstudiant.isSelected();
            }
        });
        buttonOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nom = txtNom.getText();
                String email = txtEmail.getText();
                String adreca = txtAdreca.getText();
                try {
                    adaptador.afegirUsuari(email, nom, adreca, esEstudiant);
                    dispose();
                } catch(BiblioException ex){
                    JOptionPane.showMessageDialog(AfegirUsuari.this, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        buttonCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        txtNom.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                buttonOK.setEnabled(comprobarCampsText());
            }
        });
        txtEmail.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                buttonOK.setEnabled(comprobarCampsText());
            }
        });
        txtAdreca.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                buttonOK.setEnabled(comprobarCampsText());
            }
        });
    }

    public boolean comprobarCampsText(){
        return !txtNom.getText().isEmpty() && !txtAdreca.getText().isEmpty() && !txtEmail.getText().isEmpty();
    }
}
