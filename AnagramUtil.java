package assignment4;

import java.util.ArrayList;
import java.util.Comparator;

//TODO author

/**
 * 
 * @author Christopher Morgan and Janice Hernandez Fill in this class according to the specifications
 *
 */
public class AnagramUtil {

	/**
	 * This method returns the sorted version of the input string using
	 * lexicographical ordering (as defined by Character.compareTo). The sorting
	 * must be accomplished using an insertion sort.
	 * 
	 * @param toSort
	 * @return Sorted version of String toSort
	 */
	public static String sort(String toSort) {
		if (toSort == null) {
			return null;
		}
		
		String result = "";
		String[] toSortArray = new String[toSort.length()];
		for (int i = 0; i < toSort.length(); i++) {
			toSortArray[i] = ((Character)toSort.charAt(i)).toString();
		}
		insertionSort(toSortArray, Comparator.naturalOrder());
		for (int i = 0; i < toSortArray.length; i++) {
			//System.out.println(toSortArray[i].toString());
			result = result + toSortArray[i];
		}
		return result;
	}

	/**
	 * This generic method sorts the input array using an insertion sort and the
	 * input Comparator object.
	 */
	public static <T> void insertionSort(T[] toSort, Comparator<? super T> cmp) { // TODO test
		if (toSort == null || cmp == null) {
			return;
		}

		// Iterate through all of the elements forward
		for (int index = 1; index < toSort.length; index++) {
			T temp = toSort[index];
			// Move elements right as far as they need to go.
			int jindex;
			for (jindex = index; jindex > 0; jindex--) {
				if (cmp.compare(temp, toSort[jindex - 1]) >= 0) {// If it is in the right spot then break
					break;
				}
				toSort[jindex] = toSort[jindex - 1];// This code will only execute if it is not in the right spot.
			}
			toSort[jindex] = temp;
		}
	}

	/**
	 * This generic method sorts the input array using a Shell sort and the input
	 * Comparator object.
	 * 
	 * @param toSort
	 * @param cmp
	 */
	public static <T> void shellSort(T[] toSort, Comparator<? super T> cmp) {
		// TODO test
		if (toSort == null || cmp == null) {
			return;
		}
		
		for (int gap = toSort.length / 2; gap > 0; gap /= 2) {
			for (int index = gap; index < toSort.length; index++) {
				T item = toSort[index]; // item to be inserted
				int secondIndex;
				for (secondIndex = index - gap; secondIndex >= 0 
						&& cmp.compare(item, toSort[secondIndex]) < 0; secondIndex -= gap) {
					toSort[secondIndex + gap] = toSort[secondIndex];
				}
				toSort[secondIndex + gap] = item;

			}
		}
	}

	/**
	 * This method returns true if the two input strings are anagrams of each other,
	 * otherwise returns false.
	 * 
	 * @return
	 */
	public static boolean areAnagrams(String lhs, String rhs) { // TODO test
		if (lhs == null || rhs == null) {
			return false;
		}
		
		
		if(sort(lhs.toLowerCase()).equals(sort(rhs.toLowerCase()))) {
			return true;
		}
		return false; 
	}

	/**
	 * This method returns the largest group of anagrams in the input array of
	 * words, in no particular order. It returns an empty array if there are no
	 * anagrams in the input array. Use either an insertion sort or a Shell sort to
	 * achieve this. The reason for having both sorting algorithms is for the
	 * analysis portion of this assignment. In order to properly complete the
	 * analysis portion of this assignment, this method must use the algorithm
	 * described in section 1 of this assignment.
	 * 
	 * @param anagrams
	 * @return
	 */
	public static String[] getLargestAnagramGroup(String[] anagrams) {
		if (anagrams == null)
			return null;
		
		String [] temp = new String [anagrams.length]; 
		String [] result;
		for (int i = 0; i < anagrams.length; i++) {
			temp[i] = sort(anagrams[i].toLowerCase());
			
		}
		shellSort(temp, Comparator.naturalOrder());
		//insertionSort(temp, Comparator.naturalOrder());
		int biggestAnagram = 0; 
		int currentSize = 1; 
		String mostCommonAnagram = temp[0];
		
		for(int i = 1; i < temp.length; i++) {
			if(areAnagrams(temp[i], temp[i-1])) {
				currentSize += 1; 
			}
			if(!areAnagrams(temp[i], temp[i-1])){
				if(currentSize > biggestAnagram) {
					biggestAnagram = currentSize; 
					mostCommonAnagram = temp[i-1];
				}
				currentSize = 1;
			}
			if(i == temp.length -1) {
				if(currentSize > biggestAnagram) {
					biggestAnagram = currentSize; 
					mostCommonAnagram = temp[i-1];
				}
			}
		}
		result = new String [biggestAnagram];
		int position = 0; 
		for(int i = 0; i < anagrams.length; i++) {
			
			if(areAnagrams(anagrams[i], mostCommonAnagram)) {
				result[position] = anagrams[i];
				position +=1; 
			}
		}
		
		return result;
	}

}
