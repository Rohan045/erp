# ERP Backend Project on Spring Boot

### Roles and its accessibility to the backend controllers

* ADMIN
  * This Role is given to admin of the system
  * This role has access to all controllers
  * Using this role, we can assign roles to another user
* USER
  * This is assigned as default to any new user
  * Customer - get/{id}, get
  * Inventory - filter
  * Item - get/{id}, get
  * Location - get/{id}, get
  * Roles - No Access
  * SalesOrder - get/{id}, get
  * User - No Access
  * WarehouseShipment - transform/salesOrder/{id}, get
* WAREHOUSE_MANAGER
  * All USER Roles
  * Inventory - post, update
  * Item - post
  * Location - post
  * Roles - No Access
  * User - No Access
  * WarehouseShipment - post(Changing the status), updateQty/{id}

