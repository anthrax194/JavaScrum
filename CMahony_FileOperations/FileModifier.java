import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class FileModifier {

	public void createRecord(File theFile, String fName, String lName,
			String email) throws IOException {
		String record = fName + "," + lName + "," + email + "\n";
		if(!theFile.exists())
		{
			theFile.createNewFile();
			System.out.println("The file doesn't exist!");
		}
		FileWriter fw = new FileWriter(theFile,true);
		
		fw.write(record);
		fw.flush();
		fw.close();
		System.out.println("Write Successful.");
	}

	public String[] readAllRecords(File theFile) throws IOException {
		ArrayList<String> recordList = new ArrayList<String>();
		if(!theFile.exists())
		{
			 theFile.createNewFile();
		}
		FileReader fr = new FileReader(theFile);
		BufferedReader br = new BufferedReader(fr);
		Scanner fileScan = new Scanner(br);
		while(fileScan.hasNext())
		{
			String record = fileScan.nextLine();
			recordList.add(record);
		}
		fileScan.close();
		String[] records = new String[recordList.size()];
		for (int i = 0; i < recordList.size(); i++) {
			records[i] = recordList.get(i);
		}
		return records;
	}

	public void updateRecord(String newRecord, String oldRecord, File theFile) throws IOException {
		if(!theFile.exists())
		{
			 theFile.createNewFile();
		}
		FileReader fr = new FileReader(theFile);
		BufferedReader br = new BufferedReader(fr);
		Scanner fileScan = new Scanner(br);
		String theStringFile = "";
		while(fileScan.hasNext())
		{
			theStringFile += fileScan.nextLine() + "\n";
		}
		fileScan.close();
		theStringFile = theStringFile.replace(oldRecord, newRecord);
		theFile.delete();
		theFile = new File(theFile.getName());
		theFile.createNewFile();
		FileWriter fw = new FileWriter(theFile);
		fw.write(theStringFile);
		fw.flush();
		fw.close();
		System.out.println("Update Successful");
	}

	public void deleteRecord(String record, File theFile) throws IOException {
		if(!theFile.exists())
		{
			 theFile.createNewFile();
		}
		FileReader fr = new FileReader(theFile);
		BufferedReader br = new BufferedReader(fr);
		Scanner fileScan = new Scanner(br);
		String theStringFile = "";
		while(fileScan.hasNext())
		{
			theStringFile += fileScan.nextLine() + "\n";
		}
		fileScan.close();
		theStringFile = theStringFile.replace(record + "\n", "");
		theFile.delete();
		theFile = new File(theFile.getName());
		FileWriter fw = new FileWriter(theFile);
		fw.write(theStringFile);
		fw.flush();
		fw.close();
		System.out.println("Delete Successful");
		
	}

}
