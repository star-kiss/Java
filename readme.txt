###  1.需求

```java
//用户页面发起一个请求，springmvc来处理请求，并显示请求的处理结果
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
```

### 2.处理流程

```
    1)用户发起hello.do 请求
    2)tomcat根据web.xml中url-pattern配置得到*.do的请求交给DisPatcherServlet
    3)DispatcherServlet根据初始化时加载的配置文件中component-scan 得到扫描的包
    4)DispatcherServlet在包内寻找路径为/hello的方法(就把hello.do请求转发给HelloController中hello()方法
    5)经过一系列的逻辑处理得到modelAndView对象,转发给页面--success.jsp
```

### 3.执行源码分析

```java
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
```

### 4.中央调度器

```java
    1)DispatcherServlet extends FrameworkServlet extends HttpServletBean  extends HttpServlet
    2)DispatcherServlet 继承servlet所以也是一个servle
    3)DispatcherServlet初始化负责创建springmvc容器对象,读取配置文件,并创建配置中的Controller对象
    4)作用：   负责接收用户的请求,根据请求路径分派的不同的Controller
```

### 5.配置文件

```java
    1)组件扫描(@Controller)：
        <context:component-scan base-package="controller"></context:component-scan>
    2)视图解析器：
        <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
                <property name="prefix" value="/WEB-INF/"></property>
                <property name="suffix" value=".jsp"></property>
        </bean>
    3)注解驱动
    	<mvc:annotation-driven/>
    4)url-pattern使用/时 解决静态资源拦截 
        <mvc:default-servlet-handler/>
        此配置会把所有请求交给tomcat的DefaultServlet处理和@ResponseBody冲突
        需加入<mvc:annotation-driven/>解决
    5)url-pattern使用/时 解决静态资源拦截 
    	<mvc:resources mapping="" location=""/>
        
```

###  6.注解

```java
  @RequestMapping()
            1)用来处理请求地址映射的注解，可用于类或方法上
            2)加在类上面相当于抽取此类中公共的模块--所有响应请求的方法都是以该地址作为父路径
            3)value: 指定请求的实际地址
                1.可以指定为普通的具体值  --  @RequestMapping(value="/operation")
                2.可以指定为含有某变量的一类值 --  @RequestMapping(value="/operation/{operationId}")
                3.可以指定为含正则表达式的一类值 -- @RequestMapping(value="/get/{id:\\d+}")
                4.可以有多个值(数组) -- @RequestMapping(value={"/foo","/bar"})
            4)method： 指定请求method 的类型( GET、POST、PUT、DELETE等 ) 方式不对时 405错误
            5)consumes: 指定处理请求的内容化类型--Content-Type(application/json,text/html)
            6)produces: 指定返回的内容类型,仅当request中的Accept类型中包含指定类型才能返回,解决post请求中文乱码
            7)param: request中只有包含此参数,方法才进行处理
            8)headers： request只有包含指定header值,方法才进行处理
  @RequestParam()
            1)用来接受请求中参数--当表单参数和方法形参名字不一致时，做一个名字映射
            2)value: 请求中的参数名
            3)required: 次参数是否为必须,默认值为true
  @PathVarible()
            1)用于获取uri中的参数,比如user/1中1的值             
                
```

### 7.参数接收

```java
1.使用@RequestParam注解
2.使用@PathVariable注解
3.SpringMVC自动装配(属性)
4.SpringMVC自动装配(对象)
5.直接从request等域中取
```

### 8.处理器方法的返回值

```java
1.ModelAndView
	1)适用于同时返回model 和 view
	2)数据存在model中,然后对视图执行forward操作
2.返回String
	1)适用于返回单一view,也可以配合request域返回值和域
	2)处理器方法返回的字符串可以指定逻辑视图名或完整的视图路径,通过视图解析器可以把它转为物理视图地址
	3)框架对视图执行forward操作
3.返回void -- 了解
    1)不能表示视图,也不能表示数据
    2)适用于ajax作为返回值,通过HttpServletResponse输出数据响应ajax请求.
    3)ajax请求服务端只返回数据和视图无关.
4.返回Object
    1)String Integer Map List 实体类等...
    2)返回的数据,和视图无关
    3)可以使用对象表示的数据,响应ajax请求
    4)做ajax 使用json类型的步骤:
		1.加入使用json的依赖,springmvc默认使用jackson
		2.在springmvc配置文件加上注解驱动 <mvc:annotation-driven></mvc:annotation-driven>
		3.在方法上面加上@ResponseBody注解
5.@ResponseBody
   1)String 返回字符串时 
            dataType:"text"时默认编码为"ISO-8859-1"服务器给中文时导致乱码       
            设置@RequestMapping(produces="text/html; charset=utf-8")即可解决
   2)Integer 返回integer
```

### 9.返回Json原理

```java
SpringMVC处理器返回Object,可以转为json输出到浏览器,响应ajax的内部原理：
    1)配置文件中注解驱动 <mvc:annotation-driven></mvc:annotation-driven> 
        作用：
        	通过创建HttpMessageConveter接口中的实例对象完成java对象到Json、Xml、Text、二进制等格式的转换
        过程：
        	1)把注解驱动加入配置后,会自动创建HttpMessageConveter接口中的一些实例对象
        	2)包含String处理接口(StringHttpMessageConveter)和json处理接口(MappingJackson2MessageConveter)
        	3)通过MappingJackson2MessageConveter处理器的返回值转为json字符串 
	2)@ResponseBody		
    	此注解放到处理器的上面,通过HttpResponse输出数据,响应ajax      
    3)HttpMessageConveter接口：消息转换器
			1.作用：
        		定义了java转为Json、Xml、Text等数据格式的方法，这个接口有很多的实现类实现了Java对象的转换
        	 2.方法：
				1)boolean canWrite(Class<?> var1, @Nullable MediaType var2);
				2)void write(T var1, @Nullable MediaType var2, HttpOutputMessage var3);
        	 3.过程：	
				3)canWrite()方法检查处理器的返回值是否可以转为MediaType(Json、Xml、Text...)类型
				4)write()方法把处理器的返回值转为json字符串--通过调用Jackson的ObjectMapping()方法
		  
        
    
```

### 10.静态资源处理

```python
1.在springmvc配置中加入
	1)<mvc:default-servlet-handler/>
	2)<mvc:annotation-driven/>
	3)<mvc:default-servlet-handler/> 将所有的文件，包含静态资源文件都交给tomcat的defaultServlet处理
	4)如果不加<mvc:annotation-driven/>注解DispatcherServlet则无法区分请求是资源文件还是mvc的注解,而导致controller的请求报404错误。
2.<mvc:resources mapping="" location=""/>
	1)此配置加入配置文件后,springmvc会创建ResourceHttpRequestHandler处理器对象.
	2)使用这个对象处理静态资源的访问,而不依赖tomcat的DefaultServlet
	3)mapping：访问静态资源的uri地址，可以使用通配符 /** 等
	4)location： 静态资源在项目中的位置 
3.tomcat的conf目录下web.xml
	1)默认的DefaultServlet
		1_处理静态资源
		2_处理未映射到其他servlet的请求	
4.如果在web.xml中servlet-mapping的url-pattern设置的是/
	而不是如.do。表示将所有的文件，包含静态资源文件都交给spring mvc处理
	就需要用到<mvc:annotation-driven />了
    如果不加，DispatcherServlet则无法区分请求是资源文件还是mvc的注解
    而导致controller的请求报404错误
```

### 11.转发和重定向

```python
1)springmvc把servlet时的请求转发和重定向进行了封装,现在可以使用简单地方式进行转发和重定向(关键字).
2)forward:表示转发实现了request.getRequestDispatcher("main.html").forword();-- 可以访问WEB-INFO下资源 
3)redirect:表示重定向资源 实现了response.sendRedirect("main.html");--不可以访问WEB-INFO下 获取参数使用{name}取不到应该使用{param.name}
4)显示转发和重定向不和视图解析器一起工作
```

### 12.异常处理

```java
1.springmvc异常处理
	1)springmvc异常处理：使用@ExceptionHandler注解和@ControllerAdvice
    2)springmvc采用aop思想把业务逻辑和异常处理分开
2.处理流程
	1)新建maven项目
	2)添加sprngmvc-web依赖和servlet依赖
	3)新建一个自定义异常类UserException,定义它的子类NameException和AgeException
	4)在controller抛出异常
	5)创建一个普通类,使用全局异常处理类--在类上面加@ControllerAdvice注解 方法上加@ExceptionHandler
	6)创建异常处理的页面
	7)创建springmvc的配置文件
		1)组件扫描器：扫描controller所在的包
		2)组件扫描器：扫描controllerAdvice所在的包
		3)声明注解驱动
3.异常发生时处理的逻辑
    	1)记录异常信息：记录异常发生的时间、发生异常的方法和异常内容到数据库或日志文件
    	2)发送通知：把异常信息通过短信、邮件、微信发送给相关人员
    	3)用户提示：给用户友好的提示
```

### 13.拦截器

```java
1.定义
    1)采用AOP思想,也就是说符合横切关注点的所有功能都可以放入拦截器实现。
    2)拦截器是springmvc中的一种对象类似于过滤器,使用时需要实现HandlerInterceptor接口
    3)拦截器和过滤器类似,但功能上侧重点不同: 
        1.过滤器用来过滤请求参数和设置字符编码的
        2.而拦截器是拦截用户请求,做请求判断处理的
    4)拦截器是全局的可以对多个controller做拦截
    5)拦截器常用于：用户登录、权限检查、记录日志
2.使用流程
	1)定义类实现HandlerInterceptor接口,并实现它的3个接口
	2)在配置文件中声明拦截器,并制定拦截器的请求uri地址
3.执行时间
	1)在请求处理前,也就是controller中方法执行之前先被拦截
	2)在控制器方法执行之后也会执行拦截
	3)在请求处理完成后也会被拦截
4.多个拦截器执行
	1)以ArrayList存放,按声明顺序存放
	2)执行顺序,声明顺序决定--先声明的先执行
	3)1和2拦截器均为true
          preHandle01-->preHandle02-->userInterceptor()-->postHandle02-->postHandle01-->afterCompletion02-->afterCompletion01
	4)1--true 2--false
       	  reHandle01-->preHandle02>afterCompletion01
	5)1--false 2--true|false
          reHandle01
```

### 14.拦截器和过滤器区别

|               过滤器(Filter)                |       拦截器(Interceptor)        |
| :-----------------------------------------: | :------------------------------: |
|      servlet的对象--由tomcat服务器创建      | springMVC的对象--由springMVC创建 |
|               实现filter接口                |    实现HandlerInterceptor接口    |
| 设置request、response的参数、性侧重数据过滤 |      验证请求，可以截断请求      |
|               一个执行时间点                |          三个执行时间点          |
|            处理js、html、jsp...             |     侧重拦截controller的对象     |

### 15.springmvc和structs

```
1)springmvc是方法级别的处理器structs是类级别的处理器
2)springmvc入口是servlet     structs是filter
```

### 16.web中组件顺序

```
1)context-param>listener>interceptor>filter>servlet
```
