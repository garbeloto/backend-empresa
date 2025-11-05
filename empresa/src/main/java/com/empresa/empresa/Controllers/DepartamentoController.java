package com.empresa.empresa.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.empresa.empresa.Entities.Departamento;
import com.empresa.empresa.Repositories.DepartamentoRepository;

@RestController
@RequestMapping("/departamentos")
public class DepartamentoController {

    @Autowired
    DepartamentoRepository departamentoRepository;

    @GetMapping
    public List<Departamento> listarDepartamentos() {
        return departamentoRepository.findAll();
    }
    
}
