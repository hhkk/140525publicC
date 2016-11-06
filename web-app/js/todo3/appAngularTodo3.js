/**
 * Created by henryms on 7/3/2014.
 */
// Create an application module for our demo.
var app = angular.module( "Todo3AngularModule", [] );
// <html lang="en" ng-app="Todo3AngularModule">

//eg pairs with
//    <body ng-controller="TodoCtrl" ng-init="init()">
//    <body ng-controller="AppAngularTodo3Ctrl" ng-init="init()">




// -------------------------------------------------- //
// -------------------------------------------------- //


// I control the root of the application.
app.controller(
    "AppAngularTodo3Ctrl",
    function( $scope ) {

        //alert("hbk in AppAngularTodo3Ctrl   angular init1");


        $scope.init = function () {

            //localStorageService.get

            //localStorageService.removeItem("lsTodoList")
            alert("hbk in AppAngularTodo3Ctrl   angular init2");
        }




        // Define the collection of friends to render.
        $scope.friends = [
            {
                name: "Sarah"
            },
            {
                name: "Joanna"
            },
            {
                name: "Tricia"
            }
        ];

        // Define the collection of enemies to render. This
        // collection will start out empty in order to demonstrate
        // that this approach requires at least ONE element to
        // render.
        $scope.enemies = [];

        // ---
        // PUBLIC METHODS.
        // ---

        // I add a new enemty to the collection.
        $scope.addEnemy = function() {

            $scope.enemies.push({
                name: "Banana"
            });

        }

        // I add a new friend to the collection.
        $scope.addFriend = function() {

            $scope.friends.push({
                name: "Anna"
            });

        }

        // I am the callback handler for the ngRepeat completion.
        $scope.doSomething = function( index ) {

            console.log( "ngRepeat completed (" + index + ")!" );
        };
    }
);
