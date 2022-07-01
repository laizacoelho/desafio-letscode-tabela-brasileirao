package brasileirao.dominio;

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
}
