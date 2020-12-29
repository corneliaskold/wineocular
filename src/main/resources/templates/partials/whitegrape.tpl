<!DOCTYPE html>
<html>
  <head>
    <title>White Grapes</title>
    <link rel='stylesheet' href='/static/style.css' />
    <meta http-equiv="content-type" content="text/html;charset=utf-8" />
  </head>
  <body>
  {% for grape in WhiteGrapes %}

  <div class="grape-icon">
    <img src="../images/grape.png" width="40">
  </div>

  <div class="grape-info">
     <h2>{{ grape.title }}</h2>
     <p>{{ grape.description }}</p>
     <div class="button-nav">
        <a href="" class="button">Search</a>
     </div>
  </div>

  {% endfor %}

  </body>
</html>