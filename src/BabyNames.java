
// Copy paste this Java Template and save it as "BabyNames.java"
import java.util.*;
import java.io.*;
// write your matric number here: A0102800A
// write your name here: Suranjana Sengupta
// write list of collaborators here: 
// year 2015 hash code: JESg5svjYpIsmHmIjabX (do NOT delete this line)

class BabyNames {
	// if needed, declare a private data structure here that
	// is accessible to all methods in this class

	// --------------------------------------------

	private TreeSet<String> babyGirls;
	private TreeSet<String> babyBoys;

	// --------------------------------------------

	public BabyNames() {

		babyGirls = new TreeSet<String>();
		babyBoys = new TreeSet<String>();

		// --------------------------------------------
	}

	void AddSuggestion(String babyName, int genderSuitability) {

		if (genderSuitability == 1) {
			babyBoys.add(babyName);
		} else if (genderSuitability == 2) {
			babyGirls.add(babyName);
		}

		// --------------------------------------------
	}

	void RemoveSuggestion(String babyName) {

		if (babyBoys.contains(babyName)) {
			babyBoys.remove(babyName);
		} else if (babyGirls.contains(babyName)) {
			babyGirls.remove(babyName);
		}

	}

	int Query(String START, String END, int genderPreference) {
		int ans = 0;

		NavigableSet<String> boyNames = babyBoys.subSet(START, true, END, false);
		NavigableSet<String> girlNames = babyGirls.subSet(START, true, END, false);

		if (genderPreference == 0) {
			ans = boyNames.size() + girlNames.size();
		} else if (genderPreference == 1) {
			ans = boyNames.size();
		} else if (genderPreference == 2) {
			ans = girlNames.size();
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
