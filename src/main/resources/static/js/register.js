const nameRegex = /^[A-Za-z]*$/;
const usernameRegex = /^[A-Za-z0-9_]*$/;
const elements = new Set();

const checkFirstName = function () {
    const firstName = document.querySelector('#firstName');
    const firstNameMessage = document.querySelector('#firstNameMessage');

    if (nameRegex.test(firstName.value)) {
        firstNameMessage.innerHTML = '';
        elements.add(firstName);
    } else {
        firstNameMessage.style.color = 'red';
        firstNameMessage.innerHTML = 'First name must only contain letters';
        elements.delete(firstName);
    }
};

const checkLastName = function () {
    const lastName = document.querySelector('#lastName');
    const lastNameMessage = document.querySelector('#lastNameMessage');

    if (nameRegex.test(lastName.value)) {
        lastNameMessage.innerHTML = '';
        elements.add(lastName);
    } else {
        lastNameMessage.style.color = 'red';
        lastNameMessage.innerHTML = 'Last name must only contain letters';
        elements.delete(lastName);
    }
};

const checkUsername = function () {
    const username = document.querySelector('#username');
    const usernameMessage = document.getElementById('usernameMessage');

    if (usernameRegex.test(username.value)) {
        usernameMessage.innerHTML = '';
        elements.add(username);
    } else {
        usernameMessage.style.color = 'red';
        usernameMessage.innerHTML = 'Username must only contain letters, numbers and underscores';
        elements.delete(username);
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
            confirmPasswordMessage.innerHTML = '';
            elements.add(password);
        } else {
            confirmPasswordMessage.style.color = 'red';
            confirmPasswordMessage.innerHTML = 'The passwords do not match';
            elements.delete(password);
        }
    } else {
        passwordMessage.style.color = 'red';
        passwordMessage.innerHTML = 'Password must only contain letters, numbers and underscores and be between 8 and 15 characters long';
        elements.delete(password);
    }
};

const createSubmitButton = function () {
    const submitButton = document.createElement('button');
    submitButton.id = 'registerSubmit';
    submitButton.type = 'submit';
    submitButton.innerHTML = 'Register';
    submitButton.className = 'btn rounded-4 text-dark bg-light bg-gradient w-100';
    document.getElementById("submitDiv").appendChild(submitButton);
}

const checkSubmit = function () {
    if (elements.size === 4) {
        if (document.getElementById('registerSubmit') === null) {
            createSubmitButton();
        }
    } else {
        document.getElementById("registerSubmit").remove();
    }
}

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

    document
        .querySelectorAll("input")
        .forEach((elem) => elem.addEventListener('input', checkSubmit));
}