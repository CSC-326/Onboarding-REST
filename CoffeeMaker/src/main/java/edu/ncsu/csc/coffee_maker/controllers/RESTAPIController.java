package edu.ncsu.csc.coffee_maker.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import edu.ncsu.csc.coffee_maker.Application;
import edu.ncsu.csc.coffee_maker.models.persistent.Inventory;
import edu.ncsu.csc.coffee_maker.models.persistent.Orders.Order;
import edu.ncsu.csc.coffee_maker.services.InventoryService;

/**
 * This is the single controller in the CoffeeMaker application that handles
 * REST API endpoints In a larger application, we would want multiple REST API
 * controllers, one per model.
 *
 * Spring will automatically convert all of the ResponseEntity and List results
 * to JSON
 *
 * @author Kai Presler-Marshall
 *
 */
@SuppressWarnings ( { "unchecked", "rawtypes" } )
@RestController
public class RESTAPIController {

    /**
     * The InventoryService is a Spring Service used to provide access to the
     * Inventory for API requests
     */
    @Autowired
    InventoryService              inventoryService;

    /**
     * This is the base path for the APIs. All API routes are relative to this
     * path. By keeping it in a single variable, it removes redundant
     * information and makes it easier to change if the API is
     * modified/re-versioned
     */
    static final protected String BASE_PATH = "/api/v1/";

    /**
     * REST API endpoint to provide GET access to the CoffeeMaker's singleton
     * Inventory. This will convert the Inventory to JSON.
     *
     * @return response to the request
     */
    @GetMapping ( BASE_PATH + "/inventory" )
    public ResponseEntity getInventory () {
        final Inventory inventory = inventoryService.getInventory();
        return new ResponseEntity( inventory, HttpStatus.OK );
    }

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

}
