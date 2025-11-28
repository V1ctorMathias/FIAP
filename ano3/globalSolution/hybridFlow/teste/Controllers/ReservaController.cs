namespace teste.Controllers
{
    using Microsoft.AspNetCore.Mvc;
    using teste.Models;
    using teste.Services;

    [ApiController]
    [Route("[controller]")]
    public class ReservaController : ControllerBase
    {
        private readonly FirebaseService _firebase;

        public ReservaController(FirebaseService firebase)
        {
            _firebase = firebase;
        }

        [HttpGet("/reservas")]
        public async Task<IActionResult> GetTodas()
        {
            var reservas = await _firebase.GetReservas();
            return Ok(reservas);
        }

        [HttpPost("/reservas")]
        public async Task<IActionResult> Criar([FromBody] Reserva r)
        {
            await _firebase.CriarReserva(r);
            return Ok("Reserva criada");
        }

        [HttpPut("/reservas/{id}")]
        public async Task<IActionResult> Atualizar(string id, [FromBody] Reserva r)
        {
            await _firebase.AtualizarReserva(id, r);
            return Ok("Reserva atualizada");
        }

        [HttpDelete("/reservas/{id}")]
        public async Task<IActionResult> Deletar(string id)
        {
            await _firebase.DeletarReserva(id);
            return Ok("Reserva removida");
        }

    }

}
