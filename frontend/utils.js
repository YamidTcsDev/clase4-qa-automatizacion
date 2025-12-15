const API_BASE_URL = 'http://localhost:8080';

// Obtener token del localStorage
function getToken() {
    return localStorage.getItem('token');
}

// Obtener datos del usuario del localStorage
function getUserData() {
    const userData = localStorage.getItem('userData');
    return userData ? JSON.parse(userData) : null;
}

// Guardar token y datos de usuario
function saveAuthData(token, userData) {
    localStorage.setItem('token', token);
    localStorage.setItem('userData', JSON.stringify(userData));
}

// Limpiar datos de autenticación
function clearAuthData() {
    localStorage.removeItem('token');
    localStorage.removeItem('userData');
}

// Verificar si el usuario está autenticado
function isAuthenticated() {
    return !!getToken();
}

// Redirigir si no está autenticado
function requireAuth() {
    if (!isAuthenticated()) {
        window.location.href = 'login.html';
        return false;
    }
    return true;
}

// Realizar petición HTTP con autenticación
async function fetchWithAuth(url, options = {}) {
    const token = getToken();
    
    const headers = {
        'Content-Type': 'application/json',
        ...options.headers
    };
    
    if (token) {
        headers['Authorization'] = `Bearer ${token}`;
    }
    
    try {
        const response = await fetch(`${API_BASE_URL}${url}`, {
            ...options,
            headers
        });
        
        // Si recibimos 401, la sesión expiró
        if (response.status === 401) {
            clearAuthData();
            window.location.href = 'login.html';
            throw new Error('Sesión expirada');
        }
        
        return response;
    } catch (error) {
        console.error('Error en petición:', error);
        throw error;
    }
}

// Formatear número como moneda colombiana
function formatCurrency(amount) {
    return new Intl.NumberFormat('es-CO', {
        style: 'currency',
        currency: 'COP',
        minimumFractionDigits: 0,
        maximumFractionDigits: 0
    }).format(amount);
}

// Enmascarar número de cuenta (mostrar solo últimos 4 dígitos)
function maskAccountNumber(accountNumber) {
    if (!accountNumber) return '';
    const str = accountNumber.toString();
    if (str.length <= 4) return str;
    return '**** **** ' + str.slice(-4);
}

// Mostrar toast de notificación
function showToast(message, type = 'success') {
    // Eliminar toasts existentes
    const existingToasts = document.querySelectorAll('.toast');
    existingToasts.forEach(toast => toast.remove());
    
    const toast = document.createElement('div');
    toast.className = `toast toast-${type}`;
    toast.setAttribute('data-testid', `toast-${type}`);
    toast.textContent = message;
    
    document.body.appendChild(toast);
    
    setTimeout(() => {
        toast.style.animation = 'slideIn 0.3s ease-out reverse';
        setTimeout(() => toast.remove(), 300);
    }, 3000);
}

// Validar email
function isValidEmail(email) {
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return emailRegex.test(email);
}

// Validar campo requerido
function validateRequired(value) {
    return value && value.trim().length > 0;
}

// Mostrar error en campo de formulario
function showFieldError(fieldId, message) {
    const formGroup = document.getElementById(fieldId)?.closest('.form-group');
    if (formGroup) {
        formGroup.classList.add('error');
        const errorMsg = formGroup.querySelector('.error-message');
        if (errorMsg) {
            errorMsg.textContent = message;
        }
    }
}

// Limpiar errores de campo
function clearFieldError(fieldId) {
    const formGroup = document.getElementById(fieldId)?.closest('.form-group');
    if (formGroup) {
        formGroup.classList.remove('error');
    }
}

// Limpiar todos los errores del formulario
function clearAllErrors() {
    const errorGroups = document.querySelectorAll('.form-group.error');
    errorGroups.forEach(group => group.classList.remove('error'));
}

// Cerrar sesión
function logout() {
    clearAuthData();
    window.location.href = 'login.html';
}

// Formatear fecha
function formatDate(dateString) {
    const date = new Date(dateString);
    return new Intl.DateTimeFormat('es-CO', {
        year: 'numeric',
        month: 'long',
        day: 'numeric',
        hour: '2-digit',
        minute: '2-digit'
    }).format(date);
}
