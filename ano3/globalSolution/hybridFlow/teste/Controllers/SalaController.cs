namespace teste.Controllers
{
    using Microsoft.AspNetCore.Mvc;
    using teste.Models;
    using teste.Services;

    [ApiController]
    [Route("[controller]")]
    public class SalaController : ControllerBase
    {
        private readonly FirebaseService _firebase;

        public SalaController(FirebaseService firebase)
        {
            _firebase = firebase;
        }

        // GET /sala
        [HttpGet]
        public async Task<IActionResult> GetSalas()
        {
            var salas = await _firebase.GetSalas();
            return Ok(salas);
        }

        // POST /sala
        [HttpPost]
        public async Task<IActionResult> CriarSala([FromBody] Sala sala)
        {
            await _firebase.CriarSala(sala);
            return Ok("Sala criada com sucesso");
        }

        // PUT /sala/{id}
        [HttpPut("{id}")]
        public async Task<IActionResult> AtualizarSala(string id, [FromBody] Sala sala)
        {
            await _firebase.AtualizarSala(id, sala);
            return Ok("Sala atualizada com sucesso");
        }

        // DELETE /sala/{id}
        [HttpDelete("{id}")]
        public async Task<IActionResult> DeletarSala(string id)
        {
            await _firebase.DeletarSala(id);
            return Ok("Sala removida com sucesso");
        }
    }

}
