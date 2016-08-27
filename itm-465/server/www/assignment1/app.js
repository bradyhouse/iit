/* jshint unused: vars */
/* jshint unused: vars */

(function (cakeBaby) {

    /**
     * Global variables
     */
    var me = cakeBaby,
        dataFile = 'data/a1data.json';
    me.model = {
        /**
         * Recipe Cache Model
         *
         * This model is used to represent and manipulate
         * multiple instances of the same recipe.
         *
         * @type {{name: string, recipes: Array, totalCost: number, getName: getName, getAggregateCost: getAggregateCost, getRecipes: getRecipes, getCount: getCount, toString: toString}}
         */
        recipeCacheModel: {
            name: 'emptyCache',
            recipes: [],
            totalCost: 0,
            /**
             * Name accessor
             * @returns {string}
             */
            getName: function () {
                return this.name;
            },
            /**
             * AggregateCost Accessor
             * @returns {number}
             */
            getAggregateCost: function () {
                var cost = 0,
                    recipe = null,
                    recipes = this.getRecipes();
                if (recipes.length > 0) {
                    for (var i = 0; i < recipes.length; i++) {
                        recipe = recipes[i];
                        cost += recipe.getCost();
                    }
                }
                return cost;
            },
            /**
             * Recipes store accessor
             * @returns {*}
             */
            getRecipes: function () {
                return this.recipes;
            },
            /**
             * Recipes store count accessor
             * @returns {*}
             */
            getCount: function () {
                return this.recipes.length;
            },
            /**
             * Converts the contents of the
             * recipe store to a string literal
             * containing the name, count and aggregate cost
             * @returns {string}
             */
            toString: function () {
                var aggregateCost = this.getAggregateCost(),
                    message = '';
                if (aggregateCost > 0) {
                    message = this.getName() + ' x ' + this.getCount() + ' = $' + aggregateCost.toFixed(2);
                }
                return message;
            }
        },
        aggregateModel: {

        }
    }
    /**
     * Aggregate Model
     *
     * This model is used to represent
     * and manipulate multiple recipe
     * cache model instances.
     *
     * @type {{cache: {}, addRecipe: addRecipe, getRecipeCache: getRecipeCache, getRecipeAggregateCost: getRecipeAggregateCost, getCacheAggregateCost: getCacheAggregateCost, getCache: getCache, clearRecipeCache: clearRecipeCache, toString: toString}}
     */
    me.aggregateModel = {
        // ToDo - Make this an array.
        cache: {},
        /**
         * Add recipe method
         * Add a recipe model to the
         * associated recipe cache instance
         * defined in the cache object.
         * NOTE - If a recipe cache does not
         * exist already, then one is created.
         *
         * @param model - recipe model
         */
        addRecipe: function (model) {
            var recipeCache = this.getRecipeCache(model),
                recipeName = model.getName();
            if (!recipeCache) {
                recipeCache = Object.create(me.recipeCacheModel);
                recipeCache.name = recipeName;
                this.cache[recipeName] = recipeCache;
                this.addRecipe(model);
            } else {
                recipeCache.recipes.push(model);
            }
        },
        /**
         * Recipe Model cache accessor
         * @param model recipe model
         * @returns {*} recipeCache model
         */
        getRecipeCache: function (model) {
            var recipeCache = null,
                recipeName = model.getName();
            if (this.cache[recipeName]) {
                recipeCache = this.cache[recipeName];
            }
            return recipeCache;
        },
        /**
         * Recipe cache aggregate cost accessor
         * @param model - recipe model
         * @returns {number}
         */
        getRecipeAggregateCost: function (model) {
            var recipeCache = this.getRecipeCache(model),
                aggregateCost = 0;

            if (recipeCache) {
                aggregateCost = recipeCache.getAggregateCost();
            }
            return aggregateCost;
        },
        /**
         * Cache aggregate cost accessor
         * @returns {number}
         */
        getCacheAggregateCost: function () {
            var cost = 0,
                recipeCache = null,
                aggregateRecipeCost = 0,
                cache = this.getCache();
            for (recipeName in cache) {
                recipeCache = cache[recipeName];
                if (recipeCache) {
                    aggregateRecipeCost = recipeCache.getAggregateCost();
                    cost += aggregateRecipeCost;
                }
            }
            return cost;
        },
        /**
         * Cache accessor
         * @returns {*}
         */
        getCache: function () {
            return this.cache;
        },
        /**
         * Clear Recipe Cache method
         * Clear the contents of recipes array
         * of the associated recipeCache model
         * @param model - recipe model
         */
        clearRecipeCache: function (model) {
            if (this.getRecipeCache(model)) {
                this.getRecipeCache(model).recipes = [];
            }
        },
        /**
         * Converts the contents of the
         * cache to a string literal using the format--
         *
         *      <recipe cache model toString>
         *      <recipe cache model toString>
         *      ...
         *      ------------------------------
         *      Total Cost = $ <cache aggregate cost>
         *
         * @returns {string}
         */
        toString: function () {
            var message = '',
                recipeCache = null,
                recipeCacheToString = '',
                aggregateRecipesCost = this.getCacheAggregateCost();
            for (recipeName in this.cache) {
                recipeCache = this.cache[recipeName];
                recipeCacheToString = recipeCache.toString();
                message += recipeCacheToString + '\n';
            }
            message += '------------------------------\n' +
                'Total Cost = $' + aggregateRecipesCost.toFixed(2);
            return message;
        }
    };
    /**
     * Product Model
     *
     * This model is used to store and
     * manipulate a single product.
     *
     * @type {{name: string, cost: number, per: string, supplier: string, getName: getName, getCost: getCost, getPer: getPer, getSupplier: getSupplier}}
     */
    me.productModel = {
        name: "Flour",
        cost: 1.40,
        per: "cup",
        supplier: "Wholesale Baking",
        /**
         * Name accessor
         * @returns {string}
         */
        getName: function () {
            return this.name;
        },
        /**
         * Cost accessor
         * @returns {number}
         */
        getCost: function () {
            return this.cost;
        },
        /**
         * Per accessor
         * @returns {string}
         */
        getPer: function () {
            return this.per;
        },
        /**
         * Supplier accessor
         * @returns {string}
         */
        getSupplier: function () {
            return this.supplier;
        }
    };
    /**
     * Ingredient Model
     *
     * This model is used to store and
     * and manipulate a single recipe
     * ingredient.  An ingredient includes
     * a reference to the associated
     * product.
     *
     * @type {{name: string, product: null, quantity: number, cost: number, getCost: getCost, getQuantity: getQuantity, getProduct: getProduct, convertToString: convertToString}}
     */
    me.ingredientModel = {
        name: 'ingredientName',
        product: {},
        quantity: 0,
        cost: 0,
        /**
         * Cost accessor
         * @returns {number}
         */
        getCost: function () {
            return this.cost;
        },
        /**
         * Quantity accessor
         * @returns {number}
         */
        getQuantity: function () {
            return this.quantity;
        },
        /**
         * Product accessor
         * @returns {*}
         */
        getProduct: function () {
            return this.product;
        },
        /**
         * To String Override
         *
         * Converts the contents of the
         * model to a string literal
         * using the format  -
         *
         *      <ingredient name> <product unit> <product cost> = <ingredient cost>
         *
         * @returns {string}
         */
        toString: function () {
            // ToDo ~ Refactor
            return this.product ?
                this.name + ' ' + this.getQuantity() + ' ' + this.product.getPer() + ' ' + this.product.getCost().toFixed(2) +
                ' per ' + this.product.getPer() +
                ' = $' + this.getCost().toFixed(2) :
                this.name + ' ' + this.getQuantity() + ' ?? @ ' + this.product.getCost().toFixed(2) +
                ' per ' + '??' +
                ' = $' + this.getCost().toFixed(2);
        }
    };
    /**
     * Recipe Model
     *
     * This model is used to store and
     * and manipulate a single recipe object.
     * A recipe object contains multiple
     * ingredient objects.
     *
     * @type {{name: string, id: number, cost: number, ingredients: Array, inflateIngredients: inflateIngredients, calculateCost: calculateCost, getName: getName, getIngredients: getIngredients, getId: getId, getElementId: getElementId, getCost: getCost, mapToProduct: mapToProduct, ingredientsToString: ingredientsToString, toString: toString}}
     */
    me.recipeModel = {
        name: 'Chocolate Cake',
        id: 0,
        cost: 0,
        ingredients: [],
        /**
         * Inflate the Ingredients array
         * given some json, an ingredient model template,
         * and a product model array, populate the ingredients
         * collection with ingredient models.
         *
         * @param nodes - json
         * @param model - ingredient model
         * @param store - product model array
         */
        inflateIngredients: function (nodes, model, store) {
            var newIngredient = null,
                product = null,
                productCost = 0,
                ingredientQuantity = 0;

            for (key in nodes) {
                product = this.mapToProduct(store, key);
                ingredientQuantity = nodes[key];
                productCost = product ? product.getCost() : 0;
                newIngredient = Object.create(model);
                newIngredient.name = key;
                newIngredient.quantity = ingredientQuantity;
                newIngredient.product = product;
                newIngredient.cost = ingredientQuantity * productCost;
                this.ingredients.push(newIngredient);
            }
            this.calculateCost();
        },
        /**
         * Set the cost property based on the
         * contents of the ingredient array.
         */
        calculateCost: function () {
            var product = null,
                ingredientCost = 0.0,
                productQuantity = 0.0,
                productName = '',
                productCost = 0.0,
                recipeCost = 0.0,
                product = null,
                ingredients = this.getIngredients(),
                ingredient = null;

            for (var i = 0; i < ingredients.length; i++) {
                ingredient = ingredients[i];
                product = ingredients[i].getProduct();
                productQuantity = ingredient.getQuantity();
                productCost = product.getCost();
                ingredientCost = productQuantity * productCost;
                recipeCost += ingredientCost;
            }

            this.cost = recipeCost;

        },
        /**
         * Name accessor
         * @returns {string}
         */
        getName: function () {
            return this.name;
        },
        /**
         * Ingredients array accessor
         * @returns {*} - array of ingredient models
         */
        getIngredients: function () {
            return this.ingredients;
        },
        /**
         * Id accessor
         * @returns {number}
         */
        getId: function () {
            return this.id;
        },
        /**
         * Element Id accessor
         * This method returns a string that can be
         * used to assign a row element id based on
         * the id property of the recipe.  The resulting
         * string is in the format --
         *
         *      "recipe-row-<recipe id>"
         *
         * @returns {string}
         */
        getElementId: function () {
            return ('recipe-row-' + this.getId());
        },
        /**
         * Cost Accessor
         * @returns {number}
         */
        getCost: function () {
            return this.cost;
        },
        /**
         * Product model lookup method
         * given an array of product models and the ingredient name
         * return the matching product model
         *
         * @param productStore - array of product models
         * @param ingredientName - string name of the ingredient
         * @returns {*} - product model
         */
        mapToProduct: function (productStore, ingredientName) {
            var productModel = null;
            for (var i = 0; i < productStore.length; i++) {
                productModel = productStore[i];
                if (ingredientName === productModel.getName()) {
                    return productModel;
                }
            }
            return productModel;
        },
        /**
         * Converts the contents of the
         * ingredients array to a string literal
         * using the format--
         *
         * <ingredient model toString>
         * <ingredient model toString>
         * ...
         *
         * @returns {string}
         */
        ingredientsToString: function () {
            var result = '\n',
                ingredients = this.ingredients || null,
                ingredient = null,
                product = null;
            if (ingredients) {
                for (var i = 0; i < ingredients.length; i++) {
                    ingredient = ingredients[i];
                    product = ingredients[i].getProduct();
                    result += (ingredient.toString() + '\n');
                }
                return result;
            }
        },
        /**
         * Converts the contents of model
         * to a string literal
         * using the format--
         *
         *      <Name in UpperCase>
         *      <ingredient model toString>
         *      <ingredient model toString>
         *      ...
         *      ------------------------------
         *      Total Cost = $ <recipe cost>
         *
         * @returns {string}
         */
        toString: function () {
            return (this.getName().toUpperCase() + ': \n' +
                this.ingredientsToString() +
                '\n\n' +
                '------------------------------\n' +
                'Total Cost ~ \t$' + this.getCost().toFixed(2) + '\n');
        }
    };
    /**
     * Render Recipe Method
     *
     * This method is used to add (or "render") a
     * single recipe model instance on screen.
     *
     * @param index - integer row index and tag uniqueness qualifier
     * @param model - recipe model instance
     */
    me.renderRecipe = function (index, model) {
        var recipeList = document.getElementById("recipeList"),
            row = document.createElement("tr"),
            col1 = document.createElement("td"),
            col2 = document.createElement("td"),
            buttonInputElement = document.createElement("input"),
            numberInputElement = document.createElement("input"),
            storeId = model.getId() - 1;

        // Tag Row element Id
        row.id = model.getElementId();

        // Configure the columns
        col1.className = "border-0-px padding-2-px width-60-percent pull-down-left";
        col2.className = "border-0-px padding-2-px width-40-percent pull-down-left";

        // Configure the number input controls
        buttonInputElement.className = "btn btn-info btn-lg ctrl-stretch";
        buttonInputElement.type = "button";
        buttonInputElement.id = "recipe-btn-" + model.getId();
        buttonInputElement.value = "Recipe " + model.getId();
        buttonInputElement.title = "Click to see recipe details";
        buttonInputElement.addEventListener("click", function () {
            me.onRecipeButtonClick(model);
        }, false);

        // Configure the number input controls
        numberInputElement.className = "form-control";
        numberInputElement.type = "number";
        numberInputElement.id = "recipe-amt-" + model.getId();
        numberInputElement.value = 0;
        numberInputElement.title = "Enter the number of servings";
        numberInputElement.addEventListener("change", function () {
            me.onRecipeNumberFieldChange(numberInputElement, model, me.aggregateModel);
        }, false);

        // Add the number input control to col2
        col2.appendChild(numberInputElement);

        // Add the button input control to col1
        col1.appendChild(buttonInputElement);

        // Add col1 and col2 to the row
        row.appendChild(col1);
        row.appendChild(col2);

        // Add the row to the list
        recipeList.appendChild(row);
    };
    /**
     * Render Recipes Store
     *
     * This method is used to add (or "render") a
     * a given collection (or "store") of recipe
     * models.
     *
     * @param store
     */
    me.renderRecipes = function (store) {
        for (var i = 0; i < store.length; i++) {
            me.renderRecipe(i, store[i]);
        }
    };
    /**
     *  On Retrieved Data Callback
     *
     *  This method is invoked after the
     *  initial Ajax call completes.
     *
     * @param response
     */
    me.onRetrievedData = function (response) {
        var tree = JSON.parse(response),
            root = tree.pop(),
            productStore = null,
            recipeStore = null;

        // Create product store
        productStore = me.buildProductStore(root.products, me.productModel);

        // Create recipe store
        recipeStore = me.buildRecipeStore(root.recipes, me.recipeModel, productStore);

        // Render recipe list
        me.renderRecipes(recipeStore);

        // Bind Calculate Listener
        me.bindCalculateButtonListener(me.aggregateModel);

        // Bind Total Listener
        me.bindTotalButtonListener(me.aggregateModel);

    };
    /**
     * Recipe List button click
     * event handler.
     *
     * @param model
     */
    me.onRecipeButtonClick = function (model) {
        alert(model.toString());
    };
    /**
     * Recipe Number field change event listener.
     *
     * @param field - number input field
     * @param recipe - recipe model instance
     * @param aggregate - aggregate model instance
     */
    me.onRecipeNumberFieldChange = function (field, recipe, aggregate) {
        var count = field.value;
        aggregate.clearRecipeCache(recipe);
        if (count > 0) {
            for (var i = 0; i < count; i++) {
                aggregate.addRecipe(recipe);
            }
        } else {
            field.value = 0;
            field.focus();
        }
    };
    /**
     * Calculate button click event
     * handler.
     *
     * @param button - calculate input button
     * @param model - aggregate model instance
     */
    me.onCalculateButtonClick = function (button, model) {
        var aggregateCost = 0;
        if (model) {
            aggregateCost = model.getCacheAggregateCost() || 0;
            button.value = '$' + aggregateCost.toFixed(2);
        }
    };
    /**
     * Total button click event handler
     *
     * @param model - aggregate model instance
     */
    me.onTotalButtonClick = function (model) {
        if (model.getCacheAggregateCost() > 0) {
            alert(model.toString());
        }
    };
    /**
     * Build product store function
     *
     * Given some raw json, and a product model template
     * create an array (or "store") of product model
     * instances.
     *
     * @param nodes - json product data
     * @param model - product model
     * @returns {Array} - product models
     */
    me.buildProductStore = function (nodes, model) {
        var newInstance = null,
            store = [];
        for (var i = 0; i < nodes.length; i++) {
            newInstance = Object.create(model);
            newInstance.name = nodes[i].name;
            newInstance.cost = nodes[i].cost;
            newInstance.per = nodes[i].per;
            newInstance.supplier = nodes[i].supplier;
            store.push(newInstance);
        }
        return store;
    };
    /**
     * Build recipe store function
     *
     * Given some raw json, and a recipe model template
     * create an array (or "store") of recipe model
     * instances.
     *
     * @param nodes - json data array
     * @param recipeModel - recipe model template
     * @param productStore - array of product models
     * @returns {Array} - array of recipe models
     */
    me.buildRecipeStore = function (nodes, recipeModel, productStore) {
        var newInstance = null,
            ingredientsMap = null,
            store = [];
        for (var i = 0; i < nodes.length; i++) {
            ingredientsMap = nodes[i].ingredients.pop();
            newInstance = Object.create(recipeModel);
            newInstance.id = i + 1;
            newInstance.name = nodes[i].name;
            newInstance.inflateIngredients(ingredientsMap, me.ingredientModel, productStore);
            newInstance.calculateCost(store);
            store.push(newInstance);
        }
        return store;
    };
    /**
     * Bind Calculate button event listener
     *
     * Add (or "bind") an event listener to the
     * calculate button click event. NOTE - If the model
     * parameter is null, then this method will
     * remove the existing click event listener.
     *
     * @param model - aggregate model
     */
    me.bindCalculateButtonListener = function (model) {
        var calcButton = document.getElementById('calculateButton');
        if (model) {
            calcButton.addEventListener("click", function () {
                me.onCalculateButtonClick(totalButton, model);
            }, false);
        } else {
            calcButton.removeEventListener("click");
        }
    };
    /**
     * Bind Total button Event listener
     *
     * Add (or "bind") an event listener to the
     * total button click event. NOTE - If the model
     * parameter is null, then this method will
     * remove the existing click event listener.
     *
     * @param model - aggregate model
     */
    me.bindTotalButtonListener = function (model) {
        var totalButton = document.getElementById('totalButton');
        if (model) {
            totalButton.addEventListener("click", function () {
                me.onTotalButtonClick(model);
            }, false);
        } else {
            totalButton.removeEventListener("click");
        }
    };
    /**
     * Application entry point (aka "main"), this call
     * startup the application during page load.
     */
    ajax.get(dataFile, me.onRetrievedData);

})(window.cakeBaby = window.cakeBaby || {});