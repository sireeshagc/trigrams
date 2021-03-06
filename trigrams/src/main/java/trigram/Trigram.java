package trigram;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Sireesha G
 *
 */
public class Trigram {

	/**
	 * readInput method to read the given file data.
	 * 
	 * @param fileName
	 */
	public List<String> readInput(String fileName) {

		List<String> lines = new ArrayList<String>();

		try (BufferedReader br = Files.newBufferedReader(Paths.get(fileName))) {
			br.lines().forEach(line -> {
				if (line.trim().length() > 0) {
					line = line.toLowerCase(); // assuming words are not case-sensitive
					line = Util.removeSpecialCharacters(line);
					lines.add(Util.cleanTextContent(line));
				}
			});
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("Unable to read the input file!");
		}
		return lines;

	}

	public List<String> readInputWithNewLines(String fileName) {

		List<String> lines = new ArrayList<String>();

		try (BufferedReader br = Files.newBufferedReader(Paths.get(fileName))) {
			br.lines().forEach(line -> {
					if(line.contains("\r\n") || line.trim().length() == 0) {
						lines.add(Util.NEWLINE);
					}else {
						lines.add(line);
					}
			});
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("Unable to read the input file!");
		}
		return lines;

	}
	
	/**
	 * generateTrigrams method to generate text from random key generated by trigrams.
	 * @param fileName
	 */
	public void generateTrigrams(String fileName, String withNewLines) {
		System.out.println("Start Task..");
		Generator generate = new Generator(new ArrayList<String>(), new ArrayList<String>());
		if (withNewLines.equals("0") || withNewLines.isEmpty()) {
			generate.setNewLinesAllowed(false);	
		} else {
			generate.setNewLinesAllowed(true);
		}
		// set all lines of file to a list
		List<String> lines = (generate.isNewLinesAllowed() ? readInputWithNewLines(fileName) : 
			readInput(fileName));
		generate.setLines(lines);
		// generateKeys
		String text = lines.toString();
		generate.generateKeys(text.substring(0, text.length() - 1));
		// compute trigram
		generate.computeTrigram();
		System.out.println("End of Task!");
	}

	public static void main(String args[]) {

		if (args.length < 3) {
			System.out.println("No input to process!");
			System.out.println("Try running by giving the file path as argument. ex: java Trigram c:\\inputfile.txt 1 c:\\outputfolder");
		} else {
			if (null == args[1]) {
				args[1] = "0";
			}
			Util.FILEPATH = args[2];
			new Trigram().generateTrigrams(args[0], args[1]);
		}
	}
}
