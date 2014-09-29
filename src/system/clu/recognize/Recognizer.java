package system.clu.recognize;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.io.PrintWriter;

import javax.swing.JOptionPane;

import system.clu.crash.CrashReport;
import system.clu.main.CLU;

public class Recognizer
{
	public static int recognize(String text)
	{
		try
		{
			if(text.contains("QUI") && text.contains("TU") && text.contains("ES"))
			{
				return 1;
			}
			else if(text.contains("QUI") && text.contains("JE") && text.contains("SUIS"))
			{
				return 2;
			}
			else if(text.contains("QUI") && text.contains("RINZLER") && text.contains("EST"))
			{
				return 3;
			}
			else if(text.contains("CLOSE"))
			{
				return 4;
			}
			else if(text.contains("SAUVEGARDE"))
			{
				return 5;
			}
			else if(text.contains("INFORMATION") && (text.contains("SYSTEME") || text.contains("SYSTÈME")))
			{
				return 6;
			}
			else if(text.contains("EDITEUR HTML") || text.contains("ÉDITEUR HTML"))
			{
				return 7;
			}
			else if(text.contains("SORTIE") && (text.contains("NETTOIE") || text.contains("VIDE")))
			{
				return 8;
			}
			else if(text.contains("BONJOUR"))
			{
				return 9;
			}
			else if(text.contains("QUELLE") && text.contains("HEURE"))
			{
				return 10;
			}
			else if(((text.contains("QUELLE") && text.contains("EST")) || text.contains("DONNE")) && text.contains("DATE"))
			{
				return 11;
			}
			else if(text.contains("COMBIEN") && (text.contains("MEMOIRE") || text.contains("MÉMOIRE") && text.contains("JVM") && text.contains("UTILISE")))
			{
				return 12;
			}
			else if(text.contains("COMBIEN") && (text.contains("MEMOIRE") || text.contains("MÉMOIRE") && text.contains("JVM") && text.contains("MAX") && text.contains("APPLICATION")))
			{
				return 13;
			}
			else if(text.contains("STOP") && text.contains("PROCESS"))
			{
				return 14;
			}
			else if(text.contains("START") && text.contains("PROCESS"))
			{
				return 15;
			}
			else if(text.contains("COMBIEN") && (text.contains("MEMOIRE") || text.contains("MÉMOIRE") && text.contains("JVM") && text.contains("MAX")))
			{
				return 16;
			}
			else if(text.contains("STOP") && text.contains("JVM"))
			{
				return 17;
			}
			else if(text.contains("GARBAGE COLLECTOR"))
			{
				return 18;
			}
			else if(text.contains("COMBIEN") && text.contains("COEURS") && text.contains("JVM"))
			{
				return 19;
			}
			else if(text.contains("CMD") && text.contains("WINDOWS"))
			{
				return 20;
			}
			else 
			{
				return 2147483647;
			}
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
			CLU.error.showMessageDialog(null, "Erreur durant l'analyse", "Erreur", JOptionPane.ERROR_MESSAGE);
			return 0;
		}
	}
}
