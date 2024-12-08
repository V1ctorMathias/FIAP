import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Empresa {

    private ArrayList<String> array = new ArrayList<String>();
    private final String arquivo = "empresas.txt";

    // Adiciona o nome da empresa ao ArrayList e atualiza o arquivo
    public void setNomeEmpresa(String nomeEmpresa) {
        array.add(nomeEmpresa);
        atualizarArquivo();
    }

    // Retorna o número de empresas no ArrayList
    public int getLengthLstEmpresa() {
        return array.size();
    }

    // Retorna o nome da empresa pelo índice
    public String getNomeEmpresa(int id) {
        return array.get(id);
    }

    // Remove uma empresa pelo índice e atualiza o arquivo
    public void removeEmpresa(int id) {
        if (id >= 0 && id < array.size()) {
            array.remove(id);
            atualizarArquivo();
        }
    }

    // Atualiza o arquivo TXT com a lista atual de empresas
    private void atualizarArquivo() {
        try (FileWriter writer = new FileWriter(arquivo)) {
            for (String empresa : array) {
                writer.write(empresa + System.lineSeparator());
            }
        } catch (IOException e) {
            System.err.println("Erro ao atualizar o arquivo: " + e.getMessage());
        }
    }
}