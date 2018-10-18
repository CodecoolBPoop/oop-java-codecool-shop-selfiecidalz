$(".refresh").click(refreshQuantity, event);

function refreshQuantity(event) {
    let quantity = event.target.previousElementSibling.value;
    let defValue = event.target.previousElementSibling.defaultValue;
    let unitPrice = event.target.parentElement.nextElementSibling.innerHTML;
    let id = event.target.dataset["productid"];
    if (parseInt(quantity) < 0) {
        alert("Can't be negative number");
        return;
    } else if (parseInt(quantity) == 0) {
        removeItem(event);
    } else {
        event.target.parentElement.nextElementSibling.nextElementSibling.innerHTML = parseInt(quantity)*parseFloat(unitPrice) + " $";
        let total = document.getElementById("total").innerHTML;
        document.getElementById("total").innerHTML = parseFloat(total) - ((defValue - quantity)*parseFloat(unitPrice));
        event.target.previousElementSibling.defaultValue = quantity;
    }
    $.post("/refresh", {quantity: quantity, id: id})
}