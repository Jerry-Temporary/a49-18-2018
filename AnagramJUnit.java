package assignment4;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.util.Comparator;

import org.junit.jupiter.api.Test;

class AnagramJUnit {

	String test = "bad";
	String result = "abd";

	// TODO AnagramUtil.sort() tests.
	@Test
	void BasicSort() {
		// String test2 = AnagramUtil.sort(test);
		// System.out.println(AnagramUtil.sort(test));
		assertTrue(result.equals(AnagramUtil.sort(test)));
	}

	@Test
	void BasicSortOnSorted() {
		assertTrue(result.equals(AnagramUtil.sort(result)));
	}

	@Test
	void BadCaseSort() {
		assertTrue("abcdef".equals(AnagramUtil.sort("fedcba")));
	}

	@Test
	void TestDuplicateNumbers() {
		assertTrue("abcccdeef".equals(AnagramUtil.sort("feedcccba")));
	}

	@Test
	void TestCapsEquals() {
		// System.out.println(AnagramUtil.sort("Cabd"));
		assertTrue("ACbd".equals(AnagramUtil.sort("CAbd")));
	}

	@Test
	void TestCapsDiff() {
		assertFalse("Abcdefg".equals(AnagramUtil.sort("feedcccba")));
	}

	@Test
	void TestNumbers() {
		assertTrue("12345".equals(AnagramUtil.sort("54321")));
	}

	@Test
	void TestNumbersAndLetters() {
		assertTrue("12345abcd".equals(AnagramUtil.sort("ca54321bd")));
	}

	@Test
	void TestNumbersAndCaps() {
		assertTrue("12345BCad".equals(AnagramUtil.sort("aC54321Bd")));
	}

	@Test
	void NullInput() {
		assertEquals(AnagramUtil.sort(null), null);
	}

	// TODO AnagramUtil.insertionSort tests.

	@Test
	void insertionSortTestInts() {
		Integer[] testNums = new Integer[] { 5, 4, 3, 2, 1 };
		Integer[] resultNums = new Integer[] { 1, 2, 3, 4, 5 };
		AnagramUtil.insertionSort(testNums, new IntegerComparator());
		for (int i = 0; i < testNums.length; i++) {
			assertTrue(testNums[i].equals(resultNums[i])); // Ask TA about arrays
		}
	}

	@Test
	void insertionSortTestStrings() {
		String[] testNums = new String[] { "d", "a", "c", "b" };
		String[] resultNums = new String[] { "a", "b", "c", "d" };
		AnagramUtil.insertionSort(testNums, Comparator.naturalOrder());
		for (int i = 0; i < testNums.length; i++) {
			assertTrue(testNums[i].equals(resultNums[i])); // Ask TA about arrays
		}
	}

	// TODO AnagramUtil.shellSort tests.

	@Test
	void shellsortTestStrings() {
		String[] testNums = new String[] { "d", "a", "c", "b" };
		String[] resultNums = new String[] { "a", "b", "c", "d" };
		AnagramUtil.shellSort(testNums, Comparator.naturalOrder());
		for (int i = 0; i < testNums.length; i++) {
			//System.out.println(resultNums[i]);
			assertTrue(testNums[i].equals(resultNums[i])); // Ask TA about arrays
		}

	}

	@Test
	void shellSortTestWithNumbers() {
		Integer[] testNums = new Integer[] { 5, 4, 3, 2, 1 };
		Integer[] resultNums = new Integer[] { 1, 2, 3, 4, 5 };
		AnagramUtil.shellSort(testNums, new IntegerComparator());
		for (int i = 0; i < testNums.length; i++) {
			assertTrue(testNums[i].equals(resultNums[i])); // Ask TA about arrays
		}
	}
	
	// TODO AnagramUtil.areAnagrams tests.

	
	@Test
	void areAnagramsTest() {
		String test = "cat";
		String result = "act";
		assertTrue(AnagramUtil.areAnagrams(test, result));
	}
	
	@Test
	void notAnagramsTest() {
		assertFalse(AnagramUtil.areAnagrams("cat", "dog"));
	}
	@Test
	void areAnagramsTestCaps() {
		assertTrue(AnagramUtil.areAnagrams("Cat", "cAt"));
	}
	@Test
	void notAnagramsTestCapsFalse() {
		assertFalse(AnagramUtil.areAnagrams("cAt", "doG"));
	}
	@Test
	void areAnagramsTestSameString() {
		assertTrue(AnagramUtil.areAnagrams("cat", "cat"));
	}

	// TODO AnagramUtil.getBiggestAnagramGroup tests.
	@Test 
	void testSampleWordList(){
		String[] test = AnagramTester.readFile("sample_word_list.txt");
		test = AnagramUtil.getLargestAnagramGroup(test);
		String[] result = new String[] {"carets" , "Caters", "caster", "crates", "Reacts", "recast", "traces"};
//		for (int i = 0; i < test.length; i++) {
//			System.out.println(test[i]);
//		}
		assertArrayEquals(result, test);
	}
	@Test 
	void testSampleWordListFirstGroupLargest(){
		String[] test = new String [] {"cat", "tac", "act","carets" , "Caters", "caster", "crates", "Reacts", "recast", "traces"} ;
		test = AnagramUtil.getLargestAnagramGroup(test);
		String[] result = new String[] {"carets" , "Caters", "caster", "crates", "Reacts", "recast", "traces"};
		assertArrayEquals(result, test);
		
	}
	@Test 
	void testSampleWordListLastGroupLargest(){
		String[] test = new String [] {"carets" , "Caters", "cat", "tac", "act"} ;
		test = AnagramUtil.getLargestAnagramGroup(test);
		String[] result = new String[] {"cat", "tac", "act"};
		for (int i = 0; i < test.length; i++) {
			System.out.println(test[i]);
		}
		assertArrayEquals(result, test);
		
	}
	
	@Test 
	void testSampleWordListNullInput(){
		String[] test = null;
		test = AnagramUtil.getLargestAnagramGroup(test);

		assertArrayEquals(null, test);
	}
	
	
	
	
	public class IntegerComparator implements Comparator<Integer> {

		public int compare(Integer o1, Integer o2) {
			return o1.compareTo(o2);
		}
	}

}
