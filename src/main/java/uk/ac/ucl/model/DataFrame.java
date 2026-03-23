package uk.ac.ucl.model;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class DataFrame
{
  private final LinkedHashMap<String, Column> columns = new LinkedHashMap<>();

  public void addColumn(Column column)
  {
    columns.put(column.getName(), column);
  }

  public List<String> getColumnNames()
  {
    return new ArrayList<>(columns.keySet());
  }

  public int getRowCount()
  {
    if (columns.isEmpty())
    {
      return 0;
    }
    return columns.values().iterator().next().getSize();
  }

  public String getValue(String columnName, int row)
  {
    return columns.get(columnName).getRowValue(row);
  }

  public void putValue(String columnName, int row, String value)
  {
    columns.get(columnName).setRowValue(row, value);
  }

  public void addValue(String columnName, String value)
  {
    columns.get(columnName).addRowValue(value);
  }
}
