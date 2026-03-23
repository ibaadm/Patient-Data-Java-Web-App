package uk.ac.ucl.model;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.List;

public class DataLoader
{
  public DataFrame load(String fileName)
  {
    DataFrame dataFrame = new DataFrame();

    try (Reader reader = new FileReader(fileName);
         CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT))
    {
      boolean firstRow = true;

      for (CSVRecord record : csvParser)
      {
        if (firstRow)
        {
          for (String columnName : record)
          {
            dataFrame.addColumn(new Column(columnName));
          }
          firstRow = false;
          continue;
        }

        List<String> columnNames = dataFrame.getColumnNames();
        for (int i = 0; i < columnNames.size(); i++)
        {
          dataFrame.addValue(columnNames.get(i), record.get(i));
        }
      }
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }

    return dataFrame;
  }
}
