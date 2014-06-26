import java.util.Map;


public class MergeMap {
	public static Map<String,Integer> addTo(Map<String, Integer> myMap1, Map<String, Integer> myMap2) {
        Object[] temp = myMap2.keySet().toArray();
        String mykey;
        for (int i=0; i<temp.length; i++) {
            mykey = (String)temp[i];
            if (myMap1.containsKey(mykey))         	
                myMap1.put(mykey, myMap1.get(mykey) + myMap2.get(mykey));
            else
                myMap1.put(mykey, myMap2.get(mykey));
        }
        return myMap1;
	}
}