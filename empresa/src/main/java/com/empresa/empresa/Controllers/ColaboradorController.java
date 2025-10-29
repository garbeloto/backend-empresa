package com.empresa.empresa.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.empresa.empresa.Entities.Colaborador;
import com.empresa.empresa.Repositories.ColaboradorRepository;


@RestController

@RequestMapping("/colaborador")
public class ColaboradorController {

    @Autowired
    private ColaboradorRepository colaboradorRepository;


    @PostMapping("/cadastrarColaborador")
    public String cadastrarColaborador(@RequestBody Colaborador colaborador){
        colaboradorRepository.save(colaborador);

        return "Colaborador cadastrado com sucesso!";
    }

    @GetMapping("/buscarNomeColaborador")
    public Object buscarNomeColaborador (@RequestParam String nomeColaborador){
        List<Colaborador> colaborador = colaboradorRepository.findByNomeColaboradorContainingIgnoreCase(nomeColaborador);

        if(colaborador.isEmpty()){ //se a lista é vazia retorna a mensagem
            return "Colaborador não encontrado";
        } else {
            return colaborador;
        }

    }
        @PutMapping("/editarColaborador/{idColaboradorAlterar}")
        public String editarColaborador(@PathVariable int idColaboradorAlterar, @RequestBody Colaborador novoColaborador){

            Colaborador colaborador = colaboradorRepository.findById(idColaboradorAlterar).get();

            colaborador.setNomeColaborador(novoColaborador.getNomeColaborador());
            colaborador.setCpfColaborador(novoColaborador.getCpfColaborador());
            colaborador.setEmailColaborador(novoColaborador.getEmailColaborador());
            colaborador.setSenhaColaborador(novoColaborador.getSenhaColaborador());

            colaboradorRepository.save(colaborador);

            return "Pessoa alterada com sucesso!";
        }

    



    

}
