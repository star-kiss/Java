package handler;


import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * @ClassName UserInterceptor
 * @Description TODO
 * @Author kiss
 * @Date 2020/6/18 14:51
 * @Version 1.0
 */

public class UserInterceptor02 implements HandlerInterceptor
{
    private  static long startTime = System.currentTimeMillis();
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
    {
        System.out.println("===2处理器方法处理前执行2===");
        //return false可以进行转发
        //request.getRequestDispatcher("/WEB-INF/404.jsp").forward(request,response );

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception
    {
        System.out.println("===2处理器处理完成后执行2===");
        //对数据和视图二次修正
        if(modelAndView!=null){
            modelAndView.addObject("date",new Date());
            modelAndView.setViewName("/view/interception/inter");
        }
    }

    @Override
    //一般做资源回收
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception
    {
        long endTime = System.currentTimeMillis();
        System.out.println("===2请求完成后执行2===");
        System.out.println("2请求处理时间："+(endTime - startTime)+"毫秒2");
    }
}
