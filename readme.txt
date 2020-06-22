需求：用户页面发起一个请求，springmvc来处理请求，并显示强求的处理结果

实现:
    1)新建web maven项目：new project-->maven project-->勾选 maven-archetype-webapp
    2)加入依赖
        spring-webmvc依赖,间接把spring的依赖都加入项目
        javax.servlet-api -- servlet依赖
    3)在web.xml中注册springmvc的核心对象DispatcherServlet
        1)DispatcherServlet叫做调度器是一个Servlet,继承HttpServlet
        2)DispatcherServlet也叫作前端控制器--Front Controller
        3)DispatcherServlet负责接收用户的请求,调用其他的控制器对象,把请求的结果显示给用户
    4)创建一个发送请求的页面 index.jsp
    5)创建控制器类
        1)新建HelloController,并添加@Controler注解--标记为控制器类,放到springmvc容器中
        2)添加@RequestMapping注解--标记处理的路径
        3)添加一个方法hello 返回处理结果
        4)若使@Controller生效 配置springmvc.xml组件扫描器: component-scan
    6)创建一个success.jsp,用来显示处理结果


处理流程:
    1)用户发起hello.do 请求
    2)tomcat根据web.xml中url-pattern配置得到*.do的请求交给DisPatcherServlet
    3)DispatcherServlet根据初始化时加载的配置文件中component-scan 得到扫描的包
    4)DispatcherServlet在包内寻找路径为/hello的方法(就把hello.do请求转发给HelloController中hello()方法)
    5)经过一系列的逻辑处理得到modelAndView对象,转发给页面--success.jsp

DispatcherServlet--中央调度器:
    1) DispatcherServlet extends FrameworkServlet extends HttpServletBean extends HttpServletBean extends HttpServlet
    1)负责创建springmvc容器对象,读取配置文件,并创建配置中的Controller对象
    2)负责接收用户的请求,根据请求路径分派的不同的Controller


SpringMVC执行源码分析：
    1)tomcat 启动创建容器对象
        1)web.xml中load-on-start标签值1,tomcat启动时创建DispatchServlet对象
        2)DispatcherServlet继承的是HttpServlet,所以它也是个servlet
        3)在它被创建时,会执行Init()方法,创建容器读取配置文件并把容器放到servletContext中
        4)创建容器后,会创建扫描包下的所有@Controller的类的对象并把对象放到springmvc的容器中
        容器为map 类似： map.put("HelloController",HelloController对象);
        init(){
             WebApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
             getServletContext.setAttribute(key,ctx);
        }
    2)请求处理流程
        1)protected void service(HttpServletRequest request, HttpServletResponse response)
        2)protected void doService(HttpServletRequest request, HttpServletResponse response)
        3)protected void doDispatch(HttpServletRequest request, HttpServletResponse response)
        {
            //实际处理器:
            mv = ha.handle(processedRequest, response, mappedHandler.getHandler());
            HelloController.hello();
        }

WEB-INFO/文件夹下: 浏览器无权限访问

springMVC配置文件:
    1)组件扫描(@Controller)：
        <context:component-scan base-package="controller"></context:component-scan>
    2)视图解析器：
        <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
                <property name="prefix" value="/WEB-INF/"></property>
                <property name="suffix" value=".jsp"></property>
        </bean>

SpringMVC注解:
    1_@requestMapping()
         1)用来处理请求地址映射的注解，可用于类或方法上
         2)加在类上面相当于抽取此类中公共的模块--所有响应请求的方法都是以该地址作为父路径
         3)value: 指定请求的实际地址
             1.可以指定为普通的具体值  --  @RequestMapping(value="/operation")
             2.可以指定为含有某变量的一类值 --  @RequestMapping(value="/operation/{operationId}")
             3.可以指定为含正则表达式的一类值 -- @RequestMapping(value="/get/{id:\\d+}")
             4.可以有多个值(数组) -- @RequestMapping(value={"/foo","/bar"})
         4)method： 指定请求method 的类型( GET、POST、PUT、DELETE等 )
         5)consumes: 指定处理请求的内容化类型--Content-Type(application/json,text/html)
         6)produces: 指定返回的内容类型,仅当request中的Accept类型中包含指定类型才能返回
         7)param: request中只有包含此参数,方法才进行处理
         8)headers： request只有包含指定header值,方法才进行处理