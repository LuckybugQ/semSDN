layui.use(['form', 'laytpl'], function(form, laytpl) {

    var macOptions = []
    var macValue = ''
    var timerId = 0

    var topLeftEchartsInstance = echarts.init(customEchartsTopLeft)
    var topRightEchartsInstance = echarts.init(customEchartsTopRight)
    var bottomLeftEchartsInstance = echarts.init(customEchartsBottomLeft)
    var bottomRightEchartsInstance = echarts.init(customEchartsBottomRight)

    var echartsInstance = [topLeftEchartsInstance, topRightEchartsInstance, bottomLeftEchartsInstance, bottomRightEchartsInstance]
    var title = ['端口发送带宽（Mbps）', '端口接收带宽（Mbps）', '端口发送比特量（MBits）', '端口接收比特量（MBits）']
    var xAxisName = ['单位：t/s', '单位：t/s', '单位：t/s', '单位：t/s']
    var yAxisName = ['单位：mbps', '单位：mbps', '单位：mbits', '单位：mbits']
    var key = ['bitsPerSecondTx', 'bitsPerSecondRx', 'txBytes', 'rxBytes']
    var data = [
        [],
        [],
        [],
        []
    ]

    var options = {
        title: {
            text: '',
            textStyle: {},
            left: 'center'
        },
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: 'cross'
            },
            enterable: true
        },
        xAxis: {
            type: 'time',
            name: '',
            splitNumber: 8,
            // maxInterval: 50000,
            axisLabel: {
                formatter: function(value, index) {
                    return moment(value).format('YYYY-MM-DD HH:mm:ss').replace(/\s/, '\n')
                }
            },
            splitLine: {
                show: true,
                lineStyle: {
                    type: 'dashed',
                    color: '#f3f3f3'
                }
            }
        },
        yAxis: {
            type: 'value',
            name: '',
            boundaryGap: [0, '100%'],
            splitLine: {
                show: true,
                lineStyle: {
                    type: 'dashed',
                    color: '#f3f3f3'
                }
            }
        },
        series: [{
            type: 'line',
            showSymbol: false,
            hoverAnimation: false,
            data: []
        }]
    }

    function renderPortSelect(portOptions) {
        laytpl(customPortPerformanceSelectPortTemplate.innerHTML).render({
            options: portOptions
        }, function(html) {
            customPortPerformanceSelectPort.innerHTML = html
            form.render('select', 'custom-port-performance-form')
        })

        startCron(macValue, portOptions[0])
    }

    function startRequest(mac, port) {
        $.ajax({
            url: 'semSDN/port-performance',
            type: 'GET',
            data: { mac: mac, port: port },
            dataType: 'json'
        }).done(function(result) {
            renderEcharts(result.data || {})
        }).always(function() {
            timerId = setTimeout(function() {
                startRequest(mac, port)
            }, 3000)
        })
    }

    function startCron(mac, port) {
        if (timerId) timerId = clearTimeout(timerId)

        data = _.map(data, function(item) {
            var momentInstance = moment()
            return _.map(new Array(24), function(item, index) {
                var datetime = momentInstance.subtract(index, 'seconds').format('YYYY-MM-DD HH:mm:ss')

                return {
                    name: datetime,
                    value: [datetime, 0]
                }
            }).reverse()
        })

        if (mac && port) startRequest(mac, port)
    }

    function renderEcharts(seriesData) {
        _.forEach(echartsInstance, function(instance, index) {

            data[index].shift()
            data[index].push({
                name: seriesData.time,
                value: [seriesData.time, seriesData[key[index]]]
            })

            options.title.text = title[index]
            options.xAxis.name = xAxisName[index]
            options.yAxis.name = yAxisName[index]
            options.series[0].data = data[index]

            instance.setOption(options)
        })
    }

    form.render('select', 'custom-port-performance-form')
    form.on('select(custom-port-performance-select-mac)', function(data) {
        var value = data.value

        macValue = macOptions[value].swid
        renderPortSelect(macOptions[value].spid)
    })
    form.on('select(custom-port-performance-select-port)', function(data) {
        startCron(macValue, data.value)
    })

    $.ajax({
        url: 'semSDN/switch/portlist',
        type: 'GET',
        dataType: 'json'
    }).done(function(result) {
        macOptions = result.data

        laytpl(customPortPerformanceSelectMACTemplate.innerHTML).render({
            options: macOptions
        }, function(html) {
            customPortPerformanceSelectMAC.innerHTML = html
                // form.render('select', 'custom-port-performance-form')

            macValue = macOptions[0].swid
            renderPortSelect(macOptions[0].spid || [])
        })
    }).always(function() {

    })
})