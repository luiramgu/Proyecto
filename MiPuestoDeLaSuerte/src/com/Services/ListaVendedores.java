package com.Services;

import Entities.Vendedor;
import Repositories.File;
import Repositories.Repositorio;

import javax.swing.*;
import java.io.IOException;
import java.util.*;

public  class  ListaVendedores extends Vendedor  {

    public void add (Vendedor vendedor,List<Vendedor> list){
        if (!existeCodigo(list,vendedor.getCodigo())){
            list.add(vendedor);
            Repositorio repo= new File();
            repo.saveVendedor(vendedor);
            JOptionPane.showMessageDialog(null,"Vendedor agregado");
        }else{
            JOptionPane.showMessageDialog(null,"Ya existe un Vendedor" +
                    "con este codigo, No se Puede Agregar Vendedor!!!");
        }
    }

    private boolean existeCodigo (List<Vendedor> lv,int codigo){
        boolean aux =  false;
        for (Vendedor ven : lv){
            if (ven.getCodigo()==codigo){
                aux= true  ;
            }
        }

        return aux;
    }

    public void setListVendedor (List<String> listaTexto,List<Vendedor>listaVendedores){

        for (String vendedor : listaTexto){
            String[] linea = vendedor.split(",");
            Vendedor aux = new Vendedor(Integer.parseInt(linea[1]),linea[0]);
            listaVendedores.add(aux);

        }


    }
    public Vendedor getVendorCod (int cod) throws IOException {
        Vendedor vend = new Vendedor();
        List<Vendedor> lvend= new ArrayList<>();
        Repositorio repo = new File();
        setListVendedor(repo.getListVendedor(),lvend );

        for (Vendedor item : lvend){
            if (item.getCodigo()==cod){
                vend = item;
            }
        }

        return vend;
    }

    public String[] getListaCodigoVendedor (List<String> lista){
        String[] laux = new String[lista.size()];
        int i = 0;
        for (String cod : lista){
            String[] linea = cod.split(",");
            laux[i] = linea[1];
            i=i+1;
        }
        return laux;
    }



}