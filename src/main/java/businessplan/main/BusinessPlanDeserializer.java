package businessplan.main;
import java.beans.XMLDecoder;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class BusinessPlanDeserializer {	

	
	public void encodeToXML(File inFile) throws FileNotFoundException {
    String filename = inFile.getAbsolutePath();
		XMLDecoder decoder=null;
		try {
			decoder=new XMLDecoder(new BufferedInputStream(new FileInputStream(filename)));
		} catch (FileNotFoundException e) {
			System.out.println("ERROR: xml File not found");
		}
		BusinessPlan bp=(BusinessPlan)decoder.readObject();
		System.out.println(bp);

	}
}
