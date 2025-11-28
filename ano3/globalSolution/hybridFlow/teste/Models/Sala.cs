namespace teste.Models
{
    using System.Text.Json.Serialization;

    public class Sala
    {
        public string id { get; set; }

        [JsonPropertyName("id_original")]
        public int IdOriginal { get; set; }

        [JsonPropertyName("nome")]
        public string Nome { get; set; }

        [JsonPropertyName("capacidade_maxima")]
        public int CapacidadeMaxima { get; set; }

        [JsonPropertyName("localizacao")]
        public string Localizacao { get; set; }
    }

}
