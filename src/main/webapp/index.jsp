<%@ page  contentType="text/html; charset=utf-8" language="java"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<script src="${ctx}/webjars/jquery/3.3.1-2/jquery.min.js"></script>
<body>
<h2>测试Interceptor</h2>
<form action="/interceptor/userInterceptor">
    <input type="text" name="name"/>
    <br/>
    <input type="text" name="age"/>
    <br/>
    <button type="submit">提交</button>
</form>
<hr>
<h2>测试Exception</h2>
<form action="/exception/userException">
    <input type="text" name="name"/>
    <br/>
    <input type="text" name="age"/>
    <br/>
    <button type="submit">提交</button>
</form>
<hr>
<h2>测试接收参数</h2>
<p>@RequestParam接收参数</p>
<button onclick="getRequestParam()">getRequestParam()</button>
<button onclick="postRequestParam()">postRequestParam()</button>
<hr>
<p>@PathVariable接收参数</p>
<button onclick="getPathVariable()">getPathVariable()</button>
<button onclick="posPathVariable()">posPathVariable()</button>
<hr>
<p>SprignMVC自动装配--属性接收参数</p>
<button onclick="getAutomaticMVC()">getPathVariable()</button>
<button onclick="postAutomaticMVC()">posPathVariable()</button>
<hr>
<p>SprignMVC自动装配--对象接收参数</p>
<button onclick="getAutomaticPerson()">getPathVariable()</button>
<button onclick="posAutomaticPerson()">posPathVariable()</button>

<script type="text/javascript">
   var data = {name : "小丶kiss",age : 21};
   /**************************************************************/
   function getRequestParam() {
       $.get("/param/requestParam",data,function (data) {
          alert("AJAX传递请求参数（GET请求方式）---后台返回来的参数：" + data);
       });
   }
   function postRequestParam() {
       $.post( "/param/requestParam", data, function (data) {
           alert("后台返回来的参数：" + data);
       });
   }
   /**************************************************************/
   function getPathVariable(){
        $.get("/param/pathVariable/"+data.name+"/"+data.age+"",{},function(data){
            alert(" URL传递参数---后台返回来的参数：" + data);
        });
   }
   function posPathVariable(){
        $.post("/param/pathVariable/"+data.name+"/"+data.age+"",function(data){
            alert(" URL传递一个参数（POST请求方式）---后台返回来的参数：" + data);
        });
   }
   /**************************************************************/
   function getAutomaticMVC(){
       $.get("/param/autoMVC?name="+data.name+"/"+"&&age="+data.age,{},function(data){
           alert(" URL传递参数---后台返回来的参数：" + data);
       });
   }
   function postAutomaticMVC(){
       $.post("/param/autoMVC",data,function(data){
           alert(" URL传递一个参数（POST请求方式）---后台返回来的参数：" + data);
       });
   }
   /**************************************************************/
   function getAutomaticPerson(){
       $.get("/param/autoPerson?name="+data.name+"/"+"&&age="+data.age,{},function(data){
           alert(" URL传递参数---后台返回来的参数：" + data);
       });
   }
   function posAutomaticPerson(){
       $.post("/param/autoPerson",data,function(data){
           alert(" URL传递一个参数（POST请求方式）---后台返回来的参数：" + data);
       });
   }
</script>
</body>
</html>
