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
            pointFormat: '{series.name}<br></br> <b>{point.percentage:.1f}%</b>'
        },
        plotOptions: {
            pie: {
                allowPointSelect: true,
                cursor: 'pointer',
                dataLabels: { enabled: false }
            }
        },
        series: [{
            name: ' ',
            data: [
                { name: 'Bonnes Reponses', y: 80 },
                { name: 'Mauvaises Reponses', y: 20 }
            ]
        }]
    });
});