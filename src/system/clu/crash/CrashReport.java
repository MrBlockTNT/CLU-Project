package system.clu.crash;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.Calendar;

import javax.swing.JOptionPane;

import system.clu.main.CLU;

public class CrashReport
{
	public static String fileName = "";	
	public static void generateFile()
	{
		Calendar c = Calendar.getInstance();
		fileName = "crash-reports/crash_"+ c.get(Calendar.YEAR) + "-" + (c.get(Calendar.MONTH)+1) + "-" + c.get(Calendar.DAY_OF_MONTH) + "_" + c.get(Calendar.HOUR_OF_DAY) + "." + c.get(Calendar.MINUTE) + "." + c.get(Calendar.SECOND) + ".txt";
		File file = new File(fileName);
		try
		{
			file.createNewFile();
		} catch (IOException e)
		{
			CLU.error.showMessageDialog(null, "Impossible de créer le rapport de crash", "Erreur", JOptionPane.ERROR_MESSAGE);
			CrashReport.generateFile();
			   try
			   {
				   e.printStackTrace(new PrintStream(CrashReport.fileName));
			   Runtime r = Runtime.getRuntime();
			   r.exit(1);
			   } catch (FileNotFoundException e1)
			   {
				   e1.printStackTrace();
			   }
		}
	}
}
