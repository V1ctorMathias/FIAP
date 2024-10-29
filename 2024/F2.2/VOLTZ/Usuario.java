import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

public class Usuario {

    public boolean estaLogado = false;

    public String usuario;
    private String senha;
    private String email;

    private String usuarioCadastrado = "";
    private String senhaCadastrada = "";
    private String emailCadastrado = "";

    Seguranca seguranca = new Seguranca();

    public void cadastrarUsuario(String email, String usuario, String senha) throws NoSuchAlgorithmException, UnsupportedEncodingException {

        if (usuario != "" && senha != "") {
            if (this.usuarioCadastrado.contains(usuario) && this.senhaCadastrada.contains(senha)) {

                System.out.println("Você já fez o cadastro!");

            } else {

                this.usuario = usuario;
                this.senha = senha;
                
                this.usuarioCadastrado = usuario;
                this.senhaCadastrada = senha;

                System.out.println("Seu usuário foi cadastrado com sucesso!");

            }
        }
    }

    public void login (String usuario, String senha) throws NoSuchAlgorithmException, UnsupportedEncodingException {
       this.estaLogado = this.seguranca.autenticar(usuario, this.usuarioCadastrado, senha, this.senhaCadastrada);
    }

    public void logout () {
        this.estaLogado = false;
        System.out.println("Você foi deslogado com sucesso!");
    }

}
