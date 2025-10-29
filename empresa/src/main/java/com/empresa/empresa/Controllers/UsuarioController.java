package com.empresa.empresa.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.empresa.empresa.Entities.Usuario;
import com.empresa.empresa.Repositories.UsuarioRepository;

@RestController

@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    UsuarioRepository usuarioRepository;

    @PostMapping("/cadastrarUsuario")
    public String cadastrarUsuario (@RequestBody Usuario usuario){
        usuarioRepository.save(usuario);

        return "Usuário cadastrado com sucesso!";
    }
    
}
