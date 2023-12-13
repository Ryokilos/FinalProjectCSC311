package finalproject;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import redis.clients.jedis.Jedis;

//Represents the results of a search query
public class WikiSearch {
    //Map from URLs that contain the term(s) to relevance score
	private Map<String, Integer> map;
	//Constructor
	public WikiSearch(Map<String, Integer> map) {
		this.map = map;
	}

	//Looks up the relevance of a given URL
	public Integer getRelevance(String url) {
		Integer relevance = map.get(url);
		return relevance==null ? 0: relevance;
	}

    //Prints the contents in order of term frequency
	private  void print() {
		List<Entry<String, Integer>> entries = sort();
		for (Entry<String, Integer> entry: entries) {
			System.out.println(entry);
		}
	}

	//Computes the union of two search results
	public WikiSearch or(WikiSearch that) {
		//
		return null;
	}

    //Computes intersection of two search results
	public WikiSearch and(WikiSearch that) {
		//
		return null;
	}

	//Computes intersection of two search results
	public WikiSearch minus(WikiSearch that) {
		//
		return null;
	}

    //Computes the relevance of a search with multiple terms
	 //rel1: relevance score for the first search
	 //rel2: relevance score for the second search
	protected int totalRelevance(Integer rel1, Integer rel2) {
	//simple starting place: relevance is the sum of the term frequencies
		return rel1 + rel2;
	}

	//Sort the results by relevance
	public List<Entry<String, Integer>> sort() {
		//
		return null;
	}


	//Performs a search and makes a WikiSearch object

	public static WikiSearch search(String term, JedisIndex index) {
		Map<String, Integer> map = index.getCounts(term);
		return new WikiSearch(map);
	}

	public static void main(String[] args) throws IOException {
        
		// make a JedisIndex
		Jedis jedis = JedisMaker.make();
		JedisIndex index = new JedisIndex(jedis);

		// search for the first term
		String term1 = "java";
		System.out.println("Query: " + term1);
		WikiSearch search1 = search(term1, index);
		search1.print();

		// search for the second term
		String term2 = "programming";
		System.out.println("Query: " + term2);
		WikiSearch search2 = search(term2, index);
		search2.print();

		// compute the intersection of the searches
		System.out.println("Query: " + term1 + " AND " + term2);
		WikiSearch intersection = search1.and(search2);
		intersection.print();
	}
}
