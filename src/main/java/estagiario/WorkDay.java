package estagiario;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class WorkDay {
  Date in, out;
  String note;

  WorkDay(String i, String o, String n){
    this.in  = stringToDate(i);
    this.out = stringToDate(o);
    this.note = n;
  }

  Date stringToDate(String d){
    try{

      SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
      Date date = (Date)formatter.parse(d);
      return date;
    }catch(Exception e){
      e.printStackTrace();
      Scanner sc = new Scanner(System.in);
      System.out.println("Escreva a data desejada com a seguinte formatacao:  ");
      System.out.println("=-=-=- HH:mm:ss");
      System.out.println("=EX:=- 09:01:00\n");
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