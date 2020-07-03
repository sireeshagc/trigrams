package trigrams;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

import trigram.Generator;

public class GeneratorTest {

	@Test
	public void testgenerateKeys_withOutSplChars_withNoInputText () {
		
		Generator op = new Generator(new ArrayList<String>(), new ArrayList<String>());
		op.setNewLinesAllowed(false);
		op.generateKeys(null);
		assertTrue(op.getKeys().size() == 0);
		
		op.generateKeys("");
		assertTrue(op.getKeys().size() == 0);
		
		op.generateKeys("                                                 ");
		assertTrue(op.getKeys().size() == 0);
	}
	
	@Test
	public void testgenerateKeys_withSplChars_withNewLineChars_withNoInputText () {
		
		Generator op = new Generator(new ArrayList<String>(), new ArrayList<String>());
		op.setNewLinesAllowed(true);
		op.generateKeys(null);
		assertTrue(op.getKeys().size() == 0);
		
		op.generateKeys("");
		assertTrue(op.getKeys().size() == 0);
		
		op.generateKeys("                                                 ");
		assertTrue(op.getKeys().size() == 0);
	}
	
	@Test
	public void testgenerateKeys_withOutSplChars_withInvalidText () {
		Generator op = new Generator(new ArrayList<String>(), new ArrayList<String>());
		op.setNewLinesAllowed(false);
		op.generateKeys("1 2 3 4 5 6 7 8 9 33 44 55");
		assertTrue(op.getKeys().size() == 0);
		
		op.generateKeys("@ # $ % ^ & * (% # @");
		assertTrue(op.getKeys().size() == 0);
		
		op.generateKeys("1111111111111111112222222222222222333333333333 44444444444444444");
		assertTrue(op.getKeys().size() == 0);
	}
	
	@Test
	public void testgenerateKeys_withSplChars_withNewLines_withInvalidText () {
		Generator op = new Generator(new ArrayList<String>(), new ArrayList<String>());
		op.setNewLinesAllowed(true);
		op.generateKeys("1 2 3 4 5 6 7 8 9 33 44 55");
		assertTrue(op.getKeys().size() == 0);
		
		op.generateKeys("@ # $ % ^ & * (% # @");
		assertTrue(op.getKeys().size() == 0);
		
		op.generateKeys("1111111111111111112222222222222222333333333333 44444444444444444");
		assertTrue(op.getKeys().size() == 0);
	}
	
	@Test
	public void testgenerateKeys_withOutSplChars_TextWithNoSpace () {
		Generator op = new Generator(new ArrayList<String>(), new ArrayList<String>());
		op.setNewLinesAllowed(false);
		op.getLines().add("iwishimayiwishimayimight");
		op.generateKeys("iwishimayiwishimayimight");
		assertTrue(op.getKeys().size() == 0);
		
		Generator op1 = new Generator(new ArrayList<String>(), new ArrayList<String>());
		op1.setNewLinesAllowed(false);
		op1.getLines().add("ready#to$test%words*test*is^fun@right!");
		op1.generateKeys("ready#to$test%words*test*is^fun@right!");
		System.out.println("t : " + op1.getKeys());
		assertTrue(op1.getKeys().size() == 0);
	}
	
	@Test
	public void testgenerateKeys_withSplChars_withNewLines_TextWithNoSpace () {
		Generator op = new Generator(new ArrayList<String>(), new ArrayList<String>());
		op.setNewLinesAllowed(true);
		op.getLines().add("iwishimayiwishimayimight");
		op.generateKeys("iwishimayiwishimayimight");
		assertTrue(op.getKeys().size() == 0);
		
		Generator op1 = new Generator(new ArrayList<String>(), new ArrayList<String>());
		op1.setNewLinesAllowed(false);
		op1.getLines().add("ready#to$test%words*test*is^fun@right!");
		op1.generateKeys("ready#to$test%words*test*is^fun@right!");
		System.out.println("t : " + op1.getKeys());
		assertTrue(op1.getKeys().size() == 0);
	}
	
	@Test
	public void testgenerateKeys_TextWithValidText () {
		String text = "i wish i may i wish i might";
		Generator op = new Generator(new ArrayList<String>(), new ArrayList<String>());
		op.setNewLinesAllowed(false);
		op.getLines().add(text);
		op.generateKeys(text);
		assertTrue(op.getKeys().size() == 5);
		op.computeTrigram();
		assertTrue(op.getTrigrams() != null);
		assertTrue(op.getTrigrams().containsKey("i wish"));
		assertTrue(op.getTrigrams().get("i wish").equals("i i "));
		
		assertTrue(op.getTrigrams().containsKey("wish i"));
		assertTrue(op.getTrigrams().get("wish i").equals("may might "));

		assertTrue(op.getTrigrams().containsKey("i may"));
		assertTrue(op.getTrigrams().get("i may").equals("i "));
		
	}
	
	@Test
	public void testgenerateKeys_withSplChars_withNewLineChars_TextWithValidText () {
		String text = "i wish i may i wish i might";
		Generator op = new Generator(new ArrayList<String>(), new ArrayList<String>());
		op.setNewLinesAllowed(true);
		op.getLines().add(text);
		op.generateKeys(text);
		assertTrue(op.getKeys().size() == 5);
		op.computeTrigram();
		assertTrue(op.getTrigrams() != null);
		assertTrue(op.getTrigrams().containsKey("i wish"));
		System.out.println("Test case: " + op.getTrigrams().get("i wish"));
		assertTrue(op.getTrigrams().get("i wish").equals("i i "));
		
		assertTrue(op.getTrigrams().containsKey("wish i"));
		assertTrue(op.getTrigrams().get("wish i").equals("may might "));

		assertTrue(op.getTrigrams().containsKey("i may"));
		assertTrue(op.getTrigrams().get("i may").equals("i "));
		
	}
	
	//@Test
	public void testgenerateKeys_RecursiveText () {
		String text = "i wish i may i wish i may";
		Generator op = new Generator(new ArrayList<String>(), new ArrayList<String>());
		op.setNewLinesAllowed(false);
		op.setNewLinesAllowed(false);
		op.getLines().add(text);
		op.generateKeys(text);
		assertTrue(op.getKeys().size() == 4);
		op.computeTrigram();
		assertTrue(op.getTrigrams() != null);
		System.out.println("tri: " + op.getTrigrams());
		assertTrue(op.getTrigrams().containsKey("i wish"));
		assertTrue(op.getTrigrams().get("i wish").equals("i i "));
		
		assertTrue(op.getTrigrams().containsKey("wish i"));
		assertTrue(op.getTrigrams().get("wish i").equals("may may "));

		assertTrue(op.getTrigrams().containsKey("i may"));
		assertTrue(op.getTrigrams().get("i may").equals("i 0 "));
	}
	
	
	//@Test
	public void testgenerateKeys_withOutSplChars_Textwithlines () {
		String text = "\"Are you all ready, Tom?\"\r\n" + 
				"\r\n" + 
				"\"All ready, Mr. Sharp,\" replied a young man, who was stationed near\r\n" + 
				"some complicated apparatus, while the questioner, a dark man, with a\r\n" + 
				"nervous manner, leaned over a large tank.\r\n" + 
				"\r\n" + 
				"\"I'm going to turn on the gas now,\" went on the man. \"Look out for\r\n" + 
				"yourself. I'm not sure what may happen.\"\r\n" + 
				"\r\n" + 
				"\"Neither am I, but I'm ready for it. If it does explode it can't do\r\n" + 
				"much damage.\"\r\n" + 
				"\r\n" + 
				"\"Oh, I hope it doesn't explode. We've had so much trouble with the\r\n" + 
				"airship, I trust nothing goes wrong now.\"\r\n" + 
				"\r\n" + 
				"\"Well, turn on the gas, Mr. Sharp,\" advised Tom Swift. \"I'll watch the\r\n" + 
				"pressure gauge, and, if it goes too high, I'll warn you, and you can\r\n" + 
				"shut it off.\"\r\n" + 
				"\r\n" + 
				"";
		Generator op = new Generator(new ArrayList<String>(), new ArrayList<String>());
		op.setNewLinesAllowed(false);
		op.getLines().add(text);
		op.generateKeys(text);
		assertTrue(op.getKeys().size() > 0);
		op.computeTrigram();
		assertTrue(op.getTrigrams() != null);
		assertTrue(op.getTrigrams().containsKey("Are you"));
	}
	
	@Test
	public void testgenerateKeys_withSplChars_withNewLineChars_Textwithlines () {
		String text = "\"Are you all ready, Tom?\"\r\n" + 
				"\r\n" + 
				"\"All ready, Mr. Sharp,\" replied a young man, who was stationed near\r\n" + 
				"some complicated apparatus, while the questioner, a dark man, with a\r\n" + 
				"nervous manner, leaned over a large tank.\r\n" + 
				"\r\n" + 
				"\"I'm going to turn on the gas now,\" went on the man. \"Look out for\r\n" + 
				"yourself. I'm not sure what may happen.\"\r\n" + 
				"\r\n" + 
				"\"Neither am I, but I'm ready for it. If it does explode it can't do\r\n" + 
				"much damage.\"\r\n" + 
				"\r\n" + 
				"\"Oh, I hope it doesn't explode. We've had so much trouble with the\r\n" + 
				"airship, I trust nothing goes wrong now.\"\r\n" + 
				"\r\n" + 
				"\"Well, turn on the gas, Mr. Sharp,\" advised Tom Swift. \"I'll watch the\r\n" + 
				"pressure gauge, and, if it goes too high, I'll warn you, and you can\r\n" + 
				"shut it off.\"\r\n" + 
				"\r\n" + 
				"";
		Generator op = new Generator(new ArrayList<String>(), new ArrayList<String>());
		op.setNewLinesAllowed(true);
		op.getLines().add(text);
		op.generateKeys(text);
		assertTrue(op.getKeys().size() > 0);
		op.computeTrigram();
		assertTrue(op.getTrigrams() != null);
		assertTrue(op.getTrigrams().containsKey("\"Are you"));
	}
}
