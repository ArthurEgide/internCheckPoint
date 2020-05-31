package estagiario;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
  WorkDay w1 = new WorkDay("01/01/2020", "09:00", "10:00", "test_case");
  WorkDay w2 = new WorkDay("02/01/2020", "09:05", "12:07", "test_case");
  WorkDay w3 = new WorkDay("03/01/2020", "09:00", "06:20", "test_case");
  WorkDay w4 = new WorkDay("04/01/2020", "09:00", "21:00", "test_case");
  
  @Test
  public void shouldPerfectHour(){
    assertEquals("Diferença de 01:00", 60,  w1.getBalance(), 0.00);
    assertEquals("Diferença de 12:00", 12*60,  w4.getBalance(), 0.00);
  }

  
  @Test
  public void shouldFractionHour(){
    assertEquals("Diferença de 03:02", ((3*60) + 02), w2.getBalance(), 0.00);
    assertEquals("Diferença de 02:40", ((2*60) + 40), w3.getBalance(), 0.00);
  }


}
