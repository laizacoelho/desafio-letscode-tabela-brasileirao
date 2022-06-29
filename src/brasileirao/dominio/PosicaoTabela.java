package brasileirao.dominio;

public class PosicaoTabela {
    private Time time;
    private Long vitorias;
    private Long derrotas;
    private Long empates;
    private Long golsPositivos;
    private Long golsSofridos;
    private Long saldoDeGols;

    public PosicaoTabela(Time time,
                         Long vitorias,
                         Long derrotas,
                         Long empates,
                         Long golsPositivos,
                         Long golsSofridos,
                         Long saldoDeGols)
    {
        this.time = time;
        this.vitorias = vitorias;
        this.derrotas = derrotas;
        this.empates = empates;
        this.golsPositivos = golsPositivos;
        this.golsSofridos = golsSofridos;
        this.saldoDeGols = saldoDeGols;
    }

    public Long getPontuacaoTotal() {
        return (vitorias * 3) + empates;
    }

    @Override
    public String toString() {
        return time +
                ", pontos=" + getPontuacaoTotal() +
                ", vitorias=" + vitorias +
                ", derrotas=" + derrotas +
                ", empates=" + empates +
                ", golsPositivos=" + golsPositivos +
                ", golsSofridos=" + golsSofridos +
                ", saldoDeGols=" + saldoDeGols +
                '}';
    }
}

