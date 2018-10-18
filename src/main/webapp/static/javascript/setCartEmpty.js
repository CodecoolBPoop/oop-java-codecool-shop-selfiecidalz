function setEventListener() {
    $(".payment-btn").click(emptyTheCartList)
}

function emptyTheCartList() {
    $.post("/remove-from-cart", {setCartEmpty:"true"});
}

function init() {
    setEventListener();
}

$( document ).ready(function() {
    init();
});