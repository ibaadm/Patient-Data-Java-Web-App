package uk.ac.ucl.servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uk.ac.ucl.model.Model;
import uk.ac.ucl.model.ModelFactory;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

@WebServlet("/addpatient")
public class AddPatientServlet extends HttpServlet
{
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
  {
    request.setAttribute("columnNames", ModelFactory.getModel().getColumnNames());
    request.setAttribute("formAction", "addpatient");
    ServletContext context = getServletContext();
    RequestDispatcher dispatch = context.getRequestDispatcher("/patientForm.jsp");
    dispatch.forward(request, response);
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
  {
    Model model = ModelFactory.getModel();
    Map<String, String> fields = new LinkedHashMap<>();
    for (String column : model.getColumnNames())
    {
      String value = request.getParameter(column);
      fields.put(column, value != null ? value : "");
    }
    model.addPatient(fields);
    response.sendRedirect("patientList");
  }
}
