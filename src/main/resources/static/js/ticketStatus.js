window.onload = function () {
    const ticketStatus = document.querySelectorAll('input[type="article"]');
    const statusResults = document.getElementById("statusResults");

    const base = window.location.origin;
    const path = window.location.pathname;

    const url = "/board/{id}/status";

    const option = {method: 'GET'};

    ticketStatus.addEventListener('click', function(event) {
        event.preventDefault();

        const request = ticketStatus.href;

        fetch(request, option)
            .then(function (response) {
                return response.text();
            })
            .then(function (data) {
                statusResults.innerHTML = '';
                statusResults.innerHTML = data;
            });
    });
}