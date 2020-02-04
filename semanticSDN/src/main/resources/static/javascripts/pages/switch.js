layui.use('table', function() {
   
    // {
    //   "switchId": "00:00:00:00:00:00:00:04",
    //   "ip": "/192.168.50.246:47214",
    //   "openFlowVersion": "OF_13",
    //   "connectedSince": 1545038042595
    // }
    var result = null

    var table = layui.table

    // 第一个实例    url: 'semSDN/switch',
    table.render({
        elem: '#custom-switch-table',
        height: 'full-50',
        loading: true,
        even: true,
        url: 'switcheInfo',
        method: 'GET',
        parseData: function(result) {
            return {
                code: result.errorCode,
                msg: result.message,
                count: result.data.total,
                data: result.data.lists
            }
        },
        request: {
            pageName: 'page',
            limitName: 'limit'
        },
        page: true, // 开启分页
        limit: 20,
        cols: [
            [ // 表头
                {
                    title: 'Switch',
                    field: 'switchId',
                    minWidth: 120,
                    align: 'center'
                }, {
                    title: 'IP Address',
                    field: 'ip',
                    minWidth: 120,
                    align: 'center'
                }, {
                    title: 'OpenFlow Version',
                    field: 'openFlowVersion',
                    minWidth: 120,
                    align: 'center'
                }, {
                    title: 'Connect Time',
                    field: 'connectedSince',
                    templet: function(d) {
                        return moment(d.connectedSince).format('YYYY-MM-DD HH:mm:ss')
                    },
                    minWidth: 240,
                    align: 'center'
                }
            ]
        ]
    })
})