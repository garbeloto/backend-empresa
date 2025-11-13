package com.empresa.empresa.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.empresa.empresa.Entities.Colaborador;
import com.empresa.empresa.Entities.Departamento;
import com.empresa.empresa.Entities.Usuario;
import com.empresa.empresa.Repositories.ColaboradorRepository;
import com.empresa.empresa.Repositories.DepartamentoRepository;

@RestController

@RequestMapping("/colaborador")
public class ColaboradorController {

    @Autowired
    ColaboradorRepository colaboradorRepository;

    @Autowired
    DepartamentoRepository departamentoRepository;

    @PostMapping("/cadastrarColaborador") // cadastrar colaborador com senha cript e fazer colaborador dto
    public String cadastrarColaborador(@RequestBody Colaborador colaborador) {
        colaboradorRepository.save(colaborador);

        return "Colaborador cadastrado com sucesso!";
    }

    @GetMapping("/listarColaboradores")
    public List<Colaborador> listarColaboradores() {
        List<Colaborador> listarColaboradores = colaboradorRepository.findAll();

        return listarColaboradores;
    }

    /*
     * @PostMapping("/salvarComValidacoes")
     * public String salvarEmailUnico(@RequestBody Colaborador colaborador) {
     * 
     * String email = colaborador.getEmailColaborador();
     * 
     * // Verifica se o email já existe
     * if (colaboradorRepository.findByEmailColaborador(email).isPresent()) {
     * return "Email já cadastrado!";
     * } else {
     * 
     * int idDepartamento = colaborador.getDepartamento().getIdDepartamento();
     * Departamento departamento =
     * departamentoRepository.findById(idDepartamento).get();
     * 
     * colaborador.setDepartamento(departamento);
     * colaboradorRepository.save(colaborador);
     * 
     * return "Colaborador salvo com sucesso!";
     * }
     * }
     */

    @GetMapping("/buscarNomeColaborador")
    public Object buscarNomeColaborador(@RequestParam String nomeColaborador) {
        List<Colaborador> colaborador = colaboradorRepository
                .findByNomeColaboradorContainingIgnoreCase(nomeColaborador);

        if (colaborador.isEmpty()) { // se a lista é vazia retorna a mensagem
            return "Colaborador não encontrado";
        } else {
            return colaborador;
        }

    }

    @PutMapping("/editarColaborador/{idColaboradorAlterar}")
    public String editarColaborador(@PathVariable int idColaboradorAlterar, @RequestBody Colaborador novoColaborador) {

        Colaborador colaborador = colaboradorRepository.findById(idColaboradorAlterar)
                .orElseThrow(() -> new RuntimeException("Colaborador não encontrado!"));

        colaborador.setNomeColaborador(novoColaborador.getNomeColaborador());
        colaborador.setCpfColaborador(novoColaborador.getCpfColaborador());
        colaborador.setEmailColaborador(novoColaborador.getEmailColaborador());
        colaborador.setSenhaColaborador(novoColaborador.getSenhaColaborador());

        // Atualiza o departamento também
        if (novoColaborador.getDepartamento() != null
                && novoColaborador.getDepartamento().getIdDepartamento() != null) {
            Departamento departamento = departamentoRepository.findById(
                    novoColaborador.getDepartamento().getIdDepartamento())
                    .orElseThrow(() -> new RuntimeException("Departamento não encontrado!"));
            colaborador.setDepartamento(departamento);
        }

        colaboradorRepository.save(colaborador);

        return "Colaborador alterado com sucesso!";
    }

    @DeleteMapping("/deletar/{idColaborador}")
    public String deleteForId(@PathVariable int idColaborador) { // PathVariable = vindo pela url

        if (colaboradorRepository.existsById(idColaborador)) {

            colaboradorRepository.deleteById(idColaborador);

            return "Colaborador deletado com sucesso!";
        } else {
            return "Colaborador não encontrado";
        }
    }

}
