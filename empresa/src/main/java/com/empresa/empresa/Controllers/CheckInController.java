package com.empresa.empresa.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.empresa.empresa.Dto.CheckInDto;
import com.empresa.empresa.Services.CheckInService;

import org.springframework.security.core.Authentication;


@RestController
@RequestMapping("api/checkin")

public class CheckInController {

    @Autowired
    private CheckInService checkInService;

    @PostMapping
    public ResponseEntity<String> fazerCheckIn(@RequestBody CheckInDto checkInDto) {
        // Pega o email do usuário que está autenticado (logado) via Token JWT
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String emailLogado = authentication.getName();

        checkInService.registrarCheckIn(checkInDto, emailLogado);

        return ResponseEntity.ok("Check-in realizado com sucesso!");
    }
}
