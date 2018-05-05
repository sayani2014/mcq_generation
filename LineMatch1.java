import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

 class SyntaxMatcher
{ 
   // String sub = "DT NNP" ,obj= "",verb= "";
   String REGEX= "^|^(PRP VBP VBG)|^(PRP VBP VBG DT NN)|^(NNP VBZ VBG NN)|^(NNP VBZ DT JJ NN)|^(NN VBZ DT JJ NN)|^(WP VBZ PRP$ NN)|^(PRP VBP IN NNP)|^(PRP$ NN VBZ NNP)|^(PRP VBP CD NNS JJ)|^(WRB VBP PRP VB)|^(WRB VBD PRP$ NN)";
   
    
   

     boolean matchFind( String  s)
      {
      String INPUT = s;
      Pattern p = Pattern.compile(REGEX);
      Matcher m = p.matcher(INPUT);   // get a matcher object
      int count = 0;

      while(m.find()) {
         count++;
        
            }
      if (count > 0)
      {
        return true;
      }
      else
        return  false;
      }
    
   

   
}





public class LineMatch1
{
    public static void main(String[] args)
    {

        
        try{

          
            File file =new File(args[0]);
            File ofile=new File(args[1]);

            if(file.exists()){

                FileReader fr = new FileReader(file);
                FileWriter fw= new FileWriter(ofile);
              
                 BufferedReader reader = new BufferedReader(fr);
                 BufferedWriter writer = new BufferedWriter(fw) ;

                int linenumber = 0;
                
                    for (String line = reader.readLine(); line != null  ; line = reader.readLine()) 
                    {
                       SyntaxMatcher ss = new SyntaxMatcher();
                       if(ss.matchFind(line))
                       {
                            //line=reader.readLine();

                       // writer.write( line);
                        //writer.newLine();
                        line=reader.readLine();
                         writer.write( line);
                        writer.newLine();
                       
                       }

                       else
                       {
                       line = reader.readLine();
                       }                
                        
//line=reader.readLine();
                    linenumber++;
                 
                     
                    }

                   System.out.println("Total number of lines scanned : " + linenumber);

                    reader.close();
                    writer.close();


            }else{
                 System.out.println("File does not exists!");
            }

        }catch(IOException e){
            e.printStackTrace();
        }

    }
}