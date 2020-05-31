package estagiario;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

public class Manager {
  public ArrayList<WorkDay> wds = new ArrayList<WorkDay>();
  private String fileName = "files/ponto.csv";
  private String bkpFileName = "files/ponto_bkp.csv";
  private File raw = new File(this.fileName);
  private File bkp = new File(this.bkpFileName);

  private void copyFileUsingStream(File source, File dest) throws IOException {
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

  private ArrayList<WorkDay> readHours(){
    try{
      System.out.println( " =-=-=-=-=- Iniciando essa maravilha -=-=-=-=-=" );

      copyFileUsingStream(this.raw, this.bkp);

      CSVParser parser = CSVParser.parse(this.raw, Charset.forName("UTF-8"), CSVFormat.DEFAULT);
      List<CSVRecord> lines = parser.getRecords();
      for (CSVRecord c : lines) {
        if(c != lines.get(0)){
          try{
            this.wds.add(new WorkDay(c.get(0),c.get(1), c.get(2), c.get(4)));
          }catch(ArrayIndexOutOfBoundsException e){
            System.out.println("Dia sem comentário");
            this.wds.add(new WorkDay(c.get(0),c.get(1), c.get(2), ""));
          }
        }
      }

    }catch(IOException e){
      e.printStackTrace();
    }catch(IllegalArgumentException e){
      e.printStackTrace();
    }
    return wds;
  }

  public boolean addWorkDay(WorkDay wd){
    try{
      this.wds.add(0, wd);
      return true;
    }catch(Exception e){
      e.printStackTrace();
      return false;
    }
  }

  public void writer(){
    try (final FileWriter newWriter = new FileWriter(this.raw)){
      try(final CSVPrinter printer = new CSVPrinter(newWriter, CSVFormat.DEFAULT)){
        printer.printRecord("dia","entrada","saida","saldo(min)", "obs");
        for(WorkDay wd : this.wds)
          try{
            printer.printRecord(wd.exportDay());
          }catch(IOException e){
            e.printStackTrace();
            System.err.println("Falha ao escrever no arquivo");
          }

      }
    }
    catch (IOException e){
      e.printStackTrace();
    }
  }

  Manager(String fn) {
    // TODO: Tratar o informe de outro arquivo para manipulação e considerar o ponto.csv como Default.
    this.wds= readHours();

  }
}