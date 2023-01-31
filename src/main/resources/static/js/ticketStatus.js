window.onload = function () {
    const ticketStatus = document.getElementsByName("ticketStatus");
    const statusResults = document.getElementById("statusResults");

    const base = window.location.origin;
    const path = window.location.pathname;
    const url = "/board/{id}/status";

    const option = {method: 'GET'};

    ticketStatus.addEventListener('input', function(event) {
        event.preventDefault();

        fetch(url, option)
            .then(function (response) {
                return response.text();
            })
            .then(function (data) {
                statusResults.innerHTML = '';
                statusResults.innerHTML = data;
            });
    });
}