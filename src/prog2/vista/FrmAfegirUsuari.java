package prog2.vista;

import prog2.adaptador.Adaptador;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;

public class FrmAfegirUsuari extends JDialog {
    private JPanel contentPane;
    private JButton btnAcceptar;
    private JButton btnTornar;
    private JTextField txtNom;
    private JTextField txtEmail;
    private JTextField txtAdreca;
    private JCheckBox chkEsEstudiant;
    private JLabel labelAdreca;
    private JLabel labelEmail;
    private JLabel labelNom;
    private JPanel panelDatos1;
    private JPanel panelDatos2;
    private Adaptador adaptador;
    //Dades per afegir l'usuari:
    private boolean esEstudiant;

    public FrmAfegirUsuari(JDialog parent, Adaptador adaptador) {
        super(parent);
        //Ajustes principales
        this.adaptador = adaptador;
        setContentPane(contentPane);
        setTitle("Afegir Usuari - Biblioteca UB");
        setSize(1000, 600);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setModal(true);
        getRootPane().setDefaultButton(btnAcceptar);

        //Estética
        estilizar();

        Color acceptarInactivo = new Color(79, 86, 83);
        Color acceptarActivo = new Color(165, 233, 198);

        //El botón Aceptar no está disponible hasta que estén todos los campos rellenos
        btnAcceptar.setEnabled(false);
        btnAcceptar.setToolTipText("Emplena els camps de text");
        btnAcceptar.setBackground(acceptarInactivo);
        chkEsEstudiant.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                esEstudiant = chkEsEstudiant.isSelected();
            }
        });

        //Confirmar datos
        btnAcceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nom = txtNom.getText();
                String email = txtEmail.getText();
                String adreca = txtAdreca.getText();
                try {
                    adaptador.afegirUsuari(email, nom, adreca, esEstudiant);
                    JOptionPane.showMessageDialog(FrmAfegirUsuari.this, "Usuari afegit correctament", "INFO", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                } catch(BiblioException ex){
                    JOptionPane.showMessageDialog(FrmAfegirUsuari.this, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        //Salir de la ventana sin guardar
        btnTornar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        //El botón Aceptar estará inactivo hasta que los campos de texto estén rellenos
        txtNom.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                btnAcceptar.setEnabled(comprovarCampsText());
                if(comprovarCampsText()){
                    btnAcceptar.setBackground(acceptarActivo);
                    btnAcceptar.setToolTipText("Confirmar dades");
                } else{
                    btnAcceptar.setBackground(acceptarInactivo);
                    btnAcceptar.setToolTipText("Emplena els camps de text");
                }
            }
        });
        txtEmail.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                btnAcceptar.setEnabled(comprovarCampsText());
                if(comprovarCampsText()){
                    btnAcceptar.setBackground(acceptarActivo);
                    btnAcceptar.setToolTipText("Confirmar dades");
                } else{
                    btnAcceptar.setBackground(acceptarInactivo);
                    btnAcceptar.setToolTipText("Emplena els camps de text");
                }
            }
        });
        txtAdreca.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                btnAcceptar.setEnabled(comprovarCampsText());
                if(comprovarCampsText()){
                    btnAcceptar.setBackground(acceptarActivo);
                    btnAcceptar.setToolTipText("Confirmar dades");
                } else{
                    btnAcceptar.setBackground(acceptarInactivo);
                    btnAcceptar.setToolTipText("Emplena els camps de text");
                }
            }
        });
    }

    /**
     * Comprueba que los campos de texto estén rellenos
     * @return
     */
    public boolean comprovarCampsText(){
        return !txtNom.getText().isEmpty() && !txtAdreca.getText().isEmpty() && !txtEmail.getText().isEmpty();
    }

    /**
     * Estiliza la interfaz
     */
    private void estilizar(){
        Color cBackground = new Color(227, 245, 235);
        Color cBotones = new Color(165, 233, 198);
        Color cAcceptar = new Color(127, 203, 97, 255);
        Color cTornar = new Color(226, 88, 88);

        Font fComponentes = new Font("DejaVu Sans Condensed", Font.PLAIN, 22);

        //Cambio de fuentes:
        labelAdreca.setFont(fComponentes);
        labelEmail.setFont(fComponentes);
        labelNom.setFont(fComponentes);

        txtNom.setFont(fComponentes);
        txtAdreca.setFont(fComponentes);
        txtEmail.setFont(fComponentes);

        chkEsEstudiant.setFont(fComponentes);
        btnAcceptar.setFont(fComponentes);
        btnTornar.setFont(fComponentes);

        //Cambio de colores
        contentPane.setBackground(cBackground);
        chkEsEstudiant.setBackground(cBackground);
        panelDatos1.setBackground(cBackground);
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
