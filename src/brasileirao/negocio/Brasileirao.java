package brasileirao.negocio;

import brasileirao.dados.LeituraDosDados;
import brasileirao.dados.TratamentoDosDados;
import brasileirao.dominio.Jogo;
import brasileirao.dominio.PosicaoTabela;
import brasileirao.dominio.Resultado;
import brasileirao.dominio.Time;

import java.io.IOException;
import java.nio.file.Path;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.toList;

public class Brasileirao {

//    private Map<Integer, List<Jogo>> brasileirao;
    private List<Jogo> brasileirao;
    private List<Jogo> jogos;
//    private Predicate<Jogo> filtro;
    private int ano;

/*   public Brasileirao(Path arquivo, Predicate<Jogo> filtro) throws IOException {
        this.filtro = filtro;
        this.brasileirao = jogos.stream()
                .filter(filtro) //filtrar por ano
                .collect(Collectors.groupingBy(
                        jogo -> jogo.getRodada(),
                        Collectors.mapping(Function.identity(), Collectors.toList())));
    } */

    public Brasileirao(Path arquivo, int ano) throws IOException {
        List<String> listaJogosBrasileirao = LeituraDosDados.lerArquivo(arquivo);
        this.jogos = TratamentoDosDados.tratarDados(listaJogosBrasileirao);
        this.ano = ano;

        //2020 e 2021 foi um único campeonato devido à pandemia
        if (ano == 2020){
            this.brasileirao =  this.jogos.stream()
                    .filter(jogo -> jogo.getData().getData().getYear() == 2021 ||
                            jogo.getData().getData().getYear() == 2020)
                    .collect(toList());
        } else {
            this.brasileirao = this.jogos.stream()
                    .filter(jogo -> jogo.getData().getData().getYear() == ano)
                    .collect(toList());
        }
    }

    //O que seria a média de gol em um único jogo? Considerei total de gols naquele jogo.
    public Map<Jogo, Integer> mediaGolsPorJogo() {
       return this.brasileirao.stream()
                .collect(Collectors.toMap(Function.identity(),
                        mediaGols -> mediaGols.getMandantePlacar() + mediaGols.getVisitantePlacar()));
    }

    public IntSummaryStatistics estatisticasPorJogo() {

        return this.brasileirao.stream()
                .mapToInt(jogo -> jogo.getVisitantePlacar() + jogo.getMandantePlacar())
                .summaryStatistics();
    }

    public List<Jogo> todosOsJogos() {
        return this.brasileirao;
    }

    public Long totalVitoriasEmCasa() {
       return this.brasileirao.stream()
                .filter(jogo -> jogo.getMandantePlacar() > jogo.getVisitantePlacar())
                .count();
    }

    public Long totalVitoriasForaDeCasa() {
        return this.brasileirao.stream()
                .filter(jogo -> jogo.getMandantePlacar() < jogo.getVisitantePlacar())
                .count();
    }

    public Long totalEmpates() {
        return this.brasileirao.stream()
                .filter(jogo -> jogo.getVencedor().getNome().equals("-"))
                .count();
    }

    public Long totalJogosComMenosDe3Gols() {
        return this.brasileirao.stream()
                .mapToInt(jogo -> jogo.getVisitantePlacar() + jogo.getMandantePlacar())
                .filter(gols -> gols < 3)
                .count();
    }

    public Long totalJogosCom3OuMaisGols() {
        return this.brasileirao.stream()
                .mapToInt(jogo -> jogo.getVisitantePlacar() + jogo.getMandantePlacar())
                .filter(gols -> gols >= 3)
                .count();
    }

    public Map<Resultado, Long> todosOsPlacares() {
        return this.brasileirao.stream()
                .map(resultado -> new Resultado(resultado.getMandantePlacar(), resultado.getVisitantePlacar()))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }

    public Map.Entry<Resultado, Long> placarMaisRepetido() {
        Map<Resultado, Long> placares = todosOsPlacares();

        Optional<Map.Entry<Resultado, Long>> maiorOcorrencia = placares
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue());

        return maiorOcorrencia.orElse(null);

    }

    public Map.Entry<Resultado, Long> placarMenosRepetido() {
        Map<Resultado, Long> placares = todosOsPlacares();

        Optional<Map.Entry<Resultado, Long>> menorOcorrencia = placares
                .entrySet()
                .stream()
                .min(Map.Entry.comparingByValue());

        return menorOcorrencia.orElse(null);

    }

    private List<Time> todosOsTimes() {
        List<Time> mandantes = todosOsJogos()
                .stream()
                .map(Jogo::getVisitante)
                .toList();


        List<Time> visitantes = todosOsJogos()
                .stream()
                .map(Jogo::getVisitante)
                .toList();

        return null;
    }

    /**
     * todos os jogos que cada time foi mandante
     * @return Map<Time, List<Jogo>>
     */
    private Map<Time, List<Jogo>> todosOsJogosPorTimeComoMandantes() {
        return  this.brasileirao.stream()
                .collect(Collectors.groupingBy(Jogo::getMandante));
    }


    /**
     * todos os jogos que cada time foi visitante
     * @return Map<Time, List<Jogo>>
     */
    private Map<Time, List<Jogo>> todosOsJogosPorTimeComoVisitante() {
        return this.brasileirao.stream()
                .collect(Collectors.groupingBy(Jogo::getVisitante));
    }

    public Map<Time, List<Jogo>> todosOsJogosPorTime() {
        Map<Time, List<Jogo>> jogosPorTimeComoMandantes = todosOsJogosPorTimeComoMandantes();
        Map<Time, List<Jogo>> jogosPorTimeComoVisitante = todosOsJogosPorTimeComoVisitante();

        return Stream.concat(
                        jogosPorTimeComoVisitante.entrySet().stream(),
                        jogosPorTimeComoMandantes.entrySet().stream())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (a, b) -> {
                    a.addAll(b);
                    return a;
                }));
    }

    public Map<Time, Map<Boolean, List<Jogo>>> jogosParticionadosPorMandanteTrueVisitanteFalse() {
        Map<Time, List<Jogo>> jogosPorTime = todosOsJogosPorTime();

   /*     List<Map.Entry<Time, List<Jogo>>> collect = jogosPorTime.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, Collectors.partitioningBy((Jogo jogo) -> getKey() == jogo.getMandante() ))); */

        return null;
    }

    public Set<PosicaoTabela> tabela() {
        Set<PosicaoTabela> posicoes = new TreeSet<>();

        Map<Time, List<Jogo>> jogosPorTime = todosOsJogosPorTime();

        for (Map.Entry<Time, List<Jogo>> map : jogosPorTime.entrySet()) {
            Time time = map.getKey();
            List<Jogo> jogos = map.getValue();

            int totalJogosPorTime =  jogos.size();

            long vitorias = jogos.stream()
                    .filter(vencedor -> vencedor.getVencedor().equals(time))
                    .count();

            long empates = jogos.stream()
                    .filter(empate -> empate.getMandantePlacar().equals(empate.getVisitantePlacar()))
                    .count();

            long derrotas = totalJogosPorTime - vitorias - empates;

            long golsComoMandante = jogos.stream()
                    .filter(mandante -> mandante.getMandante().equals(time))
                    .mapToInt(Jogo::getMandantePlacar)
                    .sum();

            long golsComoVisitante = jogos.stream()
                    .filter(visitante -> visitante.getVisitante().equals(time))
                    .mapToInt(Jogo::getVisitantePlacar)
                    .sum();

            long golsPositivos = golsComoMandante + golsComoVisitante;

            long golsSofridosMandante = jogos.stream()
                    .filter(mandante -> !mandante.getMandante().equals(time))
                    .mapToInt(Jogo::getMandantePlacar)
                    .sum();

            long golsSofridosVisitante = jogos.stream()
                    .filter(visitante -> !visitante.getVisitante().equals(time))
                    .mapToInt(Jogo::getVisitantePlacar)
                    .sum();

            long golsSofridos = golsSofridosMandante + golsSofridosVisitante;
            long saldoDeDols = golsPositivos - golsSofridos;

            PosicaoTabela posicaoTabela = new PosicaoTabela(
                    time,
                    vitorias,
                    derrotas,
                    empates,
                    golsPositivos,
                    golsSofridos,
                    saldoDeDols
            );

            posicoes.add(posicaoTabela);
        }
        return posicoes;
    }

    // Criada uma classe estática para este método: LeituraDosDados
   /* public List<Jogo> lerArquivo(Path file) throws IOException {
        return null;
    } */

    // Usado o getDayOfWeek da biblioteca do DayOfWeek, feito na classe TratamentoDosDados
   /* private DayOfWeek getDayOfWeek(String dia) {
        return Map.of(
                "Segunda-feira", DayOfWeek.SUNDAY,
                "Terça-feira", DayOfWeek.SUNDAY,
                "Quarta-feira", DayOfWeek.SUNDAY,
                "Quinta-feira", DayOfWeek.SUNDAY,
                "Sexta-feira", DayOfWeek.SUNDAY,
                "Sábado", DayOfWeek.SUNDAY,
                "Domingo", DayOfWeek.SUNDAY
        ).get(dia);
    } */

    // METODOS EXTRA


    private Map<Integer, Integer> totalGolsPorRodada() {
        return null;
    }

    private Map<Time, Integer> totalDeGolsPorTime() { return null; }

    private Map<Integer, Double> mediaDeGolsPorRodada() {
        return null;
    }



}
