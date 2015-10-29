$(function() 
{
	var seuilMin = parseInt(document.getElementById('seuilMin').value);
	var seuilMax = parseInt(document.getElementById('seuilMax').value);
	var seuilAtteint = parseInt(document.getElementById('seuilAtteint').value);
	
    $('#seuil').highcharts({
        chart: {
            type: 'gauge',
            plotBackgroundColor: null,
            plotBackgroundImage: null,
            plotBorderWidth: 0,
            plotShadow: false
        },
        title: { text: '' },
        exporting: { enabled: false },
        credits: { enabled: false },
        pane: {
            startAngle: -150,
            endAngle: 150,
            background: [{
                backgroundColor: {
                    linearGradient: { x1: 0, y1: 0, x2: 0, y2: 1 },
                    stops: [
                        [0, '#FFF'],
                        [1, '#333']
                    ]
                },
                borderWidth: 0,
                outerRadius: '109%'
            }, {
                backgroundColor: {
                    linearGradient: { x1: 0, y1: 0, x2: 0, y2: 1 },
                    stops: [
                        [0, '#333'],
                        [1, '#FFF']
                    ]
                },
                borderWidth: 1,
                outerRadius: '107%'
            }, {
                // default background
            }, {
                backgroundColor: '#DDD',
                borderWidth: 0,
                outerRadius: '105%',
                innerRadius: '103%'
            }]
        },

        // the value axis
        yAxis: {
            min: 0,
            max: 20,

            minorTickInterval: 'auto',
            minorTickWidth: 1,
            minorTickLength: 10,
            minorTickPosition: 'inside',
            minorTickColor: '#666',

            tickPixelInterval: 30,
            tickWidth: 2,
            tickPosition: 'inside',
            tickLength: 10,
            tickColor: '#666',
            labels: {
                step: 2,
                rotation: 'auto'
            },
            title: { text: 'Seuil atteint' },
            plotBands: [{
                from: 0,
                to: seuilMin,
                color: '#DF5353' // red
            }, {
                from: seuilMin,
                to: seuilMax,
                color: '#DDDF0D' // yellow
            }, {
                from: seuilMax,
                to: 20,
                label: "Acquis",
                color: '#55BF3B' // green
            }]
        },

        series: [{
            name: 'Seuil',
            data: [seuilAtteint],
            tooltip: {
                valueSuffix: '/20'
            }
        }]
    });
});