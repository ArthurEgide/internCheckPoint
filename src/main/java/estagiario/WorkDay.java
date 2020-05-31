package estagiario;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class WorkDay {
  Date in, out;
  String note;

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

      SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
      Date date = (Date)formatter.parse(String.format("%s %s", d , h));
      return date;
    }catch(Exception e){
      e.printStackTrace();
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

  double getBalance(){
    long secs = ((this.out.getTime() - this.in.getTime()) / 1000) > 0 ? (this.out.getTime() - this.in.getTime()) / 1000 : (this.in.getTime() - this.out.getTime()) / 1000;
    double mins = secs / 60.00;
    return mins;
  }

  @Override
  public String toString(){
    String str = "=-=-=-=-=-=-=-=-=-=\n";
    str += "Entrada : " + this.in + "\n";
    str += "Saida   : " + this.out + "\n\n";
    return str;
  }
}