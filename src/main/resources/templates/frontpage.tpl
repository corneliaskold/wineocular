{% extends "templates/layout/base.html" %}

{% block content %}

    <div id="root">
        <section class="wrapper hero">
            <article>
                <h1>Match your favorite wine
                with food in season</h1>
            </article>
        </section>
    

        {% include "templates/partials/redgrape.html" %}

        {% include "templates/partials/whitegrape.html" %}
    </div>


    {% include "templates/partials/recipepresenter.html" %}

    {% include "templates/partials/resultgrid.html" %}

    {% include "templates/partials/seasonalbanner.html" %}

{% endblock %}