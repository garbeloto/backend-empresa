package com.empresa.empresa.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.empresa.empresa.Entities.Especialidade;

import com.empresa.empresa.Repositories.EspecialidadeRepository;

@RestController
@RequestMapping("/especialidade")
public class EspecialidadeController {
    
    @Autowired
    private EspecialidadeRepository especialiadadeRepository;

    @GetMapping("/listarEspecialidades")
    public ResponseEntity<List<Especialidade>> listarEspecialidades() {
        return ResponseEntity.ok(especialiadadeRepository.findAll());
    }

}
