package estagiario;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
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
  
    private static ArrayList<WorkDay> readHours(){
      ArrayList<WorkDay> wds = new ArrayList<WorkDay>();

      try{
        System.out.println( " =-=-=-=-=- Iniciando essa maravilha -=-=-=-=-=" );
        File raw = new File("files/ponto.csv");
        File bkp = new File("files/ponto_bkp.csv");
        copyFileUsingStream(raw, bkp);
  
        CSVParser parser = CSVParser.parse(raw, Charset.forName("UTF-8"), CSVFormat.DEFAULT);
        List<CSVRecord> lines = parser.getRecords();
        for (CSVRecord c : lines) {
          if(c != lines.get(0)){
            wds.add(new WorkDay(c.get(0),c.get(1), c.get(2)));
          }
        }
  
      }catch(IOException e){
        e.printStackTrace();
      }catch(IllegalArgumentException e){
        e.printStackTrace();
      }

      return wds;
    }
  public static void main( String[] args )
  {
    ArrayList<WorkDay> wds = readHours();
    for(WorkDay w : wds){
      System.out.println(w.getBalance());
      System.out.println(w);
    }


  }
}
