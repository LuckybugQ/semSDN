<p align="center">
    <img src="img/logo.jpg" width="100px">
</p>

<p align="center"><span style="font-size:50px">semSDN - 基于本体的SDN网络感知与描述系统</span>
</p>

- 基于 `Ontology` 的一款 `SDN` 网管系统
- 如果觉得不错 给个 `Star` 支持一下 🤓
## 技术栈
- SpringBoot 核心框架  
- MyBatis 数据访问层  
- ThymeLeaf 模板引擎
- LayUI 前端UI框架
- MySQL 关系型数据库
- Ontology 本体理论
- Diameter AAA协议
## 功能介绍
- SDN网络数据捕获：服务端基于SDN控制器的RESTful API获取SDN网络数据
- SDN网络拓扑构建：客户端基于Echarts构建SDN网络拓扑图
- SDN网络本体构建：把SDN网络数据注入本体owl文件并应用预先设定的本体规则
- SDN网络数据显示：表格&折线图形式实时显示SDN网络中的交换机、主机、链路等数据
- SDN网络资源查询：可以通过SPARQL语句通过本体文件对SDN网络资源进行查询。
## 本地部署
- 客户端配置Constant类中的客户端和服务端地址
- 服务端配置URLConstant类中的SDN控制器Rest API地址
- 服务端启动SDN控制器（ONOS、Floodlight等）
- 服务端启动Mininet网络仿真
- 服务端运行agent项目ONMPServer类
- 客户端运行semanticSDN项目NMApplication类
- 客户端浏览器访问：http://localhost:8080
## 图片演示
![](./img/1.png)
![](./img/2.png)
![](./img/3.png)
