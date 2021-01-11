<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description"
        content="Project work API Mashup in the course Web services DA159A/DA358A at Malmö University 2020">
    <meta name="author" content="Student Group 10">

    <title>{% block title %}WineOcular - Match your favorite wine{% endblock %}</title>

    <link rel="shortcut icon" href="favicon.ico">
    <link href="/css/normalize.css" rel="stylesheet">
    <link href="/css/style.css" rel="stylesheet">
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Pacifico&display=swap" rel="stylesheet">
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@300&display=swap" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>

</head>

<body>

    <div class="top"></div>
    
    {% include "templates/partials/nav.tpl" %}
   
    {% block content %}{% endblock %} 

    {% include "templates/partials/footer.tpl" %}
</body>
<script src="/js/script.js"></script>
</html>