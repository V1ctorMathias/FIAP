namespace teste.Models
{
    using System.Text.Json.Serialization;

    public class Sensor
    {
        public string id { get; set; }

        [JsonPropertyName("temperatura")]
        public double temperatura { get; set; }

        [JsonPropertyName("ocupacao")]
        public int ocupacao { get; set; }

        [JsonPropertyName("atualizado_em")]
        public string atualizadoEm { get; set; }
    }


}
