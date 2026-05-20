package prog2.vista;

import prog2.adaptador.Adaptador;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GestioPrestecs extends JDialog {
    private JPanel panelGestioPrestecs;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JButton btnVisualitzarPrestec;
    private JButton btnAfegirPrestec;
    private JButton btnRetornarPrestec;
    private JCheckBox chkPrtNoRet;
    private JList listPrestecs;
    private Adaptador adaptador;

    public GestioPrestecs(JFrame parent, Adaptador adaptador) {
        super(parent);
        this.adaptador = adaptador;
        setContentPane(panelGestioPrestecs);
        setTitle("Gestió Préstecs - Biblioteca UB");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(700,700);
        setLocationRelativeTo(null);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        listPrestecs.setListData(adaptador.recuperarPrestecs().toArray());
        listPrestecs.setVisible(false);
        chkPrtNoRet.setVisible(false);

        buttonOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        buttonCancel.addActionListener(new ActionListener() {
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
    }
}
