package prog2.vista;

import prog2.adaptador.Adaptador;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrmAfegirPrestec extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JComboBox cmbUsuari;
    private JComboBox cmbExemplar;
    private JCheckBox chkEsLlarg;
    private JLabel labelExemplar;
    private JLabel labelUsuari;
    private Adaptador adaptador;
    private boolean prestecLlarg;

    public FrmAfegirPrestec(JDialog parent, Adaptador adaptador) {
        super(parent);
        this.adaptador = adaptador;
        setContentPane(contentPane);
        setModal(true);
        setTitle("Afegir Préstec - Biblioteca UB");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(800, 800);
        setLocationRelativeTo(null);
        getRootPane().setDefaultButton(buttonOK);
        buttonOK.setEnabled(false);

        //Añadimos las opciones correpondientes a los JComboBox:
        cmbUsuari.addItem("...");
        for(String usuari: adaptador.recuperarUsuaris()){
            cmbUsuari.addItem(usuari);
        }

        cmbExemplar.addItem("...");
        for(String exemplar: adaptador.recuperarExemplars()){
            cmbExemplar.addItem(exemplar);
        }

        buttonOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int exemplarPos = cmbExemplar.getSelectedIndex() - 1;
                int usuariPos = cmbUsuari.getSelectedIndex() - 1;

                try {
                    adaptador.afegirPrestec(exemplarPos, usuariPos, prestecLlarg);
                    dispose();
                }catch(BiblioException ex){
                    JOptionPane.showMessageDialog(FrmAfegirPrestec.this, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        chkEsLlarg.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                prestecLlarg = chkEsLlarg.isSelected();
            }
        });
        cmbUsuari.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonOK.setEnabled(comprovarCamps());
            }
        });
        cmbExemplar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonOK.setEnabled(comprovarCamps());
            }
        });
    }
    public boolean comprovarCamps(){
        return !cmbUsuari.getSelectedItem().equals("...") && !cmbExemplar.getSelectedItem().equals("...");
    }
}
