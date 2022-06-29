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

public class TratamentoDosDados {

    public static List<Jogo> tratarDados(List<String> listaDeJogosBrasileirao) {
        int tamanho = listaDeJogosBrasileirao.size();
        List<Jogo> jogos = new ArrayList<>();

        for (int i=1; i< tamanho; i++) {
            String[] dadosDoJogo = listaDeJogosBrasileirao.get(i).split(";");

            Integer rodada = Integer.parseInt(dadosDoJogo[0]);

            LocalDate dataDoJogo = tratarDataDoJogo(dadosDoJogo[1]);
            LocalTime horarioDoJogo = tratarHoraDoJogo(dadosDoJogo[2]);
            DayOfWeek diaDoJogo = dataDoJogo.getDayOfWeek();
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

            Jogo jogo = new Jogo(
                    rodada,
                    data,
                    mandante,
                    visitante,
                    vencedor,
                    arena,
                    mandantePlacar,
                    visitantePlacar,
                    estadoMandante,
                    estadoVisitante,
                    estadoVencedor
            );
            jogos.add(jogo);
        }

        return jogos;
    }


    private static LocalDate tratarDataDoJogo (String data) {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        return LocalDate.parse(data, formato);
    }

    private static LocalTime tratarHoraDoJogo(String hora) {
        DateTimeFormatter formato;

        if (hora.equals("")) {
            return null;
        }

        hora = hora.replaceAll("h", ":");
        formato = DateTimeFormatter.ofPattern("HH:mm");

        return LocalTime.parse(hora, formato);
    }
}
