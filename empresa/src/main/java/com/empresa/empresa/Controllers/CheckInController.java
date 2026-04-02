package com.empresa.empresa.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.empresa.empresa.Dto.CheckInDto;
import com.empresa.empresa.Dto.HistoricoCheckInDto;
import com.empresa.empresa.Services.CheckInService;

import org.springframework.security.core.Authentication;
import java.util.List;

@RestController
@RequestMapping("/checkin")

public class CheckInController {

    @Autowired
    private CheckInService checkInService;

    @PostMapping("/fazerCheckIn")
    public ResponseEntity<String> fazerCheckIn(@RequestBody CheckInDto checkInDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String emailLogado = authentication.getName();

        checkInService.registrarCheckIn(checkInDto, emailLogado);

        return ResponseEntity.ok("Check-in realizado com sucesso!");
    }

    @GetMapping("/historico")
    public ResponseEntity<List<HistoricoCheckInDto>> obterHistorico() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String emailLogado = authentication.getName();

        List<HistoricoCheckInDto> historico = checkInService.obterHistoricoCheckIns(emailLogado);

        return ResponseEntity.ok(historico);
    }
}
