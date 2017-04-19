(function() {
    'use strict';

    angular
        .module('sioeSqlApp')
        .controller('AnexoDetailController', AnexoDetailController);

    AnexoDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Anexo'];

    function AnexoDetailController($scope, $rootScope, $stateParams, previousState, entity, Anexo) {
        var vm = this;

        vm.anexo = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('sioeSqlApp:anexoUpdate', function(event, result) {
            vm.anexo = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
