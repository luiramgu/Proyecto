package Repositories;

import Entities.Loteria;
import Entities.Tiempos;
import Entities.Vendedor;

import javax.swing.*;
import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

public class File implements Repositorio{
    private final String Vendedores_PATH = "Vendedores.txt";
    private final String Loteria_PATH = "Loteria.txt";
    private final String Tiempos_PATH = "Tiempos.txt";

    public void saveVendedor(Vendedor vendedor) {

        String text = vendedor.getNombre() + "," + Integer.toString(vendedor.getCodigo()) ;
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(Vendedores_PATH, true));
            writer.write(text);
            writer.newLine();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveLoteria(Loteria loteria) {

        String text = loteria.getCliente() + "," + loteria.getNumero() +"," +
                loteria.getSerie()+ ","+ loteria.getCantidad()+","+loteria.getVendedor().getNombre()+"," +
                loteria.getVendedor().getCodigo() + ","+
                loteria.getMontoAPagar()+","+loteria.getFechaSorteo() + "\r\n";
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(Loteria_PATH, true));
            writer.append(text);
            writer.newLine();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveTiempos(Tiempos tiempos) {

        String text = tiempos.getCliente() + "," + tiempos.getNumero() +","
                +tiempos.getVendedor().getNombre()+"," +
                tiempos.getVendedor().getCodigo() + ","+
                tiempos.getCantidad()+","+tiempos.getFechaSorteo() + "\r\n";
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(Tiempos_PATH, true));
            writer.append(text);
            writer.newLine();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public List<String> getListVendedor() throws IOException {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(Vendedores_PATH));
            return reader.lines().collect(Collectors.toList());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,"Archivo No Existe");
            BufferedWriter writer = new BufferedWriter(new FileWriter(Vendedores_PATH));
        }
        return null;
    }

}
