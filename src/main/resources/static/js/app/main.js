angular.module('MainApp', ['ngRoute', 'ngMaterial'])
    .config(function($routeProvider, $httpProvider) {

        $routeProvider
            .when('/registration', {
                templateUrl : 'registration.html',
                controller : 'registrationController',
                controllerAs: 'vm'
            })
            .when('/login', {
                templateUrl : 'login.html',
                controller : 'loginController',
                controllerAs: 'vm'
            })
            .when('/', {
                templateUrl : 'home.html',
                controller : 'homeController',
                controllerAs : 'vm'
            })
            .otherwise('/');

        $httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';

    })
    
    .controller('homeController', homeController)
    .controller('loginController', loginController)
    .controller('registrationController', registrationController)

    .factory('authHttpResponseInterceptor', function($q,$location){
        return {
            response: function(response){
                if (response.status === 401) {
                    console.log("Response 401");
                }
                return response || $q.when(response);
            },
            responseError: function(rejection) {
                if (rejection.status === 401) {
                    console.log("Response Error 401",rejection);
                    $location.path('/login');
                }
                return $q.reject(rejection);
            }
        }
    })
    .config(function($httpProvider) {
        $httpProvider.interceptors.push('authHttpResponseInterceptor');
    });


    function registrationController($http ,$location) {
        var vm = this;

        vm.signUp = function() {
            $http.post('/signup', vm.user);
            $location.path("/login");
        }
    };


    function loginController( $location, $rootScope, $mdDialog) {
        var vm = this;

        vm.credentials = {};
        vm.login = function() {
            $rootScope.authenticate(vm.credentials, function() {
                if ($rootScope.authenticated) {
                    $location.path("/");
                    vm.error = false;
                } else {
                    $location.path("/login");
                    vm.error = true;

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
    }


    function homeController($http, $location, $rootScope) {
        var vm = this;

        var authenticate = function(credentials, callback) {
            var headers = credentials ? {authorization : "Basic "
                + btoa(credentials.username + ":" + credentials.password)
            } : {};

            $http.get('user', {headers : headers}).success(function(data) {
                if (data.name) {
                    $rootScope.authenticated = true;
                    vm.currentUser = data;
                } else {
                    $rootScope.authenticated = false;
                }
                callback && callback();
            }).error(function() {
                $rootScope.authenticated = false;
                callback && callback();
            });

        }
        authenticate();
        $rootScope.authenticate = authenticate;

        vm.logout = function() {
            $http.post('logout', {}).finally(function() {
                $rootScope.authenticated = false;
                $location.path("/login");
                $rootScope.currentUser = null;
            });
        }
    }