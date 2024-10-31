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

    public boolean isEstaLogado() {
        return estaLogado;
    }

    public void setEstaLogado(boolean estaLogado) {
        this.estaLogado = estaLogado;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsuarioCadastrado() {
        return usuarioCadastrado;
    }

    public void setUsuarioCadastrado(String usuarioCadastrado) {
        this.usuarioCadastrado = usuarioCadastrado;
    }

    public String getSenhaCadastrada() {
        return senhaCadastrada;
    }

    public void setSenhaCadastrada(String senhaCadastrada) {
        this.senhaCadastrada = senhaCadastrada;
    }

    public String getEmailCadastrado() {
        return emailCadastrado;
    }

    public void setEmailCadastrado(String emailCadastrado) {
        this.emailCadastrado = emailCadastrado;
    }

    public void cadastrarUsuario(String usuario, String senha) throws NoSuchAlgorithmException, UnsupportedEncodingException {

        if (!usuario.isEmpty() && !senha.isEmpty()) {
            if (this.usuarioCadastrado.contains(usuario) && this.senhaCadastrada.contains(senha)) {
                System.out.println("Você já fez o cadastro!");
            } else {
                this.setUsuario(usuario);
                this.setSenha(senha);
                this.setUsuarioCadastrado(usuario);
                this.setSenhaCadastrada(senha);
                System.out.println("Seu usuário foi cadastrado com sucesso!");
            }
        }
    }

    // Overload - adicionou email ao cadastrarUsuario
    public void cadastrarUsuario(String email, String usuario, String senha) throws NoSuchAlgorithmException, UnsupportedEncodingException {

        if (!usuario.isEmpty() && !senha.isEmpty()) {
            if (this.usuarioCadastrado.contains(usuario) && this.senhaCadastrada.contains(senha)) {
                System.out.println("Você já fez o cadastro!");
            } else {
                this.setUsuario(usuario);
                this.setSenha(senha);
                this.setUsuarioCadastrado(usuario);
                this.setSenhaCadastrada(senha);
                this.setEmail(email);
                this.setEmailCadastrado(email);
                System.out.println("Seu usuário foi vinculado ao email "+this.getEmailCadastrado()+" e cadastrado com sucesso!");
            }
        }
    }

    public void login(String usuario, String senha) throws NoSuchAlgorithmException, UnsupportedEncodingException {
       this.estaLogado = this.seguranca.autenticar(usuario, this.usuarioCadastrado, senha, this.senhaCadastrada);
    }

    public void logout() {
        this.estaLogado = false;
    }
}
