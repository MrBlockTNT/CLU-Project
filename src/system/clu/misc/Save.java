package system.clu.misc;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;

import system.clu.crash.CrashReport;
import system.clu.main.CLU;

public class Save
{
	public static boolean isSaving = false;
	public static String answer = "";
	public static String dirIn = "";
	public static String dirOut = "";
	
	private static void copy(final InputStream inStream, final OutputStream outStream, final int bufferSize) throws IOException {
		 final byte[] buffer = new byte[bufferSize];
		 int nbRead;
		 while ((nbRead = inStream.read(buffer)) != -1) {
		 	outStream.write(buffer, 0, nbRead);
		 }
	}
	
	private static void copyDirectory(final File from, final File to) throws IOException
	{
		 if (!to.exists()) {
		 	to.mkdir();
		 }
		 final File[] inDir = from.listFiles();
		 for (int i = 0; i < inDir.length; i++) {
		 	final File file = inDir[i];
		 	copy(file, new File(to, file.getName()));
		 }
	}
	
	private static void copyFile(final File from, final File to) throws IOException 
	{ 
	      final InputStream inStream = new FileInputStream(from); 
	      final OutputStream outStream = new FileOutputStream(to); 
	      if (from.length() > 0){ 
	        copy(inStream, outStream, (int) Math.min(from.length(), 4*1024)); 
	      } 
	      else
	      {
	    	  File dir = new File(to + from.getName());
	    	  dir.createNewFile();
	      }
	      inStream.close(); 
	      outStream.close(); 
	} 
	
	public static void copy(final File from, final File to) throws IOException 
	{
		 if (from.isFile()) 
		 {
		 	copyFile(from, to);
		 } 
		 else if (from.isDirectory())
		 {
		 	copyDirectory(from, to);
		 } 
		 else 
		 {
		 	throw new FileNotFoundException(from.toString() + " does not exist" );
		 }
	} 
	
	/*public static int save(String par1, String par2) throws IOException
	 {
		 // 0 : All is OK
	     // 1 : File doesn't exists
	     // 2 : Unable to Read/Write 
	     // 3 : Incorrect output
	     // 4 : Not a directory
	     File input = new File(par1);
	     File output = new File(par2);
	     if(input.exists() && output.exists())
	     {
	    	 if(output.canWrite() && input.canRead())
	    	 {
	    		 if(!output.isFile())
	    		 {
	    			 if(input.isDirectory() && output.isDirectory())
	    			 {
	    			 final File[] inDir = input.listFiles();
	    			 for (int i = 0; i < inDir.length; i++) 
	    			 {
	    			 	final File file = inDir[i];
	    			 	copy(file, new File(output, file.getName()));
	    			 }
	    				 return 0;
	    			 }
	    			 else
	    			 {
	    				 return 4;
	    			 }
	    		 	}
	    		 	else
	    		 	{
	    		 		return 3;
	    		 	}
	    	 	}
	    	 	else
	    	 	{
	    	 		return 2;
	    	 	}
	     	}
	     	else
	     	{
	     		return 1;
	     	}
	 }*/
	    
	public static void doSave()
	{
		ThreadSave tsave = new ThreadSave();
		tsave.start();
		
		/*int result = 5;
		try
		{
			result = save(dirIn, dirOut);
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		switch(result)
		{
			case 0 : 
				CLU.out("Tout s'est déroulé correctement !");
				break;
			case 1 :
				CLU.out("Le répertoire d'entrée et/ou le répertoire de sortie n'existent pas.");
				break;
			case 2 :
				CLU.out("Impossible de Lire/Écrire depuis/vers les répertoires spécifiés.");
				break;
			case 3:
				CLU.out("Le chemin de sortie n'est pas un fichier.");
				break;
			case 4:
				CLU.out("Les chemins d'entrée/sortie ne sont pas des répertoires.");
				break;
			default:
				CLU.error.showMessageDialog(null, "ERREUR", "Erreur", JOptionPane.ERROR_MESSAGE);
		}*/
		dirIn = "";
		dirOut = "";
		isSaving = false;
	}
}

class ThreadSave extends Thread
{
    @Override
    public void run()
    {
    	try
		{
			Save.copy(new File(Save.dirIn), new File(Save.dirOut));
		}
		catch(Exception e)
		{
			CrashReport.generateFile();
			   try
			   {
				   e.printStackTrace(new PrintStream(CrashReport.fileName));
			   } catch (FileNotFoundException e1)
			   {
				   e1.printStackTrace();
			   }
		}
    	CLU.out("[Sauvegarde] Tout s'est déroulé correctement !");
    	this.stop();
    }
}
