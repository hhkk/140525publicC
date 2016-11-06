/**
 * Created by henryms on 5/28/2014.
 */


app.controller("MainController", function($scope){
    var c3 =['tom3', 'dick3'];
    $scope.inputValue = "joe";
    $scope.items=['tom', 'dick']
    $scope.items3=c3;
    $scope.countries = [
        {name: 'France', population: 63.1},
        {name: 'United Kingdom', population: 61.8}
    ];

    $scope.population = 7000;
    $scope.countries = [
        {name: 'France', population: 63.1},
        {name: 'United Kingdom', population: 61.8}
    ];


});


