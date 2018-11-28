function setEventListener() {
    $(".register-button").click(registerUser, event);
}

function registerUser(event){
    $.post("/register-handler");
}

function init() {
    setEventListener();
}

$( document ).ready(function() {
    init();
});
