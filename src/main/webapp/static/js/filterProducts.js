$(".dropdown-item").click(filterProduct, event);

function filterProduct(event) {
    var id = event.target.id;
    $.post("/ajax/filter-products", {id:id}, function (response) {
        alert(response);
    });
}