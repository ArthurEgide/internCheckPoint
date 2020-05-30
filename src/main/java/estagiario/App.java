package estagiario;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class App {
    private static void copyFileUsingStream(File source, File dest) throws IOException {
      InputStream is = null;
      OutputStream os = null;
      try {
          is = new FileInputStream(source);
          os = new FileOutputStream(dest);
          byte[] buffer = new byte[1024];
          int length;
          while ((length = is.read(buffer)) > 0) {
              os.write(buffer, 0, length);
          }
      } finally {
          is.close();
          os.close();
      }
  }
  public static void main( String[] args )
  {
    try{
      System.out.println( " =-=-=-=-=- Iniciando essa maravilha -=-=-=-=-=" );
      File raw = new File("files/ponto.csv");
      File bkp = new File("files/ponto_bkp.csv");
      copyFileUsingStream(raw, bkp);

      CSVParser parser = CSVParser.parse(raw, Charset.forName("UTF-8"), CSVFormat.DEFAULT);
      List<CSVRecord> lines = parser.getRecords();
      for (CSVRecord c : lines) {
        System.out.println(c.get(0));
      }

    }catch(IOException e){
      e.printStackTrace();
    }catch(IllegalArgumentException e){
      e.printStackTrace();
    }
  }
}
