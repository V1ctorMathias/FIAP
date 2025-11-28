namespace teste.Services
{
    using System.Text.Json;
    using System.Text;

    public class SensorSimulator
    {
        private readonly HttpClient _http = new HttpClient();
        private readonly string baseUrl = "https://hybridflow-5df2a-default-rtdb.firebaseio.com/sensores";

        public async Task EnviarLeitura()
        {
            var random = new Random();

            var leitura = new
            {
                temperatura = random.Next(19, 31),     // 19°C a 30°C
                ocupacao = random.Next(0, 20),         // 0 a 20 pessoas
                atualizado_em = DateTime.Now.ToString("yyyy-MM-dd HH:mm")
            };

            string json = JsonSerializer.Serialize(leitura);
            var content = new StringContent(json, Encoding.UTF8, "application/json");

            // envia como update do sensor "sala1"
            await _http.PutAsync(baseUrl + "/sala1.json", content);
        }

    }

}
