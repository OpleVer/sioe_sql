(function() {
    'use strict';

    angular
        .module('sioeSqlApp')
        .controller('PeticionDetailController', PeticionDetailController);

    PeticionDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'DataUtils', 'entity', 'Peticion', 'Origen', 'Peticionario'];

    function PeticionDetailController($scope, $rootScope, $stateParams, previousState, DataUtils, entity, Peticion, Origen, Peticionario) {
        var vm = this;

        vm.peticion = entity;
        vm.previousState = previousState.name;
        vm.byteSize = DataUtils.byteSize;
        vm.openFile = DataUtils.openFile;

        var unsubscribe = $rootScope.$on('sioeSqlApp:peticionUpdate', function(event, result) {
            vm.peticion = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
