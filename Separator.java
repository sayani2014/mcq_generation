import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;

 class SentenceTaggerSeparator
{ 
    String s,line,tag;
    
    SentenceTaggerSeparator(String s)
    {
        this.s=s;
      
    }
    void separate()
    {
       // System.out.println(s);
        s=s.replaceAll("/", " ");
         StringBuilder line1 = new StringBuilder();
         StringBuilder tag1 = new StringBuilder();
        String[] data = s.split(" ");
        for(int i=0;i<data.length-1;i+=2)
        {
          
            line1.append(data[i]);
            line1.append(" ");
        
          
            tag1.append(data[i+1]);
            tag1.append(" ");
            

           
        }
        line = line1.toString();
        tag = tag1.toString();

    }
    String getSentence()
    {

       return line;
    }
    String getTag()
    {
      
        return tag;
    }

   
}





public class Separator
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
                
                   // int even=1;
                    for (String line = reader.readLine(); line != null  ; line = reader.readLine()) {
                   //  if(even%2==1)
                    // {
                        SentenceTaggerSeparator st = new SentenceTaggerSeparator(line);
                        st.separate();
                        //st.getTag();
                      // System.out.println(st.getTag());
                      // System.out.println(st.getSentence());
                        writer.write( st.getTag() );
                        writer.newLine();
                        writer.write( st.getSentence() );                         
                       // writer.newLine();

                    linenumber++;
                    // }
                     
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