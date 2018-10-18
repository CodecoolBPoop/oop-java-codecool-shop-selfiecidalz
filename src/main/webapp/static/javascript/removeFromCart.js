function setEventListeners() {
    $('.remove-item').click(removeItem, event);
}

function removeItem(event) {
    let id = event.target.dataset["productid"];
    console.log(id);
    $.post("/remove-from-cart", {id:id});
    event.target.closest('tr').remove();
}

setEventListeners();