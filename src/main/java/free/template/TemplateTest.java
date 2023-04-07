package free.template;

//模板方法模式是一种行为设计模式， 它在超类中定义了一个算法的框架， 允许子类在不修改结构的情况下重写算法的特定步骤
//如本例子中不同平台的登录实现不一致但是发消息的内容是一致的子类自行实现登录内容即可
public class TemplateTest {
    public static void main(String[] args) {
        var network = new Wechat("test", "12345");
        network.post("Hello, world!");
    }
}
