(function() {
    'use strict';
    angular
        .module('sioeSqlApp')
        .factory('Peticion', Peticion);

    Peticion.$inject = ['$resource', 'DateUtils'];

    function Peticion ($resource, DateUtils) {
        var resourceUrl =  'api/peticions/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                        data.fecha = DateUtils.convertDateTimeFromServer(data.fecha);
                    }
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    }
})();
