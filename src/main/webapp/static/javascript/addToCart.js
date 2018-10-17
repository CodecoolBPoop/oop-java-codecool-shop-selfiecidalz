function setEventListeners() {
    for (btn of document.getElementsByClassName("add-to-cart-btn")) {
        btn.addEventListener("click", sendProductIdToServer);
    }
}

function sendProductIdToServer() {

}

function init() {
    setEventListeners();
}

init();
