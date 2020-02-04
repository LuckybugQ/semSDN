layui.use(['form', 'laytpl'], function(form, laytpl) {

    var timerId = 0

    var topLeftEchartsInstance = echarts.init(customEchartsTopLeft)
    var topRightEchartsInstance = echarts.init(customEchartsTopRight)
    var bottomLeftEchartsInstance = echarts.init(customEchartsBottomLeft)

    var echartsInstance = [topLeftEchartsInstance, topRightEchartsInstance, bottomLeftEchartsInstance]
    // 可以修改以下的，分别取曲线图的标题、横坐标单位、纵坐标单位、从JSON中取的字段
    var title = ['链路时延', '链路带宽', '链路丢包率','链路抖动']
    var xAxisName = ['单位：t/s', '单位：t/s', '单位：t/s']
    var yAxisName = ['单位：ms', '单位：Mbps', '单位：100%']
    var key = ['latency', 'bandwidthUtil', 'dropRate']
    // 取出的字段，返给data，此处是三个曲线，若扩展，可以再加
    var data = [
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

    function startRequest(link) {
        $.ajax({
            url: 'semSDN/link-performance',  // 获取性能信息的URL，可修改
            type: 'GET',
            data: { link: link },
            dataType: 'json'
        }).done(function(result) {
            renderEcharts(result.data || {})
        }).always(function() {
            timerId = setTimeout(function() {
                startRequest(link)
            }, 5500)  // 此处的5500是曲线获取数据的周期为5500ms,可修改
        })
    }

    function startCron(link) {
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

        if (link) startRequest(link)
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

    form.render('select', 'custom-link-performance-form')
    form.on('select(custom-link-performance-select-link)', function(data) {
        startCron(data.value)
    })

    $.ajax({
        url: 'semSDN/linkList',  // 获取链路列表的URL
        type: 'GET',
        dataType: 'json'
    }).done(function(result) {
        var data = result.data

        laytpl(customLinkPerformanceSelectLinkTemplate.innerHTML).render({
            options: data
        }, function(html) {
            customLinkPerformanceSelectLink.innerHTML = html
            form.render('select', 'custom-link-performance-form')

            startCron(data[0])
        })
    }).always(function() {

    })
})