package utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import javax.imageio.ImageIO;

import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;

public class CommonUtilities {

	public static String generateNewEmail() {//static method so that we dont have to create an object to use the methods in this class
		Date date=new Date();
		String dateString=date.toString();
		String dateStringWithoutSpaces=dateString.replaceAll("\\s", "");
		String dateStringWithoutSpacesAndColon=dateStringWithoutSpaces.replaceAll("\\:", "");
		String newemail=dateStringWithoutSpacesAndColon +"@gmail.com";
		return newemail;
		
	}
	
	public static boolean CompareScreenshots(String actualImgPath,String expectedImgPath) throws IOException {
		
			BufferedImage bufferActualImg=ImageIO.read(new File(actualImgPath));
			BufferedImage bufferExpectedImg=ImageIO.read(new File(expectedImgPath));
			ImageDiffer differ=new ImageDiffer();
			ImageDiff imageDiff= differ.makeDiff(bufferExpectedImg, bufferActualImg);
		
		    return imageDiff.hasDiff();
	}
	
	public static Properties loadPropertiesFile() {
		
		Properties prop=new Properties();
		FileReader fr;
		try {
			fr = new FileReader(System.getProperty("user.dir") + "\\src\\test\\resources\\projectdata.properties");
			prop.load(fr);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
		}
}
