package uk.ac.ucl.model;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Model
{
  private final DataFrame dataFrame;

  public Model(String fileName)
  {
    dataFrame = new DataLoader().load(fileName);
  }

  public DataFrame getDataFrame()
  {
    return dataFrame;
  }

  public List<String[]> getPatientList()
  {
    List<String[]> patients = new ArrayList<>();
    for (int row = 0; row < dataFrame.getRowCount(); row++)
    {
      String id = dataFrame.getValue("ID", row);
      String name = dataFrame.getValue("FIRST", row) + " " + dataFrame.getValue("LAST", row);
      patients.add(new String[]{id, name});
    }
    return patients;
  }

  public Map<String, String> getPatientById(String id)
  {
    List<String> columnNames = dataFrame.getColumnNames();
    for (int row = 0; row < dataFrame.getRowCount(); row++)
    {
      if (id.equals(dataFrame.getValue("ID", row)))
      {
        Map<String, String> patient = new LinkedHashMap<>();
        for (String column : columnNames)
        {
          patient.put(column, dataFrame.getValue(column, row));
        }
        return patient;
      }
    }
    return null;
  }

  public List<String> searchFor(String keyword)
  {
    return new ArrayList<>();
  }
}
