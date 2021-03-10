


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author TOSHIBA
 *
 **/
public class MultiMediaLZW {
    
         public static void compression(String Text)
        {
               //String Text;
               int index=0;    //index in dictionary
               int J=0;        //use in search
               String TestWord="";
               ArrayList<String    >  Dictionary     = new ArrayList <String    > ();
               ArrayList<Integer   >  Compressed     = new ArrayList <Integer   > ();
               
               for(int i=0; i<128; i++)
               {
                   char s=(char) i;
                   Dictionary.add(""+s);
               }

//               Scanner input = new Scanner(System.in);
//               System.out.println("Please Enter Text :");
//               Text= input.next();

               try    //Put Data In File "Data.Txt"
               {
                   FileWriter FOut =new FileWriter("Data.txt");
                   FOut.write(Text);
                   FOut.close();
               }
               catch (Exception ex)
               {
                   System.out.println("Error Message");
               }
           
         
               for(int i =0 ;i<Text.length() ;i++)
               {
                       TestWord+=Text.charAt(i);
                       for ( int j=0 ; j<Dictionary.size(); j++)   
                       {
                           if(TestWord.equals(Dictionary.get(j)))
                           {
                               index =j;
                               J=1;
                               break;
                           }
                           
                           else 
                           {
                               J=0;
                           }
                       }
                       if((i==Text.length()-1) && (J==1))        //to candel final index 
                       {
                           Compressed.add(index);
                       }
                       else if(J==0)
                       {
                            Compressed.add(index);
                            Dictionary.add(TestWord);
                            TestWord="";
                            i=i-1;
                        }
               
                    }

               
               
               try    //Put Compressed In File "Compressed.Txt"
               {
                   FileWriter FOut =new FileWriter("Compressed.txt");
                   for(int i=0; i<Compressed.size() ;i++)
                   {
                       String COMPRESSION=""+(Compressed.get(i));
                       FOut.write(Compressed.get(i));
                   }
                   
                   FOut.close();
               }
               catch (Exception ex)
               {
                   System.out.println("Error Message");
               }
               


               System.out.println("*************Dictionary compression***********");
               for(int p=0 ; p<Dictionary.size(); p++)
               {

                   System.out.println(p+"----->"+Dictionary.get(p));
               }
               
               System.out.println("*****************compression******************");
               for(int p=0 ; p<Compressed.size(); p++)
               {

                   System.out.print(Compressed.get(p)+" ");
               }

        }
         public static void Decompression()
            {
                ArrayList <Integer>  Compressed   = new ArrayList <Integer>();
                ArrayList <String >  DeDictionary = new ArrayList <String >();
                ArrayList <String >  Data         = new ArrayList <String >();
                String Temp = null ;                        // Prevoius
                 for(int i=0; i<128; i++)
               {
                   char s=(char) i;
                   DeDictionary.add(""+s);
               }
                
                try    //read Compressed from File "Compressed.Txt"
                    {
                        int pointer=0;
                        File fileName =new File("Compressed.txt");
                        FileReader fileReader =  new FileReader(fileName);
                        BufferedReader bufferedReader =  new BufferedReader(fileReader);
                        while((pointer = bufferedReader.read()) != -1)
                           Compressed.add(pointer);
                    }
                catch (Exception ex)
                      {
                        System.out.println("*-*Error Message");
                      }
                for (int i=0 ;i<Compressed.size(); i++ )
                {
                    if(Compressed.get(i)>=DeDictionary.size())   // if not found in dictionary 
                    {
                        String w;
                        w= Temp + Temp.charAt(0);
                        DeDictionary.add(w);
                        Data.add(w);
                        Temp=w;
                    }
                    else if (i==0)
                    {
                        Data.add(DeDictionary.get(Compressed.get(i)));  //to get the place of index "compressed" from dic
                        Temp= DeDictionary.get(Compressed.get(i));
                    }
                    else 
                    { 
                        String w;
                        Data.add(DeDictionary.get(Compressed.get(i)));  //to get the place of index "compressed" from dic
                        w= Temp + DeDictionary.get(Compressed.get(i)).charAt(0) ; 
                        DeDictionary.add(w);
                        Temp= DeDictionary.get(Compressed.get(i));
                    }
                }
                
                System.out.println("*************DeDictionary compression***********");
               for(int p=0 ; p<DeDictionary.size(); p++)
               {

                   System.out.println(p+"----->"+DeDictionary.get(p));
               }
               
               System.out.println("\n*****************Data******************");
               for(int p=0 ; p<Data.size(); p++)
               {

                   System.out.print(Data.get(p));
               }

            }
               
        
         
    public static void main(String[] args) {
//        compression();
//        Decompression();
              LZW k=new LZW();
              k.setVisible(true) ;
    }
    
}
