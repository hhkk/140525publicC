/**
 * Created by henryms on 6/14/2014.
 */
/*!
 ** Todo-Sortable-List Example App
 ** Licensed under the Apache License v2.0
 ** http://www.apache.org/licenses/LICENSE-2.0
 ** Built by Jay Kanakiya ( @techiejayk )
 **/
"use strict";

var App = angular.module("todo",["ui.sortable","LocalStorageModule"]);
// <html lang="en" ng-app="todo">

App.controller("TodoCtrl",function  ($scope,localStorageService) {

    $scope.init = function () {

        //localStorageService.get

//        if (localStorageService.get("lsTodoList")===null)
//        {
//            //alert ("localStorageService.get('lsTodoList')===null");
//        }
//        else
//        {
//            alert ("localStorageService.get('lsTodoList')!==null");
//        }
//
//        if (localStorageService===null)
//        {
//            alert ("localStorageService===null");
//        }
//        else
//        {
////            alert ("localStorageService!==null");
////            alert ("getclass:" + getClass("in ng init", localStorageService));
//
//        }
//
        //alert ("at stage 2");

        try {
            //alert ("getClass(localStorageService.get('lsTodoList'):" + getClass("in ng init1", localStorageService.get('lsTodoList')));
            //alert ("getclass:" + getClass("in ng init", localStorageService));
            localStorageService.clearAll();

            //localStorageService.removeItem("lsTodoList")
        }
        catch(err) {
            var text = "Exception description 2: " + err.message + "\n\n";
            alert(text);
            throw err;
        }

        //alert ("hbk in angular init");

        if (localStorageService.get("lsTodoList")===null) {
            //alert ("hbk in angular init data");
            //
            //  alert ("hi hk ls get is null");
            //debugger;

            $scope.model = [
                {
                    name : "Primary" , list : [
                    { taskName : "1.Create an Angular-js TodoList" , isDone : false },
                    { taskName : "1.Understanding Angular-js Directives" , isDone : true }
                ]
                },
                {
                    name : "Secondary" , list : [
                    { taskName : "2.Build an open-source website builder" , isDone : false },
                    { taskName : "2.BUild an Email Builder" , isDone : false }
                ]
                },
                {
                    name : "Archive" , list : [
                    { taskName : "3.Build an open-source website builder" , isDone : false },
                    { taskName : "3.BUild an Email Builder" , isDone : false }
                ]
                }
            ];
        }else{
            //salert ("was not null");
            $scope.model = localStorageService.get("lsTodoList");
        }
        $scope.show = "All";
        $scope.currentShow = 0;
    };

    $scope.addTodo = function  () {
        /*Should prepend to array*/
        $scope.model[$scope.currentShow].list.splice(0,0,{taskName : $scope.newTodo , isDone : false });
        /*Reset the Field*/
        $scope.newTodo = "";
    };

    $scope.deleteTodo = function  (index) {
        $scope.model[$scope.currentShow].list.splice(index, 1);
    };

    $scope.todoSortable = {
        containment : "parent",//Dont let the user drag outside the parent
        cursor : "move",//Change the cursor icon on drag
        tolerance : "pointer"//Read http://api.jqueryui.com/sortable/#option-tolerance
    };

    $scope.changeTodo = function  (i) {
        alert ("in changeTodo to:"+ i.toString()) ;
        $scope.currentShow = i;
    };

    $scope.hbkbutton = function  () {
        alert ("in hbkbutton:") ;
        $scope.model[0].list[0].taskName = "hkhkhk";
    };
    /* Filter Function for All | Incomplete | Complete */
    $scope.showFn = function  (todo) {
        if ($scope.show === "All") {
            return true;
        }else if(todo.isDone && $scope.show === "Complete"){
            return true;
        }else if(!todo.isDone && $scope.show === "Incomplete"){
            return true;
        }else{
            return false;
        }
    };


    $scope.$watch("model",function  (newVal,oldVal) {
        debugger;
        var t = localStorageService.get("lsTodoList");
        //alert(t.toString());
        debugger;
        if (newVal !== null && angular.isDefined(newVal) && newVal!==oldVal) {
            //alert("angular.toJson(newVal):"+angular.toJson(newVal));
            debugger;
            localStorageService.add("lsTodoList",angular.toJson(newVal));
            alert("in watch model");
        }
    },true);

});