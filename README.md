# lunch
REST API test

/api - common REST API namespace

/api/admin - admin area

/api/admin/users - standard CRUD for user data administration

/api/admin/dishes - standard CRUD for dish data administration

/api/admin/restaurants - standard CRUD for restaurant data administration

/api/admin/restaurants/{restaurant id}/{menu date} (GET) - get restaurant menu

/api/admin/restaurants/{restaurant id}/{menu date}/{dish id} (PUT) - add dish to restaurant menu

/api/admin/restaurants/{restaurant id}/{menu date}/{dish id} (DELETE) - remove dish from restaurant menu



/api/restaurants - regular users area

/api/restaurants (GET) - get today's voting current results

/api/restaurants/{restaurant id} (GET) - get today's restaurant menu

/api/restaurants/{restaurant id} (PUT) - vote for restaurant

See test cases for details.
