package trigrams;

import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import trigram.Generator;
import trigram.Util;

public class TrigramTest {

	@Test
	public void testreadInput_withOutSpecialCharacters_file1() {
		List<String> lines = readTestInput("testfile1.txt");
		Generator op = new Generator(new ArrayList<String>(), new ArrayList<String>());
		op.setNewLinesAllowed(false);
		op.setLines(lines);
		assertTrue(op.getLines().size() > 0);
		op.generateKeys(Util.removeSpecialCharacters(op.getLines().toString()));
		assertTrue(op.getKeys().size() > 0);
		op.computeTrigram();
		assertTrue(op.getTrigrams().size() > 0);
	}
	
	@Test
	public void testreadInput_withCharacters_withNewLineChars_file1() {
		List<String> lines = readInputWithNewLines("testfile1.txt");
		Generator op = new Generator(new ArrayList<String>(), new ArrayList<String>());
		op.setNewLinesAllowed(true);
		op.setLines(lines);
		assertTrue(op.getLines().size() > 0);
		op.generateKeys(Util.removeSpecialCharacters(op.getLines().toString()));
		assertTrue(op.getKeys().size() > 0);
		op.computeTrigram();
		assertTrue(op.getTrigrams().size() > 0);
	}
	
	private List<String> readTestInput(String fileName) {
		java.net.URL url = TrigramTest.class.getResource("src/test/resources/"+ fileName);
		List<String> lines = new ArrayList<String>();

		String path = "src/test/resources/"+fileName;
		 
		File file = new File(path);
		String absolutePath = file.getAbsolutePath();
		
		try (BufferedReader br = Files.newBufferedReader(Paths.get(absolutePath))) {
			br.lines().forEach(line -> {
				if (line.trim().length() > 0) {
					line = line.toLowerCase(); // assuming words are not case-sensitive
					line = Util.removeSpecialCharacters(line);
					lines.add(Util.cleanTextContent(line));
				}
			});
		} catch (IOException e) {
			e.printStackTrace();
		}  catch (Exception e) {
			System.out.println("Unable to read the input file!");
		}
		return lines;
	}
	private List<String> readInputWithNewLines(String fileName) {

		java.net.URL url = TrigramTest.class.getResource("src/test/resources/"+ fileName);
		String path = "src/test/resources/"+fileName;
		File file = new File(path);
		String absolutePath = file.getAbsolutePath();
		
		List<String> lines = new ArrayList<String>();

		try (BufferedReader br = Files.newBufferedReader(Paths.get(absolutePath))) {
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
}
