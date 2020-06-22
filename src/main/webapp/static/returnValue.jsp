<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/6/16
  Time: 12:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>处理器返回值</title>
</head>
<script src="${ctx}/webjars/jquery/3.3.1-2/jquery.min.js"></script>
<body>
<button onclick="submit()">返回对象</button>
<hr>
<button onclick="returnStr()">返回字符串</button>
</body>
<script>
    function  submit(){
        data = {
            name: "tom",
            age: 20
        };
        $.ajax({
            url:"retrunPerson.do",
            data:data,
            success:function(data){
                console.log(data);
            }})
    }

    function returnStr(){
        $.ajax({url:"returnStr",data:"",dataType:"text",success:function (data) {
                alert(data);
            }})
    }

</script>
</html>
