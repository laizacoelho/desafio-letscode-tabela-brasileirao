package brasileirao.dominio;

import java.util.Objects;

public class Jogo{

    private Integer rodada;
    private DataDoJogo data;
    private Time mandante;
    private Time visitante;
    private Time vencedor;
    private String arena;
    private Integer mandantePlacar;
    private Integer visitantePlacar;
    private String estadoMandante;
    private String estadoVisitante;
    private String estadoVencedor;

    public Jogo(Integer rodada,
                DataDoJogo data,
                Time mandante,
                Time visitante,
                Time vencedor,
                String arena,
                Integer mandantePlacar,
                Integer visitantePlacar,
                String estadoMandante,
                String estadoVisitante,
                String estadoVencedor)
    {
        this.rodada = rodada;
        this.data = data;
        this.mandante = mandante;
        this.visitante = visitante;
        this.vencedor = vencedor;
        this.arena = arena;
        this.mandantePlacar = mandantePlacar;
        this.visitantePlacar = visitantePlacar;
        this.estadoMandante = estadoMandante;
        this.estadoVisitante = estadoVisitante;
        this.estadoVencedor = estadoVencedor;
    }

    public Integer getRodada() {
        return rodada;
    }

    public DataDoJogo getData() {
        return data;
    }

    public Time getVencedor() {
        return vencedor;
    }

    public Time getMandante() {
        return mandante;
    }

    public Time getVisitante() {
        return visitante;
    }

    public Integer getMandantePlacar() {
        return mandantePlacar;
    }

    public Integer getVisitantePlacar() {
        return visitantePlacar;
    }

 /*   @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Jogo)) return false;
        Jogo jogo = (Jogo) o;
        return (Objects.equals(mandantePlacar, jogo.mandantePlacar) && Objects.equals(visitantePlacar, jogo.visitantePlacar)) || (Objects.equals(visitantePlacar, jogo.mandantePlacar) && Objects.equals(mandantePlacar, jogo.visitantePlacar));
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRodada(), getData(), getMandante(), visitante, getVencedor(), arena, getMandantePlacar(), getVisitantePlacar(), estadoMandante, estadoVisitante, estadoVencedor);
    } */
}