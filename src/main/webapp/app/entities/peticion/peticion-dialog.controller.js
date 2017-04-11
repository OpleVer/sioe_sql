(function() {
    'use strict';

    angular
        .module('sioeSqlApp')
        .controller('PeticionDialogController', PeticionDialogController);

    PeticionDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'DataUtils', 'entity', 'Peticion', 'Origen', 'Peticionario'];

    function PeticionDialogController ($timeout, $scope, $stateParams, $uibModalInstance, DataUtils, entity, Peticion, Origen, Peticionario) {
        var vm = this;

        vm.peticion = entity;
        vm.clear = clear;
        vm.datePickerOpenStatus = {};
        vm.openCalendar = openCalendar;
        vm.byteSize = DataUtils.byteSize;
        vm.openFile = DataUtils.openFile;
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

        vm.datePickerOpenStatus.fecha = false;

        vm.setOficio = function ($file, peticion) {
            if ($file) {
                DataUtils.toBase64($file, function(base64Data) {
                    $scope.$apply(function() {
                        peticion.oficio = base64Data;
                        peticion.oficioContentType = $file.type;
                    });
                });
            }
        };

        vm.setOficio_prevencion = function ($file, peticion) {
            if ($file) {
                DataUtils.toBase64($file, function(base64Data) {
                    $scope.$apply(function() {
                        peticion.oficio_prevencion = base64Data;
                        peticion.oficio_prevencionContentType = $file.type;
                    });
                });
            }
        };

        vm.setNotificacion_prevencion = function ($file, peticion) {
            if ($file) {
                DataUtils.toBase64($file, function(base64Data) {
                    $scope.$apply(function() {
                        peticion.notificacion_prevencion = base64Data;
                        peticion.notificacion_prevencionContentType = $file.type;
                    });
                });
            }
        };

        vm.setRespuesta_prevencion = function ($file, peticion) {
            if ($file) {
                DataUtils.toBase64($file, function(base64Data) {
                    $scope.$apply(function() {
                        peticion.respuesta_prevencion = base64Data;
                        peticion.respuesta_prevencionContentType = $file.type;
                    });
                });
            }
        };

        vm.setActa_procede = function ($file, peticion) {
            if ($file) {
                DataUtils.toBase64($file, function(base64Data) {
                    $scope.$apply(function() {
                        peticion.acta_procede = base64Data;
                        peticion.acta_procedeContentType = $file.type;
                    });
                });
            }
        };

        vm.setAcuerdo_procede = function ($file, peticion) {
            if ($file) {
                DataUtils.toBase64($file, function(base64Data) {
                    $scope.$apply(function() {
                        peticion.acuerdo_procede = base64Data;
                        peticion.acuerdo_procedeContentType = $file.type;
                    });
                });
            }
        };

        vm.setNotificacion_procede = function ($file, peticion) {
            if ($file) {
                DataUtils.toBase64($file, function(base64Data) {
                    $scope.$apply(function() {
                        peticion.notificacion_procede = base64Data;
                        peticion.notificacion_procedeContentType = $file.type;
                    });
                });
            }
        };

        vm.setAcuerdo_noprocede = function ($file, peticion) {
            if ($file) {
                DataUtils.toBase64($file, function(base64Data) {
                    $scope.$apply(function() {
                        peticion.acuerdo_noprocede = base64Data;
                        peticion.acuerdo_noprocedeContentType = $file.type;
                    });
                });
            }
        };

        vm.setNotificacion_noprocede = function ($file, peticion) {
            if ($file) {
                DataUtils.toBase64($file, function(base64Data) {
                    $scope.$apply(function() {
                        peticion.notificacion_noprocede = base64Data;
                        peticion.notificacion_noprocedeContentType = $file.type;
                    });
                });
            }
        };

        vm.setAcuerdo_presentacion = function ($file, peticion) {
            if ($file) {
                DataUtils.toBase64($file, function(base64Data) {
                    $scope.$apply(function() {
                        peticion.acuerdo_presentacion = base64Data;
                        peticion.acuerdo_presentacionContentType = $file.type;
                    });
                });
            }
        };

        vm.setNotificacion_presentacion = function ($file, peticion) {
            if ($file) {
                DataUtils.toBase64($file, function(base64Data) {
                    $scope.$apply(function() {
                        peticion.notificacion_presentacion = base64Data;
                        peticion.notificacion_presentacionContentType = $file.type;
                    });
                });
            }
        };

        function openCalendar (date) {
            vm.datePickerOpenStatus[date] = true;
        }
    }
})();
