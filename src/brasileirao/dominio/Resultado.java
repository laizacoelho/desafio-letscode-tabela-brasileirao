package brasileirao.dominio;

import java.util.Objects;

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

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj.getClass() != this.getClass()) {
            return false;
        }

        Resultado outro = (Resultado) obj;
        if (!outro.mandante.equals(this.mandante)) {
            return false;
        }

        if (!Objects.equals(this.visitante, outro.visitante)) {
            return false;
        }

        if (outro.mandante.equals(this.visitante) && Objects.equals(this.mandante, outro.visitante)) {
            return true;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(mandante, visitante);
    }
}


