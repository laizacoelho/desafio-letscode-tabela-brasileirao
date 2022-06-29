package brasileirao.dados;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LeituraDosDados {

    public static List<String> lerArquivo(Path arquivo) {
        List<String> dadosArquivoPorLinha = new ArrayList<>();

        try (Stream<String> stream = Files.lines(arquivo)) {
            dadosArquivoPorLinha = stream
                    .collect(Collectors.toList());
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        return dadosArquivoPorLinha;
    }
}
