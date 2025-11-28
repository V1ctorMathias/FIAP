namespace teste.Controllers
{
    using Microsoft.AspNetCore.Mvc;
    using teste.Models;
    using teste.Services;

    [ApiController]
    [Route("[controller]")]
    public class UsuarioController : ControllerBase
    {
        private readonly FirebaseService _firebase;

        public UsuarioController(FirebaseService firebase)
        {
            _firebase = firebase;
        }

        [HttpGet("/usuarios")]
        public async Task<IActionResult> GetTodos()
        {
            var usuarios = await _firebase.GetUsuarios();
            return Ok(usuarios);
        }

        // Criptografia hash para senha
        [HttpPost("/usuarios")]
        public async Task<IActionResult> Criar([FromBody] Usuario u)
        {
            // Criptografa a senha
            u.senha_hash = BCrypt.Net.BCrypt.HashPassword(u.senha_hash);

            await _firebase.CriarUsuario(u);
            return Ok("Usuário criado com senha segura");
        }

        // Validação de login
        [HttpPost("login")]
        public async Task<IActionResult> Login([FromBody] Usuario loginInput)
        {
            var usuarios = await _firebase.GetUsuarios();

            var usuario = usuarios.Values
                .FirstOrDefault(x => x.email == loginInput.email);

            if (usuario == null)
                return Unauthorized("E-mail não encontrado");

            bool senhaCorreta = BCrypt.Net.BCrypt.Verify(loginInput.senha_hash, usuario.senha_hash);

            if (!senhaCorreta)
                return Unauthorized("Senha incorreta");

            if (string.IsNullOrWhiteSpace(loginInput.email) || !loginInput.email.Contains("@"))
                return BadRequest("E-mail inválido.");

            if (string.IsNullOrWhiteSpace(loginInput.nome))
                return BadRequest("Nome não pode ser vazio.");

            if (loginInput.senha_hash.Length < 3)
                return BadRequest("A senha deve ter no mínimo 3 caracteres.");


            return Ok("Login autorizado");
        }


        [HttpPut("/usuarios/{id}")]
        public async Task<IActionResult> Atualizar(string id, [FromBody] Usuario u)
        {
            await _firebase.AtualizarUsuario(id, u);
            return Ok("Usuário atualizado");
        }

        [HttpDelete("/usuarios/{id}")]
        public async Task<IActionResult> Deletar(string id)
        {
            await _firebase.DeletarUsuario(id);
            return Ok("Usuário removido");
        }

    }

}
