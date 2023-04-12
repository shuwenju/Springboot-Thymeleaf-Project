function toggleForms() {
  const loginForm = document.getElementById("form-container-login");
  const registerForm = document.getElementById("form-container-register");
  const loginButton = document.getElementById("login-button");
  const registerButton = document.getElementById("register-button");

  loginForm.classList.toggle("disable-section");
  registerForm.classList.toggle("disable-section");
  loginButton.classList.toggle("disable-link");
  registerButton.classList.toggle("disable-link");
}
