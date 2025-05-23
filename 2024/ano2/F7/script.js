const apiKey = "620ebeee314cd0ab010149bbd339ec52";
const apiCountryURL = "https://flagsapi.com/";
const apiUnsplash = "https://source.unsplash.com/1600x900/?";

const cityInput = document.querySelector("#city-input");
const searchBtn = document.querySelector("#search");

const cityElement = document.querySelector("#city");
const tempElement = document.querySelector("#temperature span");
const descElement = document.querySelector("#description");
const weatherIconElement = document.querySelector("#weather-icon");
const countryElement = document.querySelector("#country");
const umidityElement = document.querySelector("#umidity span");
const windElement = document.querySelector("#wind span");

const weatherContainer = document.querySelector("#weather-data");

const errorMessageContainer = document.querySelector("#error-message");
const loader = document.querySelector("#loader");

const floodAlertBox = document.querySelector("#flood-alert");
const landslideAlertBox = document.querySelector("#landslide-alert");

// Loader
const toggleLoader = () => {
    loader.classList.toggle("hide");
};

const getWeatherData = async (city) => {
    toggleLoader();

    const apiWeatherURL = `https://api.openweathermap.org/data/2.5/weather?q=${city}&units=metric&appid=${apiKey}&lang=pt_br`;

    const res = await fetch(apiWeatherURL);
    const data = await res.json();

    toggleLoader();

    return data;
};

// Tratamento de erro
const showErrorMessage = () => {
    errorMessageContainer.classList.remove("hide");
};

const hideInformation = () => {
    errorMessageContainer.classList.add("hide");
    weatherContainer.classList.add("hide");
};

const showWeatherData = async (city) => {
    hideInformation();

    const data = await getWeatherData(city);

    if (data.cod === "404") {
        showErrorMessage();
        return;
    } else {
        checkRiskAlerts(data)
    }

    cityElement.innerText = data.name;
    tempElement.innerText = parseInt(data.main.temp);
    descElement.innerText = data.weather[0].description;
    weatherIconElement.setAttribute(
        "src",
        `http://openweathermap.org/img/wn/${data.weather[0].icon}.png`
    );
    countryElement.setAttribute("src", apiCountryURL + data.sys.country + "/flat/64.png");
    umidityElement.innerText = `${data.main.humidity}%`;
    windElement.innerText = `${data.wind.speed}km/h`;

    weatherContainer.classList.remove("hide");
};

searchBtn.addEventListener("click", async (e) => {
    e.preventDefault();

    const city = cityInput.value;

    showWeatherData(city);
});

cityInput.addEventListener("keyup", (e) => {
    if (e.code === "Enter") {
        const city = e.target.value;

        showWeatherData(city);
    }
});

const checkRiskAlerts = (data) => {
    const description = data.weather[0].description.toLowerCase();
    const rainVolume = data.rain?.["1h"] || 0;

    console.log("Descrição:", description);
    console.log("Volume de chuva:", rainVolume);

    const floodKeywords = ["chuva forte", "chuva intensa", "tempestade", "chuva extrema"];
    const landslideKeywords = ["chuva contínua", "chuva por várias horas", "chuva intensa"];

    const floodRisk = floodKeywords.some((kw) => description.includes(kw)) || rainVolume >= 20;
    const landslideRisk = landslideKeywords.some((kw) => description.includes(kw)) || rainVolume >= 30;

    console.log("floodRisk:", floodRisk);
    console.log("landslideRisk:", landslideRisk);

    if (floodRisk) {
        alert("⚠️ Risco de alagamento!")
    }

    if (landslideRisk) {
        alert("⚠️ Risco de deslizamento!")
    }
    
    if (!floodRisk && !landslideRisk) {
        alert("Não há risco nem de deslizamento e nem de alagamento!")
    }
};
