package com.UI;

import Entities.Loteria;
import Entities.Vendedor;
import Repositories.File;
import Repositories.Repositorio;
import com.Services.ImprimeComprobantes;
import com.Services.ListaVendedores;


import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class LoteriaForm extends JFrame{
    private List<Loteria> ListaLoteria = new ArrayList<>();
    private final JLabel lblCiente = new JLabel("Cliente:");
    private JTextField txtCliente;
    private final  JLabel lblNumero = new JLabel("Numero:");
    private JSpinner spnNumero ;
    private final JLabel lblSerie =new JLabel("Serie:");
    private JSpinner spnSerie;
    private final Label lblCantPedacitos = new Label("Cantidad de Pedacitos:");
    private final JLabel lblVendedor =new JLabel("Vendedor:");
    private JComboBox cbVendedor ;
    private JSpinner spnCantidad;
    private final JLabel lblTotal = new JLabel("Total:");
    private JLabel lblValorTotal;
    private final JLabel lblFechaSorteo =new JLabel("Fecha Sorteo:");
    private JLabel lblFecha;
    private JButton btnAceptar;
    private Repositorio repo;
    private JPanel panel;

    public LoteriaForm() throws IOException {
        super.setTitle("Vender Loteria");
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
        SpinnerNumberModel modeloS =
                new SpinnerNumberModel(1, 1, 4, 1);
        this.spnSerie= new JSpinner(modeloS);
        SpinnerNumberModel modeloN =
                new SpinnerNumberModel(0, 0, 99, 1);
        this.spnNumero = new JSpinner(modeloN);
        this.lblValorTotal = new JLabel();
        this.lblFecha =new JLabel();
        this.panel = new JPanel();

        //Setea los valores del spinner de pedacitos
        SpinnerNumberModel modeloP =
                new SpinnerNumberModel(1, 1, 10, 1);
        this.spnCantidad = new JSpinner(modeloP);

        //inicializa el combo box con los codigos de los vendedores
        ListaVendedores lv = new ListaVendedores();
        this.cbVendedor = new JComboBox(lv.getListaCodigoVendedor(repo.getListVendedor()));

        //agregamos los componentes
        this.add(lblCiente);
        this.add(txtCliente);
        this.add(lblNumero);
        this.add(spnNumero);
        this.add(lblSerie);
        this.add(spnSerie);

        this.add(lblCantPedacitos);
        this.add(spnCantidad);
        //agregamos accion de Cambio de Cantidad
        spnCantidad.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                Loteria loteria = new Loteria();
                try {
                    spnCantidad.commitEdit();
                    int value = (Integer) spnCantidad.getValue();
                    lblValorTotal.setText(Integer.toString(value*loteria.getPrecio()));
                } catch (ParseException parseException) {
                    parseException.printStackTrace();
                }
            }
        });
        this.add(lblVendedor);
        this.add(cbVendedor);

        this.add(lblTotal);
        this.add(lblValorTotal);
        lblValorTotal.setText("2000");
        lblValorTotal.setFont(new Font("TimesRoman", Font.BOLD,18));
        Loteria loteria = new Loteria();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        lblFecha.setText(formatter.format(loteria.getFechaSorteo()));
        this.add(lblFechaSorteo);
        this.add(lblFecha);

        this.btnAceptar = new JButton("Aceptar");
        this.add(btnAceptar);
        btnAceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (estaLleno()){
                    int numero = (Integer) spnNumero.getValue();
                    int codigo = Integer.parseInt(cbVendedor.getSelectedItem().toString());
                    Vendedor vendedor = new Vendedor();
                    int cantidad = (Integer) spnCantidad.getValue();
                    int serie = (Integer) spnSerie.getValue();

                    try {
                        vendedor = lv.getVendorCod(codigo);
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                    Loteria loto = new Loteria(numero,vendedor,cantidad,serie,txtCliente.getText());
                    repo.saveLoteria(loto);
                    JOptionPane.showMessageDialog(null,"Loteria Vendida, Imprimiento Comprobante");
                    ImprimeComprobantes.ImprimeLoteria(loto);
                    dispose();
                } else{
                    JOptionPane.showMessageDialog(null,
                            "Error Almacenando, Llene todos los campos");
                }


            }
        });

    }

    private boolean estaLleno(){
        if (txtCliente.getText().equals("")){
            return false;
        } else{return true;}
    }
}
