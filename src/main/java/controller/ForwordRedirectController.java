package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @ClassName ForwordRedirectController
 * @Description TODO
 * @Author kiss
 * @Date 2020/6/17 14:54
 * @Version 1.0
 */
@Controller
public class ForwordRedirectController
{

    @RequestMapping("forward01")
    public ModelAndView forward01()
    {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("name","tom");
        modelAndView.addObject("age",22);
        //隐式转发
        modelAndView.setViewName("/view/success");
        return modelAndView;
    }

    @RequestMapping("forward02")
    public ModelAndView forward02()
    {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("name","Jack");
        modelAndView.addObject("age",20);
        //显式转发
        modelAndView.setViewName("forward:/WEB-INF/404.jsp");
        return modelAndView;
    }

    @RequestMapping("doRedirect")
    public ModelAndView doRedirect()
    {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("name","Jack");
        modelAndView.addObject("age",20);
        //显式转发 WEB-INFO 访问不到
        modelAndView.setViewName("redirect:/404.jsp");
        return modelAndView;
    }


}
