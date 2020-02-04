layui.use('table', function() {
    // JSON字符串封装格式
    // {
    //   "ip": "10.0.0.6",
    //   "mac": "e2:eb:15:1a:f3:71",
    //   "sId": "00:00:00:00:00:00:00:03",
    //   "spid": 6,
    //   "lastSeen": 1545038436002
    // }
    var result = null

    var table = layui.table

    // 第一个实例  url: '/hostInfo',
    table.render({
        elem: '#custom-host-table',
        height: 'full-50',
        loading: true,
        even: true,
        url: 'hostInfo',   // 获取主机信息的URL， 可修改
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
                    title: 'IP Address',  // 表的列名，可修改
                    field: 'ip',  // 从JSON中取值的字段，可修改
                    minWidth: 120,
                    align: 'center'
                }, {
                    title: 'MAC Address',
                    field: 'mac',
                    minWidth: 120,
                    align: 'center'
                }, {
                    title: 'Switch ID',
                    field: 'sId',
                    minWidth: 120,
                    align: 'center'
                }, {
                    title: 'Port ID',
                    field: 'spid',
                    minWidth: 120,
                    align: 'center'
                }, {
                    title: 'Connect Time',
                    field: 'lastSeen',
                    templet: function(d) {
                        return moment(d.lastSeen).format('YYYY-MM-DD HH:mm:ss')
                    },
                    minWidth: 240,
                    align: 'center'
                }
            ]
        ]
    })
})