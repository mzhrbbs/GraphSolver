<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<link href="form.css"  rel='stylesheet' type='text/css'>
<body>

<div id="home" align="right">
		<a href="index.jsp">HOME</a>
	</div>

	<div id=outer>
		
		<a>
			<h3>
				Upload a csv in this format
				<h3>
					<h6>Directed with nodes having  priority</h6>
					<img src="Backgrounds/priority.png" width="200" height="200">
		</a>
	</div>

<div class="grid">
	<h3 align="center" style="color:white"> Upload File</h3>
<form action="FindCenterPriority" method="post" enctype="multipart/form-data" align="right" class="form login">
				<div class="form__field">
				<label for="maxNodes"><span class="hidden">"Max Nodes"</span></label>
				<input id="maxNodes" type="text" class="form_input" placeholder="Number of Nodes" name="maxNodes" required>
				</div>
				<div class="form__field">
				<label for="file"><span class="hidden">"File"</span></label>
                <input id="file" type="file" class="form_input" name="file" placeholder="Add file" required/><br>
                </div>
                <div class="form__field">
                <input type="submit" class="button" value="upload"/>
               </div>
            </form> 

</div>
</body>
</html>