package brasileirao.dominio;

public class Time {

    private String nome;

    public Time(String nome) {
        this.nome = nome;
    }

    @Override
        public String toString() {
            return  nome;
        }
}
