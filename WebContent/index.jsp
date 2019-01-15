<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<form action="servletRelatorio" method="POST">
		<label>Data Inicio:</label>
		<input type="text" name="data_ini"/>
		
		<label>Data Fim:</label>
		<input type="text" name="data_fim"/>
		
		<input type="submit" value="gera pdf"/>
	</form>

</body>
</html>