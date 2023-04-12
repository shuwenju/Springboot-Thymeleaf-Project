  function toggleForms() {
    const loginForm = document.getElementById('login-form');
    const registerForm = document.getElementById('register-form');
    const loginButton = document.getElementById('login-button');
    const registerButton = document.getElementById('register-button');

    loginForm.classList.toggle('active');
    registerForm.classList.toggle('active');
    loginButton.disabled = !loginButton.disabled;
    registerButton.disabled = !registerButton.disabled;
  }