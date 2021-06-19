package Entities;

import javax.swing.*;
import java.time.LocalDateTime;

public class Tiempos extends Numero{

    private final int PrecioMinimo = 100;
    private final int PrecioMaximo = 3000;
    private int Valor;
    private final LocalDateTime FechaSorteo = LocalDateTime.now();;

    public Tiempos(int numero, Vendedor vendedor,int valor,String cliente) {
        super(numero, vendedor,cliente);
        setValor(valor);

    }

    public Tiempos() {

    }

    public LocalDateTime getFechaSorteo() {
        return FechaSorteo;
    }

    public int getValor() {
        return Valor;
    }

    private void setValor(int valor) {
        if (valor<this.PrecioMinimo | valor>this.PrecioMaximo){
            JOptionPane.showMessageDialog(null,
                    "El Monto minimo es de " +this.PrecioMinimo +
                    " y el maximo es de "+this.PrecioMaximo);
        } else{
            this.Valor = valor;
        }
    }

}
