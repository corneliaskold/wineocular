  function search(id) {
    console.log("searching: " + id);
    $.ajax({
      url: "/search/" + id,
      dataType: 'json'
    }).done(function(json) {
      $("#root").hide();
      $("#results_wrapper").show();
      var output = "";
      for (var i=0; i<json.length; i++) {
        var recept = json[i];
        output += '<div class="result-item" onclick="load_recept(\'' + recept.details + '\')">';
        output += '<div class="polaroid">';
        output += '<div class="result-image"><img src="' + recept.imageURL + '"></div>';
        output += '<div class="result-title"><h3>' + recept.title + '</h3></div>';
        output += '</div>';
        output += '</div>';
      }
      $('#results').html(output);
    });
  }

  function load_recept(url) {
    console.log("searching url " + url);
    
  }


  