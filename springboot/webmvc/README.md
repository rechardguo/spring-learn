运行入口
`rechard.learn.springboot.Bootstrap` 启动一个springboot程序

打开postman,输入
http://127.0.0.1:9898/process

这个例子使用SimpleController来post模拟
@JmsListener 接收到消息后给MQListenerJMSHandlerDispatcher处理，主要是annotation的使用来替代工厂模式。


主要逻辑在`MQListenerJMSHandlerDispatcher`,根据消息类型来选择对应的@JMSHandler的类来处理。

