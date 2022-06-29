package brasileirao.dominio;

public class Resultado{
    private Integer mandante;
    private Integer visitante;

    public Resultado(Integer mandante, Integer visitante) {
        this.mandante = mandante;
        this.visitante = visitante;
    }

    @Override
    public String toString() {
        return mandante + " x " + visitante;
    }
}


