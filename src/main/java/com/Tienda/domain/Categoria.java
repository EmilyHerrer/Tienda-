
package com.Tienda.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import lombok.Data;

@Data
@Entity
@Table (name="categoria")
public class Categoria implements Serializable{
    
    //version de serializacion
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name='id_categoria')
    private long idcategoria; // se transforma en id_categoria 
    private string descripcion;
    private string rutaImagen;
    private boolean activo;
    
    public Categoria() {
    
}
    public Categoria(string descripcion, string rutaImagen, boolean activo){
        this.descripcion = descripcion;
        this.rutaImagen = rutaImagen;
        this.activo = activo;
    }
}
