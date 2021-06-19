package Entities;

import java.util.Random;

public class Numero {

    private int Numero;
    private Vendedor Vendedor;
    private int Cantidad;
    private String Cliente;

    public Numero(int numero, Vendedor vendedor,String cliente) {
        Numero = numero;
        Vendedor = vendedor;
        Cliente = cliente;
    }

    public Numero(int numero, Vendedor vendedor, int cantidad,String cliente) {
        Numero = numero;
        Vendedor = vendedor;
        Cantidad = cantidad;
        Cliente=cliente;
    }

    public Numero() {
    }

    public int galloTapado (){
        Random rand = new Random();
        int limite = 99;
        return rand.nextInt(limite);
    }

    public String getCliente() {
        return Cliente;
    }

    public void setCliente(String cliente) {
        Cliente = cliente;
    }

    public int getNumero() {
        return Numero;
    }

    public void setNumero(int numero) {
        Numero = numero;
    }

    public Vendedor getVendedor() {
        return Vendedor;
    }

    public void setVendedor(Vendedor vendedor) {
        Vendedor = vendedor;
    }

    public int getCantidad() {
        return Cantidad;
    }

    public void setCantidad(int cantidad) {
        Cantidad = cantidad;
    }
}
