package Spring4_IOC;

import Spring4_IOC.annotation.controler.UserController;
import Spring4_IOC.bean.*;
import Spring4_IOC.bean.CarCycle;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;


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

    /**
     * �ض��������:
     * Component: ����ע��, ��ʶ��һ���� Spring ��������
     * Respository: ��ʶ�־ò����
     * Service: ��ʶ�����(ҵ���)���: UserServiceImpl userService
     * Controller: ��ʶ���ֲ����
     *
     * ����ɨ�赽�����, Spring ��Ĭ�ϵ���������:
     * ʹ�÷��޶�����, ��һ����ĸСд!!!. Ҳ������ע����ͨ�� value ����ֵ��ʶ���������
     *
     * ����Ҫ�� Spring �������ļ������� <context:component-scan>
     */
    @Test
    public void testAnnotation() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("Spring4_IOC/beans-annotation.xml");

//        TestObject to = (TestObject) ctx.getBean("testObject");
//        System.out.println(to);

        //��UserController��һ����ĸСд userController
        UserController userController = (UserController) ctx.getBean("userController");
        System.out.println(userController);
        userController.execute();
//
//        UserService userService = (UserService) ctx.getBean("userService");
//        System.out.println(userService);
//
//        UserRepository userRepository = (UserRepository) ctx.getBean("userRepository");
//        System.out.println(userRepository);
    }


    /**********Bean������ʽ��
     *         һ������ XML �ļ��ķ�ʽ��
     *         ��������ע��ķ�ʽ������ע������ Bean������ע����װ�� Bean �����ԣ�
     * ����ķ�������ʹ�õĻ��� XML �ļ��ķ�ʽ*************/


    /**
     * Bean �����÷�ʽ��-��ͨ��ȫ���������䣩������ͨ��������������̬�������� & ʵ������������������FactoryBean
     *
     * ����FactoryBean
     * Spring �����������͵� Bean, һ������ͨBean, ��һ���ǹ���Bean, ��FactoryBean
     */
    @Test
    public void testFactoryBean() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("Spring4_IOC/beans-beanFactory.xml");
        CarFactoryBean car = (CarFactoryBean) ctx.getBean("car");
        System.out.println(car);

    }

    /**
     * Bean �����÷�ʽ��-��ͨ��ȫ���������䣩������ͨ��������������̬�������� & ʵ������������������FactoryBean
     *
     * ��̬����������ֱ�ӵ���ĳһ����ľ�̬�����Ϳ��Է���Beanʵ��
     * ʵ������������ʵ�������ķ���������Ҫ�ȴ������������ٵ��ù�����ʵ������������bean��ʵ��
     */
    @Test
    public void testStaticFactory() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("Spring4_IOC/beans-factory.xml");
        //���Ծ�̬��������
        Car car1 = (Car) ctx.getBean("car1");
        System.out.println(car1);

        //ʵ����������
        Car car2 = (Car) ctx.getBean("car2");
        System.out.println(car2);
    }

    /**
     * IOC ������ Bean ����������
     * Spring IOC ������ Bean ���������ڽ��й���Ĺ���:
     * -ͨ���������򹤳��������� Bean ʵ��
     * -Ϊ Bean ����������ֵ�Ͷ����� Bean ������
     * -���� Bean �ĳ�ʼ������ !!
     * -Bean ����ʹ����
     * -�������ر�ʱ, ���� Bean �����ٷ��� !!
     *
     * �� Bean ������������ init-method �� destroy-method ����, Ϊ Bean ָ����ʼ�������ٷ���.
     */
    @Test
    public void testCycle() {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("Spring4_IOC/beans-cycle.xml");
        CarCycle car = (CarCycle) ctx.getBean("car");
        System.out.println(car);

        //�ر�IOC����
        ctx.close();
    }
    /**
     * ����SpEL
     */
    @Test
    public void testSpEL() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("Spring4_IOC/beans-spel.xml");
        Address address = (Address) ctx.getBean("address");
        System.out.println(address);

        Car car1 = (Car) ctx.getBean("car");
        System.out.println(car1);

        PersonSpEL person = (PersonSpEL) ctx.getBean("person");
        System.out.println(person);
    }
    /**
     * ʹ���ⲿ�����ļ�
     */
    @Test
    public void testProperties() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("Spring4_IOC/beans-properties.xml");
        DataSource dataSource = (DataSource) ctx.getBean("dataSource");
        System.out.println(dataSource);
    }
    /**
     * bean ��������
     * singleton��Ĭ��ֵ��������ʼʱ����beanʵ��������������������������ֻ������һ��bean.������
     * prototype��ԭ�͵ģ�������ʼ��ʱ������bean��ʵ��������ÿ������ʱ������һ���µ�beanʵ��!!��������
     * WEB ����������(request,session)
     */
    @Test
    public void testScope() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("Spring4_IOC/beans-scope.xml");

        Car2 car1 = (Car2) ctx.getBean("car");
        Car2 car2 = (Car2) ctx.getBean("car");
        System.out.println(car1 == car2); //Ĭ����singleton�����Ϊtrue
    }

    /**
     * bean ֮��Ĺ�ϵ���̳У�����
     */
    @Test
    public void testRelation() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("Spring4_IOC/beans-relation.xml");
        //���Լ̳�
        Address address = (Address) ctx.getBean("address");
        System.out.println(address);

        Address address2 = (Address) ctx.getBean("address2");
        System.out.println(address2);

        //��������
        PersonAutowire person = (PersonAutowire) ctx.getBean("person");
        System.out.println("��������: " + person);
    }

    /**
     * �Զ�װ�� Bean
     * byType(���������Զ�װ��)
     * byName(���������Զ�װ��)
     * constructor(ͨ���������Զ�װ��):���Ƽ�ʹ��
     */
    @Test
    public void testAutowire() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("Spring4_IOC/beans-autowire.xml");
        //�����Զ�װ��byName
        PersonAutowire person = (PersonAutowire) ctx.getBean("person");
        System.out.println("�����Զ�װ��byName: " + person);

        //�����Զ�װ��byType
        PersonAutowire person2 = (PersonAutowire) ctx.getBean("person2");
        System.out.println("�����Զ�װ��byType: " + person2);
    }

    /***********
     * Bean �����÷�ʽ��-��ͨ��ȫ���������䣩������ͨ��������������̬�������� & ʵ������������������FactoryBean
     *
     * ����Bean�����÷�ʽ��ͨ��ȫ���������䣩***************/
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

        //����ʹ�õ���List��������
        PersonCarList person3_2 = (PersonCarList) ctx.getBean("personCarList");
        System.out.println("���Ե�����List����: " + person3_2);

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
