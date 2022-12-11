const passwordCheck = function () {
    if (document.getElementById('password').value ===
        document.getElementById('confirm-password').value) {
        document.getElementById('registerSubmit').disabled = false;
        document.getElementById('message').innerHTML = '';
    } else {
        document.getElementById('registerSubmit').disabled = true;
        document.getElementById('message').style.color = 'red';
        document.getElementById('message').innerHTML = 'The passwords do not match';
    }
};