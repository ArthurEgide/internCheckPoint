package estagiario;

import java.sql.Date;

public class WorkDay {
  String in, out, day_balance;
  String note;

  WorkDay(String i, String o, String n){
    this.in = i;
    this.out = o;
    this.note = n;
  }

  @Override
  public String toString(){
    String str = "=-=-=-=-=-=-=-=-=-=\n";
    str += "Entrada : " + this.in + "\n";
    str += "Saida   : " + this.out + "\n\n";
    return str;
  }
}