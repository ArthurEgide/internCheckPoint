package estagiario;

public class App {
    
  public static void main( String[] args )
  {
    Manager manager = new Manager("Path_Alternative_File");
    WorkDay w1 = new WorkDay("10/12/2010", "13:00", "20:54", "Estava testando a paciencia da PO");
    manager.addWorkDay(w1);
    manager.writer();
  }
}
