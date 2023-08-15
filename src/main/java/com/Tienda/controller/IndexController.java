package com.Tienda.controller;

import com.Tienda.dao.UsuarioDao;
import com.Tienda.domain.Usuario;
import com.Tienda.service.ProductoService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @Autowired
    ProductoService productoService;

    @RequestMapping("/")
    public String page(Model model, HttpSession session) {

        var productos = productoService.getProductos(true);
        model.addAttribute("productos", productos);
        return "index";
    }

}
