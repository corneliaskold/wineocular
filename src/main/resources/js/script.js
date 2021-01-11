  function search(grape) {                    // Metod för att söka på druva och matcha mot recept och skriver ut det i ett sökresultat
    console.log("searching: " + grape);
    $.ajax({
      url: "/search/" + grape,
      dataType: 'json'
    }).done(function(json) {                  
      $("#root").hide();
      $("#results_wrapper").show();
      var output = "";                        // Container som presenterar recept
      for (var i=0; i<json.length; i++) {     
        var recipe = json[i];

        output += '<div class="result-item" onclick="load_recipe(\'' + recipe.details + '\')">';
        output += '<div class="polaroid">';
        output += '<div class="result-image"><img src="' + recipe.imageURL + '"></div>';
        output += '<div class="result-title"><h3>' + recipe.title + '</h3></div>';
        output += '</div>';
        output += '</div>';
      }
      $('#results').html(output);
    });
  }

  function load_recipe(url) {                 // Metod som kallas på när man klickar på ett recept i sökresultatet.
    console.log("searching url " + url);

    $.ajax({
        url: url,
        dataType: 'json'
    }).done(function(json) {
        $("#root").hide();
        $("#recipe-grid-container").show();
        $("#recipe_presenter").show();

        var recipe = json;
        var output = "";

        output += '<div class="recipe-grid-item1"><img src="' + recipe.imageURL + '"></div>';
        output += '<div class="recipe-grid-item2">';
        output += '<h2>Ingredients: </h2>';
        for(var i=0; i<recipe.ingredients.length; i++) {
            output += '<p>' + recipe.ingredients[i] + '</p>';
        }
        output += '</div>';
        output += '<div class="recipe-grid-item3">';
        output += '<h1>' + recipe.title + '</h1>';
        output += '<p>'+ recipe.description + '</p>';
        output += '<h2>Instructions:</h2>';

        for(var i = 0; i<recipe.instructions.length; i++) {
            output += '<p>' + recipe.instructions[i] + '</p>';
        }
        output += '</div>';

        $('#recipe-grid-container').html(output);

        $(window).scrollTop(0); // Scrollar upp sidan efter klick på valt recept i sökresultatet

    });
  }



  