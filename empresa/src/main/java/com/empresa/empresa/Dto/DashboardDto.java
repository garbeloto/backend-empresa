package com.empresa.empresa.Dto;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class DashboardDto {
    
    // gráfico 1: linha do tempo (Média de bem-estar nos últimos 15 dias)
    private List<MediaBemEstarDiaria> historicoBemEstar;

    // gráfico 2: Pizza/Donut (Taxa de Adesão Hoje)
    private long totalColaboradores;
    private long totalCheckInsHoje;
    private long naoRealizaramCheckIn;

    // gráfico 3: Barras (Contagem de Emoções no período)
    private Map<String, Long> contagemSentimentos;

    public DashboardDto(List<MediaBemEstarDiaria> historicoBemEstar, long totalColaboradores, long totalCheckInsHoje, Map<String, Long> contagemSentimentos) {
        this.historicoBemEstar = historicoBemEstar;
        this.totalColaboradores = totalColaboradores;
        this.totalCheckInsHoje = totalCheckInsHoje;
        this.naoRealizaramCheckIn = totalColaboradores - totalCheckInsHoje;
        this.contagemSentimentos = contagemSentimentos;
    }

    public List<MediaBemEstarDiaria> getHistoricoBemEstar() { 
        return historicoBemEstar; 
    }

    public long getTotalColaboradores() { 
        return totalColaboradores; 
    }

    public long getTotalCheckInsHoje() { 
        return totalCheckInsHoje; 
    }

    public long getNaoRealizaramCheckIn() { 
        return naoRealizaramCheckIn; 
    }

    public Map<String, Long> getContagemSentimentos() { 
        return contagemSentimentos; 
    }

    // classe interna auxiliar para o gráfico de linha
    public static class MediaBemEstarDiaria {
        private LocalDate data;
        private Double media; // de 0 a 5

        public MediaBemEstarDiaria(LocalDate data, Double media) {
            this.data = data;
            this.media = media;
        }

        public LocalDate getData() { 
            return data; 
        }

        public Double getMedia() { 
            return media; 
        }
    }
}
