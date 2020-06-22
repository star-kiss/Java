package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @ClassName HelloController
 * @Description TODO
 * @Author kiss
 * @Date 2020/6/9 16:38
 * @Version 1.0
 */

@Controller
public class HelloController
{

    @RequestMapping("/hello")
    private ModelAndView hello(HttpServletRequest request)
    {
        System.out.println(request.getParameter("name"));

        ModelAndView modelAndView = new ModelAndView();
        //添加数据请求处理的最后会把数据放到reques作用域
        modelAndView.addObject("name",request.getParameter("name"));
        //指定视图路径,会对视图做forward操作：request.getRequestDispatcher("/success.jsp").forward(...);
        modelAndView.setViewName("view/success");
        return modelAndView;
    }
}
