package com.ustodo.utilg;

public class UtilHashMap {

	private static HashMap<String, Double> sortHashMap(HashMap<String, Double> input){
		Map<String, Double> tempMap = new HashMap<String, Double>();
		for (String wsState : input.keySet()){
			tempMap.put(wsState,input.get(wsState));
		}
	
		List<String> mapKeys = new ArrayList<String>(tempMap.keySet());
		List<Double> mapValues = new ArrayList<Double>(tempMap.values());
		HashMap<String, Double> sortedMap = new LinkedHashMap<String, Double>();
		TreeSet<Double> sortedSet = new TreeSet<Double>(mapValues);
		Object[] sortedArray = sortedSet.toArray();
		int size = sortedArray.length;
		for (int i=0; i<size; i++){
			sortedMap.put(mapKeys.get(mapValues.indexOf(sortedArray[i])),
						  (Double)sortedArray[i]);
		}
		return sortedMap;
	}
	
	public static HashMap<String, String> sortHashMapStrStr(HashMap<String, String> input){
		Map<String, String> tempMap = new HashMap<String, String>();
		for (String wsState : input.keySet()){
			tempMap.put(wsState,input.get(wsState));
		}
	
		List<String> mapKeys = new ArrayList<String>(tempMap.keySet());
		List<String> mapValues = new ArrayList<String>(tempMap.values());
		HashMap<String, String> sortedMap = new LinkedHashMap<String, String>();
		TreeSet<String> sortedSet = new TreeSet<String>(mapValues);
		Object[] sortedArray = sortedSet.toArray();
		int size = sortedArray.length;
		for (int i=0; i<size; i++){
			sortedMap.put(mapKeys.get(mapValues.indexOf(sortedArray[i])),
						  (String)sortedArray[i]);
		}
		return sortedMap;
	}
	
	public static void main (String[] args)
	{
		try
		{
			def ago = "13m";
			def s = buildMinDateFromMaxAge(ago)
			O.o("ago [${ago}] yields s:" + s)
			//O.o("getDateLikeFileFormatFromUtilDate(dtmin):", getDateLikeFileFormatFromUtilDate(dtmin))
		}
		catch (Throwable t )
		{
			O.or("t", t);
			throw t;
			assert(false);
		} finally {
			O.o "done"
			assert (true)
		}


	} // main
}

