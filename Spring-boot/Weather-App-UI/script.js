// Weather condition to theme mapping
const weatherThemes = {
    'sunny': 'sunny',
    'clear': 'sunny',
    'rain': 'rainy',
    'shower': 'rainy',
    'drizzle': 'rainy',
    'cloud': 'cloudy',
    'overcast': 'cloudy',
    'snow': 'snowy',
    'blizzard': 'snowy',
    'thunder': 'stormy',
    'storm': 'stormy'
};

// SVG icons for different weather conditions
const weatherIcons = {
    sunny: `<svg viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
        <circle cx="12" cy="12" r="5" fill="#FFD700"/>
        <path d="M12 1v2M12 21v2M4.22 4.22l1.42 1.42M18.36 18.36l1.42 1.42M1 12h2M21 12h2M4.22 19.78l1.42-1.42M18.36 5.64l1.42-1.42" stroke="#FFD700" stroke-width="2" stroke-linecap="round"/>
    </svg>`,
    
    rainy: `<svg viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
        <path d="M20 16.58A5 5 0 0 0 18 7h-1.26A8 8 0 1 0 4 15.25" fill="#87CEEB"/>
        <path d="M8 19v2M8 13v2M16 19v2M16 13v2M12 21v2M12 15v2" stroke="#4682B4" stroke-width="2" stroke-linecap="round"/>
    </svg>`,
    
    cloudy: `<svg viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
        <path d="M20 16.58A5 5 0 0 0 18 7h-1.26A8 8 0 1 0 4 15.25" fill="#B0C4DE"/>
        <path d="M17.5 21H9a7 7 0 1 1 6.71-9h1.79a4.5 4.5 0 0 1 0 9Z" fill="#D3D3D3"/>
    </svg>`,
    
    snowy: `<svg viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
        <path d="M20 16.58A5 5 0 0 0 18 7h-1.26A8 8 0 1 0 4 15.25" fill="#E6E6FA"/>
        <circle cx="8" cy="19" r="1" fill="white"/>
        <circle cx="12" cy="17" r="1" fill="white"/>
        <circle cx="16" cy="19" r="1" fill="white"/>
        <circle cx="10" cy="21" r="1" fill="white"/>
        <circle cx="14" cy="21" r="1" fill="white"/>
    </svg>`,
    
    stormy: `<svg viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
        <path d="M20 16.58A5 5 0 0 0 18 7h-1.26A8 8 0 1 0 4 15.25" fill="#696969"/>
        <path d="M13 16l-4 6 2-3-4-1 4-6-2 3 4 1Z" fill="#FFD700"/>
    </svg>`,
    
    default: `<svg viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
        <path d="M20 16.58A5 5 0 0 0 18 7h-1.26A8 8 0 1 0 4 15.25" fill="#87CEEB"/>
    </svg>`
};

/**
 * Determines the theme based on weather condition
 * @param {string} condition - Weather condition string
 * @returns {string} - Theme class name
 */
function getWeatherTheme(condition) {
    const conditionLower = condition.toLowerCase();
    for (const [key, theme] of Object.entries(weatherThemes)) {
        if (conditionLower.includes(key)) {
            return theme;
        }
    }
    return 'default';
}

/**
 * Returns appropriate SVG icon for weather condition
 * @param {string} condition - Weather condition string
 * @returns {string} - SVG icon HTML
 */
function getWeatherIcon(condition) {
    const conditionLower = condition.toLowerCase();
    if (conditionLower.includes('sunny') || conditionLower.includes('clear')) {
        return weatherIcons.sunny;
    } else if (conditionLower.includes('rain') || conditionLower.includes('shower') || conditionLower.includes('drizzle')) {
        return weatherIcons.rainy;
    } else if (conditionLower.includes('cloud') || conditionLower.includes('overcast')) {
        return weatherIcons.cloudy;
    } else if (conditionLower.includes('snow') || conditionLower.includes('blizzard')) {
        return weatherIcons.snowy;
    } else if (conditionLower.includes('thunder') || conditionLower.includes('storm')) {
        return weatherIcons.stormy;
    }
    return weatherIcons.default;
}

/**
 * Formats date string to readable format
 * @param {string} dateString - Date in YYYY-MM-DD format
 * @returns {string} - Formatted date string
 */
function formatDate(dateString) {
    const date = new Date(dateString);
    return date.toLocaleDateString('en-US', {
        weekday: 'short',
        month: 'short',
        day: 'numeric'
    });
}

/**
 * Shows error message to user
 * @param {string} message - Error message to display
 */
function showError(message) {
    const errorDiv = document.getElementById('error');
    errorDiv.textContent = message;
    errorDiv.style.display = 'block';
    document.getElementById('weatherDisplay').style.display = 'none';
}

/**
 * Hides error message
 */
function hideError() {
    document.getElementById('error').style.display = 'none';
}

/**
 * Shows loading indicator
 */
function showLoading() {
    document.getElementById('loading').style.display = 'block';
    document.getElementById('weatherDisplay').style.display = 'none';
    hideError();
}

/**
 * Hides loading indicator
 */
function hideLoading() {
    document.getElementById('loading').style.display = 'none';
}

/**
 * Main function to fetch weather data from API
 */
async function fetchWeather() {
    const city = document.getElementById('cityInput').value.trim();
    const days = document.getElementById('daysInput').value;

    // Validate input
    if (!city) {
        showError('Please enter a city name');
        return;
    }

    const fetchBtn = document.querySelector('.fetch-btn');
    fetchBtn.disabled = true;
    showLoading();

    try {
        const response = await fetch(`http://localhost:8081/weather/forcast?city=${encodeURIComponent(city)}&days=${days}`);
        
        if (!response.ok) {
            throw new Error(`HTTP error! status: ${response.status}`);
        }

        const data = await response.json();
        displayWeather(data);
        hideError();
    } catch (error) {
        console.error('Error fetching weather:', error);
        showError(`Failed to fetch weather data. Please check if the API server is running and try again.`);
    } finally {
        fetchBtn.disabled = false;
        hideLoading();
    }
}

/**
 * Displays weather data in the UI
 * @param {Object} data - Weather data from API
 */
function displayWeather(data) {
    const { weatherResponse, dayTemp } = data;
    
    // Update theme based on weather condition
    const theme = getWeatherTheme(weatherResponse.condition);
    document.body.className = theme;

    // Display current weather
    document.getElementById('weatherIcon').innerHTML = getWeatherIcon(weatherResponse.condition);
    document.getElementById('location').textContent = `${weatherResponse.city}, ${weatherResponse.region}`;
    document.getElementById('condition').textContent = weatherResponse.condition;
    document.getElementById('temperature').textContent = `${Math.round(weatherResponse.temperature)}°C`;

    // Display forecast
    const forecastGrid = document.getElementById('forecastGrid');
    forecastGrid.innerHTML = '';

    dayTemp.forEach(day => {
        const card = document.createElement('div');
        card.className = 'forecast-card';
        
        card.innerHTML = `
            <div class="forecast-date">${formatDate(day.date)}</div>
            <div class="temp-range">
                <span class="temp-low">${Math.round(day.minTemp)}°</span>
                <span class="temp-avg">${Math.round(day.avgTemp)}°</span>
                <span class="temp-high">${Math.round(day.maxTemp)}°</span>
            </div>
        `;
        
        forecastGrid.appendChild(card);
    });

    // Show weather display
    document.getElementById('weatherDisplay').style.display = 'block';
}

// Event Listeners
document.addEventListener('DOMContentLoaded', function() {
    // Add enter key support for city input
    document.getElementById('cityInput').addEventListener('keypress', function(e) {
        if (e.key === 'Enter') {
            fetchWeather();
        }
    });

    // Load default weather on page load
    fetchWeather();
});