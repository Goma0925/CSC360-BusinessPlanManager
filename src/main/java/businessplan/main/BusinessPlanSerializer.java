package businessplan.main;


import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class BusinessPlanSerializer {
	
	public void encodeToXML(BusinessPlan bp, File outFile) throws FileNotFoundException {

			String filename = outFile.getAbsolutePath();
			XMLEncoder encoder=null;
			try{
			encoder=new XMLEncoder(new BufferedOutputStream(new FileOutputStream(filename)));
			}catch(FileNotFoundException fileNotFound){
				throw fileNotFound;
			};
         encoder.writeObject(bp);
         encoder.close();
	}

	public BusinessPlan decodeFromXML(File inFile) throws FileNotFoundException {
		String filename = inFile.getAbsolutePath();
	XMLDecoder decoder=null;
	try {
		decoder=new XMLDecoder(new BufferedInputStream(new FileInputStream(filename)));
	} catch (FileNotFoundException e) {
		System.out.println("ERROR: xml File not found");
	}
	BusinessPlan bp=(BusinessPlan)decoder.readObject();
	return bp;

}
}
