package test;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Image
{

public static void main(String[] args) throws FileNotFoundException
{
	int pixel,largeur,hauteur,max;
	int[] histogramme;
	File fichier=null;

	
	//Lecture le fichier
	fichier =new File("/Users/Floremassoullie1/Documents/Workspace/TP_27_09_2013_vf/lena.pgm");
	System.out.println(fichier.getName());
	try
	{
		Scanner scan=new Scanner(fichier);
		try
		{
			scan.nextLine(); //ligne indiquant P2
			scan.nextLine(); // ligne #
			largeur = scan.nextInt(); // Lecture de la largeur
			hauteur = scan.nextInt(); // Lecture de la hauteur
			max = scan.nextInt(); // Lecture de la valeur max en niveaux de gris
		
			int[][] valeurs = new int[hauteur][largeur];
			histogramme = new int[max+1] ;
		
			// On lit tous les pixels
			for (int i=0;i<hauteur;i++) {
				for (int j=0;j<largeur;j++) {
					pixel = scan.nextInt();
					
					System.out.println("Precedent:"+histogramme[pixel]);
					histogramme[pixel] = histogramme[pixel] + 1;
					System.out.println("Suivant:"+histogramme[pixel]);
					valeurs[i][j] = pixel;
				}
			}
			
		
			// Ecriture de l'histogramme dans un fichier sortie.pgm 
			FileWriter fichierEcrit = null;
			try 
			{
				fichierEcrit = new FileWriter("/Users/Floremassoullie1/Documents/Workspace/TP_27_09_2013_vf/ecrit.pgm");
				fichierEcrit.write("P2\n");
				fichierEcrit.write("70 " + Integer.toString(max)+"\n");
				fichierEcrit.write("255\n");
				int maximum = 0;
				for (int i=0;i<max;i++) 
				{ 
					if (histogramme[i] > maximum) {
						maximum = histogramme[i];
					}
				}
				for (int i=0;i<largeur;i++) { // à partir d'ici, il faudrait écrire l'histogramme. Brouillon.
					for (int j=1;j<=hauteur;j++) {
						if(j < (histogramme[i]*hauteur/maximum)) {
							fichierEcrit.write("255");
						}
						else {
							fichierEcrit.write("0");
						}
						fichierEcrit.write("\n");
					}
				}
				fichierEcrit.close();
		
				} 
		catch (IOException e) 
		{
		e.printStackTrace();
		}

		scan.close(); // Fermeture du flux de lecture de fichier
		System.out.println("Fin du programme !");
	}
	catch (NoSuchElementException e) 
	{
	e.printStackTrace();
	System.out.println("Erreur lors de l'import du fichier...");
	System.out.println("Fin du programme : ERREUR !");
	e.printStackTrace();
	}
	}
catch(FileNotFoundException e) 
{
System.out.println("Erreur lors de l'import du fichier");
e.printStackTrace();
}
}
}