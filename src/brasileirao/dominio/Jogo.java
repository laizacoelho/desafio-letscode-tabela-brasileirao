package brasileirao.dominio;

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

    public void setRodada(Integer rodada) {
        this.rodada = rodada;
    }

    public void setData(DataDoJogo data) {
        this.data = data;
    }

    public void setMandante(Time mandante) {
        this.mandante = mandante;
    }

    public void setVisitante(Time visitante) {
        this.visitante = visitante;
    }

    public void setVencedor(Time vencedor) {
        this.vencedor = vencedor;
    }

    public void setArena(String arena) {
        this.arena = arena;
    }

    public void setMandantePlacar(Integer mandantePlacar) {
        this.mandantePlacar = mandantePlacar;
    }

    public void setVisitantePlacar(Integer visitantePlacar) {
        this.visitantePlacar = visitantePlacar;
    }

    public void setEstadoMandante(String estadoMandante) {
        this.estadoMandante = estadoMandante;
    }

    public void setEstadoVisitante(String estadoVisitante) {
        this.estadoVisitante = estadoVisitante;
    }

    public void setEstadoVencedor(String estadoVencedor) {
        this.estadoVencedor = estadoVencedor;
    }
}
