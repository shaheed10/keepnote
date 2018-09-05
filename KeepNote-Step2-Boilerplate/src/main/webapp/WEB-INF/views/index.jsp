<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<title>Keep-Board</title>
</head>

<body>
	<!-- Create a form which will have text boxes for Note title, content and status along with a Add 
		 button. Handle errors like empty fields -->
		 <form  action = "add" method="post">
	
	
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
	
	 <h3>Notes Details</h3>
  <table border="2">
      <thead>
              tr>
                  <th>Id</th>
                  <th>Title</th>
                  <th>Content</th>
                  <th>Status</th>
                  <th>CreatedAt</th>
                  <th>Delete</th>
              </tr>
      </thead>
      <c:forEach items="${note}" var="note">
       
         <tr>
               <td>${note.noteId}</td>
               <td>${note.noteTitle}</td>
              <td>${note.noteContent}</td>
              <td>${note.noteStatus}</td>
               <td>${note.createdAt}</td>
              <td>
              <form action="edit" method="get">
                 <input type="hidden" name="noteId" value="${note.noteId}" />
             <input type="submit" value="edit">
                </form>
              <form action="delete" method="get">
               <input type="hidden" name="noteId" value="${note.noteId}" />
             <input type="submit" value="Remove" name="remove">
                </form>
                
             </td>
       
         </tr>
       
     </c:forEach>
     </table>

	<!-- display all existing notes in a tabular structure with Title,Content,Status, Created Date and Action -->
</body>

</html>