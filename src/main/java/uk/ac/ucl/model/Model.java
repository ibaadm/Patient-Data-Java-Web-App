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

  public int getPatientCount()
  {
    return dataFrame.getRowCount();
  }

  public String[] getOldestPatient()
  {
    String oldestId = null;
    String oldestName = null;
    String oldestBirthdate = null;

    for (int row = 0; row < dataFrame.getRowCount(); row++)
    {
      String birthdate = dataFrame.getValue("BIRTHDATE", row);
      if (birthdate.isEmpty())
      {
        continue;
      }
      if (oldestBirthdate == null || birthdate.compareTo(oldestBirthdate) < 0)
      {
        oldestBirthdate = birthdate;
        oldestId = dataFrame.getValue("ID", row);
        oldestName = dataFrame.getValue("FIRST", row) + " " + dataFrame.getValue("LAST", row);
      }
    }
    return new String[]{oldestId, oldestName, oldestBirthdate};
  }

  public int getDeceasedCount()
  {
    int count = 0;
    for (int row = 0; row < dataFrame.getRowCount(); row++)
    {
      if (!dataFrame.getValue("DEATHDATE", row).isEmpty())
      {
        count++;
      }
    }
    return count;
  }

  public Map<String, Integer> getCountByColumn(String columnName)
  {
    Map<String, Integer> counts = new LinkedHashMap<>();
    for (int row = 0; row < dataFrame.getRowCount(); row++)
    {
      String value = dataFrame.getValue(columnName, row);
      counts.put(value, counts.getOrDefault(value, 0) + 1);
    }

    List<Map.Entry<String, Integer>> entries = new ArrayList<>(counts.entrySet());
    entries.sort((a, b) -> b.getValue() - a.getValue());

    Map<String, Integer> sorted = new LinkedHashMap<>();
    for (Map.Entry<String, Integer> entry : entries)
    {
      sorted.put(entry.getKey(), entry.getValue());
    }
    return sorted;
  }

  public List<String[]> searchFor(String keyword)
  {
    List<String[]> results = new ArrayList<>();
    String[] terms = keyword.trim().toLowerCase().split("\\s+");
    List<String> columnNames = dataFrame.getColumnNames();

    for (int row = 0; row < dataFrame.getRowCount(); row++)
    {
      for (String term : terms)
      {
        boolean matched = false;
        for (String column : columnNames)
        {
          if (dataFrame.getValue(column, row).toLowerCase().contains(term))
          {
            matched = true;
            break;
          }
        }
        if (matched)
        {
          String id = dataFrame.getValue("ID", row);
          String name = dataFrame.getValue("FIRST", row) + " " + dataFrame.getValue("LAST", row);
          results.add(new String[]{id, name});
          break;
        }
      }
    }
    return results;
  }
}
