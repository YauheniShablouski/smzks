angular.module('MainApp', ['ngRoute', 'ngMaterial'])
    .config(function($routeProvider, $httpProvider) {

        $routeProvider.when('/registration', {
            templateUrl : 'registration.html',
            controller : 'registration',
            controllerAs: 'controller'
        }).when('/login', {
            templateUrl : 'login.html',
            controller : 'login',
            controllerAs: 'controller'
        }).when('/', {
            templateUrl : 'home.html',
            controller : 'navigation',
            controllerAs : 'controller'
        }).otherwise('/');

        $httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';

    })


    .controller('navigation', function($http, $location, $rootScope) {
        var self = this;

        var authenticate = function(credentials, callback) {
            var headers = credentials ? {authorization : "Basic "
            + btoa(credentials.username + ":" + credentials.password)
            } : {};

            $http.get('user', {headers : headers}).success(function(data) {
                if (data.name) {
                    $rootScope.authenticated = true;
                    $rootScope.currentUser = data;
                } else {
                    $rootScope.authenticated = false;
                }
                callback && callback();
            }).error(function() {
                $rootScope.authenticated = false;
                callback && callback();
            });

        }
        $rootScope.authenticate = authenticate;
        authenticate();

        $rootScope.logout = function() {
            $http.post('logout', {}).finally(function() {
                $rootScope.authenticated = false;
                $location.path("/login");
                $rootScope.currentUser = null;
            });
        }
    })


    .controller('login', function($http, $location, $rootScope, $mdDialog) {
        var self = this;


        $rootScope.authenticate();
        self.credentials = {};
        self.login = function() {
            $rootScope.authenticate(self.credentials, function() {
                if ($rootScope.authenticated) {
                    $location.path("/");
                    self.error = false;
                } else {
                    $location.path("/login");
                    self.error = true;

                    $mdDialog.show(
                        $mdDialog.alert()
                            .parent(angular.element(document.querySelector('#popupContainer')))
                            .clickOutsideToClose(true)

                            .title('Sign in error!')
                            .textContent('Wrong login or password.')
                            .ok('Got it!')

                    );
                }
            });
        };

    })



    .controller('registration', function($http ,$location, $rootScope) {
        var self = this;
        $rootScope.authenticate();
        self.signUp = function() {
            $http.post('/signup', self.user);
            $location.path("/login");
        }

    });
