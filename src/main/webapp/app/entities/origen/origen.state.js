(function() {
    'use strict';

    angular
        .module('sioeSqlApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('origen', {
            parent: 'entity',
            url: '/origen',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'Origens'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/origen/origens.html',
                    controller: 'OrigenController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
            }
        })
        .state('origen-detail', {
            parent: 'origen',
            url: '/origen/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'Origen'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/origen/origen-detail.html',
                    controller: 'OrigenDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                entity: ['$stateParams', 'Origen', function($stateParams, Origen) {
                    return Origen.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'origen',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('origen-detail.edit', {
            parent: 'origen-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/origen/origen-dialog.html',
                    controller: 'OrigenDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Origen', function(Origen) {
                            return Origen.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('origen.new', {
            parent: 'origen',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/origen/origen-dialog.html',
                    controller: 'OrigenDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                nombre: null,
                                privilegio: null,
                                zona: null,
                                distrito: null,
                                municipio: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('origen', null, { reload: 'origen' });
                }, function() {
                    $state.go('origen');
                });
            }]
        })
        .state('origen.edit', {
            parent: 'origen',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/origen/origen-dialog.html',
                    controller: 'OrigenDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Origen', function(Origen) {
                            return Origen.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('origen', null, { reload: 'origen' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('origen.delete', {
            parent: 'origen',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/origen/origen-delete-dialog.html',
                    controller: 'OrigenDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Origen', function(Origen) {
                            return Origen.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('origen', null, { reload: 'origen' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
