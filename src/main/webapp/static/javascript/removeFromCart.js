function setEventListeners() {
    $('.remove-item').click(removeItem, event);
}

function removeItem(event) {
    let id = event.target.dataset["productid"];
    $.post("/remove-from-cart", {id: id});
    let subTotal = document.getElementById("subtotal" + id);
    let total = document.getElementById("total");
    total.innerHTML = String(parseFloat(total.innerText) - parseFloat(subTotal.innerHTML));
    event.target.closest('tr').remove();

}

setEventListeners();