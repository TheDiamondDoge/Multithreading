/**
 * Created by The Diamond Doge on 09.12.2017.
 */

$(function () {
    $.ajax({
        type: "POST",
        url: window.location + "core/converter/inOutDirectory",
        success: function (str) {
            var inVal = $("#in").text();
            var outVal = $("#out").text();

            $("#in").text(inVal + str[0]);
            $("#out").text(outVal + str[1]);
        }
    });
});