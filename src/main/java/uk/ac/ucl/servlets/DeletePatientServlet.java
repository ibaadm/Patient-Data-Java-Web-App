package uk.ac.ucl.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uk.ac.ucl.model.ModelFactory;

import java.io.IOException;

@WebServlet("/deletepatient")
public class DeletePatientServlet extends HttpServlet
{
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
  {
    String id = request.getParameter("id");
    if (id != null && !id.trim().isEmpty())
    {
      ModelFactory.getModel().deletePatient(id);
    }
    response.sendRedirect("patientList");
  }
}
