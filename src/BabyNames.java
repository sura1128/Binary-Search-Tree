
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

	private TreeMap<String, Integer> babyMap;
	private TreeSet<String> babySet;

	// --------------------------------------------

	public BabyNames() {

		babyMap = new TreeMap<String, Integer>();
		babySet = new TreeSet<String>();

		// --------------------------------------------
	}

	void AddSuggestion(String babyName, int genderSuitability) {

		babyMap.put(babyName, genderSuitability);
		babySet.add(babyName);

		// --------------------------------------------
	}

	void RemoveSuggestion(String babyName) {
		babyMap.remove(babyName);
		babySet.remove(babyName);
	}

	int Query(String START, String END, int genderPreference) {
		int ans = 0;

		NavigableSet<String> babies = babySet.subSet(START, true, END, false);

		Iterator<String> itr = babies.iterator();

		if (!babies.isEmpty()) {
			if (genderPreference == 0) {
				ans = babies.size();
			} else {
				while (itr.hasNext()) {
					String temp = itr.next();
					if (babyMap.containsKey(temp) && (babyMap.get(temp) == genderPreference)) {
						ans++;
					}
				}
			}
		}

		return ans;
	}

	boolean babyInRange(String START, String END, String babyName) {
		String babyStart = babyName.substring(0, START.length());
		String babyEnd = babyName.substring(0, END.length());

		if (babyStart.compareToIgnoreCase(babyStart) >= 0 && babyStart.compareToIgnoreCase(babyEnd) < 0) {
			return true;
		} else {
			return false;
		}
	}

	void print(NavigableMap<String, Integer> map) {
		for (Map.Entry<String, Integer> entry : map.entrySet()) {
			System.out.println(entry.getKey());
		}
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
