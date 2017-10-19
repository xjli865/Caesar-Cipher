package Caesar;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.io.*;
import static java.lang.Math.toIntExact;

public class Caesar {
	Set<String> stringSet;
	
	/**
	 * Enciphers the message
	 * @param shift Shifts each letter some number of places
	 * @param plainText Text needs to be enciphered
	 * @return Enciphered messages
	 */
	public String encipher(int shift, String plainText){
		String newWord = "";
		for(int i = 0; i < plainText.length(); i ++){
			int charNumb;
			int updatedNumb;
			if (Character.isUpperCase(plainText.charAt(i))){
				charNumb = (int) plainText.charAt(i);
				updatedNumb = charNumb + shift;
				if (updatedNumb > (int)'Z' && shift > 0 && shift <= 25){
					int diff = updatedNumb - (int)'Z';
					updatedNumb = (int)'A' + diff -1;
				}else if (updatedNumb < (int)'A' && shift < 0 && shift >= -25){
					System.out.println("negative");
					int diff = (int)'A' - updatedNumb;
					updatedNumb = (int)'Z' - diff +1;
				}else if (shift > 25 || shift < -25){
					System.out.println("The shift is out of range");
					System.exit(0);
				}
			}else if(Character.isLowerCase(plainText.charAt(i))){
				charNumb = (int) plainText.charAt(i);
				updatedNumb = charNumb + shift;
				if (updatedNumb > (int)'z' && shift <= 25){
					int diff = updatedNumb - (int)'z';
					updatedNumb = (int)'a' + diff -1;
				}else if (updatedNumb < (int)'a' && shift < 0 && shift >= -25){
					System.out.println("negative2");
					int diff = (int)'a'- updatedNumb;
					updatedNumb = (int)'z' - diff +1;
				}else if (shift > 25 || shift < -25){
					System.out.println("The shift is out of range");
					System.exit(0);
				}
			}else{
				charNumb = (int) plainText.charAt(i);
				updatedNumb = charNumb;
			}
			char newLetter = (char)updatedNumb;
			String letter = String.valueOf(newLetter);
			newWord = newWord + letter;
		}
		return newWord;
	}
	
	/**
	 * Deciphers the message
	 * @param cipheredText Text has been ciphered
	 * @return Deciphered message
	 * @throws IOException 
	 */
	public String decipher(String cipheredText) throws IOException{
		String regExp = "[,\\s/!.{}*^%$&#@()-=+%@~]+";
		String lowerCase = cipheredText.toLowerCase();
		String ss = lowerCase.replaceAll(regExp, " " );
		String[] arr = ss.split(" ");
		int shiftResults;
		List<Integer> countShift = new ArrayList<Integer>();
		stringSet = stringSet();
		for( String s : arr) {
			for(int shift = 0; shift <= 25; shift++){
				int updatedNumb = 0;
				String newWord = "";
				for(int i = 0; i < s.length(); i++ ){
					if( s.charAt(i) + shift <= (int)'z'){
						updatedNumb = s.charAt(i) + shift;
					}else if(s.charAt(i) + shift > (int)'z'){
						updatedNumb = (int)'a' + (shift - ((int)'z' - s.charAt(i)))-1;
					}
				char newLetter = (char)updatedNumb;
				String letter = String.valueOf(newLetter);
				newWord = newWord + letter;
				}
				if (stringSet.contains(newWord)){
					countShift.add(shift);	
				}
			}
		}
		Map<Integer, Long> counted = countShift.stream().
				collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
		Collection<Long> index = counted.values();
	    List<Integer> c = new ArrayList<Integer>();
	    int findMaxIndex = 0;
	    for (long i: index){
	    	int eachCount = toIntExact(i);
	        c.add(eachCount);
	     }
	    int maxCount = Collections.max(c);
	    for (int i = 0; i < c.size(); i++){
	    	if (c.get(i) == maxCount){
	    		findMaxIndex = i;
	        }
	    }
	    Set<Integer> keySet = counted.keySet();
	    List<Integer> shiftC = new ArrayList<Integer>();
	    for (Integer value: keySet){
	    	shiftC.add(value);
	    }
	    shiftResults = shiftC.get(findMaxIndex);
	    String decipheredText = encipher(shiftResults,cipheredText);
	    return decipheredText;
	}

	
	/**
	 * Creates hasSet from the words list
	 * @return A set of words
	 * @throws IOException
	 */
	public Set<String> stringSet() throws IOException{
		FileReader fileReader = null;
		String s;
		stringSet = new HashSet<String>();
		File myFile = new File("wordsEn.txt");
		try {
			fileReader = new FileReader(myFile);
		} catch (FileNotFoundException e) {
			System.err.println("LineReader can¡¯t find input file: " + fileReader);
			e.printStackTrace();
		} 
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		
		while ((s = bufferedReader.readLine()) != null) {
			stringSet.add(s.trim());
		}
		return stringSet;
	}

}
