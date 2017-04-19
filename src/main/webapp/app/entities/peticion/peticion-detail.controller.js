(function() {
    'use strict';

    angular
        .module('sioeSqlApp')
        .controller('PeticionDetailController', PeticionDetailController);

    PeticionDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Peticion', 'Origen', 'Peticionario'];

    function PeticionDetailController($scope, $rootScope, $stateParams, previousState, entity, Peticion, Origen, Peticionario) {
        var vm = this;

        vm.peticion = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('sioeSqlApp:peticionUpdate', function(event, result) {
            vm.peticion = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
