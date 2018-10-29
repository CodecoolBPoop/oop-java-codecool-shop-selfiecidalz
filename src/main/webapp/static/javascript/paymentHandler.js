function setEventListener() {
    $(".payment-btn").click(handlePayment)
}

function handlePayment() {
    $.post("/payment-handler");
}

function init() {
    setEventListener();
}

$( document ).ready(function() {
    init();
});