  function search(id) {
    var fullUrl = location.protocol + '//' + location.hostname + ':' + location.port;
    location.href = fullUrl + "/search/" + id;
  }
  