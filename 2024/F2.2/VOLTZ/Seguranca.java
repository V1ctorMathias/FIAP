import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Seguranca {

    private byte[] senhaCriptografada;

    public boolean autenticar(String usuario, String usuarioCadastrado, String senha, String senhaCadastrada)
            throws NoSuchAlgorithmException, UnsupportedEncodingException {
        return criptografar(usuario, usuarioCadastrado, senha, senhaCadastrada);
    }

    private boolean criptografar(String usuario, String usuarioCadastrado, String senha, String senhaCadastrada)
            throws NoSuchAlgorithmException,
            UnsupportedEncodingException {

        MessageDigest algorithmCadastrado = MessageDigest.getInstance("MD5");
        byte senhaCadastrado[] = algorithmCadastrado.digest(senhaCadastrada.getBytes("UTF-8"));

        if (senhaCadastrada.contains(senha) && usuarioCadastrado.contains(usuarioCadastrado)) {
            this.senhaCriptografada = senhaCadastrado;
            System.out.println("Seja bem-vindo "+usuario+"!");
            return true;
        } else {
            
            System.out.println("Login ou senha incorretos!");
            return false;
        }
    }

}
