package com.empresa.empresa.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.empresa.empresa.Dto.CheckInDto;
import com.empresa.empresa.Entities.CheckIn;
import com.empresa.empresa.Entities.Colaborador;
import com.empresa.empresa.Entities.Usuario;
import com.empresa.empresa.Repositories.CheckInRepository;
import com.empresa.empresa.Repositories.ColaboradorRepository;
import com.empresa.empresa.Repositories.UsuarioRepository;

@Service
public class CheckInService {

    @Autowired
    private CheckInRepository checkInRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ColaboradorRepository colaboradorRepository;

    public CheckIn registrarCheckIn(CheckInDto checkInDto, String emailUsuarioLogado) {
        // 1. Acha o Usuario pelo email (que vem do token)
        Usuario usuario = usuarioRepository.findByEmail(emailUsuarioLogado)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        // 2. Acha o Colaborador vinculado a esse Usuario
        Colaborador colaborador = colaboradorRepository.findByUsuarioId(usuario);
        if (colaborador == null) {
            throw new RuntimeException("Colaborador não encontrado");
        }

        // 3. Cria o objeto CheckIn
        CheckIn novoCheckIn = new CheckIn(

                checkInDto.getSentimento(),
                checkInDto.getDescricaoCheckIn(),
                colaborador);

        // 4. Salva no banco
        return checkInRepository.save(novoCheckIn);
    }
}
