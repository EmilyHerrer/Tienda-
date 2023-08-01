
package com.Tienda.service;

import jakarta.mail.MessagingException;


public interface CorreosService {
    public void enviarCorreoHtml(
            String para, 
            String asunto, 
            String contenidoHtml) 
            throws MessagingException;
}
