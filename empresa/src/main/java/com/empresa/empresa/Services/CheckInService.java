package com.empresa.empresa.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.empresa.empresa.Dto.CheckInDto;
import com.empresa.empresa.Dto.HistoricoCheckInDto;
import com.empresa.empresa.Entities.CheckIn;
import com.empresa.empresa.Entities.Colaborador;
import com.empresa.empresa.Entities.Usuario;
import com.empresa.empresa.Repositories.CheckInRepository;
import com.empresa.empresa.Repositories.ColaboradorRepository;
import com.empresa.empresa.Repositories.UsuarioRepository;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CheckInService {

    @Autowired
    private CheckInRepository checkInRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ColaboradorRepository colaboradorRepository;

    public CheckIn registrarCheckIn(CheckInDto checkInDto, String emailUsuarioLogado) {
        // 1. acha o Usuario pelo email (que vem do token)
        Usuario usuario = usuarioRepository.findByEmail(emailUsuarioLogado)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        // 2. acha o Colaborador vinculado a esse Usuario
        Colaborador colaborador = colaboradorRepository.findByUsuario(usuario);
        if (colaborador == null) {
            throw new RuntimeException("Colaborador não encontrado");
        }

        // 3. cria o objeto CheckIn
        CheckIn novoCheckIn = new CheckIn(

                checkInDto.getSentimento(),
                checkInDto.getDescricaoCheckIn(),
                colaborador);

        // 4. salva no banco
        return checkInRepository.save(novoCheckIn);
    }

    public List<HistoricoCheckInDto> obterHistoricoCheckIns(String emailUsuarioLogado) {
        // 1. acha o Usuario pelo email (que vem do token)
        Usuario usuario = usuarioRepository.findByEmail(emailUsuarioLogado)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        // 2. acha o Colaborador vinculado a esse Usuario
        Colaborador colaborador = colaboradorRepository.findByUsuario(usuario);
        if (colaborador == null) {
            throw new RuntimeException("Colaborador não encontrado");
        }

        // 3. Busca todos os check-ins do colaborador
        List<CheckIn> checkIns = checkInRepository.findByColaborador(colaborador);

        // 4. Converte para DTO e retorna
        return checkIns.stream()
                .map(checkIn -> new HistoricoCheckInDto(
                        checkIn.getIdCheckIn(),
                        checkIn.getDataCheckIns(),
                        checkIn.getDescricaoCheckIn(),
                        checkIn.getSentimento()))
                .collect(Collectors.toList());
    }
}