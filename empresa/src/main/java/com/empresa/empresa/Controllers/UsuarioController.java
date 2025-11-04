package com.empresa.empresa.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.empresa.empresa.Dto.UsuarioLoginDto;
import com.empresa.empresa.Entities.Usuario;
import com.empresa.empresa.Repositories.UsuarioRepository;

@RestController

@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @PostMapping("/cadastrarComValidacoes")
    public String cadastrarEmailUnico (@RequestBody Usuario usuario){ //verificar se o email já existe no banco de dados

        String email = usuario.getEmailUsuario();

        if (usuarioRepository.findByEmailUsuario(email).isPresent()) {
            return "Email já cadastrado!";
        } else {

            String senhaCript = passwordEncoder.encode(usuario.getSenhaUsuario());
            usuario.setSenhaUsuario(senhaCript);

            usuarioRepository.save(usuario);
            return "Usuário cadastrado com sucesso!";
        }
    
    }

    @PostMapping("/loginUsuario") //login 
    private String login (@RequestBody UsuarioLoginDto usuarioLoginDto) {

        Usuario usuario = usuarioRepository.findByEmailUsuario(usuarioLoginDto.getEmailUsuario()).get();

        if (usuario != null) {
            
            if (passwordEncoder.matches(usuarioLoginDto.getSenhaUsuario(), usuario.getSenhaUsuario())) {
                
                return "Login realizado com sucesso!";
            } else {
                return "Senha incorreta.";
            }


        } else {
            return "Email não cadastrado.";
        }
    }
}
