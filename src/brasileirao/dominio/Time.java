package brasileirao.dominio;

import java.util.Objects;

public class Time {

    private String nome;

    public Time(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    @Override
        public String toString() {
            return  nome;
        }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Time time)) return false;
        return Objects.equals(getNome(), time.getNome());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNome());
    }
}
