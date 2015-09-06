
// Copy paste this Java Template and save it as "BabyNames.java"
import java.util.*;
import java.util.Map.Entry;
import java.io.*;

// write your matric number here: A0102800A
// write your name here: Suranjana Sengupta
// write list of collaborators here: 
// year 2015 hash code: JESg5svjYpIsmHmIjabX (do NOT delete this line)

class BabyNames {
	// if needed, declare a private data structure here that
	// is accessible to all methods in this class

	// --------------------------------------------

	private TreeMap<String, Integer> babyNames;

	// --------------------------------------------

	public BabyNames() {

		babyNames = new TreeMap<String, Integer>();

		// --------------------------------------------
	}

	void AddSuggestion(String babyName, int genderSuitability) {

		babyNames.put(babyName, genderSuitability);

		// --------------------------------------------
	}

	void RemoveSuggestion(String babyName) {
		babyNames.remove(babyName);
	}

	int Query(String START, String END, int genderPreference) {
		int ans = 0;

		NavigableMap<String, Integer> searchedBabies = babyNames.subMap(START, true, END, false);

		if (genderPreference == 0) {
			ans = searchedBabies.size();
		} else {
			for (Entry<String, Integer> entry : searchedBabies.entrySet()) {
				if (entry.getValue() == genderPreference) {
					ans++;
				}
			}

		}

		return ans;
	}

	void run() throws Exception {
		// do not alter this method to avoid unnecessary errors with the
		// automated judging
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int command = Integer.parseInt(st.nextToken());
			if (command == 0) // end of input
				break;
			else if (command == 1) // AddSuggestion
				AddSuggestion(st.nextToken(), Integer.parseInt(st.nextToken()));
			else if (command == 2) // RemoveSuggestion
				RemoveSuggestion(st.nextToken());
			else // if (command == 3) // Query
				pr.println(Query(st.nextToken(), // START
						st.nextToken(), // END
						Integer.parseInt(st.nextToken()))); // GENDER
		}
		pr.close();
	}

	public static void main(String[] args) throws Exception {
		// do not alter this method to avoid unnecessary errors with the
		// automated judging
		BabyNames ps2 = new BabyNames();
		ps2.run();
	}
}
