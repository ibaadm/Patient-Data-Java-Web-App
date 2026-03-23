package uk.ac.ucl.servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uk.ac.ucl.model.ModelFactory;

import java.io.IOException;
import java.util.Map;

@WebServlet("/patient")
public class ViewPatientServlet extends HttpServlet
{
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
  {
    try
    {
      String id = request.getParameter("id");
      if (id == null || id.trim().isEmpty())
      {
        request.setAttribute("errorMessage", "No patient ID provided.");
      }
      else
      {
        Map<String, String> patient = ModelFactory.getModel().getPatientById(id);
        if (patient == null)
        {
          request.setAttribute("errorMessage", "Patient not found.");
        }
        else
        {
          request.setAttribute("patient", patient);
        }
      }
      ServletContext context = getServletContext();
      RequestDispatcher dispatch = context.getRequestDispatcher("/patient.jsp");
      dispatch.forward(request, response);
    }
    catch (IOException e)
    {
      request.setAttribute("errorMessage", "Error loading data: " + e.getMessage());
      ServletContext context = getServletContext();
      RequestDispatcher dispatch = context.getRequestDispatcher("/error.jsp");
      dispatch.forward(request, response);
    }
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
  {
    doGet(request, response);
  }
}
