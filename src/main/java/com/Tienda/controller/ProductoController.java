package com.Tienda.controller;

import com.Tienda.domain.Categoria;
import com.Tienda.domain.Producto;
import com.Tienda.service.CategoriaService;
import com.Tienda.service.ProductoService;
import com.Tienda.service.impl.FirebaseStorageServiceImpl;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@Slf4j
@RequestMapping("/producto")
public class ProductoController {

    @Autowired
    ProductoService productoservice;

    @Autowired
    CategoriaService categoriaservice;

    @Autowired
    private FirebaseStorageServiceImpl firebasestorageservice;

    @GetMapping("/listado")
    public String inicio(Model model) {
        log.info("consumiendo el recurso /producto/listado");
        List<Producto> productos = productoservice.getProductos(false);
        List<Categoria> categorias = categoriaservice.getCategorias(true);
        model.addAttribute("productos", productos);
        model.addAttribute("categorias", categorias);
        model.addAttribute("totalProductos", productos.size());
        return "/producto/listado";
    }

    @GetMapping("/nuevo")
    public String productoNuevo(Producto producto) {
        return "/producto/modifica";
    }

    @PostMapping("/guardar")
    public String productoGuardar(Producto producto,
            @RequestParam("imagenFile") MultipartFile imagenFile) {
        if (!imagenFile.isEmpty()) {
            productoservice.save(producto);
            producto.setRutaImagen(
                    firebasestorageservice.cargaImagen(
                            imagenFile,
                            "producto",
                            producto.getIdProducto()));
        }
        productoservice.save(producto);
        return "redirect:/producto/listado";
    }

    @GetMapping("/eliminar/{idProducto}")
    public String productoEliminar(Producto producto) {
        productoservice.delete(producto);
        return "redirect:/producto/listado";
    }

    @GetMapping("/modificar/{idProducto}")
    public String productoModificar(Producto producto, Model model) {
        producto = productoservice.getProducto(producto);
        List<Categoria> categorias = categoriaservice.getCategorias(true);
        model.addAttribute("producto", producto);
        model.addAttribute("categorias", categorias);
        return "/producto/modifica";
    }
}
