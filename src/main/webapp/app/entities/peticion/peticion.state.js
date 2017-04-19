(function() {
    'use strict';

    angular
        .module('sioeSqlApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('peticion', {
            parent: 'entity',
            url: '/peticion?page&sort&search',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'Peticions'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/peticion/peticions.html',
                    controller: 'PeticionController',
                    controllerAs: 'vm'
                }
            },
            params: {
                page: {
                    value: '1',
                    squash: true
                },
                sort: {
                    value: 'id,asc',
                    squash: true
                },
                search: null
            },
            resolve: {
                pagingParams: ['$stateParams', 'PaginationUtil', function ($stateParams, PaginationUtil) {
                    return {
                        page: PaginationUtil.parsePage($stateParams.page),
                        sort: $stateParams.sort,
                        predicate: PaginationUtil.parsePredicate($stateParams.sort),
                        ascending: PaginationUtil.parseAscending($stateParams.sort),
                        search: $stateParams.search
                    };
                }],
            }
        })
        .state('peticion-detail', {
            parent: 'peticion',
            url: '/peticion/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'Peticion'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/peticion/peticion-detail.html',
                    controller: 'PeticionDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                entity: ['$stateParams', 'Peticion', function($stateParams, Peticion) {
                    return Peticion.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'peticion',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('peticion-detail.edit', {
            parent: 'peticion-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/peticion/peticion-dialog.html',
                    controller: 'PeticionDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Peticion', function(Peticion) {
                            return Peticion.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('peticion.new', {
            parent: 'peticion',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/peticion/peticion-dialog.html',
                    controller: 'PeticionDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                numero: null,
                                nombre_solicitante: null,
                                paterno_solicitante: null,
                                materno_solicitante: null,
                                cargo_solicitante: null,
                                direccion_solicitante: null,
                                acto_constar: null,
                                fecha: null,
                                responsable: null,
                                status_prevencion: null,
                                tipo_evaluacion: null,
                                status_trabajo: null,
                                oficio: null,
                                oficio_prevencion: null,
                                notificacion_prevencion: null,
                                respuesta_prevencion: null,
                                acta: null,
                                acuerdo: null,
                                notificacion: null,
                                respuesta: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('peticion', null, { reload: 'peticion' });
                }, function() {
                    $state.go('peticion');
                });
            }]
        })
        .state('peticion.edit', {
            parent: 'peticion',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/peticion/peticion-dialog.html',
                    controller: 'PeticionDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Peticion', function(Peticion) {
                            return Peticion.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('peticion', null, { reload: 'peticion' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('peticion.remitir', {
            parent: 'peticion',
            url: '/{id}/remitir',
            data: {
                authorities: ['ROLE_ADMIN']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/peticion/peticion-remitir.html',
                    controller: 'PeticionRemitirController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Peticion', function(Peticion) {
                            return Peticion.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('peticion', null, { reload: 'peticion' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('peticion.evaluar', {
            parent: 'peticion',
            url: '/{id}/evaluar',
            data: {
                authorities: ['ROLE_ADMIN']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/peticion/peticion-evaluar.html',
                    controller: 'PeticionEvaluarController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Peticion', function(Peticion) {
                            return Peticion.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('peticion', null, { reload: 'peticion' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('peticion.prevencion', {
            parent: 'peticion',
            url: '/{id}/prevencion',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/peticion/peticion-prevencion.html',
                    controller: 'PeticionPrevencionController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Peticion', function(Peticion) {
                            return Peticion.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('peticion', null, { reload: 'peticion' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('peticion.delete', {
            parent: 'peticion',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/peticion/peticion-delete-dialog.html',
                    controller: 'PeticionDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Peticion', function(Peticion) {
                            return Peticion.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('peticion', null, { reload: 'peticion' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
