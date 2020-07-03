package trigram;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Util {

	public final static String SPACE = " ";
	
	public final static String NEWLINE = "NEWLINE";
	
	public static String FILEPATH = "c:/";

	/**
	 * isAlpha method to verify whether given text contains alphabets
	 * 
	 * @param text
	 * @return
	 */
	public static boolean isAlpha(String text) {
		return text != null && text.trim().length() > 0
				&& text.replaceAll(" ", "").chars().anyMatch(Character::isLetter);
	}

	/**
	 * cleanTextContent method to remove non printable characters from the given
	 * text
	 * 
	 * @param text
	 * @return
	 */
	public static String cleanTextContent(String text) {
		if (null == text) {
			return text;
		}
		// strips off all non-ASCII characters
		text = text.replaceAll("[^\\x00-\\x7F]", "");
		// erases all the ASCII control characters
		text = text.replaceAll("[\\p{Cntrl}&&[^\r\n\t]]", "");
		// removes non-printable characters from Unicode
		text = text.replaceAll("\\p{C}", "");
		return text.trim();
	}

	/**
	 * removeSpecialCharacters method to remove special characters from the given
	 * text
	 * 
	 * @param text
	 * @return
	 */
	public static String removeSpecialCharacters(String text) {
		if (null == text) {
			return text;
		}
		Pattern pattern = Pattern.compile("[^A-Z a-z0-9]");
		Matcher matcher = pattern.matcher(text);
		text = matcher.replaceAll("");
		return text;
	}

	/**
	 * getRandomNumberInRange method to get random number in range
	 * 
	 * @param min
	 * @param max
	 * @return
	 */
	public static int getRandomNumberInRange(int min, int max) {
		if (min >= max) {
			throw new IllegalArgumentException("max must be greater than min");
		}
		Random r = new Random();
		return r.nextInt((max - min) + 1) + min;
	}

	/**
	 * writeToFile method to write the given text to file
	 * 
	 * @param text
	 * @param fileName
	 */
	public static void writeToFile(String text, String fileName) {
		// Get the file reference
		if (null != text && null != fileName) {
			System.out.println("Writing to file...."+FILEPATH+ "\\" + fileName + ".txt");
			Path path = Paths.get(FILEPATH+"\\" + fileName + ".txt");
			text = text.replaceAll(NEWLINE+",", "\r\n");
			try (BufferedWriter writer = Files.newBufferedWriter(path)) {
				writer.write(text);
			} catch (IOException e) {
				System.out.println("Unable to generate the output file!" + e.getMessage());
			}catch (Exception e) {
				System.out.println("Unable to generate the output file!" + e.getMessage());
			}
			System.out.println("File generated path: c:/" + fileName + ".txt");
		}
	}
}
