
package com.Tienda.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import lombok.Data;

@Data
@Entity
@Table (name="Categoria")
public class Categoria implements Serializable{
    
    //version de serializacion
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private long idCategoria; // se transforma en id_categoria 
    private String descripcion;
    private String rutaImagen;
    private boolean activo;
    
    public Categoria() {
    
}
    public Categoria(String descripcion, String rutaImagen, boolean activo){
        this.descripcion = descripcion;
        this.rutaImagen = rutaImagen;
        this.activo = activo;
    }
}
