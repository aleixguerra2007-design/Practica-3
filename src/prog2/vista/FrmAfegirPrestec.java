package prog2.vista;

import prog2.adaptador.Adaptador;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FrmAfegirPrestec extends JDialog {
    private JPanel contentPane;
    private JButton btnAcceptar;
    private JButton btnTornar;
    private JComboBox cmbUsuari;
    private JComboBox cmbExemplar;
    private JCheckBox chkEsLlarg;
    private JLabel labelExemplar;
    private JLabel labelUsuari;
    private JPanel panelDatos1;
    private JPanel panelDatos2;
    private JPanel panelDatos3;
    private JPanel subPanelDatos1;
    private Adaptador adaptador;
    private boolean prestecLlarg;

    public FrmAfegirPrestec(JDialog parent, Adaptador adaptador) {
        super(parent);

        //Ajustes principales
        this.adaptador = adaptador;
        setContentPane(contentPane);
        setModal(true);
        setTitle("Afegir Préstec - Biblioteca UB");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(1000, 600);
        setLocationRelativeTo(null);
        getRootPane().setDefaultButton(btnAcceptar);
        btnAcceptar.setEnabled(false);
        btnAcceptar.setToolTipText("Emplena els camps de text");

        //Estética:
        estilizar();

        Color acceptarInactivo = new Color(79, 86, 83);
        Color acceptarActivo = new Color(165, 233, 198);

        btnAcceptar.setBackground(acceptarInactivo);

        //Añadimos las opciones correpondientes a los JComboBox:
        cmbUsuari.addItem("...");
        for(String usuari: adaptador.recuperarUsuaris()){
            cmbUsuari.addItem(usuari);
        }

        cmbExemplar.addItem("...");
        for(String exemplar: adaptador.recuperarExemplars()){
            cmbExemplar.addItem(exemplar);
        }

        //Confirmar datos
        btnAcceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int exemplarPos = cmbExemplar.getSelectedIndex() - 1;
                int usuariPos = cmbUsuari.getSelectedIndex() - 1;

                try {
                    adaptador.afegirPrestec(exemplarPos, usuariPos, prestecLlarg);
                    JOptionPane.showMessageDialog(FrmAfegirPrestec.this, "Prestec afegit correctament", "INFO", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                }catch(BiblioException ex){
                    JOptionPane.showMessageDialog(FrmAfegirPrestec.this, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
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

        //Check Box de si el préstamo es largo
        chkEsLlarg.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                prestecLlarg = chkEsLlarg.isSelected();
            }
        });

        //El botón Aceptar estará dispoible solo si se ha seleccionado un usuario y ejemplar
        cmbUsuari.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnAcceptar.setEnabled(comprovarCamps());
                if(comprovarCamps()){
                    btnAcceptar.setBackground(acceptarActivo);
                }else{
                    btnAcceptar.setBackground(acceptarInactivo);
                }
            }
        });
        cmbExemplar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnAcceptar.setEnabled(comprovarCamps());
                if(comprovarCamps()){
                    btnAcceptar.setBackground(acceptarActivo);
                    btnAcceptar.setToolTipText("Confirmar dades");
                }else{
                    btnAcceptar.setBackground(acceptarInactivo);
                    btnAcceptar.setToolTipText("Emplena els camps de text");
                }
            }
        });
    }

    /**
     * Comprueba que se ha seleccionado un usuario y un ejemplar
     * @return usuario y ejemplar están seleccionados
     */
    public boolean comprovarCamps(){
        return !cmbUsuari.getSelectedItem().equals("...") && !cmbExemplar.getSelectedItem().equals("...");
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
        Font fComboBoxPequeña = new Font("DejaVu Sans Condensed", Font.PLAIN, 18);

        //Cambio de fuente
        btnAcceptar.setFont(fComponentes);
        btnTornar.setFont(fComponentes);

        cmbExemplar.setFont(fComboBoxPequeña);
        cmbUsuari.setFont(fComboBoxPequeña);

        labelExemplar.setFont(fComponentes);
        labelUsuari.setFont(fComponentes);

        chkEsLlarg.setFont(fComponentes);

        //Cambio de color
        contentPane.setBackground(cBackground);
        panelDatos1.setBackground(cBackground);
        subPanelDatos1.setBackground(cBackground);
        panelDatos2.setBackground(cBackground);
        panelDatos3.setBackground(cBackground);
        chkEsLlarg.setBackground(cBackground);
        btnTornar.setBackground(cBotones);
        btnAcceptar.setBackground(cBotones);
        cmbUsuari.setBackground(Color.WHITE);
        cmbExemplar.setBackground(Color.WHITE);

        btnAcceptar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if(comprovarCamps()){
                    btnAcceptar.setBackground(cAcceptar);
                    btnAcceptar.setBorderPainted(false);
                    btnAcceptar.setCursor(new Cursor(Cursor.HAND_CURSOR));
                }
            }

            @Override
            public void mouseExited(MouseEvent e){
                if(comprovarCamps()){
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
