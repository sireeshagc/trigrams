package trigrams;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import trigram.Util;

public class UtilTest {

	@Test
	public void testisAlpha_WithNoText() {
		assertFalse(Util.isAlpha(null));
		assertFalse(Util.isAlpha(""));
	}
	
	@Test
	public void testisAlpha_WithTextSplChars() {
		
		String paragraph = "Trigram analysis is very simple. "
				+ "Look at each set of three adjacent words in a document. "
				+ "Use the first two words of the set as a key, and remember the fact that the "
				+ "third word followed that key. Once you’ve finished, you know the list "
				+ "of individual words that can follow each two word sequence in the document.";
		
		assertTrue(Util.isAlpha("quick test"));
		
		assertTrue(Util.isAlpha("the quick red fox jumps over the lazy :) brown dog..."));
		
		assertTrue(Util.isAlpha(paragraph));
	}
	
	@Test
	public void testisAlpha_WithNoCharacters() {
		assertTrue(Util.isAlpha("I K L H U I M N O P Q R S"));
		
		assertTrue(Util.isAlpha("1 K 9 L 8 P a u i 9"));
	}
	
	@Test
	public void testisAlpha_WithNoAlphabets() {
		assertFalse(Util.isAlpha("1 23 4 5 67 89"));
	}
}
