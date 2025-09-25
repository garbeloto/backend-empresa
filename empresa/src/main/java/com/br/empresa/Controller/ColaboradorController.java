package com.br.empresa.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.empresa.Entities.Colaborador;
import com.br.empresa.Repositories.ColaboradorRepository;

@RestController

@RequestMapping("/colaborador")
public class ColaboradorController {

    @Autowired
    private ColaboradorRepository colaboradorRepository;

    @PostMapping("/adicionar") //adicionar colaborador via postman
    public void adicionarColaborador (@RequestBody Colaborador colaborador){
        colaboradorRepository.save(colaborador);
    }
    
    
}
