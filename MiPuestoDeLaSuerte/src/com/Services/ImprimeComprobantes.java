package com.Services;

import Entities.Loteria;
import Entities.Numero;
import Entities.Tiempos;
import Entities.Vendedor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class ImprimeComprobantes {


    public static void ImprimeTiempos (Tiempos tiempos){

        System.out.println("***********************************************");
        System.out.println ("Cliente: "+ tiempos.getCliente());
        System.out.println("Numero: "+tiempos.getNumero());
        System.out.println("Monto: "+tiempos.getValor());
        System.out.println("Vendedor: "+tiempos .getVendedor().getNombre() + " Codigo de Vendedor: "+
                tiempos.getVendedor().getCodigo());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        System.out.println("Fecha Del Sorteo: "+formatter.format(tiempos.getFechaSorteo()));
        System.out.println("***********************************************");
    }

    public static void  ImprimeLoteria (Loteria loteria){

        System.out.println("***********************************************");
        System.out.println ("Cliente: "+ loteria.getCliente());
        System.out.println("Numero: "+loteria.getNumero());
        System.out.println("Serie: "+loteria.getSerie());
        System.out.println("Cant Pedazos: "+loteria.getCantidad());
        System.out.println("Monto por Pedazo: "+loteria.getPrecio());
        System.out.println("Total a Pagar: "+ loteria.getMontoAPagar());
        System.out.println("Vendedor: "+loteria.getVendedor().getNombre() + " Codigo de Vendedor: "+
                loteria.getVendedor().getCodigo());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        System.out.println("Fecha Del Sorteo: "+ formatter.format(loteria.getFechaSorteo()));
        System.out.println("***********************************************");
    }
}
