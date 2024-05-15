import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;

public class FileReader {
    private Scanner loadFile(String fileName)throws FileNotFoundException{
      File file=new File(fileName);
      Scanner scnr;
      scnr=new Scanner(file);
      return scnr;
    }
    public ArrayList<User> readRatingFile(String fileName) throws FileNotFoundException{
      ArrayList<User> user_array=new ArrayList<User>(); // Stores each user, returns this array once file is read
      int lineCounter=1;
      Scanner reader=loadFile(fileName);
      String[] line; // String array of the whole line
      try{ //creates final array of the whole file, any errors are thrown if an incorrect format is found
        if(!reader.hasNextLine()){
          System.err.println("Error: Blank Ratings File");
          return null;
        }
        int minUser=0;
        while(reader.hasNextLine()){
            String next=reader.nextLine();
            line=next.split(" ");
            if(next.trim().isEmpty()){
              System.err.println("Error: Invalid whitespace in Ratings File at line "+(lineCounter));
              return null;
            }
            for(int i=0;i<line.length;i++){
                if(line[i].charAt(0)<='5' && line[i].charAt(0)>='0'&& line[i].length()==1){
                    if(lineCounter==1){
                        user_array.add(new User());
                    }
                    user_array.get(i).addRating(Integer.parseInt(line[i]));
                }
                else{
                    System.err.println("Error: Invalid input in Ratings File at line "+(lineCounter)+", ratings must only be "+
                    "whole numbers from 0 to 5 and no blank lines");
                    return null;
                }
        }
        if(minUser!=0 && minUser!=line.length){
          System.err.printf("Error: Missing rating value line %d in ratings file. If user did not rate the song,"+
          " please input \'0\' for the signal value.\n",lineCounter);
          return null;
        }
        minUser=line.length;
        lineCounter++;
      }
    }
    finally{
      if(reader!=null){
        reader.close();
      }
    }
      return user_array;
  }
  //Much simpler version of readRatingsFile, as the strings dont have to be converted, and the only check is if there is a blank line
  //Otherwise the entire line is added to the list and so on until there are no more lines
  public ArrayList<Song> readNameFile(String fileName)throws FileNotFoundException{
    ArrayList<Song> songNames=new ArrayList<Song>(); 
    File input_nums= new File(fileName);
    Scanner reader=null;
    try{
        reader=new Scanner(input_nums);
        if(!reader.hasNextLine()){
          System.err.println("Error: Blank Songs File");
        }
        while(reader.hasNextLine()){
            String next=reader.nextLine();
            if(!next.trim().isEmpty()){
                songNames.add(new Song(next));
            }
            else{
                System.err.println("Error: Blank Space in Song Names file");
                return null;
            }
        }
    }finally{
      if(reader!=null){
        reader.close();
      }
    }
      return songNames;
  }
  public void writeOutput(String fileName,ArrayList<Song> songNames, ArrayList<User> users){
    try{
        FileWriter writer=new FileWriter(fileName);
        for(int i=0;i<songNames.size();i++){
            writer.write(songNames.get(i).name+" ");
            for(int j=0;j<songNames.get(0).normalizedRatings.size();j++){
              writer.write(songNames.get(i).normalizedRatings.get(j)+ " ");
            }
            writer.write("\n");
        }
        writer.close();
    }
    catch(IOException e){
        System.err.println(e.getMessage());
    }
  }
}