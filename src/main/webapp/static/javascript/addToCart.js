function setEventListeners() {
    $(".add-to-cart-btn").click(sendProductIdToServer, event)
}

function sendProductIdToServer(event) {
    let id = event.target.dataset["productid"];
    $.post("/add-to-cart", {id:id}, function(data, status) {
        console.log("data " + data + "status " + status);
    })
}

function init() {
    setEventListeners();
}

$( document ).ready(function() {
    init();
});