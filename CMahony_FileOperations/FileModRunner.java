import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;


public class FileModRunner 
{
	FileModifier fileMod = new FileModifier();
	File theFile = new File("Students");
	public static void main(String[] args)
	{
		FileModRunner runner = new FileModRunner();
		try {
			runner.runMainMenu();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void runMainMenu() throws InterruptedException, IOException{
		String choice = "";
		boolean continueMenu = true;
		Scanner inScan = new Scanner(System.in);
		do{
			System.out.println("Welcome to the file modification program!\nEnter a choice:\n" +
					"1. Create a record in the file\n" +
					"2. Output the records\n" +
					"3. Update a record\n" +
					"4. Delete a record\n" +
					"5. Quit");
			choice = inScan.nextLine();
			if(!choice.equals("5"))
			{
				processChoice(choice,inScan);
			}
			else
			{
				continueMenu = false;
			}
		}while(continueMenu);
		System.out.println("\nGood Bye!");
		Thread.sleep(1000);
		inScan.close();
	}

	private void processChoice(String choice, Scanner inScan) throws IOException {
		if(choice.equals("1"))
		{
			runCreateRecordMenu(inScan);
		}
		else if(choice.equals("2"))
		{
			runReadRecordsMenu(inScan);
		}
		else if(choice.equals("3"))
		{
			runUpdateRecordMenu(inScan);
		}
		else if(choice.equals("4"))
		{
			runDeleteRecordMenu(inScan);
		}
		
	}

	private void runDeleteRecordMenu(Scanner inScan) throws IOException {
		String[] records = runReadRecordsMenu(inScan);
		if(records.length != 0)
		{
			System.out.println("Enter the record number to modify.");
		    int recordNumber = Integer.parseInt(inScan.nextLine());
		    if(!(recordNumber < 1) && !(recordNumber > records.length))
		    {
		    	String record = records[recordNumber - 1];
		    	fileMod.deleteRecord(record,theFile);
		    }
		    else
		    {
		    	System.out.println("No record found.");
		    }
		}
	}

	private void runUpdateRecordMenu(Scanner inScan) throws IOException {
		String[] records = runReadRecordsMenu(inScan);
		System.out.println("Enter the record number to modify.");
	    int recordNumber = Integer.parseInt(inScan.nextLine());
	    if(!(recordNumber < 1) && !(recordNumber > records.length))
	    {
	    	String record = records[recordNumber - 1];
	    	String[] details = record.split(",");
	    	System.out.println("Which would you like to change?: ");
	    	System.out.println("1. First Name: " + details[0]);
	    	System.out.println("2. Last Name: " + details[1]);
	    	System.out.println("3. Email: " + details[2]);
	    	int choice = inScan.nextInt();
	    	inScan.nextLine();
	    	System.out.println("Enter the new data (Commas will be removed): ");
	    	String change = inScan.nextLine().trim().replace(",","");
	    	String newRecord = "";
	    	if(choice == 1)
	    	{
	    		newRecord = change + ","+ details[1] + "," + details[2];
	    		fileMod.updateRecord(newRecord,record,theFile);
	    	}
	    	else if(choice == 1)
	    	{
	    		newRecord = details[0] + "," + change + "," + details[2];
	    		fileMod.updateRecord(newRecord,record,theFile);
	    	}
	    	else if(choice == 2)
	    	{
	    		newRecord = details[0] + "," + details[1] + "," + change;
	    		fileMod.updateRecord(newRecord,record,theFile);
	    	}
	    	else
	    	{
	    		System.out.println("Change not valid.");
	    	}
	    }
	    else
	    {
	    	System.out.println("No record found.");
	    }
	}

	private String[] runReadRecordsMenu(Scanner inScan) throws IOException {
		String[] records = fileMod.readAllRecords(theFile);
		if(records.length > 0)
		{
		for(int i = 0; i < records.length; i++)
		{
			System.out.println((i + 1) + ". " + records[i]);
		}
		}
		else
		{
			System.out.println("No records found.");
		}
		return records;
	}

	private void runCreateRecordMenu(Scanner inScan) throws IOException 
	{
		System.out.println("Enter first name (Commas will be ignored):");
		String fName = inScan.nextLine().trim().replace(",","");
		System.out.println("Enter last name (Commas will be ignored):");
		String lName = inScan.nextLine().trim().replace(",","");
		System.out.println("Enter email (Commas will be ignored):");
		String email = inScan.nextLine().trim().replace(",","");
		fileMod.createRecord(theFile,fName,lName,email);
	}

}
