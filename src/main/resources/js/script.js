  function search(id) {
    console.log(id);
    var url = "http://localhost:2020/search/" + id;
    location.href=url;
  }