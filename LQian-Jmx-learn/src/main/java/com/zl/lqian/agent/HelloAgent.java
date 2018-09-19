package com.zl.lqian.agent;

import javax.management.*;
import java.io.IOException;
import java.lang.management.ManagementFactory;

public class HelloAgent  {

    public static void main(String[] args) throws MalformedObjectNameException,
            NotCompliantMBeanException, InstanceAlreadyExistsException,
            MBeanRegistrationException, IOException {

        //下面这种方式不能再JConsole中使用
        //MBeanServer server = MBeanServerFactory.createMBeanServer();
        //首先创建一个mbeanServer 用来管理我们的bean通常是通过MBeanServer来获取我们MBean的信息，间接
        // 调用MBean的方法，然后生产我们的资源的一个对象。
        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
        String domainName = "MyMBean";
        ObjectName helloName = new ObjectName(domainName+":name=HelloWorld");
        //将这个name 注册到Mbean上
        mbs.registerMBean(new HelloAgent(),helloName);
        // Distributed Layer, 提供了一个HtmlAdaptor。支持Http访问协议，并且有一个不错的HTML界面，这里的Hello就是用这个作为远端管理的界面
        // 事实上HtmlAdaptor是一个简单的HttpServer，它将Http请求转换为JMX Agent的请求
        ObjectName adapterName = new ObjectName(domainName+":name=htmladapter,port=8082");
       /* HtmlAdaptorServer adapter = new HtmlAdaptorServer();
        adapter.start();
        mbs.registerMBean(adapter,adapterName);*/



    }

}


