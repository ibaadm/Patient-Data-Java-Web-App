<%@ page import="java.util.Map" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
  <jsp:include page="/meta.jsp"/>
  <title>Patient Data App</title>
</head>
<body>
<jsp:include page="/header.jsp"/>
<div class="main">
  <%
    String errorMessage = (String) request.getAttribute("errorMessage");
    if (errorMessage != null)
    {
  %>
      <p style="color: red;"><%= errorMessage %></p>
  <%
    }
    Map<String, String> patient = (Map<String, String>) request.getAttribute("patient");
    if (patient != null)
    {
  %>
  <h2><%= patient.get("FIRST") %> <%= patient.get("LAST") %></h2>
  <table>
    <%
      for (Map.Entry<String, String> entry : patient.entrySet())
      {
    %>
    <tr>
      <td><strong><%= entry.getKey() %></strong></td>
      <td><%= entry.getValue() %></td>
    </tr>
    <%
      }
    %>
  </table>
  <p>
    <a href="editpatient?id=<%= patient.get("ID") %>">Edit</a>
    &nbsp;
    <form method="POST" action="deletepatient" style="display:inline;">
      <input type="hidden" name="id" value="<%= patient.get("ID") %>"/>
      <input type="submit" value="Delete" onclick="return confirm('Delete this patient?');"/>
    </form>
  </p>
  <%
    }
  %>
  <p><a href="patientList">Back to patient list</a></p>
</div>
<jsp:include page="/footer.jsp"/>
</body>
</html>
