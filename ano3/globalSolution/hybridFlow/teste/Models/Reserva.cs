namespace teste.Models


{
    using System.Text.Json.Serialization;

    public class Reserva
    {

        [JsonPropertyName("id_usuario_original")]
        public int UsuarioId { get; set; }

        [JsonPropertyName("id_sala_original")]
        public int SalaId { get; set; }

        [JsonPropertyName("data_reserva")]
        public string Data { get; set; }

        [JsonPropertyName("horario_inicio")]
        public string Inicio { get; set; }

        [JsonPropertyName("horario_fim")]
        public string Fim { get; set; }

        [JsonPropertyName("status")]
        public string Status { get; set; }
        
    }


}
