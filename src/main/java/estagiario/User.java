package estagiario;

import java.util.Scanner;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class User {
  Scanner sc = new Scanner(System.in);
  WorkDay workinDay;
  Manager manager;
  private SimpleDateFormat fullDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
  private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
  private SimpleDateFormat hourFormat = new SimpleDateFormat("HH:mm");

  void close(){
    this.sc.close();
  }
  
  public void startWork(){
    System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
    System.out.println("Tenha um excelente dia de trabalho meu(a) consagrado(a) !!!");
    System.out.println("Começou a trabalhar agora? [s/n]");
    
    if(getInput().toLowerCase().equals("s")){
      System.out.println("Começou a trabalhar agora!!");
      Date date = new Date();
      System.out.println(fullDateFormat.format(date).toString());
      this.workinDay = new WorkDay( dateFormat.format(date).toString().strip(), hourFormat.format(date).toString().strip(), "" , "");
    }else{
      try{
        System.out.println("Qual horario começou a trabalhar? [hh:mm]");
        Date today = new Date();
        Date date = (Date)fullDateFormat.parse(String.format("%s %s", dateFormat.format(today) , getInput()));
        this.workinDay = new WorkDay( dateFormat.format(date).toString(), hourFormat.format(date).toString(), "" , "");
      } catch(ParseException e){
        e.printStackTrace();
      }
    }
    System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
  }

  public void stopWork(){
    System.out.println("Stopping work");
    for( WorkDay wd : this.manager.wds ){
      if(wd.getIn().equals(wd.getOut())){
        System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
        System.out.println("Falta informação sobre este dia.");
        System.out.println(wd);
        System.out.println("Qual horario terminou de trabalhar? [hh:mm]");
        System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n\n");
        wd.setOut(getInput());
      }
    }
  }

  public boolean begin(){
    System.out.println("Registrar entrada? [s/n]");
    
    if(getInput().toLowerCase().equals("s")){
      this.startWork();
      return true;
    }else{
      this.stopWork();
      return false;
    }
  }

  private String getInput(){
    System.out.print("Digite agora: ");
    return this.sc.nextLine();
  }

  User(Manager mn){
    this.manager = mn;
  }
}