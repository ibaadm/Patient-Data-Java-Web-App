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
  <h2>Statistics</h2>
  <%
    String errorMessage = (String) request.getAttribute("errorMessage");
    if (errorMessage != null)
    {
  %>
      <p style="color: red;"><%= errorMessage %></p>
  <%
    }
    else
    {
      Integer patientCount = (Integer) request.getAttribute("patientCount");
      Integer deceasedCount = (Integer) request.getAttribute("deceasedCount");
      String[] oldest = (String[]) request.getAttribute("oldestPatient");
      Map<String, Integer> genderCounts = (Map<String, Integer>) request.getAttribute("genderCounts");
      Map<String, Integer> cityCounts = (Map<String, Integer>) request.getAttribute("cityCounts");
  %>

  <h3>Overview</h3>
  <table>
    <tr><td>Total patients</td><td><%= patientCount %></td></tr>
    <tr><td>Deceased</td><td><%= deceasedCount %></td></tr>
    <tr><td>Living</td><td><%= patientCount - deceasedCount %></td></tr>
  </table>

  <h3>Oldest Patient</h3>
  <p><a href="patient?id=<%= oldest[0] %>"><%= oldest[1] %></a> (born <%= oldest[2] %>)</p>

  <h3>Gender Breakdown</h3>
  <table>
    <%
      for (Map.Entry<String, Integer> entry : genderCounts.entrySet())
      {
    %>
    <tr><td><%= entry.getKey().isEmpty() ? "(unknown)" : entry.getKey() %></td><td><%= entry.getValue() %></td></tr>
    <%
      }
    %>
  </table>

  <h3>Patients by City</h3>
  <table>
    <%
      for (Map.Entry<String, Integer> entry : cityCounts.entrySet())
      {
    %>
    <tr><td><%= entry.getKey().isEmpty() ? "(unknown)" : entry.getKey() %></td><td><%= entry.getValue() %></td></tr>
    <%
      }
    %>
  </table>

  <%
    }
  %>
</div>
<jsp:include page="/footer.jsp"/>
</body>
</html>
