package Repositories;

import Entities.Loteria;
import Entities.Tiempos;
import Entities.Vendedor;

import java.io.IOException;
import java.util.List;

public interface Repositorio {
    void saveVendedor(Vendedor vendedor);
    void saveLoteria (Loteria loteria);
    void saveTiempos(Tiempos tiempos);
    List<String> getListVendedor() throws IOException;

}
