$(document).ready(function() {
    $("#submit").click(function() {
        $.post("/",{url: $("#url").val()}, function(data) {
            $("#new-url").html(data);
        });
    });

    $("#url-shortener").bind("keypress", function(e) {
        if (e.keyCode == 13) return false;
    });
});