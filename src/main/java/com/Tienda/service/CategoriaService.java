
package com.Tienda.service;

import com.Tienda.domain.Categoria;
import java.util.List;


public interface CategoriaService {
    // metodo que retorna la lista de categorias
    public  List<Categoria> getCategorias(boolean activos);
    
}
