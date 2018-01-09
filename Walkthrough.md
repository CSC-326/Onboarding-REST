# Walkthrough


Fortunately, Spring makes it extremely simple to write REST APIs.  In Spring, _any_ class can be a "REST controller", responding to REST API requests.  The only requirement is that any such class must have a `@RestController` annotation on the class itself, such as [CoffeeMaker's `RESTAPIController`](https://github.ncsu.edu/engr-csc326-staff/Onboarding/blob/master/CoffeeMaker/src/main/java/edu/ncsu/csc/coffee_maker/controllers/RESTAPIController.java):
```
@RestController
@SuppressWarnings ( { "unchecked", "rawtypes" } )
public class RESTAPIController {
    ...
}
```

Within an annotated class, individual methods must be annotated as well.  First, the method must have an annotation indicating _which_ type of REST request it will respond to.  For instance,
```
@DeleteMapping ( BASE_PATH + "/recipes/{id}" )
public ResponseEntity deleteRecipe ( @PathVariable final String id ) {
	...
}
```
indicates via annotation that the method will respond only to DELETE requests.  The Spring framework will make sure of this automatically and will not route other requests to the controller.

Notice the presence of the `{id}` in the annotation and its re-occurrence in the parameter list of the method.  This specifies _which_ recipe it is that we're trying to delete.  In general, when trying to interact with a specific instance of an object, you'll need to specify this parameter (but you will _not_ when interacting with the entire collection, such as performing a `GET /recipes/` to retrieve all of them).

Exactly what path variable is used to interact with a REST API is up to the writer of the API, but should generally be the primary key of whatever the data type is.  For `Recipe`s in CoffeeMaker, this is a Long that is the `Recipe` `id`.  For other object types, this primary key might be a String that is the name of the object.  Use whatever makes sense and lets you unambiguously refer to the object in question (such as `username` when interacting with Users, but probably not `firstName`).

Additionally, notice the return type of the method above: Spring REST API methods will (typically) return a `ResponseEntity`, which is a Spring data structure that is automatically serialized to JSON when sent back to the client.  The one exception to this rule is that a "get all" (such as the `GET /recipes/` mentioned above) will return a `List` of the appropriate type, which will again automatically be serialized to JSON.

Within the method body itself, the developer is responsible for writing whatever code is necessary for retrieving, updating, or deleting the request that comes in.  For instance, in our CoffeeMaker "POST recipe" method, we see the following:

```
@PostMapping ( BASE_PATH + "/recipes" )
public ResponseEntity createRecipe ( @RequestBody final Recipe recipe ) {
    if ( null != Application.getCoffeeMaker().getRecipeBook().findRecipe( recipe.getName() ) ) {
    return new ResponseEntity( "Recipe with the name " + recipe.getName() + " already exists",
                HttpStatus.CONFLICT );
    }
    try {
        Application.getCoffeeMaker().getRecipeBook().addRecipe( recipe );
        return new ResponseEntity( recipe, HttpStatus.OK );
    }
    catch ( final Exception e ) {
        return new ResponseEntity( "Insufficient space in recipe book for recipe " + recipe.getName(),
                HttpStatus.INSUFFICIENT_STORAGE );
    }

}
```

Here, we must perform some validation to make sure that the recipe does not already exist and that space exists for it.  Then, we create and return a `ResponseEntity` with the result.  Spring's `ResponseEntity` is a _very_ flexible structure whose constructor takes two parameters: the response body to be returned to the user (a String indicating an error message, or any other Object that was retrieved) and a [HTTP status](https://en.wikipedia.org/wiki/List_of_HTTP_status_codes) indicating the status of the request.
