(function() {
    'use strict';

    angular
        .module('sioeSqlApp')
        .controller('PeticionRemitirController', PeticionRemitirController);

    PeticionRemitirController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'DataUtils', 'entity', 'Peticion', 'Origen', 'Peticionario'];

    function PeticionRemitirController ($timeout, $scope, $stateParams, $uibModalInstance, DataUtils, entity, Peticion, Origen, Peticionario) {
        var vm = this;

        vm.peticion = entity;
        vm.clear = clear;
        vm.save = save;
        vm.origens = Origen.query();
        vm.peticionarios = Peticionario.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.peticion.id !== null) {
                Peticion.update(vm.peticion, onSaveSuccess, onSaveError);
            } else {
                Peticion.save(vm.peticion, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('sioeSqlApp:peticionUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }
    }
})();
