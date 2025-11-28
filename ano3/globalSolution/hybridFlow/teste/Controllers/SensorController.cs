namespace teste.Controllers
{
    using Microsoft.AspNetCore.Mvc;
    using teste.Services;

    [ApiController]
    [Route("[controller]")]
    public class SensorController : ControllerBase
    {
        private readonly FirebaseService _firebase;

        public SensorController(FirebaseService firebase)
        {
            _firebase = firebase;
        }

        [HttpGet]
        public async Task<IActionResult> GetSensores()
        {
            var sensores = await _firebase.GetSensores();
            return Ok(sensores);
        }

        [HttpGet("alertas")]
        public async Task<IActionResult> GetAlertas()
        {
            var sensores = await _firebase.GetSensores();
            var sala1 = sensores["sala1"];

            List<string> alertas = new();

            if (sala1.temperatura > 28)
                alertas.Add("Temperatura alta na Sala 1.");

            if (sala1.ocupacao >= 20)
                alertas.Add("Sala 1 lotada.");

            return Ok(alertas);
        }
    }

}
