import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class CS_214_Project_Tester {

CS_214_Project FP=new CS_214_Project();
  @Test
  public void testOutput() {
    String[] args={"input_files/song1","input_files/rating1","output_files/output1","5","1"};
    FP.main(args);
    String output=buildMatrixString("output_files/output1");
  }
  @Test
  public void testMoreUsers(){
    String[] args2={"input_files/song3","input_files/rating3","output_files/output2","8","161"};
    FP.main(args2);
    String output=buildMatrixString("output_files/output2");
  }
  @Test
  public void testMoreClusters(){
    String[] args2={"input_files/song3","input_files/rating3","output_files/output3","200","161"};
    FP.main(args2);
    String output=buildMatrixString("output_files/output3");
  }
  @Test
  public void testUncooperative(){
    String[] args2={"input_files/song2","input_files/rating2","output_files/output4","5","1"};
    FP.main(args2);
    String output=buildMatrixString("output_files/output4");
    assertEquals(buildMatrixString("output_files/output1"), output);
  }
  @Test
  public void testNoMatchingSongs(){
    String[] args2={"input_files/song0","input_files/rating0","output_files/output5","5","8"};
    FP.main(args2);
    String output=buildMatrixString("output_files/output5");
    assertEquals("No similar songs located within cluster ", output);
  }
  @Test
  public void testFileName(){
    String[] args2={"input_files/song0","input_files/notAValidFile","output_files/output5","5","8"};
    ByteArrayOutputStream outContent= new ByteArrayOutputStream();
    System.setErr(new PrintStream(outContent));
    FP.main(args2);
    System.setErr(System.err);
    assertEquals("Error: invalid File Name, please make sure the directory and name(s) are correct\n", outContent.toString());
  }
  @Test
  public void testUserIndex(){
    String[] args={"input_files/song0","input_files/rating0","output_files/output5","5","900000"};
    String[]args2={"input_files/song0","input_files/rating0","output_files/output5","5","8ae[p]"};
    ByteArrayOutputStream outContent= new ByteArrayOutputStream();
    System.setErr(new PrintStream(outContent));
    FP.main(args);
    assertEquals("Error: User access index 900000 exceeds user count of 10\n", outContent.toString());
    outContent.reset();
    FP.main(args2);
    System.setErr(System.err);
    assertEquals("Error: Invalid n value, must be a whole integer with no decimal\n", outContent.toString());
  }
  protected String buildMatrixString(String fileName){
    String finalString="";
    Scanner scnr=null;
    ArrayList<String> lines=new ArrayList<String>();
    try{
      scnr=new Scanner(new File(fileName));
      while(scnr.hasNextLine()){
        lines.add(scnr.nextLine());
      }
    }
    catch(FileNotFoundException e){
      System.out.println("Error in testing output");
    }
    for(int i=0;i<lines.size();i++){
      finalString+=lines.get(i)+ " ";
    }
    if(scnr!=null){
      scnr.close();
    }
    return finalString;
  }
}
