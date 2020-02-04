layui.use('table', function() {
    // 包装JSON字符串的格式
    // {
    //   "src_sid": "00:00:00:00:00:00:00:03",
    //   "src_pid": 3,
    //   "dst_sid": "00:00:00:00:00:00:00:04",
    //   "dst_pid": 2,
    //   "type": "internal",
    //   "direction": "bidirectional",
    //   "latency": 324
    // }
    var result = null
    var table = layui.table

    table.render({
        elem: '#custom-link-table',
        height: 'full-50',
        loading: true,
        even: true,
        url: 'linkInfo', // 获取链路信息的url，可修改
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
                    title: 'Source Switch',
                    field: 'src_sid',
                    minWidth: 120,
                    align: 'center'
                }, {
                    title: 'Source Port',
                    field: 'src_pid',
                    minWidth: 120,
                    align: 'center'
                }, {
                    title: 'Destination Dwitch',
                    field: 'dst_sid',
                    minWidth: 120,
                    align: 'center'
                }, {
                    title: 'Destination Port',
                    field: 'dst_pid',
                    minWidth: 120,
                    align: 'center'
                }, {
                    title: 'Type',
                    field: 'type',
                    minWidth: 120,
                    align: 'center'
                }, {
                    title: 'Direction',
                    field: 'direction',
                    minWidth: 120,
                    align: 'center'
                }, {
                    title: 'Latency',
                    field: 'latency',
                    minWidth: 120,
                    align: 'center'
                }
            ]
        ]
    })
})