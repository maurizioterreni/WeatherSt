export function drawGauge() {
		var gauge = new RGraph.Gauge({
        id: 'cvs',
        min: -50,
        max: 50,
        value: 26,
        options: {
            valueTextUnitsPost: 'mm',
            tickmarksSmal: 50,
            tickmarksMedium: 5,
            tickmarksBig: 5,
            labelsCount: 10,
            colorsRanges: [
                [-50,-45,'red'],
                [-45,40,'yellow'],
                [-40,40, '#cfc'],
                [40,45, 'yellow'],
                [45,50,'red']
            ]
        }
    }).draw();

};
