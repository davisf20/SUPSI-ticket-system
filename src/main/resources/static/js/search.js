window.onload = function () {
    const searchInput = document.getElementById('searchInput');
    const searchResults = document.getElementById('searchResults');

    const base = window.location.origin;
    const path = window.location.pathname;
    const url = base +path+"ticket/search";

    const option = {method: 'GET'};

    searchInput.addEventListener('input', function(event) {
        if (searchInput.value.length >= 3 || searchInput.value.length === 0) {

            const searchValue = document.getElementById('searchInput').value;

            fetch(url + `?q=` + searchValue, option)
                .then(function (response) {
                    return response.text();
                })
                .then(function (data) {
                    searchResults.innerHTML = '';
                    searchResults.innerHTML = data;
                });
        }
    });
}