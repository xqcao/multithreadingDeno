import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


public class MultiThreadCallReturn{
	
	public static void main(String args[]) throws InterruptedException, ExecutionException{

		
		String saveFile2txtAll = "/home/xqcao/push2github/cc/all.txt";
		
		
		
		String filePath ="/media/xqcao/DC3C013E62AED233/ubuntuFile/temp2/tempwikipedia1.txt";
		String saveFile2txt ="/home/xqcao/push2github/cc/a1.txt";
		GetReturnFromThread task1 = new GetReturnFromThread(filePath, saveFile2txt);
		
		String filePath2 ="/media/xqcao/DC3C013E62AED233/ubuntuFile/temp2/tempwikipedia2.txt";
		String saveFile2txt2 ="/home/xqcao/push2github/cc/a2.txt";
		GetReturnFromThread task2 = new GetReturnFromThread(filePath2,saveFile2txt2);
		
		
		String filePath3 ="/media/xqcao/DC3C013E62AED233/ubuntuFile/temp2/tempwikipedia3.txt";
		String saveFile2txt3 ="/home/xqcao/push2github/cc/a3.txt";
		GetReturnFromThread task3 = new GetReturnFromThread(filePath3,saveFile2txt3);
		
		String filePath4 ="/media/xqcao/DC3C013E62AED233/ubuntuFile/temp2/tempwikipedia4.txt";
		String saveFile2txt4 ="/home/xqcao/push2github/cc/a4.txt";
		GetReturnFromThread task4 = new GetReturnFromThread(filePath4,saveFile2txt4);
		
		ExecutorService es = Executors.newFixedThreadPool(4);
		
		Future future1 = es.submit(task1);		
		Map<String, Integer> myMap1 =  (Map<String, Integer>) future1.get();
	
		Future future2 = es.submit(task2);		
		Map<String, Integer> myMap2 = (Map<String, Integer>) future2.get();
	
		Future future3 = es.submit(task3);		
		Map<String, Integer> myMap3 = (Map<String, Integer>) future3.get();
	
		Future future4 = es.submit(task4);		
		Map<String, Integer> myMap4 =  (Map<String, Integer>) future4.get();
		
		es.shutdownNow();
		
		MergeMap combinMap = new MergeMap();
		
		Map<String, Integer> myMap12 = combinMap.addTo(myMap1, myMap2);
		
		Map<String, Integer> myMap123 = combinMap.addTo(myMap12, myMap3);
		
		Map<String, Integer> myMap1234 = combinMap.addTo(myMap123, myMap4);
		
		String saveFileFromThread1 ="/home/xqcao/push2github/cc/t1.txt";
		String saveFileFromThread2 ="/home/xqcao/push2github/cc/t2.txt";
		String saveFileFromThread3 ="/home/xqcao/push2github/cc/t3.txt";
		String saveFileFromThread4 ="/home/xqcao/push2github/cc/t4.txt";
		
		OutputHashMap outmap = new OutputHashMap();
		outmap.writeMap2Txt(myMap1, saveFileFromThread1);
		outmap.writeMap2Txt(myMap2, saveFileFromThread2);
		outmap.writeMap2Txt(myMap3, saveFileFromThread3);
		outmap.writeMap2Txt(myMap4, saveFileFromThread4);
		
		
				
		List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(myMap1234.entrySet());
		 Collections.sort(list, new Comparator<Map.Entry<String, Integer>>(){
			 public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {  
	                return (o2.getValue()).compareTo(o1.getValue());  
	            } 	
		});
		try {
					
					BufferedWriter out = new BufferedWriter(new FileWriter(saveFile2txtAll));
					for(int i =0; i<list.size(); i++) {
						out.write(list.get(i).getKey() + "  " + list.get(i).getValue());
						out.newLine();
					}
					out.close();
		} catch (Exception e) {
					e.printStackTrace();
		}	
		Iterator<String> it = myMap4.keySet().iterator();
		while(it.hasNext()){
	            String key = (String) it.next();
	            Integer v = (Integer) myMap4.get(key);
	           System.out.println("key="+key+" v="+ v);
	    }		

	}
}

class GetReturnFromThread implements Callable{
	private String filePath;
	private String saveFile2txt;
	
	
	public GetReturnFromThread(String filePath, String saveFile2txt) {
		super();
		this.filePath = filePath;
		this.saveFile2txt = saveFile2txt;
	}
	
	@Override
	public Map<String, Integer> call() throws Exception {
		Map<String, Integer> myMap = CountFrequency.getWordFrequency(filePath, saveFile2txt);
		return myMap;
	}
	
}


