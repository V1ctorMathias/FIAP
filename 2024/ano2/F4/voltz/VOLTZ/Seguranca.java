import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Map;

public class Seguranca {

    private byte[] senhaCriptografada;
    Conexao connect = new Conexao();

    public byte[] getSenhaCriptografada() {
        return senhaCriptografada;
    }

    public void setSenhaCriptografada(byte[] senhaCriptografada) {
        this.senhaCriptografada = senhaCriptografada;
    }

    public boolean autenticar(String usuario, String senha)
            throws NoSuchAlgorithmException, UnsupportedEncodingException, SQLException {
        return criptografar(usuario, senha);
    }

    private boolean criptografar(String usuario, String senha)
            throws NoSuchAlgorithmException, UnsupportedEncodingException, SQLException {

        // MessageDigest algorithmCadastrado = MessageDigest.getInstance("MD5");
        // byte senhaCadastrado[] = algorithmCadastrado.digest(usuario.getBytes("UTF-8"));

        boolean resultado = false;

            if (this.connect.login(usuario, senha) == true) {
                System.out.println("Seja bem-vindo " + usuario + "!");
                this.connect.logs();
                resultado = true;
            } else {
                System.out.println("Login ou senha incorretos!");
                resultado = false;
            }
       

        return resultado;

    }
}
