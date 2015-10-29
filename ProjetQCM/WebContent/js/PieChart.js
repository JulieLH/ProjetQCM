$(document).ready(function() 
{
    // Build the chart
    $('#chart').highcharts(
    {
    	colors : ['#009933', '#CC0000'],
        chart: {
            plotBackgroundColor: null,
            plotBorderWidth: null,
            plotShadow: false,
            type: 'pie'
        },
        title: '',
        exporting: { enabled: false },
        credits: { enabled: false },
        tooltip: {
            pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
        },
        plotOptions: {
            pie: {
                allowPointSelect: true,
                cursor: 'pointer',
                dataLabels: {
                    enabled: false,
                    format: '<b>{point.name}</b>: {point.percentage:.1f} %',
                    style: {
                        color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
                    }
                }
            }
        },
        series: [{
            name: ' ',
            data: [
                { name: 'Bonnes Réponses', y: 80 },
                { name: 'Mauvaises Réponses', y: 20 }
            ]
        }]
    });
});