namespace teste.Services
{
    using Microsoft.AspNetCore.Mvc;
    using System.Net.Http;
    using System.Text;
    using System.Text.Json;
    using teste.Models;

    public class FirebaseService
    {
        private readonly HttpClient _http;
        private readonly string _baseUrl = "https://hybridflow-5df2a-default-rtdb.firebaseio.com/";

        public FirebaseService(HttpClient http)
        {
            _http = http;
        }

        // GET (usuarios)
        public async Task<Dictionary<string, Usuario>> GetUsuarios()
        {
            string json = await _http.GetStringAsync(_baseUrl + "usuarios.json");
            return JsonSerializer.Deserialize<Dictionary<string, Usuario>>(json);
        }

        // POST (criar usuario)
        public async Task CriarUsuario(Usuario u)
        {
            string dados = JsonSerializer.Serialize(u);
            var content = new StringContent(dados, Encoding.UTF8, "application/json");

            await _http.PostAsync(_baseUrl + "usuarios.json", content);
        }

        // PUT (atualizar usuario)
        public async Task AtualizarUsuario(string id, Usuario u)
        {
            string json = JsonSerializer.Serialize(u);
            var content = new StringContent(json, Encoding.UTF8, "application/json");

            await _http.PutAsync(_baseUrl + $"usuarios/{id}.json", content);
        }

        // DELETE (deletar usuario)
        public async Task DeletarUsuario(string id)
        {
            await _http.DeleteAsync(_baseUrl + $"usuarios/{id}.json");
        }

        // POST login simples
        public async Task<Usuario> Login(string email)
        {
            var usuarios = await GetUsuarios();
            return usuarios.Values.FirstOrDefault(x => x.email == email);
        }

        // GET reservas
        public async Task<Dictionary<string, Reserva>> GetReservas()
        {
            string json = await _http.GetStringAsync(_baseUrl + "reservas.json");
            return JsonSerializer.Deserialize<Dictionary<string, Reserva>>(json);
        }

        // POST reserva
        public async Task CriarReserva(Reserva r)
        {
            string dados = JsonSerializer.Serialize(r);
            var content = new StringContent(dados, Encoding.UTF8, "application/json");

            await _http.PostAsync(_baseUrl + "reservas.json", content);
        }

        // PUT reserva
        public async Task AtualizarReserva(string id, Reserva r)
        {
            string json = JsonSerializer.Serialize(r);
            var content = new StringContent(json, Encoding.UTF8, "application/json");

            await _http.PutAsync(_baseUrl + $"reservas/{id}.json", content);
        }

        // DELETE reserva
        public async Task DeletarReserva(string id)
        {
            await _http.DeleteAsync(_baseUrl + $"reservas/{id}.json");
        }

        // GET sensores
        public async Task<Dictionary<string, Sensor>> GetSensores()
        {
            string url = _baseUrl + "sensores.json";
            string json = await _http.GetStringAsync(url);

            var sensores = JsonSerializer.Deserialize<Dictionary<string, Sensor>>(json);

            // Adiciona o ID do Firebase em cada sensor
            if (sensores != null)
            {
                foreach (var item in sensores)
                {
                    item.Value.id = item.Key;
                }
            }

            return sensores;
        }

        public async Task<Dictionary<string, Sala>> GetSalas()
        {
            string url = _baseUrl + "salas.json";
            var json = await _http.GetStringAsync(url);

            if (string.IsNullOrWhiteSpace(json) || json == "null")
                return new Dictionary<string, Sala>();

            var salas = JsonSerializer.Deserialize<Dictionary<string, Sala>>(json);

            foreach (var item in salas)
                item.Value.id = item.Key;

            return salas;
        }

        public async Task CriarSala(Sala sala)
        {
            string json = JsonSerializer.Serialize(sala);
            var content = new StringContent(json, Encoding.UTF8, "application/json");

            await _http.PostAsync(_baseUrl + "salas.json", content);
        }

        public async Task AtualizarSala(string id, Sala sala)
        {
            string json = JsonSerializer.Serialize(sala);
            var content = new StringContent(json, Encoding.UTF8, "application/json");

            await _http.PutAsync(_baseUrl + $"salas/{id}.json", content);
        }

        public async Task DeletarSala(string id)
        {
            await _http.DeleteAsync(_baseUrl + $"salas/{id}.json");
        }


    }

}
