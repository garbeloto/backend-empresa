package com.empresa.empresa.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.empresa.empresa.Dto.DashboardDto;
import com.empresa.empresa.Services.DashboardService;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {
    @Autowired
    private DashboardService dashboardService;

    @GetMapping("/dados")
    public ResponseEntity<DashboardDto> getDadosDashboard(@RequestParam(defaultValue = "15") int dias) {
        // pega os dados do dashboard para os últimos '15' dias
        DashboardDto dados = dashboardService.gerarDashboard(dias);
        return ResponseEntity.ok(dados);
    }
}
