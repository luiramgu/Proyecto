package Entities;

import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;

public class Loteria extends Numero{

    private int Serie;
    private LocalDateTime FechaSorteo;
    private final int Precio= 2000;

    public Loteria(int numero, Vendedor vendedor, int cantidad,int serie,String cliente) {
        super(numero, vendedor, cantidad,cliente);
        this.Serie = serie;
        setFechaSorteo();

    }

    public Loteria() {
        super(0,null ,"Prueba");
        setFechaSorteo();
    }

    public int getMontoAPagar (){
        return this.Precio*super.getCantidad();
    }

    private void setFechaSorteo(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        LocalDateTime now = LocalDateTime.now();
        this.FechaSorteo = now.with((TemporalAdjusters.next(DayOfWeek.FRIDAY)));


    }

    public LocalDateTime getFechaSorteo() {
             return FechaSorteo;
    }

    public int getSerie() {
        return Serie;
    }

    public void setSerie(int serie) {
        Serie = serie;
    }

    public int getPrecio() {
        return Precio;
    }
}
