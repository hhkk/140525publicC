//
//// from http://maxburstein.com/blog/create-your-own-jquery-autocomplete-function/
//
//
//$(document).ready(function(){
//    var people = ['Peter Bishop', 'Nicholas Brody', 'Gregory House', 'Hank Lawson', 'Tyrion Lannister', 'Nucky Thompson'];
//    var cache = {};
//    var drew = false;
//
//    $("#search").on("keyup", function(event){
//        var query = $("#search").val()
//
//        if($("#search").val().length > 2){
//
//            //Check if we've searched for this term before
//            if(query in cache){
//                results = cache[query];
//            }
//            else{
//                //Case insensitive search for our people array
//                var results = $.grep(people, function(item){
//                    return item.search(RegExp(query, "i")) != -1;
//                });
//
//                //Add results to cache
//                cache[query] = results;
//            }
//
//            //First search
//            if(drew == false){
//                //Create list for results
//                $("#search").after('<ul id="res"></ul>');
//
//                //Prevent redrawing/binding of list
//                drew = true;
//
//                //Bind click event to list elements in results
//                $("#res").on("click", "li", function(){
//                    $("#search").val($(this).text());
//                    $("#res").empty();
//                 });
//            }
//            //Clear old results
//            else{
//                $("#res").empty();
//            }
//
//            //Add results to the list
//            for(term in results){
//                $("#res").append("<li>" + results[term] + "</li>");
//            }
//        }
//        //Handle backspace/delete so results don't remain with less than 3 characters
//        else if(drew){
//            $("#res").empty();
//        }
//    });
//});

function tim()
{
    alert ("HiFromTim");
}
