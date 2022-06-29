package brasileirao.dados;

import brasileirao.dominio.DataDoJogo;
import brasileirao.dominio.Jogo;
import brasileirao.dominio.Time;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class TratamentoDosDados {

    public static List<Jogo> separarDadosPorLinha(List<String> listaDeJogosBrasileirao) {
        int tamanho = listaDeJogosBrasileirao.size();
        List<Jogo> jogos = new ArrayList<>();

        for (int i=1; i< tamanho; i++) {
            String[] dadosDoJogo = listaDeJogosBrasileirao.get(1).split(";");

            Integer rodada = Integer.parseInt(dadosDoJogo[0]);

            LocalDate dataDoJogo = tratarDataDoJogo(dadosDoJogo[1]);
            LocalTime horarioDoJogo = tratarHoraDoJogo(dadosDoJogo[2]);
            DayOfWeek diaDoJogo = tratarDiaDoJogo(dataDoJogo);
            DataDoJogo data = new DataDoJogo(dataDoJogo,horarioDoJogo,diaDoJogo);

            Time mandante = new Time(dadosDoJogo[4]);
            Time visitante = new Time(dadosDoJogo[5]);
            Time vencedor = new Time(dadosDoJogo[6]);

            String arena = dadosDoJogo[7];

            Integer mandantePlacar = Integer.parseInt(dadosDoJogo[8]);
            Integer visitantePlacar = Integer.parseInt(dadosDoJogo[9]);

            String estadoMandante = dadosDoJogo[10];
            String estadoVisitante = dadosDoJogo[11];
            String estadoVencedor = dadosDoJogo[12];

            Jogo jogo = new Jogo(rodada, data, mandante,visitante, vencedor, arena, mandantePlacar, visitantePlacar, estadoMandante, estadoVisitante, estadoVencedor);
            jogos.add(jogo);
        }
        
        return jogos;
    }


    private static LocalDate tratarDataDoJogo (String data) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dataFormatada = LocalDate.parse(data, formatter);

        return dataFormatada;
    }

    private static LocalTime tratarHoraDoJogo (String hora) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("H'h'mm");
        LocalTime horaFormatada = LocalTime.parse(hora, formatter);

        return horaFormatada;
    }

    private static DayOfWeek tratarDiaDoJogo (LocalDate data) {
        DayOfWeek diaFormatado = data.getDayOfWeek();

        return diaFormatado;
    }
}
