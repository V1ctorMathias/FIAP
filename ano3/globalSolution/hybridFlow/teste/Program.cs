using teste.Services;

var builder = WebApplication.CreateBuilder(args);

builder.Services.AddHttpClient<FirebaseService>();

builder.Services.AddEndpointsApiExplorer();
builder.Services.AddSwaggerGen();

// Adicione os serviços ao contêiner.
builder.Services.AddControllersWithViews();

// Inicia o simulador de sensores
var simulator = new SensorSimulator();

var timer = new System.Threading.Timer(async _ =>
{
    await simulator.EnviarLeitura();
    Console.WriteLine("Sensor atualizado...");
}, null, TimeSpan.Zero, TimeSpan.FromSeconds(10)); // a cada 10 segundos


var app = builder.Build();


app.UseSwagger();
app.UseSwaggerUI();


// Configure o pipeline de requisição HTTP.
if (!app.Environment.IsDevelopment())
{
    app.UseExceptionHandler("/Home/Error");
    // O valor padrão do HSTS é 30 dias. Você pode querer alterá-lo para cenários de produção, veja https://aka.ms/aspnetcore-hsts.
    app.UseHsts();
}

app.UseHttpsRedirection();
app.UseRouting();

app.UseAuthorization();

app.MapControllers();

app.MapStaticAssets();

app.MapControllerRoute(
    name: "default",
    pattern: "{controller=Home}/{action=Index}/{id?}")
    .WithStaticAssets();

app.Run();
