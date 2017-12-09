/**
 * Created by The Diamond Doge on 09.12.2017.
 */

$(function () {
   $("#refreshButton").on("click", function () {
       $("#refreshButton").prop("disabled", true);
       ajaxGet();
   });

   function ajaxGet() {
       $.ajax({
           type: "POST",
           url: window.location + "core/converter/processed",
           success: function (result) {
               $("#processedList").empty();

               $.each(result, function (i, path) {
                   $("#processedList").append("<li>" + path + "</li>");
               });

           },

           error: function () {
               $("#convertedDiv").html("<h1>ERROR</h1>");
           }
       });
       $("#refreshButton").prop("disabled", false);
   }
});