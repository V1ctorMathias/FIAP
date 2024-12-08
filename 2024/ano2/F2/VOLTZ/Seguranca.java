import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

public class Seguranca {

    private byte[] senhaCriptografada;

    public byte[] getSenhaCriptografada() {
        return senhaCriptografada;
    }

    public void setSenhaCriptografada(byte[] senhaCriptografada) {
        this.senhaCriptografada = senhaCriptografada;
    }

    public boolean autenticar(String usuario, String senha, Map<String, String> lstUsuarios)
            throws NoSuchAlgorithmException, UnsupportedEncodingException {
        return criptografar(usuario, senha, lstUsuarios);
    }

    private boolean criptografar(String usuario, String senha, Map<String, String> lstUsuarios)
            throws NoSuchAlgorithmException, UnsupportedEncodingException {

        // MessageDigest algorithmCadastrado = MessageDigest.getInstance("MD5");
        // byte senhaCadastrado[] = algorithmCadastrado.digest(usuario.getBytes("UTF-8"));

        boolean resultado = false;

        for (String key : lstUsuarios.keySet()) {
            if (lstUsuarios.get(key).contains(senha)) {
                System.out.println("Seja bem-vindo " + usuario + "!");
                resultado = true;
            } else {
                System.out.println("Login ou senha incorretos!");
                resultado = false;
            }
        }

        return resultado;

    }
}
