package experiment;

import java.util.Date;

public class GenerateNewEmail {

	public static void main(String[] args) {
		System.out.println(GenerateNewEmail());

	}
	
	public static String GenerateNewEmail() {
		Date date=new Date();
		String dateString=date.toString();
		String dateStringWithoutSpaces=dateString.replaceAll("\\s", "");
		String dateStringWithoutSpacesAndColon=dateStringWithoutSpaces.replaceAll("\\:", "");
		String newemail=dateStringWithoutSpacesAndColon +"@gmail.com";
		return newemail;
		
	}

}
