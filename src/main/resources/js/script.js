  function search(grape) {
    console.log("searching: " + grape);
    $.ajax({
      url: "/search/" + grape,
      dataType: 'json'
    }).done(function(json) {
      $("#root").hide();
      $("#results_wrapper").show();
      var output = "";
      for (var i=0; i<json.length; i++) {
        var recept = json[i];
        var url = recept.details;
        var grape = grape;
        output += '<div class="result-item" onclick="load_recept(url, grape)">';
        output += '<div class="polaroid">';
        output += '<div class="result-image"><img src="' + recept.imageURL + '"></div>';
        output += '<div class="result-title"><h3>' + recept.title + '</h3></div>';
        output += '</div>';
        output += '</div>';
      }
      $('#results').html(output);
    });
  }

  function load_recept(url, grape) {
    console.log("searching url " + url + "with grape: " + grape);

    $.ajax({
        url: url,
        dataType: 'json'
    }).done(function(json) {
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


    });
  }



  