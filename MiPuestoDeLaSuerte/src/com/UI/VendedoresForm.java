package com.UI;

import Entities.Vendedor;
import Repositories.File;
import Repositories.Repositorio;
import com.Services.ListaVendedores;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class VendedoresForm extends JFrame {
    private final Label lblNombre = new Label("Nombre:");
    private final Label lblCodigo = new Label("Codigo:");
    private TextField txtNombre;
    private TextField txtCodigo;
    private JButton btnAgregar;
    private List<Vendedor> lvendedores = new ArrayList<>();
    Repositorio repo;

    public VendedoresForm() throws IOException {
        super.setTitle("Registrar Vendedor");
        super.setSize(400,100);
        GridLayout layout = new GridLayout(3,2);
        super.setLayout(layout);
        super.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        InicializaComponentes();
        repo = new File();

        ListaVendedores lv = new ListaVendedores();
        lv.setListVendedor(repo.getListVendedor(),lvendedores);


    }

    private void InicializaComponentes() {
       //Crea los componentes
        txtNombre = new TextField();
        txtCodigo = new TextField();
        btnAgregar = new JButton("Agregar");

        //Agrega los componentes
        super.add(lblNombre);
        super.add(txtNombre);
        super.add(lblCodigo);
        super.add(txtCodigo);
        super.add(btnAgregar);

        btnAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ListaVendedores lven = new ListaVendedores();
                if (!txtNombre.getText().equals("")&!txtCodigo.getText().equals("")){
                    try {
                        Vendedor ven = new Vendedor(Integer.parseInt(txtCodigo.getText()), txtNombre.getText());
                        lven.add(ven, lvendedores);
                        dispose();
                    }catch(Exception er){
                        JOptionPane.showMessageDialog(null,"El Codigo Debe Ser numerico");
                    }
                } else{
                    JOptionPane.showMessageDialog(null,"Debe Llenar todos los campos");
                }

            }
        });

    }


}
