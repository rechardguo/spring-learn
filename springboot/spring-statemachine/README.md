#### 底层使用 spring-statemachine来处理

#### 怎么运行这个demo

`rechard.learn.springboot.springstatemachine.demo`里分了2个不同的demo

- simple 下 Bootstrap是简单的statemachine

- 运行 groupadd 下 Bootstrap main()启动程序，  使用statemachine来处理状态的变化,启动后使用postman
- - http://127.0.0.1:9898/group/create 创建一个组
- - http://127.0.0.1:9898/group/list
  列出组
- - http://127.0.0.1:9898/group/{id}/{event}
  处理组  

