$(document).ready(function() 
{
	var pourcentageBonnes = parseInt(document.getElementById('pourcentage').value);
	var pourcentageFausses = 100 - pourcentageBonnes

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
            pointFormat: '{series.name}<br></br> <b>{point.percentage}%</b>'
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
                { name: 'Bonnes Reponses', y: pourcentageBonnes },
                { name: 'Mauvaises Reponses', y: pourcentageFausses }
            ]
        }]
    });
});