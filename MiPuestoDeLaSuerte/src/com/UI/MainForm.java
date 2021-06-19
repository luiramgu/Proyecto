package com.UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class MainForm extends JFrame {

    private JButton btnRegistraVendedores;
    private JButton btnVenderLoteria;
    private JButton btnVenderTiempos;
    private JPanel panel;

    public MainForm(){
        super.setTitle("Mi Puesto De la Suerte");
        super.setSize(500,100);
        FlowLayout layout = new FlowLayout();
        super.setLayout(layout);
        super.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        InicializaComponentes();

    }

    private void InicializaComponentes(){
        //Inicializa Componentes y los agrega al form

        this.panel = new JPanel();
        FlowLayout layout = new FlowLayout();
        this.panel.setLayout(layout);
        super.add(panel);
        this.btnRegistraVendedores =  new JButton("Registrar Vendedores");

        btnRegistraVendedores.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               VendedoresForm vend;
                try {
                    vend = new  VendedoresForm();
                    vend.setVisible(true);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }

            }
        });


        this.btnVenderLoteria = new JButton("Vender Loteria");
        btnVenderLoteria.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    JFrame form = new LoteriaForm();
                    form = new LoteriaForm();
                    form.setVisible(true);
                } catch (IOException ioException) {
                    JOptionPane.showMessageDialog(null,"Error abriendo el form");
                }
            }
        });
        this.btnVenderTiempos = new JButton("Vender Tiempos");
        this.btnVenderTiempos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    TiemposForm tiemposForm = new   TiemposForm();
                    tiemposForm.setVisible(true);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }

            }
        });

        //Los agrega al form

        panel.add(btnRegistraVendedores);
        panel.add(btnVenderLoteria);
        panel.add(btnVenderTiempos);
    }



}
