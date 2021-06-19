package com.UI;

import Entities.Loteria;
import Entities.Tiempos;
import Entities.Vendedor;
import Repositories.File;
import Repositories.Repositorio;
import com.Services.ImprimeComprobantes;
import com.Services.ListaVendedores;
import com.sun.source.tree.NewArrayTree;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class TiemposForm extends  JFrame{
    private List<Tiempos> ListaLoteria = new ArrayList<>();
    private final JLabel lblCiente = new JLabel("Cliente:");
    private JTextField txtCliente;
    private final  JLabel lblNumero = new JLabel("Numero:");
    private JSpinner spnNumero ;
    private final JLabel lblVendedor =new JLabel("Vendedor:");
    private JComboBox cbVendedor ;
    private final JLabel lblMonto = new JLabel("Monto:");
    private JSpinner spnMonto;
    private final JLabel lblTotal = new JLabel("Total:");
    private JLabel lblValorTotal;
    private final JLabel lblFechaSorteo =new JLabel("Fecha Sorteo:");
    private JLabel lblFecha;
    private JButton btnAceptar;
    private Repositorio repo;

    public TiemposForm() throws IOException {
        super.setTitle("Vender Tiempos");
        super.setSize(400,200);
        GridLayout layout = new GridLayout(8,2);
        super.setLayout(layout);
        super.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.ListaLoteria = new ArrayList<>();
        repo = new File();
        InicializaComponentes();
    }

    private void InicializaComponentes() throws IOException {
        //Creamos los componentes
        this.txtCliente = new JTextField();
        SpinnerNumberModel modeloN =
                new SpinnerNumberModel(0, 0, 99, 1);
        this.spnNumero = new JSpinner(modeloN);
        this.lblValorTotal = new JLabel();
        this.btnAceptar = new JButton("Aceptar");

        //Setea los valores del spinner de pedacitos
        SpinnerNumberModel modeloM =
                new SpinnerNumberModel(100, 100, 2000, 1);
        this.spnMonto = new JSpinner(modeloM);
        spnMonto.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                try {
                    spnMonto.commitEdit();
                    lblValorTotal.setText(String.valueOf((Integer) spnMonto.getValue()));
                } catch (ParseException parseException) {
                    parseException.printStackTrace();
                }
            }
        });


        //inicializa el combo box con los codigos de los vendedores
        ListaVendedores lv = new ListaVendedores();
        this.cbVendedor = new JComboBox(lv.getListaCodigoVendedor(repo.getListVendedor()));

        //agregamos los componentes
        this.add(lblCiente);
        this.add(txtCliente);
        this.add(lblNumero);
        this.add(spnNumero);

        this.add(lblMonto);
        this.add(spnMonto);
        this.add(lblVendedor);
        this.add(cbVendedor);

        lblTotal.setText("Total a Pagar:");
        lblValorTotal.setText("100");
        lblValorTotal.setFont(new Font("TimesRoman", Font.BOLD,18));
        this.add(lblTotal);
        this.add(lblValorTotal);
        this.lblFecha = new JLabel();
        this.add(lblFechaSorteo);
        this.add(lblFecha);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDateTime now = LocalDateTime.now();
        this.lblFecha.setText(formatter.format(now).toString());

        this.add(btnAceptar);

        btnAceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (txtCliente.getText().equals("")){
                    JOptionPane.showMessageDialog(null,"Debe Llenar todos los campos");
                }
                else{
                    Vendedor vend = new Vendedor();
                    int codigo = Integer.parseInt(cbVendedor.getSelectedItem().toString());
                    try {
                        vend= lv.getVendorCod(codigo);
                        int numero = (Integer) spnNumero.getValue();

                        Tiempos tiempos= new Tiempos(numero,vend,Integer.parseInt(lblValorTotal.getText()),
                                txtCliente.getText());
                        repo.saveTiempos(tiempos);
                        JOptionPane.showMessageDialog(null,"Tiempos Vendidos, " +
                                "Imprimiendo Comprobate");
                        ImprimeComprobantes.ImprimeTiempos(tiempos);
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }

                }
            }
        });
    }
}
