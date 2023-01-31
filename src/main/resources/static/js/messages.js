window.onload = function () {
    const messageText = document.getElementById('messageText');
    const sendButton = document.getElementById('sendButton');
    const messageResults = document.getElementById('messageResults');

    const base = window.location.origin;
    const path = window.location.pathname;

    const request = document.getElementById('messageForm').action;
    //const url = base +path+"'/ticket/' + ${ticket.id} + '/message'";
    const url = base +path+ request;

    const option = {method: 'GET'};

    sendButton.addEventListener('click', function(event) {
        event.preventDefault();

        if (messageText.value.length >= 3 || messageText.value.length === 0) {

            fetch(request, option)
                .then(function (response) {
                    return response.text();
                })
                .then(function (data) {
                    messageResults.innerHTML = '';
                    messageResults.innerHTML = data;
                });
        }
    });
}