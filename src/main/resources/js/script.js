  function search(id) {
    var fullUrl = location.protocol + '//' + location.hostname + ':' + location.port;
    location.href = fullUrl + "/search/" + id;
  }

  $(document).ready(function () {
      $.ajax({
        url: 'http://localhost:2020/',
        headers: {"Accept": "application/json"}
      })
      .done();

//      $(".result-item").click(getRecipe());
    $('#rioja').click(getGrapeResult());
    });

  $(document).ready(function () {});






  