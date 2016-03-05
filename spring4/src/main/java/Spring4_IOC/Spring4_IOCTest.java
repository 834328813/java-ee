package Spring4_IOC;

import Spring4_IOC.bean.*;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


/**
 * Spring �ṩ���������͵� IOC ����ʵ��.
 BeanFactory: IOC �����Ļ���ʵ��.
 ApplicationContext: �ṩ�˸���ĸ߼�����. �� BeanFactory ���ӽӿ�.

 BeanFactory �� Spring ��ܵĻ�����ʩ������ Spring ����
 ApplicationContext ����ʹ�� Spring ��ܵĿ����ߣ��������е�Ӧ�ó��϶�ֱ��ʹ�� ApplicationContext ���ǵײ�� BeanFactory
 ����ʹ�ú��ַ�ʽ, �����ļ�ʱ��ͬ��.
 * @author Administrator on 2016/3/5.
 */
public class Spring4_IOCTest {
    /***********����Bean��
     *          ������ʽ������ XML �ļ��ķ�ʽ
     *          ���÷�ʽ��ͨ��ȫ���������䣩***************/

    //��������ע��
    @Test
    public void testDependencyInjection() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("Spring4_IOC/applicationContext.xml");
        Car car = (Car) ctx.getBean("car");
        System.out.println(car);

        //����car���ж�����������������IOC
        Car car2 = (Car) ctx.getBean("car2");
        System.out.println(car2);

        //���� Person������һ��������Car������ã���ref����bean֮������ù�ϵ
        Person person = (Person) ctx.getBean("person");
        System.out.println("����ref: " + person);

        //���Լ�������
        Person person2 = (Person) ctx.getBean("person2");
        System.out.println("���Լ�������: " + person2);

        //����ʹ��List��������
        PersonCarList person3 = (PersonCarList) ctx.getBean("person3");
        System.out.println("����ʹ��List��������: " + person3);

        //����ʹ��List��������
        PersonCarList person3_2 = (PersonCarList) ctx.getBean("personCarList");
        System.out.println("���Ե����ļ���bea: " + person3_2);

        //����ʹ��Map��������
        PersonCarMap person4 = (PersonCarMap) ctx.getBean("person4");
        System.out.println("����ʹ��Map��������: " + person4);

        //����ʹ��Properties�������ԣ�����һ���������������ݿ��Ƿ����ӳɹ�������
        DataSource dataSource = (DataSource) ctx.getBean("dataSource");
        System.out.println("����ʹ��Properties��������: " + dataSource);

        //����ͨ��P�����ռ�Ϊbean�����Ը�ֵ
        PersonCarList person5 = (PersonCarList) ctx.getBean("person5");
        System.out.println("����ͨ��P�����ռ�Ϊbean�����Ը�ֵ: " + person5);
    }


    @Test
    public void testHelloWorld() {
        //        ǰ���п�����Spring��ʵ��
//        HelloWorldBean helloWorldBean = new HelloWorldBean();
//        helloWorldBean.setUser("Tom");
//        helloWorldBean.hello();

        //1. ���� Spring �� IOC ����(ApplicationContext����Spring �� IOC ������
        //������ʱ����ȵ����޲ι�������ͬʱ����� setter���������Ը�ֵ!!
        ApplicationContext ctx = new ClassPathXmlApplicationContext("Spring4_IOC/applicationContext.xml");

        //2. �� IOC �����е�id��ȡ bean ��ʵ��
        HelloWorldBean helloWorldBean = (HelloWorldBean) ctx.getBean("helloWorld");

        //������������ȡ bean ��ʵ��: Ҫ����  IOC ������ֻ��һ����֮����ƥ��� bean, ���ж������׳��쳣.
        //һ�������, �÷�������, ��Ϊһ�������, ��һ�� IOC ������һ�����Ͷ�Ӧ�� bean Ҳֻ��һ��.
//       HelloWorldBean helloWorld1 = ctx.getBean(HelloWorldBean.class);

        //3. ʹ�� bean
        helloWorldBean.hello();
    }
}
