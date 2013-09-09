<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="bootstrap"/>
		<title>Welcome</title>
        <r:require modules="jquery,chartjs" />
        <style>
            #categoriesLegend .item {
                display: block;
                margin: 0.5em;
                border-style: solid;
                border-width: 0 0 0 1em;
                padding: 0 0.3em;
            }

            #sidebar {
                margin-top: 30px;
            }
        </style>
	</head>
	<body>

    <div class="container">

        <div class="row">
            <div class="col-md-9">
                <div class="page-header">
                    <h2><g:message code="category.overview" default="Category Overview" /></h2>
                </div>
                %{--Main content--}%
                <div class="row">
                    <div class="col-md-5">
                        <canvas id="categoriesChart" height="300" width="300"></canvas>
                    </div>

                    <div id="categoriesLegend" class="col-md-3"></div>
                </div>
            </div>

            <div id="sidebar" class="col-md-3 well">
                %{--Sidebar--}%
                <ul>
                <li>Test</li>
                <li>Test</li>
                <li>Test</li>
                <li>Test</li>
                <li>Test</li>
                <li>Test</li>
                </ul>
            </div>
        </div>

    </div>

    <r:script>
    jQuery(function($){
        var ctx = $('#categoriesChart').get(0).getContext("2d"),
            pieData = [
            {
                value: 30,
                title: "Fast Food",
                color: "${org.stevegood.util.color.ColorUtils.randomHexColor()}"
            },
            {
                value: 50,
                title: "Personal",
                color: "${org.stevegood.util.color.ColorUtils.randomHexColor()}"
            },
            {
                value: 100,
                title: "Groceries",
                color: "${org.stevegood.util.color.ColorUtils.randomHexColor()}"
            }],
            categoriesChart = new Chart(ctx).Pie(pieData),
            $categoriesLegend = $('#categoriesLegend');

            $.each(pieData, function(){
                $categoriesLegend.append(
                    $('<span class="item">').css('borderColor', this.color, 'borderStyle', 'solid').append(this.title)
                );
            });
    });
    </r:script>

	</body>
</html>
