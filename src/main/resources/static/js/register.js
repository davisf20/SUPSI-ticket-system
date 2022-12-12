const nameRegex = /^[A-Za-z]*$/;
const usernameRegex = /^[A-Za-z0-9_]*$/;
//const submitButton = document.getElementById('registerSubmit');
const submitButton = document.querySelector('#registerSubmit');

const checkFirstName = function () {
    const firstName = document.querySelector('#firstName');
    const firstNameMessage = document.querySelector('#firstNameMessage');

    if (nameRegex.test(firstName.value)) {
        //submitButton.disabled = false;
        firstNameMessage.innerHTML = '';
    } else {
        //submitButton.disabled = true;
        firstNameMessage.style.color = 'red';
        firstNameMessage.innerHTML = 'First name must only contain letters';
    }
};

const checkLastName = function () {
    const lastName = document.querySelector('#lastName');
    const lastNameMessage = document.querySelector('#lastNameMessage');

    if (nameRegex.test(lastName.value)) {
        //submitButton.disabled = false;
        lastNameMessage.innerHTML = '';
    } else {
        //submitButton.disabled = true;
        lastNameMessage.style.color = 'red';
        lastNameMessage.innerHTML = 'Last name must only contain letters';
    }
};

const checkUsername = function () {
    const username = document.querySelector('#username');
    const usernameMessage = document.getElementById('usernameMessage');

    if (usernameRegex.test(username.value)) {
        //submitButton.disabled = false;
        usernameMessage.innerHTML = '';
    } else {
        //submitButton.disabled = true;
        usernameMessage.style.color = 'red';
        usernameMessage.innerHTML = 'Username must only contain letters, numbers and underscores';
    }
};

const passwordCheck = function () {
    const password = document.querySelector('#password');
    const confirmPassword = document.querySelector('#confirm-password');
    const passwordMessage = document.querySelector('#passwordMessage');
    const confirmPasswordMessage = document.querySelector('#confirmPasswordMessage');

    if (usernameRegex.test(password.value) && password.value.length >= 8 && password.value.length <= 15) {
        passwordMessage.innerHTML = '';

        if (password.value === confirmPassword.value) {
            //submitButton.disabled = false;
            confirmPasswordMessage.innerHTML = '';
        } else {
            //submitButton.disabled = true;
            confirmPasswordMessage.style.color = 'red';
            confirmPasswordMessage.innerHTML = 'The passwords do not match';
        }
    } else {
        //submitButton.disabled = true;
        passwordMessage.style.color = 'red';
        passwordMessage.innerHTML = 'Password must only contain letters, numbers and underscores and be between 8 and 15 characters long';
    }
};

window.onload = function () {
    const firstName = document.getElementById('firstName');
    const lastName = document.getElementById('lastName');
    const username = document.getElementById('username');
    const password = document.getElementById('password');
    const confirmPassword = document.getElementById('confirm-password');

    firstName.addEventListener('input', checkFirstName);
    lastName.addEventListener('input', checkLastName);
    username.addEventListener('input', checkUsername);
    password.addEventListener('input', passwordCheck);
    confirmPassword.addEventListener('input', passwordCheck);
}