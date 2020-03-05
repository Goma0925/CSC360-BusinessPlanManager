package businessplan.main;


import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.File;
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

}
