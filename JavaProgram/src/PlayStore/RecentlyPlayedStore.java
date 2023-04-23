package PlayStore;

import java.util.*;

	public class RecentlyPlayedStore {
	    private final int capacity;
	    private final Map<String, LinkedList<String>> recentlyPlayed;

	    public RecentlyPlayedStore(int capacity) {
	        this.capacity = capacity;
	        this.recentlyPlayed = new HashMap<>();
	    }

	    public void addSong(String user, String song) {
	        LinkedList<String> songs = recentlyPlayed.computeIfAbsent(user, k -> new LinkedList<>());
	        songs.remove(song);
	        songs.addFirst(song);
	        if (songs.size() > capacity) {
	            songs.removeLast();
	        }
	    }

	    public List<String> getRecentlyPlayed(String user) {
	        LinkedList<String> songs = recentlyPlayed.get(user);
	        if (songs == null) {
	            return Collections.emptyList();
	        }
	        return new ArrayList<>(songs);
	    }

	    public static void main(String[] args) {
	        RecentlyPlayedStore store = new RecentlyPlayedStore(3);
	        store.addSong("user", "s1");
	        store.addSong("user", "s2");
	        store.addSong("user", "s3");
	        System.out.println(store.getRecentlyPlayed("user")); // [s3, s2, s1]
	        store.addSong("user", "s4");
	        System.out.println(store.getRecentlyPlayed("user")); // [s4, s2, s3]
	        store.addSong("user", "s2");
	        System.out.println(store.getRecentlyPlayed("user")); // [s2, s4, s3]
	        store.addSong("user", "s1");
	        System.out.println(store.getRecentlyPlayed("user")); // [s1, s2, s4]
	    }
	} 



