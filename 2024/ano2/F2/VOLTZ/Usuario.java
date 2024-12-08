import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

public class Usuario {

    Seguranca seguranca = new Seguranca();

    private boolean estaLogado = false;
    private String usuario;
    private String senha;
    private String email;

    private String usuarioCadastrado = "";
    private String senhaCadastrada = "";
    private String emailCadastrado = "";

    Map<String, String> lstUsuario = new HashMap<String, String>();

    public boolean isEstaLogado() {
        return estaLogado;
    }

    public void setEstaLogado(boolean estaLogado) {
        this.estaLogado = estaLogado;
    }

    public void setUsuarioSenha(String usuario, String senha) {
        this.lstUsuario.put(usuario, senha);
    }

    public String getUsuario() {
        return usuario;
    }

    public String getSenha() {
        return this.senha;
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

    public String getSenhaCadastrada() {
        return senhaCadastrada;
    }

    public boolean isUsuarioCadastrado(String usuario) {
        return lstUsuario.containsKey(usuario);
    }

    public String getEmailCadastrado() {
        return emailCadastrado;
    }

    public void setEmailCadastrado(String emailCadastrado) {
        this.emailCadastrado = emailCadastrado;
    }

    public void cadastrarUsuario(String usuario, String senha)
            throws NoSuchAlgorithmException, UnsupportedEncodingException, IOException {

        if (!usuario.isEmpty() && !senha.isEmpty()) {
            if (isUsuarioCadastrado(usuario)) {
                System.out.println("Você já fez o cadastro!");
            } else {
                this.setUsuarioSenha(usuario, senha);
                atualizarArquivo(usuario, senha);
                System.out.println("Seu usuário foi cadastrado com sucesso!");
            }
        }
    }

    // Overload - adicionou email ao cadastrarUsuario
    public void cadastrarUsuario(String email, String usuario, String senha)
            throws NoSuchAlgorithmException, UnsupportedEncodingException, IOException {

        if (!usuario.isEmpty() && !senha.isEmpty()) {
            if (isUsuarioCadastrado(usuario)) {
                System.out.println("Você já fez o cadastro!");
            } else {
                this.setUsuarioSenha(usuario, senha);
                this.setEmail(email);
                this.setEmailCadastrado(email);
                atualizarArquivo(usuario, senha);
                System.out.println("Seu usuário foi vinculado ao email " + this.getEmailCadastrado()
                        + " e cadastrado com sucesso!");
            }
        }
    }

    public void login(String usuario, String senha) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        this.estaLogado = this.seguranca.autenticar(usuario, senha, this.lstUsuario);
    }

    public void logout() {
        this.estaLogado = false;
    }

    // Método para atualizar o arquivo com o novo usuário
    private void atualizarArquivo(String usuario, String senha) throws IOException {
        try (FileWriter writer = new FileWriter("usuarios.txt", true)) {
            writer.write("Usuário: " + usuario + ", Senha: " + senha + System.lineSeparator());
        }
    }
}
