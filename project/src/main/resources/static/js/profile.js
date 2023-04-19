document.addEventListener("DOMContentLoaded", function () {
  const editBtn = document.getElementById("editBtn");
  const saveBtn = document.getElementById("saveBtn");
  const form = document.querySelector("form");

  editBtn.addEventListener("click", function () {
    Array.from(form.elements).forEach((element) => {
      element.removeAttribute("disabled");
    });

    editBtn.classList.add("d-none");
    saveBtn.classList.remove("d-none");
  });

  form.addEventListener("submit", async function (event) {
    event.preventDefault();

    if (Array.from(form.elements).some((element) => element.value.trim() === "")) {
      console.log(form.elements);
      alert("Please fill out all fields before saving.");
      return;
    }

    const formData = new FormData(form);

    const response = await fetch('/social/profile', {
      method: 'POST',
      body: formData,
    });

    if (response.ok) {
      Array.from(form.elements).forEach((element) => {
        if (element.type !== "button" && element.type !== "submit") {
          element.setAttribute("disabled", "");
        }
      });

      saveBtn.classList.add("d-none");
      editBtn.classList.remove("d-none");

      showAlert("Data Saved!");
    } else {
      alert('An error occurred while saving the user data.');
    }
  });

  function showAlert(message) {
    const alertContainer = document.getElementById("alert-container");
    const alertHTML = `
      <div class="alert alert-success fade show" role="alert">
        ${message}
      </div>
    `;
    alertContainer.innerHTML = alertHTML;

    setTimeout(() => {
      const alert = document.querySelector('.alert');
      if (alert) {
        alert.classList.remove('show');
        setTimeout(() => alert.remove(), 150);
      }
    }, 2500);
  }
});

    function toggleComments(postId) {
        console.log("toggleComments called for post ID:", postId);
        let comments = document.getElementById(postId);
        if (comments.style.display === "none") {
            comments.style.display = "block";
        } else {
            comments.style.display = "none";
        }
    }
