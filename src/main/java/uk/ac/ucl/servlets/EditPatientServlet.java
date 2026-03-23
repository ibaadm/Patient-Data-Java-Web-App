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

@WebServlet("/editpatient")
public class EditPatientServlet extends HttpServlet
{
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
  {
    String id = request.getParameter("id");
    Model model = ModelFactory.getModel();
    Map<String, String> patient = model.getPatientById(id);

    if (patient == null)
    {
      request.setAttribute("errorMessage", "Patient not found.");
      ServletContext context = getServletContext();
      RequestDispatcher dispatch = context.getRequestDispatcher("/patient.jsp");
      dispatch.forward(request, response);
      return;
    }

    request.setAttribute("patient", patient);
    request.setAttribute("columnNames", model.getColumnNames());
    request.setAttribute("formAction", "editpatient");
    ServletContext context = getServletContext();
    RequestDispatcher dispatch = context.getRequestDispatcher("/patientForm.jsp");
    dispatch.forward(request, response);
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
  {
    String id = request.getParameter("ID");
    Model model = ModelFactory.getModel();
    Map<String, String> fields = new LinkedHashMap<>();
    for (String column : model.getColumnNames())
    {
      String value = request.getParameter(column);
      fields.put(column, value != null ? value : "");
    }
    fields.put("ID", id);
    model.updatePatient(id, fields);
    response.sendRedirect("patient?id=" + id);
  }
}
