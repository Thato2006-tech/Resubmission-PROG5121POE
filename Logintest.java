/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Oratile_Dell
 */
public class Logintest {
    
    Login login = new Login();
    
   @Test
   public void testUsername_FormatCorrect() {
       assertTrue(login.checkUserName("ky1_1"));
     
   }
   
   @Test
   public void testUsername_FormatIncorrectly() {
       assertFalse(login.checkUserName("kyle!!!!"));
       
   }
   
   @Test
   public void testPassword_RequirementsMeet() {
       assertTrue(login.checkPassword("Ch&&sec@ke99"));

   }
   
   @Test
   public void testPassword_RequirementsNotMet() {
       assertFalse(login.checkPassword("password"));
       
   }
   
   @Test
   public void testPhone_PlacedCorrectly() {
       assertTrue(login.checkPhone("+27838968976"));
       
   }
   
   @Test
   public void testPhone_PlacedIncorrectly() {
       assertFalse(login.checkPhone("0838968976"));
   }
}
   
    
    

