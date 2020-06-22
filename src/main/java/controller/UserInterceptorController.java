package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @ClassName UserInterceptorController
 * @Description TODO
 * @Author kiss
 * @Date 2020/6/18 10:39
 * @Version 1.0
 */
@Controller
@RequestMapping("interceptor")
public class UserInterceptorController
{
    @RequestMapping("userInterceptor")
    public ModelAndView userInterceptor(String name,Integer age)
    {
        System.out.println("===userInterceptor()===");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("name",name);
        modelAndView.addObject("age",age);
        modelAndView.setViewName("/view/success");

        return modelAndView;
    }
}
