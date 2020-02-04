$(function() {
    var echartsInstance = echarts.init(document.getElementById('customNetworkTopologyEcharts'))
    
    var data = []
    var links = []
    var href = location.href.replace(/[^\/]*\.html?$/, '')
    href = href.slice(-1) === '/' ? href : (href + '/')

    var hostSymbol = 'image://' + href + '/images/network-topology/host.png'
    var switchSymbol = 'image://' + href + '/images/network-topology/switch.png'

    var options = {
        title: {
            text: '网络拓扑图',
            textStyle: {},
            left: 'center'
        },
        tooltip: {
            backgroundColor: '#98eae9',
            textStyle: {
                color: '#333',
                fontSize: 16,
                lineHeight: 32
            },
            extraCssText: 'white-space: pre-line;'
        },
        grid: {},
        series: [{
            type: 'graph',
            hoverAnimation: true,
            layout: 'force',
            force: {
                initLayout: 'force',
                repulsion: 1500
            },
            roam: true,
            nodeScaleRatio: 0.8,
            draggable: true,
            focusNodeAdjacency: true,
            symbolSize: 48,
            //edgeSymbol: ['circle', 'arrow'],
            lineStyle: {
                width: 5,
                color: '#23e45c',
                opacity: 0.8,
                curveness: 0.0
            },
            label: {
                show: true,
                position: 'bottom'
            },
            edgeLabel: {
                show: false
            },
            categories: [{
                name: 'host',
                symbol: hostSymbol
            }, {
                name: 'switch',
                symbol: switchSymbol
            }],
            data: data,
            links: links
        }]
    }

    echartsInstance.setOption(options)

    $.ajax({
        url: 'semSDN/network-topology',
        type: 'GET',
        dataType: 'json'
    }).done(function(result) {
        var data = []
        var linksData = []

        var hosts = _.get(result, 'data.hosts', [])
        var switches = _.get(result, 'data.switches', [])
        var links = _.get(result, 'data.links', [])

        _.forEach(hosts, function(item, index) {
            data.push({
                name: item.ip,
                // value: 0,
                category: 0,
                symbol: hostSymbol,
                label: {
                    formatter: function(params) {
                        var data = params.data

                        return data.aliasName
                    }
                },
                tooltip: {
                    formatter: function(params, ticket, callback) {
                        var data = params.data

                        return data.source ? data.source + ' ==> ' + data.target : 'Type：host<br/>' + 'IP：' + data.name + '<br/>' + 'MAC：' + data.mac + '<br/>' + 'switchID：' + data.sId + '<br/>' + 'switchPort：' + data.spId + '<br/>' + 'connectDuration：' + data.datetime
                    }
                },
                type: 'host',
                aliasName: 'host_' + (index + 1),
                mac: item.mac,
                sId: item.sId,
                
                spId: item.spid, // 修改
                datetime: moment(item.lastSeen).format('YYYY-MM-DD HH:mm:ss')
            })

            linksData.push({
                source: item.sId,
                target: item.ip,
                // value: 0,
                label: {

                },
                lineStyle: {
                    color: '#23e45c',
                }
            })
        })

        _.forEach(switches, function(item, index) {
            data.push({
                name: item.switchId,
                // value: 0,
                category: 1,
                symbol: switchSymbol,
                label: {
                    formatter: function(params) {
                        var data = params.data

                        return data.aliasName
                    }
                },
                tooltip: {
                    formatter: function(params, ticket, callback) {
                        var data = params.data

                        return data.source ? data.source + ' ==> ' + data.target : 'Type：switch<br/>' + 'IP：' + data.ip + '<br/>' + 'switchID：' + data.name + '<br/>' + 'openFlowVersion：' + data.openFlowVersion + '<br/>' + 'connectDuration：' + data.datetime
                    }
                },
                type: 'switch',
                aliasName: 'switch_' + (index + 1),
                ip: item.ip,
                openFlowVersion: item.openFlowVersion,
                spId: item.spId,
                datetime: moment(item.connectedSince).format('YYYY-MM-DD HH:mm:ss')
            })
        })

        _.forEach(links, function(item, index) {
            linksData.push({
                source: item.src_sid,
                target: item.dst_sid,
                // value: 0,
                label: {

                },
                lineStyle: {
                    color: '#2b2bec',
                }
            })
        })

        options.series[0].data = data
        options.series[0].links = linksData

        echartsInstance.setOption(options)
    }).always(function() {

    })
})