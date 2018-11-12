function openTab(event) {
    // Declare all variables
    var i, tabcontent, tablinks;


    // Get all elements with class="tabcontent" and hide them
    tabcontent = document.getElementsByClassName("tabcontent");
    for (i = 0; i < tabcontent.length; i++) {
        tabcontent[i].style.display = "none";
    }

    // Get all elements with class="tablinks" and remove the class "active"
    tablinks = document.getElementsByClassName("tablinks");
    for (i = 0; i < tablinks.length; i++) {
        tablinks[i].className = tablinks[i].className.replace(" active", "");
    }

    var value = event.target.value;

    // Show the current tab, and add an "active" class to the button that opened the tab
    document.getElementById(value).style.display = "block";
    event.currentTarget.className += " active";
}

var evt = document.getElementsByClassName("tablinks");
for (i = 0; i < evt.length; i++) {
    evt[i].addEventListener("click", function() {
        openTab(event);
    });
}
