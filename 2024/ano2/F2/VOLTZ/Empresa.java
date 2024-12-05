import java.util.ArrayList;

public class Empresa {

    private ArrayList<String> array = new ArrayList<String>();

    public void setNomeEmpresa(String nomeEmpresa) {
        array.add(nomeEmpresa);
    }

    public int getLengthLstEmpresa() {
        return array.size();
    }

    public String getNomeEmpresa(int id) {
        return array.get(id);
    }

    public void removeEmpresa(int id) {
        array.remove(id);
    }

}
