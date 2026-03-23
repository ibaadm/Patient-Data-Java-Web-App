<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
  <jsp:include page="/meta.jsp"/>
  <title>Patient Data App</title>
</head>
<body>
<jsp:include page="/header.jsp"/>
<div class="main">
  <h2>Patients:</h2>
  <p>
    <a href="addpatient">Add Patient</a>
    &nbsp;|&nbsp;
    <a href="savejson">Save as JSON</a>
  </p>
  <%
    if ("json".equals(request.getParameter("saved")))
    {
  %>
    <p style="color: green;">Data saved to data/patients.json</p>
  <%
    }
  %>
  <%
    String errorMessage = (String) request.getAttribute("errorMessage");
    if (errorMessage != null)
    {
  %>
      <p style="color: red;"><%= errorMessage %></p>
  <%
    }
  %>
  <ul>
    <%
      List<String[]> patients = (List<String[]>) request.getAttribute("patients");
      if (patients != null)
      {
        for (String[] patient : patients)
        {
    %>
    <li><a href="patient?id=<%= patient[0] %>"><%= patient[1] %></a></li>
    <%
        }
      }
    %>
  </ul>
</div>
<jsp:include page="/footer.jsp"/>
</body>
</html>
