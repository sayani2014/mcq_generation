import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;

 class Count
{

      int no_of_word(String s)
      {
        int count = 1;
 
             for (int i = 0; i < s.length() - 1; i++)
             {
                if ((s.charAt(i) == ' ') && (s.charAt(i + 1) != ' '))
                 {
                     count++;
                     //System.out.println(count);
 
                 }
            }

            int idx = 0;
            String substring = ",/,";
            while ((idx = s.indexOf(substring, idx)) != -1)
                {
                    idx++;
                    count--;
                }
            return count-1;
      }
   
        
        int no_of_noun(String s)
        {
                String[] substring={"NN ","NNS ","NNP ","NNPS"};
                int count = 0;
      
      for(int i=0;i< substring.length ;i++) 
      {
        int idx = 0;
                
                while ((idx = s.indexOf(substring[i], idx)) != -1)
                    {
                        idx++;
                        count++;
                    }
      } 
              
             return count;
        }
        int no_of_pronoun(String s)
        {
                String[] substring={"PRP ","PRP$ ","WP ","WP$ "};
                int count = 0;
      
      for(int i=0;i< substring.length ;i++) 
      {
        int idx = 0;
                
                while ((idx = s.indexOf(substring[i], idx)) != -1)
                    {
                        idx++;
                        count++;
                    }
      } 
             return count;
        }
         





         int no_of_Discourse_Connective(String s)
        {

                String[] substring={"Since","Because","Being","But","Whemever","However","Although","As well as"};
                int count = 0;
      
      for(int i=0;i< substring.length ;i++) 
      {
        int idx = 0;
                
                while ((idx = s.indexOf(substring[i], idx)) != -1)
                    {
                        idx++;
                        count++;
                    }
      } 
              


             return count;
        }
    String getFirstWord(String text) {
    if (text.indexOf(' ') > -1) { // Check if there is more than one word.
      return text.substring(0, text.indexOf(' ')); // Extract first word.
        }
     else {
      return text; // Text is the first word itself.
        }
    }

    int compareFirstWord(String s)
    {
      String[] tag={"RB","RBR","RBS","WRB","WRB"};
      
      for(int i=0;i< tag.length ;i++) 
      {
        if(tag[i].equals(s))
            return 1;
      } 
      return 0;
    }
       
       // System.out.println("Number of words in a string = " + count);
   
}





public class LineExtractNoun1
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
                
                    int count=0;
                    for (String tag = reader.readLine(); tag != null  ; tag = reader.readLine()) {
                    
                        Count wc=new Count();
                     int totalnoun = wc.no_of_noun(tag) ;
                     int totalpronoun = wc.no_of_pronoun(tag);
                     
                     int totalwords = wc.no_of_word(tag);
                     
                     String sentence=reader.readLine();
                     int total_discourse_connective =wc.no_of_Discourse_Connective(sentence);
                     int first=wc.compareFirstWord(wc.getFirstWord(tag));

                     if(first==0 && totalnoun > 0 && total_discourse_connective <= 0 && totalpronoun<=0 && (totalwords >8 && totalwords<25) )
                     {
                       // System.out.println(line +"  ===  "+ totalnoun+"  ===  "+ totalpronoun+"  ===  "+ total_discourse_connective + " === "+totalwords);
                        writer.write( tag );
                        
                        writer.newLine();
                        
                         writer.write( sentence );
                         writer.newLine();
                         count++;
            
                     }
                    
                   

                    linenumber++;
                     
                     
                    }

                   System.out.println("Total number of lines scanned : " + linenumber);
                   System.out.println("Total number of lines extracted : " + count);

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