/**
 * Created with IntelliJ IDEA.
 * User: steve
 * Date: 9/10/13
 * Time: 3:57 PM
 * To change this template use File | Settings | File Templates.
 */

function getCategoryOverview($) {
    var ctx = $('#categoriesChart').get(0).getContext("2d"),
        categoriesChart,
        $categoriesLegend = $('#categoriesLegend');

    $.getJSON('/dashboard/categoryOverview', {}, function(result){
        categoriesChart = new Chart(ctx).Pie(result.categories);
        $categoriesLegend.html('');
        $.each(result.categories, function(){
            $categoriesLegend.append(
                $('<span class="item">').css('borderColor', this.color, 'borderStyle', 'solid').append(this.title)
            );
        });
    });
}

jQuery(function($){
    getCategoryOverview($);
});
