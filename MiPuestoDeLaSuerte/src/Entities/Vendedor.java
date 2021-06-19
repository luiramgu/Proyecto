package Entities;

public class Vendedor {
    private int Codigo;
    private String Nombre;

    public Vendedor(int codigo, String nombre) {
        Codigo = codigo;
        Nombre = nombre;
    }
    public Vendedor(){

    }
    public int getCodigo() {
        return Codigo;
    }

    public void setCodigo(int codigo) {
        Codigo = codigo;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }
}
