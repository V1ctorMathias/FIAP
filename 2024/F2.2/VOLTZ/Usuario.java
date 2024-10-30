import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

public class Usuario {

    private boolean estaLogado = false;
    private String usuario;
    private String senha;
    private String email;

    private String usuarioCadastrado = "";
    private String senhaCadastrada = "";
    private String emailCadastrado = "";

    private Seguranca seguranca = new Seguranca();

    // Getters e Setters para estaLogado
    public boolean isEstaLogado() {
        return estaLogado;
    }

    public void setEstaLogado(boolean estaLogado) {
        this.estaLogado = estaLogado;
    }

    // Getters e Setters para usuario
    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    // Getters e Setters para senha
    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    // Getters e Setters para email
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Getters e Setters para usuarioCadastrado
    public String getUsuarioCadastrado() {
        return usuarioCadastrado;
    }

    public void setUsuarioCadastrado(String usuarioCadastrado) {
        this.usuarioCadastrado = usuarioCadastrado;
    }

    // Getters e Setters para senhaCadastrada
    public String getSenhaCadastrada() {
        return senhaCadastrada;
    }

    public void setSenhaCadastrada(String senhaCadastrada) {
        this.senhaCadastrada = senhaCadastrada;
    }

    // Getters e Setters para emailCadastrado
    public String getEmailCadastrado() {
        return emailCadastrado;
    }

    public void setEmailCadastrado(String emailCadastrado) {
        this.emailCadastrado = emailCadastrado;
    }

    public void cadastrarUsuario(String email, String usuario, String senha) throws NoSuchAlgorithmException, UnsupportedEncodingException {

        if (!usuario.isEmpty() && !senha.isEmpty()) {
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

    public void login(String usuario, String senha) throws NoSuchAlgorithmException, UnsupportedEncodingException {
       this.estaLogado = this.seguranca.autenticar(usuario, this.usuarioCadastrado, senha, this.senhaCadastrada);
    }

    public void logout() {
        this.estaLogado = false;
        System.out.println("Você foi deslogado com sucesso!");
    }
}
