<%@ page import="java.util.List" %>
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
    Map<String, String> patient = (Map<String, String>) request.getAttribute("patient");
    List<String> columnNames = (List<String>) request.getAttribute("columnNames");
    String formAction = (String) request.getAttribute("formAction");
    boolean isEdit = patient != null;
  %>
  <h2><%= isEdit ? "Edit Patient" : "Add Patient" %></h2>
  <form method="POST" action="<%= formAction %>">
    <% if (isEdit) { %>
    <input type="hidden" name="ID" value="<%= patient.get("ID") %>"/>
    <% } %>
    <table>
      <%
        for (String column : columnNames)
        {
          if (column.equals("ID")) continue;
          String value = isEdit ? patient.get(column) : "";
      %>
      <tr>
        <td><label for="<%= column %>"><%= column %></label></td>
        <td><input type="text" id="<%= column %>" name="<%= column %>" value="<%= value %>"/></td>
      </tr>
      <%
        }
      %>
    </table>
    <p>
      <input type="submit" value="<%= isEdit ? "Save Changes" : "Add Patient" %>"/>
      <% if (isEdit) { %>
      <a href="patient?id=<%= patient.get("ID") %>">Cancel</a>
      <% } else { %>
      <a href="patientList">Cancel</a>
      <% } %>
    </p>
  </form>
</div>
<jsp:include page="/footer.jsp"/>
</body>
</html>
