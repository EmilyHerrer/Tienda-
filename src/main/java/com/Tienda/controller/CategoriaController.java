package com.Tienda.controller;

import com.Tienda.domain.Categoria;
import com.Tienda.service.CategoriaService;
import com.Tienda.service.impl.FirebaseStorageServiceImpl;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@Slf4j
@RequestMapping("/categoria")
public class CategoriaController {

    @Autowired
    CategoriaService categoriaservice;

    @GetMapping("/listado")
    public String inicio(Model model, HttpSession session) {

        log.info("consumiendo el recurso /categoria/listado");
        List<Categoria> categorias = categoriaservice.getCategorias(false);
        //List<Categoria> categorias = categoriaservice.getPorDescripcion("Teclados");
//        String imagen = (String)session.getAttribute("usuarioImagen");
//        model.addAttribute("avatar", imagen);
        model.addAttribute("categorias", categorias);
        model.addAttribute("totalCategorias", categorias.size());
//        model.addAttribute("EmailUsuario", (String)session.getAttribute("Email"));
        return "/categoria/listado";
    }

    @GetMapping("/nuevo")
    public String categoriaNuevo(Categoria categoria) {
        return "/categoria/modifica";
    }

    @Autowired
    private FirebaseStorageServiceImpl firebasestorageservice;

    @PostMapping("/guardar")
    public String categoriaGuardar(Categoria categoria,
            @RequestParam("imagenFile") MultipartFile imagenFile) {
        if (!imagenFile.isEmpty()) {
            categoriaservice.save(categoria);
            categoria.setRutaImagen(
                    firebasestorageservice.cargaImagen(
                            imagenFile,
                            "categoria",
                            categoria.getIdCategoria()));
        }
        categoriaservice.save(categoria);
        return "redirect:/categoria/listado";
    }

    @GetMapping("/eliminar/{idCategoria}")
    public String categoriaEliminar(Categoria categoria) {
        categoriaservice.delete(categoria);
        return "redirect:/categoria/listado";
    }

    @GetMapping("/modificar/{idCategoria}")
    public String categoriaModificar(Categoria categoria, Model model) {
        categoria = categoriaservice.getCategoria(categoria);
        model.addAttribute("categoria", categoria);
        return "/categoria/modifica";
    }
}
