package brasileirao.dominio;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;

public class DataDoJogo {
    private LocalDate data;
    private LocalTime horario;
    private DayOfWeek dia;

    public DataDoJogo(LocalDate data, LocalTime horario, DayOfWeek dia) {
        this.data = data;
        this.horario = horario;
        this.dia = dia;
    }

    public LocalDate getData() {
        return data;
    }
}

