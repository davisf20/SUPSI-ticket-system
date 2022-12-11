const nameRegex = /^[A-Za-z]*$/;
const usernameRegex = /^[A-Za-z0-9_]*$/;
const submitButton = document.getElementById('registerSubmit');

const checkFirstName = function () {
    const firstName = document.getElementById('firstName').value;
    const firstNameMessage = document.getElementById('firstNameMessage');

    if (nameRegex.test(firstName)) {
        submitButton.disabled = false;
        firstNameMessage.innerHTML = '';
    } else {
        submitButton.disabled = true;
        firstNameMessage.style.color = 'red';
        firstNameMessage.innerHTML = 'First name must only contain letters';
    }
};

const checkLastName = function () {
    const lastName = document.getElementById('lastName').value;
    const lastNameMessage = document.getElementById('lastNameMessage');

    if (nameRegex.test(lastName)) {
        submitButton.disabled = false;
        lastNameMessage.innerHTML = '';
    } else {
        submitButton.disabled = true;
        lastNameMessage.style.color = 'red';
        lastNameMessage.innerHTML = 'Last name must only contain letters';
    }
};

const checkUsername = function () {
    const username = document.getElementById('username').value;
    const usernameMessage = document.getElementById('usernameMessage');

    if (usernameRegex.test(username)) {
        submitButton.disabled = false;
        usernameMessage.innerHTML = '';
    } else {
        submitButton.disabled = true;
        usernameMessage.style.color = 'red';
        usernameMessage.innerHTML = 'Username must only contain letters, numbers and underscores';
    }
};

const passwordCheck = function () {
    const password = document.getElementById('password').value;
    const confirmPassword = document.getElementById('confirm-password').value;
    const passwordMessage = document.getElementById('passwordMessage');
    const confirmPasswordMessage = document.getElementById('confirmPasswordMessage');

    if (usernameRegex.test(password) && password.length >= 8 && password.length <= 15) {
        passwordMessage.innerHTML = '';

        if (password === confirmPassword) {
            submitButton.disabled = false;
            confirmPasswordMessage.innerHTML = '';
        } else {
            submitButton.disabled = true;
            confirmPasswordMessage.style.color = 'red';
            confirmPasswordMessage.innerHTML = 'The passwords do not match';
        }
    } else {
        submitButton.disabled = true;
        passwordMessage.style.color = 'red';
        passwordMessage.innerHTML = 'Password must only contain letters, numbers and underscores and be between 8 and 15 characters long';
    }
};