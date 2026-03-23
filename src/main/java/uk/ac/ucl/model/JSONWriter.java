package uk.ac.ucl.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class JSONWriter
{
  public void write(DataFrame dataFrame, String fileName)
  {
    ObjectMapper mapper = new ObjectMapper();
    ArrayNode array = mapper.createArrayNode();
    List<String> columnNames = dataFrame.getColumnNames();

    for (int row = 0; row < dataFrame.getRowCount(); row++)
    {
      ObjectNode node = mapper.createObjectNode();
      for (String column : columnNames)
      {
        node.put(column, dataFrame.getValue(column, row));
      }
      array.add(node);
    }

    try
    {
      mapper.writerWithDefaultPrettyPrinter().writeValue(new File(fileName), array);
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }
}
