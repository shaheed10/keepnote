<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<body>
	<!-- Create a form which will have text boxes for Note title, content and status along with a Add 
		 button. Handle errors like empty fields -->
		 <form  action = "edit" method="post">
	<td><span>${note.noteId}</span></td>
     <input name="noteId" value="${note.noteId}" type="hidden"/>		 
	
	<p>Title :<input type = "text" name = "noteTitle" required/></p>
	<p>Content:<input type = "text" name = "noteContent" required/></p>
	<p> Status: <select name="noteStatus">
                      <option>Active</option>
                   	  <option>Inactive</option>
                   	  <option>Reject</option>
                   </select>                      
    </p>
	<input type = "submit" type="button button-default"/>
	</form>

</body>
</html>