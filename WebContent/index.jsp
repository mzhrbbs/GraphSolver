<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>

.button {
	background-color: #C0C0C0;;
	border: none;
	color: white;
	padding: 20px 50px;
	margin-left: 60px;
	text-decoration: none;
	display: inline-block;
	font-size: 16px;
	margin: 4px 2px;
	cursor: pointer;
	border-radius: 50%;
	display:inline-block;
}
</style>
<link href='http://fonts.googleapis.com/css?family=Merienda+One' rel='stylesheet' type='text/css'>
<link href="CSS/form.css"  rel='stylesheet' type='text/css'>

</head>

   <body> 
   <h1 align=center>Graph Center</h1>
     <form action="directed.jsp" >
     <input type="submit" class="button" value="Base Case" >
     </form>
     <form action="Undirected.jsp" >
     <input type="submit" class="button" value="Variation-1" >
     </form>
     <form action="negative.jsp" >
     <input type="submit" class="button" value="Variation-2 " >
     </form>
     <form action="priority.jsp" >
     <input type="submit" class="button" value="Variation-3 " >
     </form>                
    
    </body>

</html>