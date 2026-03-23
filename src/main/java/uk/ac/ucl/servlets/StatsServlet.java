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

@WebServlet("/stats")
public class StatsServlet extends HttpServlet
{
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
  {
    try
    {
      Model model = ModelFactory.getModel();

      request.setAttribute("patientCount", model.getPatientCount());
      request.setAttribute("oldestPatient", model.getOldestPatient());
      request.setAttribute("deceasedCount", model.getDeceasedCount());
      request.setAttribute("genderCounts", model.getCountByColumn("GENDER"));
      request.setAttribute("cityCounts", model.getCountByColumn("CITY"));

      ServletContext context = getServletContext();
      RequestDispatcher dispatch = context.getRequestDispatcher("/stats.jsp");
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
