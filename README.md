# ss-deamon
Spring和qz定时调度任务的整合

1.dynamic包中主要实现了，通过拦截的方式，实现对动态数据源的切换功能

  DataSource注解为拦截标志，用于指定目标数据源－目前作用域为对象级别
  
  DynamicMethodInterceptor为拦截执行点，在这里，会根据反射的类，来切换数据源
  
  DynamicMongoFactory这里是为了扩展spring原有的mongofactory，使之能够动态切换不同的db
  
  ExtendMongoClientFactory，这里是为了扩展mongoClient。这种扩展和具体系统场景有关，不可直接复制
  
  
  
2.listener包中，主要是qz的监听接口，在系统中并没有使用



3.persist包为封装统一的数据访问层，避免service层和dao出现一一对应。



4.support包，实现功能为对于spring管理的bean，通过注解查找到对应的bean，并将其转化为qz的Job和trigger，加入调度队列 /r/n

  IOCJobFactory通过既有约定实现 获取JobDelegate对象时，动态解析出目标bean和方法，一半此方法在job开始执行的时候调用
  
  ScheduleAnnotationProcessor，解析注解，生成JobDetail和trriger，并加入到调度队列。JobDetail作为一个描述性对象，
  不会真的执行。JobDelegate才是定时任务的执行对象。
  
  
  
5.job包为需要执行的定时任务。@ScheduleAnnotation标示这个类会解析为普通的定时任务。service包为各个定时任务执行时需要依赖的底层服务

################################################
这里的动态数据源不支持跨数据库事务，若需要事务，可以将事务注解标注到service上面。数据源切换也是以service为最小单位。
  
  
