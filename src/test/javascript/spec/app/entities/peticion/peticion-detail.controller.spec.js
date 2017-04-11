'use strict';

describe('Controller Tests', function() {

    describe('Peticion Management Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockPreviousState, MockPeticion, MockOrigen, MockPeticionario;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockPreviousState = jasmine.createSpy('MockPreviousState');
            MockPeticion = jasmine.createSpy('MockPeticion');
            MockOrigen = jasmine.createSpy('MockOrigen');
            MockPeticionario = jasmine.createSpy('MockPeticionario');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity,
                'previousState': MockPreviousState,
                'Peticion': MockPeticion,
                'Origen': MockOrigen,
                'Peticionario': MockPeticionario
            };
            createController = function() {
                $injector.get('$controller')("PeticionDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'sioeSqlApp:peticionUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
