### 探究静态内部类初始化时机

静态内部类，在外部类被初始化的时候不会被一起初始化

只要外部类初始化是涉及到静态内部类内部静态属性被赋值、使用；静态方法被调用；静态内部类示例被创建的情况下才会去被初始化

### 利用静态内部类特性实现对象的延迟初始化
利用这种机制，我们可以通过这种机制来实现对象的延迟初始化方案