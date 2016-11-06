/**
 * Created by henryms on 5/28/2014.
 */

var c3 =['tom5', 'dick5'];

app.controller("MainController", function($scope){
    $scope.inputValue = "joe";
    $scope.items=['tom', 'dick']
    $scope.items3=['tom5', 'dick5'];
    $scope.countries = [
        {name: 'France', population: 63.1},
        {name: 'United Kingdom', population: 61.8}
    ];

    $scope.population = 7000;
    $scope.countries = [
        {name: 'France', population: 63.1},
        {name: 'United Kingdom', population: 61.8}
    ];




    // Use the relatively new watchCollection().
    $scope.$watchCollection(
        "items3",
        function( newValue, oldValue ) {

            alert ( "in $watch1" );
            //alert ( $scope.watchCollectionLog );

        }
    );

    // Use the old watch() with default object equality, which defaults to use object REFERENCE checks.
    $scope.$watch(
        "items3",
        function( newValue, oldValue ) {

            alert ( "in $watch2:" + oldValue.toString() + ":" + newValue.toString());
            //$scope.items3=['tom55', 'dick55'];
            //$scope.$apply();
            $scope.$digest();
            alert ( "in $watch2.2:" + oldValue.toString() + ":" + newValue.toString());
            //alert ( $scope.watchLog );

        }
    );

    // Use the old watch() method, but turn on deep object equality, which will compare the deep object tree for changes.
    $scope.$watch(
        "items3",
        function( newValue, oldValue ) {

            alert ( "in $watch3" );
            //alert ( $scope.watchEqualityLog );

        },
        true // Object equality (not just reference).
    );

    $scope.go = function() {
        alert ("in go");
        $scope.items3=['tom566', 'dick566'];
        alert ("in go2");
        //$scope.msg = 'clicked';
    }

    function doclickhk()
    {
        c3 =['tomchg', 'dickchg'];
        alert ("did chg");
    }






});


