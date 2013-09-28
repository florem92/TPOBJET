/**package test
 import java.io.*;


public class Image2 {
	private static double [] imagechargee;
	private static double [] imagesauvee;
	//largeur et hauteur de l'image
	public static int width;
	public static int height;

	//definir la taille de l'image
	
	public static createImage (int w, int h){
		width=w;
		height=h;
		imagechargee=new double [w*h];
		imagesauvee=new double[w*h];
	}
	public static void transfert{
		for ( int i = 0 ; i < width * height ; ++i )
		{
		    imagechargee[ i ] = imagesauvee[ i ] ;
		}
	}

}
*/



	
    /**public Image()
    {
        pixels = new int[1500][1500];
        depth = width = height = 0;
    }

    public Image(int inDepth, int inWidth, int inHeight)
    {
        pixels = new int[inWidth][inHeight];
        width = inWidth;
        height = inHeight;
        depth = inDepth;
    }

    public static void LirePGM(String fileName)
    {
        String ligne;
        StringTokenizer st;

        try {
            BufferedReader in =
              new BufferedReader(new InputStreamReader(
                new BufferedInputStream(
                  new FileInputStream(fileName))));

            DataInputStream in2 =
              new DataInputStream(
                new BufferedInputStream(
                  new FileInputStream(fileName)));

            // lecture de l'en-tête PGM 
            ligne = in.readLine();
            in2.skip((ligne+"\n").getBytes().length);
            do {
                ligne = in.readLine();
                in2.skip((ligne+"\n").getBytes().length);
            } while (ligne.charAt(0) == '#');

            // les dimensions de la ligne 
            st = new StringTokenizer(ligne);
            width = Integer.parseInt(st.nextToken());
            height = Integer.parseInt(st.nextToken());

            // la ligne suivante
            ligne = in.readLine();
            in2.skip((ligne+"\n").getBytes().length);
            st = new StringTokenizer(ligne);
            depth = Integer.parseInt(st.nextToken());

            // lire les pixels
            for (int y = 0; y < height; y++)
                for (int x = 0; x < width; x++)
                    pixels[x][y] = in2.readUnsignedByte();
            
            in.close();
            in2.close();
        } catch(ArrayIndexOutOfBoundsException e) {
            System.out.println("Error: image in "+fileName+" too big");
        } catch(FileNotFoundException e) {
            System.out.println("Error: file "+fileName+" not found");
        } catch(IOException e) {
            System.out.println("Error: end of stream encountered when reading "+fileName);
        }
    }

    public void EcrirePGM(String fileName)
    {
        String ligne;
        StringTokenizer st;
        int i;

        try {
            DataOutputStream out =
              new DataOutputStream(
                new BufferedOutputStream(
                  new FileOutputStream(fileName)));

            out.writeBytes("P5\n");
            out.writeBytes(width+" "+height+"\n255\n");

            for (int y = 0; y < height; y++)
                for (int x = 0; x < width; x++)
                    out.writeByte((byte)pixels[x][y]);

            out.close();
        } catch(IOException e) {
            System.out.println("ERROR: cannot write output file");
        }
    }
}
/**import java.awt.Container;
import java.io.*;
import java.util.*;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import javax.swing.JFrame;

public class Image extends JFrame{

	
	private static final long serialVersionUID = 1L;
	public static int pixelx[]= new int[256];
	private BufferedReader fichx;    

	public static void main(String[] args) 
	{
		
		PrintWriter pw = null;
		int tab[]= new int [256];
		
		//Initialisation de tab à {0}
		for (int i=0; i<256;i++){
			tab[i]=0;
		}
		
		//Lecture du fichier pgm
	     Path file = FileSystems.getDefault().getPath("src", "lena.pgm");
		try (InputStream in = Files.newInputStream(file);
		    BufferedReader reader = new BufferedReader(new InputStreamReader(in))) 
		    {
		    	String line = null;
		    	while ((line = reader.readLine()) != null) 
		    	{
		    		char[] charArray = line.toCharArray();
		    		System.out.println(line);
		    	}
		    } catch (IOException x) 
		    {
		    System.err.println(x);
		    }


		
		//Ecriture dans le fichier lena.pgm
		  try 
		  {
		     File file1 = new File("lena.pgm");
		     FileWriter fw = new FileWriter(file1, true);
		     pw = new PrintWriter(fw);
		     pw.println("Fichier ouvert!");
		  } 
		  catch (IOException e) 
		  {
		     e.printStackTrace();
		  } 
		  finally
		  {
		     if (pw != null) 
		     {
		        pw.close();
		     }
		     else
		     {
		    	 histogramme(file1);
		     }
		  }
		  
	}

		//Creation de l'histogramme
		  public static void histogramme (File file)
		  {
			  try
			  {
				int vxy=0;
				for(int k=0 ; k<=255;k++ )
				{    
			     pixelx[k]=0;    
			    }
				String ligne;
				StringTokenizer st;
				     
				String lignex;
				StringTokenizer stx;
				int cmp=0;
				int cmpx=0;
				BufferedReader fich=new BufferedReader(new FileReader(file));
				while ((ligne=fich.readLine())!=null)
				{
					st=new StringTokenizer(ligne," ");
			    	while(st.hasMoreTokens())
			    	{
			    		String mot=st.nextToken();
			    		cmpx++;
			    	}
			    }
				fich.close();
		        int pixels[]= new int[cmpx];    
		        fichx = new BufferedReader(new FileReader(file));
			    lignex=fichx.readLine();
			    lignex=fichx.readLine();
			    lignex=fichx.readLine();
			    lignex=fichx.readLine();
			    
				    
				        
			    while ((lignex=fichx.readLine())!=null)
		       	{
		    	   stx=new StringTokenizer(lignex," ");
		    	   while(stx.hasMoreTokens())
		    	   {
		    		   String    mot=stx.nextToken();
				       pixels[cmp]=Integer.parseInt(mot);
				       vxy=pixels[cmp];
				       pixelx[vxy]=pixelx[vxy]+1;
				        
				       cmp++;
		    	   }
	       		}
			    
	
			  }
		    catch(IOException ex)
		    {
		        System.out.println(ex);
		    }   
			  
		     catch(NumberFormatException ey)
		     {
		     }
		     
		     Imaga ima=new Imaga();
		     ima.setVisible(true);
	}
		  // affichage
		  class Imaga  extends JFrame
		  {
		           Cartesien pes;
		      Container cont;
		  public Imaga()
		  {
		      
		      
		    this.setBounds(300,30,268,630);
		     this.setTitle("Histogramme Image");
		    getContentPane().setBackground(SystemColor.white);
		    cont=this.getContentPane();
		    cont.setLayout(null);
		    pes = new Cartesien();
		    pes.setBounds(0,00,401,600);
		    cont.add(pes);
		      
		  }    
		      
		  }

}
*/