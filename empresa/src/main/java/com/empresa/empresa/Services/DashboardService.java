package com.empresa.empresa.Services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.empresa.empresa.Dto.DashboardDto;
import com.empresa.empresa.Entities.CheckIn;
import com.empresa.empresa.Repositories.CheckInRepository;
import com.empresa.empresa.Repositories.ColaboradorRepository;

@Service
public class DashboardService {
    
    @Autowired
    private CheckInRepository checkInRepository;

    @Autowired
    private ColaboradorRepository colaboradorRepository;

    public DashboardDto gerarDashboard(int diasParaAnalise) { // Ex: 30 dias
        LocalDate hoje = LocalDate.now();
        LocalDate dataInicio = hoje.minusDays(diasParaAnalise);

        // 1. Buscar dados brutos
        List<CheckIn> checkInsPeriodo = checkInRepository.findByDataCheckInsBetween(dataInicio, hoje);
        long totalColaboradores = colaboradorRepository.count(); // Total de funcionários ativos
        long totalCheckInsHoje = checkInRepository.countByDataCheckIns(hoje);

        // 2. Processar Gráfico de Emoções (Barras)
        // Conta quantas vezes cada sentimento apareceu: { "FELIZ": 10, "TRISTE": 2 }
        Map<String, Long> contagemSentimentos = checkInsPeriodo.stream()
                .collect(Collectors.groupingBy(c -> c.getSentimento().name(), Collectors.counting()));

        // 3. Processar Gráfico de Média de Bem-Estar (Linha)
        List<DashboardDto.MediaBemEstarDiaria> historico = new ArrayList<>();
        
        // Agrupa check-ins por data
        Map<LocalDate, List<CheckIn>> checkInsPorData = checkInsPeriodo.stream()
                .collect(Collectors.groupingBy(CheckIn::getDataCheckIns));

        // Para cada dia no intervalo, calcula a média
        for (LocalDate data = dataInicio; !data.isAfter(hoje); data = data.plusDays(1)) {
            List<CheckIn> doDia = checkInsPorData.getOrDefault(data, new ArrayList<>());
            
            if (doDia.isEmpty()) {
                historico.add(new DashboardDto.MediaBemEstarDiaria(data, 0.0));
            } else {
                double somaPontos = doDia.stream()
                        .mapToInt(this::converterSentimentoParaPontos)
                        .sum();
                double media = somaPontos / doDia.size();
                historico.add(new DashboardDto.MediaBemEstarDiaria(data, media));
            }
        }

        return new DashboardDto(historico, totalColaboradores, totalCheckInsHoje, contagemSentimentos);
    }

    // Regra de Negócio: Convertendo sentimento em nota (0 a 5) para gerar o gráfico de linha
    private Integer converterSentimentoParaPontos(CheckIn checkIn) {
        if (checkIn.getSentimento() == null) return 0;
        
        switch (checkIn.getSentimento()) {
            case FELIZ: return 5;
            case BEM: return 4;
            case NEUTRO: return 3;
            case DESANIMADO: return 2;
            case TRISTE: return 1;
            case IRRITADO: return 0;
            default: return 0;
        }
    }
}
