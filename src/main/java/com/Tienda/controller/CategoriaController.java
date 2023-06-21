
package com.Tienda.controller;

import com.Tienda.domain.Categoria;
import com.Tienda.service.CategoriaService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@Slf4j
@RequestMapping("/categoria")
public class CategoriaController {
    
    
    @Autowired
    CategoriaService categoriaservice;
    
    @GetMapping("/listado")
    public String inicio(Model model) {
        
        
        log.info("consumiendo el recurso /categoria/listado");
        List<Categoria> Categorias = categoriaservice.getCategorias(false);
        model.addAttribute("categorias", categoriaservice);
        model.addAttribute(totalCategorias, categoriaservice.size());
        return "/categoria/listado";
    }
    
}
