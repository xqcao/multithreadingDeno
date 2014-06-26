import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;


public class OutputHashMap {
	public void writeMap2Txt(Map<String, Integer> myMap,String saveFile2txt){
		List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(myMap.entrySet());
		Collections.sort(list, new Comparator<Map.Entry<String, Integer>>(){
			 public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {  
	                return (o2.getValue()).compareTo(o1.getValue());  
	            } 	
		});
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter(saveFile2txt));
			for(int i =0; i<list.size(); i++) {
				out.write(list.get(i).getKey() + "  " + list.get(i).getValue());
				out.newLine();
			}
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
