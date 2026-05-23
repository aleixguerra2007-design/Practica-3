package prog2.vista;

import prog2.adaptador.Adaptador;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FrmGestioPrestecs extends JDialog {
    private JPanel panelGestioPrestecs;
    private JButton btnTornar;
    private JButton btnVisualitzarPrestec;
    private JButton btnAfegirPrestec;
    private JButton btnRetornarPrestec;
    private JCheckBox chkPrtNoRet;
    private JList listPrestecs;
    private JPanel panelDatos1;
    private JPanel subPanelDatos1;
    private JPanel panelDatos2;
    private JPanel subPanelDatos2;
    private JPanel subPanelDatos2_2;
    private JScrollPane scrollPanePrestec;
    private Adaptador adaptador;


    public FrmGestioPrestecs(JFrame parent, Adaptador adaptador) {
        super(parent);
        this.adaptador = adaptador;
        setContentPane(panelGestioPrestecs);
        setTitle("Gestió Préstecs - Biblioteca UB");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(1000,600);
        setLocationRelativeTo(null);
        setModal(true);
        getRootPane().setDefaultButton(btnTornar);
        listPrestecs.setListData(adaptador.recuperarPrestecs().toArray());
        scrollPanePrestec.setVisible(false);
        listPrestecs.setVisible(false);
        chkPrtNoRet.setVisible(false);

        estilizar();

        Color retornarInactivo = new Color(79, 86, 83);
        Color retornarActivo = new Color(165, 233, 198);

        btnRetornarPrestec.setEnabled(false);
        btnRetornarPrestec.setBackground(retornarInactivo);
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
                scrollPanePrestec.setVisible(true);
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
                        btnRetornarPrestec.setBackground(retornarActivo);
                        btnRetornarPrestec.setToolTipText("Retorna el préstec seleccionat");
                    }
                    else{
                        btnRetornarPrestec.setEnabled(false);
                        btnRetornarPrestec.setBackground(retornarInactivo);
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
                    JOptionPane.showMessageDialog(FrmGestioPrestecs.this, "Préstec retornat correctament", "INFO", JOptionPane.INFORMATION_MESSAGE);
                    btnRetornarPrestec.setEnabled(false);
                    btnRetornarPrestec.setBackground(retornarInactivo);
                    btnRetornarPrestec.setToolTipText("Selecciona un element de la llista");
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

    private void estilizar(){
        Color cBackground = new Color(227, 245, 235);
        Color cBotones = new Color(165, 233, 198);
        Color cHover = new Color(110, 194, 150);
        Color cTornar = new Color(226, 88, 88);

        Font fBotones = new Font("DejaVu Sans Condensed", Font.PLAIN, 24);
        Font fListaYChk = new Font("DejaVu Sans Condensed", Font.PLAIN, 18);

        //Cambio de fuente
        btnTornar.setFont(fBotones);
        btnAfegirPrestec.setFont(fBotones);
        btnRetornarPrestec.setFont(fBotones);
        btnVisualitzarPrestec.setFont(fBotones);

        listPrestecs.setFont(fListaYChk);
        chkPrtNoRet.setFont(fListaYChk);

        //Cambio de colores
        panelGestioPrestecs.setBackground(cBackground);
        panelDatos1.setBackground(cBackground);
        subPanelDatos1.setBackground(cBackground);
        panelDatos2.setBackground(cBackground);
        subPanelDatos2.setBackground(cBackground);
        subPanelDatos2_2.setBackground(cBackground);
        chkPrtNoRet.setBackground(cBackground);

        btnTornar.setBackground(cBotones);
        btnVisualitzarPrestec.setBackground(cBotones);
        btnRetornarPrestec.setBackground(cBotones);
        btnAfegirPrestec.setBackground(cBotones);

        //Cambios de color al interactuar con el cursor
        btnAfegirPrestec.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnAfegirPrestec.setBackground(cHover);
                btnAfegirPrestec.setBorderPainted(false);
                btnAfegirPrestec.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e){
                btnAfegirPrestec.setBackground(cBotones);
                btnAfegirPrestec.setBorderPainted(true);
            }
        });

        btnVisualitzarPrestec.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnVisualitzarPrestec.setBackground(cHover);
                btnVisualitzarPrestec.setBorderPainted(false);
                btnVisualitzarPrestec.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e){
                btnVisualitzarPrestec.setBackground(cBotones);
                btnVisualitzarPrestec.setBorderPainted(true);
            }
        });

        btnRetornarPrestec.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if(btnRetornarPrestec.isEnabled()){
                    btnRetornarPrestec.setBackground(cHover);
                    btnRetornarPrestec.setBorderPainted(false);
                    btnRetornarPrestec.setCursor(new Cursor(Cursor.HAND_CURSOR));
                }
            }

            @Override
            public void mouseExited(MouseEvent e){
                if(btnRetornarPrestec.isEnabled()) {
                    btnRetornarPrestec.setBackground(cBotones);
                    btnRetornarPrestec.setBorderPainted(true);
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
