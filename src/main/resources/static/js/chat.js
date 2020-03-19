var client = null;

function getFormattedDate(date) {
    var year = date.getFullYear();
    var month = (1 + date.getMonth()).toString();
    month = month.length > 1 ? month : '0' + month;
    var day = date.getDate().toString();
    day = day.length > 1 ? day : '0' + day;
    var hours = date.getHours();
    var minutes = date.getMinutes();
    return year + '-' + month + '-' + day + " " + hours + ":" + minutes;
}

function isEmptyOrSpaces(str) {
    return str === null || str.match(/^ *$/) !== null || str.match(/^\n*$/) != null;
}

function showMessage(user, text, date) {
    var respone = document.getElementById('messages-box');
    var newResponse = "<div class=\"card mb-4\" >\n" +
        "                    <div class=\"card-header\" >\n" +
        "                        User <b>" + user + "</b> wrote on <i>\n" +
        "                            " + getFormattedDate(new Date(date)) + "</i>:\n" +
        "                    </div>\n" +
        "                    <div class=\"card-body\">\n" +
        "                        <p class=\"card-text\" >" + text + "</p>\n" +
        "                    </div>\n" +
        "                </div>"
    respone.innerHTML += newResponse;
    respone.scrollIntoView(false);
}

function connect() {
    client = Stomp.client('ws://localhost:8080/chat');
    client.connect({}, function (frame) {
        client.subscribe("/topic/messages", function (message) {
            showMessage(JSON.parse(message.body).user.username, JSON.parse(message.body).text, JSON.parse(message.body).date)
        });
    })
}

function sendMessage() {
    var textareaMessage = document.getElementById('textareaMessage');
    if (!isEmptyOrSpaces(textareaMessage.value)) {
        client.send("/app/chat", {}, JSON.stringify({'text': textareaMessage.value}));
    }
    textareaMessage.value = "";
}

var input = document.getElementById("textareaMessage");
input.addEventListener("keypress", function (e) {
    if (e.code === 'Enter') {
        document.getElementById("buttonSend").click();
    }
});
