运行入口
`rechard.learn.springboot.Bootstrap` 启动一个springboot程序

打开postman,输入
http://127.0.0.1:9898/process

这个例子使用SimpleController来post模拟
 @JmsListener 接收到消息后给MQListenerJMSHandlerDispatcher处理，主要是annotation的使用来替代工厂模式。


主要逻辑在`MQListenerJMSHandlerDispatcher`,根据消息类型来选择对应的- @JMSHandler的类来处理。



加入swagger2支持
1. pom.xml
2. @EnableSwagger2
3. swagger api的使用
- @Api：修饰整个类，描述Controller的作用
- @ApiOperation：描述一个类的一个方法，或者说一个接口
- @ApiParam：单个参数描述
- @ApiModel：用对象来接收参数
- @ApiProperty：用对象接收参数时，描述对象的一个字段
- @ApiResponse：HTTP响应其中1个描述
- @ApiResponses：HTTP响应整体描述
- @ApiIgnore：使用该注解忽略这个API
- @ApiError ：发生错误返回的信息
- @ApiImplicitParam：一个请求参数
- @ApiImplicitParams：多个请求参数
4. 如何在生产中关闭
- https://www.jianshu.com/p/fa3230ffb27c
- 直接去掉@EnableSwagger2

