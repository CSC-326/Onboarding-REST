```
    /**
     * REST API endpoint to provide update access to CoffeeMaker's singleton
     * Inventory. This will update the Inventory of the CoffeeMaker by adding
     * amounts from the Inventory provided to the CoffeeMaker's stored inventory
     *
     * @param inventory
     *            amounts to add to inventory
     * @return response to the request
     */
    @PutMapping ( BASE_PATH + "/inventory" )
    public ResponseEntity updateInventory ( @RequestBody @Valid final Inventory inventory ) {
        inventoryService.addInventory( inventory );
        return new ResponseEntity( inventoryService.getInventory(), HttpStatus.OK );
    }

    @GetMapping ( BASE_PATH + "/orders/{id}" )
    public ResponseEntity getOrder (@PathVariable("id") int id) throws Exception {
        List<Order> orders = inventoryService.getOrders();
        if( id <= orders.size() )
        {
            return new ResponseEntity( orders.get(id-1), HttpStatus.OK );
        }
        else
        {
            return new ResponseEntity( "Could not find order: " + id + ", number of orders " + orders.size(), HttpStatus.NOT_FOUND );
        }
    }
```
