function setEventListeners() {
    $(".add-to-cart-btn").click(sendProductIdToServer, event)
}

function sendProductIdToServer(event) {
    let id = event.target.dataset["productid"];
    $.post("/add-to-cart", {id:id}, function(data, status) {
        if (status == "success") {
            incrementCartItemNumber();
        }
        console.log("data " + data + "status " + status);
    })
}

function incrementCartItemNumber() {
    let cartItemCount = parseInt(document.getElementById("product-num").innerText);
    document.getElementById("product-num").innerText = String(++cartItemCount);
}

function init() {
    setEventListeners();
}

$( document ).ready(function() {
    init();
});