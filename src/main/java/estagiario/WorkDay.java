package estagiario;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class WorkDay {
  Date in, out;
  String note;
  private SimpleDateFormat fullDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
  private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
  private SimpleDateFormat hourFormat = new SimpleDateFormat("HH:mm");


  WorkDay(String d, String i, String o, String n){
    this.in  = stringToDate(d, i);
    this.out = stringToDate(d, o);
    this.note = n;
  }
  
  Date stringToDate(String d){
    return stringToDate(d.split(" ")[0], d.split(" ")[1]);
  }
  Date stringToDate(String d, String h){
    try{

      
      Date date = (Date)fullDateFormat.parse(String.format("%s %s", d , h));
      return date;
    }catch(Exception e){
      if(this.in != null && h == ""){
        System.out.println("Hora da saida ainda não informada");
        return this.in;
      }else{

        Scanner sc = new Scanner(System.in);
        System.out.println("Escreva a data desejada com a seguinte formatacao:  ");
        System.out.println("=-=-=- dd/MM/yyyy HH:mm");
        System.out.println("=EX:=- 01/01/2020 09:01\n");
        System.out.println("");
        String data = sc.nextLine();
        sc.close();
        return stringToDate(data);
      }
    }
  }

  double getBalance(){
    long secs = ((this.out.getTime() - this.in.getTime()) / 1000) > 0 ? (this.out.getTime() - this.in.getTime()) / 1000 : (this.in.getTime() - this.out.getTime()) / 1000;
    double mins = secs / 60.00;
    return mins;
  }

  String getDay(){
    return this.dateFormat.format(this.in);
  }
  
  String getIn(){
    return this.hourFormat.format(this.in);
  }
  
  String getOut(){
    return this.hourFormat.format(this.out);
  }

  public void setOut(String o){
    this.out = stringToDate(this.dateFormat.format(this.in), o);
  }

  String getNote(){
    if(this.note != "" ) return this.note;
    return "Sem observacoes ";
  }

  public String[] exportDay(){
    return new String[]{this.getDay(), this.getIn(), this.getOut(), String.valueOf(this.getBalance()), this.getNote()};
  }

  @Override
  public String toString(){
    String str = "=-=-=-=-=-=-=-=-=-=\n";
    str += "Entrada : " + this.in + "\n";
    if(this.in.equals(this.out)){
      str += "Saida   : \n\n";
    }else{
      str += "Saida   : " + this.out + "\n\n";
    }
    return str;
  }
}