package prog2.vista;

import prog2.adaptador.Adaptador;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrmGestioPrestecs extends JDialog {
    private JPanel panelGestioPrestecs;
    private JButton btnTornar;
    private JButton btnVisualitzarPrestec;
    private JButton btnAfegirPrestec;
    private JButton btnRetornarPrestec;
    private JCheckBox chkPrtNoRet;
    private JList listPrestecs;
    private Adaptador adaptador;


    public FrmGestioPrestecs(JFrame parent, Adaptador adaptador) {
        super(parent);
        this.adaptador = adaptador;
        setContentPane(panelGestioPrestecs);
        setTitle("Gestió Préstecs - Biblioteca UB");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(700,700);
        setLocationRelativeTo(null);
        setModal(true);
        getRootPane().setDefaultButton(btnTornar);
        listPrestecs.setListData(adaptador.recuperarPrestecs().toArray());
        listPrestecs.setVisible(false);
        chkPrtNoRet.setVisible(false);
        btnRetornarPrestec.setEnabled(false);
        btnRetornarPrestec.setToolTipText("Selecciona un element de la llista");

        btnTornar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        chkPrtNoRet.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if(chkPrtNoRet.isSelected()){
                    listPrestecs.setListData(adaptador.recuperarPrestecsNoRetornats().toArray());
                }
                else{
                    listPrestecs.setListData(adaptador.recuperarPrestecs().toArray());
                }
            }
        });
        btnVisualitzarPrestec.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listPrestecs.setVisible(true);
                chkPrtNoRet.setVisible(true);
            }
        });
        btnAfegirPrestec.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FrmAfegirPrestec frmAfegirPrestec = new FrmAfegirPrestec(FrmGestioPrestecs.this, adaptador);
                frmAfegirPrestec.setVisible(true);
                actualizarLists();
            }
        });
        listPrestecs.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(listPrestecs.getSelectedValue() instanceof String){
                    if(((String)listPrestecs.getSelectedValue()).contains("retornat=false")){
                        btnRetornarPrestec.setEnabled(true);
                        btnRetornarPrestec.setToolTipText("Retorna el préstec seleccionat");
                    }
                    else{
                        btnRetornarPrestec.setEnabled(false);
                        btnRetornarPrestec.setToolTipText("Selecciona un element de la llista");
                    }
                }
            }
        });
        btnRetornarPrestec.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int indice;
                if(!chkPrtNoRet.isSelected()){
                    indice = adaptador.recuperarPrestecsNoRetornats().indexOf(listPrestecs.getSelectedValue());
                }else{
                    indice = listPrestecs.getSelectedIndex();
                }
                try{
                    adaptador.retornar(indice);
                    actualizarLists();
                }catch(BiblioException ex){
                    JOptionPane.showMessageDialog(FrmGestioPrestecs.this, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
    public void actualizarLists(){
        if(chkPrtNoRet.isSelected()){
            listPrestecs.setListData(adaptador.recuperarPrestecsNoRetornats().toArray());
        }else{
            listPrestecs.setListData(adaptador.recuperarPrestecs().toArray());
        }
    }
}
