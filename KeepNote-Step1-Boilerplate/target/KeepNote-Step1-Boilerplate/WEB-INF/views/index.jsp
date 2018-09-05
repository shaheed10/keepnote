
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<title>KeepNote</title>
</head>
<body>



	<!-- Create a form which will have text boxes for Note ID, title, content and status along with a Send 
		 button. Handle errors like empty fields -->
	<form  action = "saveNote" method="post">
	<form:errors path="*" cssClass="errorBox"/>
	<p> NoteID:<input type = "text" name = "noteID" required/></p>
	<p>Title :<input type = "text" name = "title" required/></p>
	<p>Content:<input type = "text" name = "content" required/></p>
	<p>Status: <input type="text" name="status" required /> 
	<option>Active</option>
	<option>Inactive</option>
	<option>Reject</option>
	</p>
	<input type = "submit"/>
	</form>

	<!-- display all existing notes in a tabular structure with Id, Title,Content,Status, Created Date and Action -->

  <h3>Notes Details</h3>

  <table border="5">
      <thead>
              <tr>
                  <th>Id</th>
                  <th>Title</th>
                  <th>Content</th>
                  <th>Status</th>
                  <th>CreatedAt</th>
                  <th>Delete</th>
              </tr>
      </thead>
                 <c:forEach items="${list}" var="note">
       
         <tr>
       
             <td>${noteId}</td>
               <td>${noteTitle}</td>
              <td>${noteContent}</td>
              <td>${noteStatus}</td>
               <td>${createdAt}</td>
              <td><form action="deleteNote" method="get">
               <input type="hidden" name="noteId" value="${note.noteId}" />
             <input type="submit" value="Remove" name="remove">
                </form>
             </td>
       
         </tr>
       
     </c:forEach>
     
</table>
</body>
</html>