(function() {
    'use strict';

    angular
        .module('sioeSqlApp')
        .controller('OrigenDetailController', OrigenDetailController);

    OrigenDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Origen', 'Peticion'];

    function OrigenDetailController($scope, $rootScope, $stateParams, previousState, entity, Origen, Peticion) {
        var vm = this;

        vm.origen = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('sioeSqlApp:origenUpdate', function(event, result) {
            vm.origen = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
