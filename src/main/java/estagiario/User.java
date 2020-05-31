package estagiario;

import java.util.Scanner;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class User {
  Scanner sc = new Scanner(System.in);
  WorkDay workinDay;
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

  private String getInput(){
    System.out.print("Digite agora: ");
    return this.sc.nextLine();
  }
}