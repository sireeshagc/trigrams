package trigram;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Sireesha G
 *
 */
public class Generator {

	private List<String> keys = null;

	private List<String> lines = null;

	private Map<String, String> trigrams = null;

	private boolean isNewLinesAllowed = false;

	public Generator(List<String> keys, List<String> lines) {
		this.keys = keys;
		this.lines = lines;
	}

	public boolean isNewLinesAllowed() {
		return isNewLinesAllowed;
	}

	public void setNewLinesAllowed(boolean isNewLinesAllowed) {
		this.isNewLinesAllowed = isNewLinesAllowed;
	}

	public List<String> getLines() {
		return lines;
	}

	public void setLines(List<String> lines) {
		this.lines = lines;
	}

	public List<String> getKeys() {
		return keys;
	}

	public Map<String, String> getTrigrams() {
		return trigrams;
	}

	/**
	 * generateKeys method to traverse the text and store pair of words as a key
	 * 
	 * @param text
	 */
	public void generateKeys(String text) {

		if (null != text && text.isEmpty() || !Util.isAlpha(text)) {
			return;
		}
		if (!this.isNewLinesAllowed()) {
			text = Util.removeSpecialCharacters(text);
			text = Util.cleanTextContent(text);
		}
		// collect all the words from the file in arraylist
		List<String> words = Arrays.stream(text.split(Util.SPACE)).map(String::trim).collect(Collectors.toList());

		int index = 0;
		while (true) {
			if (index >= words.size() - 1) {
				break;
			}
			// pair up the words and store in list
			String value = words.get(index) + Util.SPACE + words.get(index + 1);
			if (!this.getKeys().contains(value))
				this.getKeys().add(value);
			index++;
		}
	}

	/**
	 * computeTrigram method to find the next consecutive words for the given key
	 * 
	 */
	public void computeTrigram() {

		if (null != this.getKeys() && null != this.getLines() && this.getLines().size() > 0) {

			trigrams = new HashMap<String, String>();
			StringBuilder builder = new StringBuilder();
			String lines = this.getLines().toString();
			if (!this.isNewLinesAllowed()) {
				lines = lines.substring(0, lines.length() -1 );
				lines = Util.removeSpecialCharacters(lines);
				lines = Util.cleanTextContent(lines);
			} else {
				lines = lines.substring(0 + 1, lines.length() -1 );
			}
			builder.append(lines);
			System.out.println("lines " + builder.toString());
			this.getKeys().forEach(key -> {
				String line = builder.toString();
				if (line.contains(key)) {
					StringBuilder res = new StringBuilder();
					if (null != trigrams.get(key)) {
						res.append(trigrams.get(key).trim() + Util.SPACE);
					}
					String words[] = line.split(Util.SPACE);
					for (int i = 0; i < words.length; i++) {
						if (key.startsWith(words[i])) {
							if ((i + 2) < words.length) {
								if (key.endsWith(words[i + 1])) {
									res.append(words[i + 2].trim() + Util.SPACE);
								}
							}
						}
					}
					if (line.endsWith(key)) {
						res.append(getNextLineWord(this.getLines().indexOf(line)).trim() + Util.SPACE);
					}
					trigrams.put(key, res.toString());
				}
			});
			System.out.println("Total non empty Lines: " + this.getLines().size());
			System.out.println("Total keys: " + this.getKeys().size());
			this.getKeys().forEach((key1) -> {
				System.out.println(key1 + " ==> " + trigrams.get(key1));
			});
			this.generateTextWithRandomKey(); // calling method to generate text by starting with random words pair
		}

	}

	public void generateTextWithRandomKey() {
		int randomIndex = Util.getRandomNumberInRange(0, this.getKeys().size() - 1);
		Map<String, String> temp_trigrams = new HashMap<String, String>();
		temp_trigrams.putAll(trigrams);
		if (null != this.trigrams) {
			String fileName = this.getKeys().get(randomIndex) + "_" + randomIndex;
			fileName = Util.removeSpecialCharacters(fileName);
			String startWith = this.getKeys().get(randomIndex);
			System.out.println("Generating text with random key " + startWith);
			StringBuilder newText = new StringBuilder();
			boolean verifyNext = false;
			newText.append(startWith + Util.SPACE);
			do {
				if (null != temp_trigrams.get(startWith)) {
					String word = temp_trigrams.get(startWith);
					String splitWord[] = word.split(Util.SPACE);
					String textToAppend = "";
					if (splitWord.length > 1) {
						/*
						 * If key has more than one consecutive word ex: wish I => may might then read
						 * the first available word and update the temp map by removing that word so
						 * that next text search with this key will take the next word
						 */
						word = word.replaceFirst(splitWord[0], "");
						textToAppend = splitWord[0];
						newText.append(textToAppend + Util.SPACE);
						temp_trigrams.put(startWith, word.trim());
					} else {
						textToAppend = word;
						newText.append(word + Util.SPACE);
					}

					startWith = startWith.split(Util.SPACE)[1] + " " + textToAppend.trim();
					verifyNext = false;
					if (null != temp_trigrams.get(startWith)) {
						System.out.println(startWith + " ==> " + temp_trigrams.get(startWith));
						verifyNext = true;
						if (temp_trigrams.get(startWith).equals("0")) {
							// condition to break the loop if the given text is repeated ex:I wish I may I
							// wish I may
							verifyNext = false;
						}
					}

				}
			} while (verifyNext);
			System.out.println("Generated Text: " + newText.toString().replace("0", ""));
			Util.writeToFile(newText.toString().replace("0", ""), fileName);

		}
	}

	private String getNextLineWord(int index) {
		if ((index + 1) < this.getLines().size()) {
			String lineSplit[] = this.getLines().get(index + 1).split(Util.SPACE);
			for (int k = 0; k < lineSplit.length; k++) {
				if (lineSplit[k].trim().length() > 0) {
					return lineSplit[k];
				}
			}
		}
		return "0";
	}
}
