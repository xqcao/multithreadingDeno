import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;


public class splitBigFile2subfile {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		long start = System.currentTimeMillis();
		
		FileInputStream fs = new FileInputStream("/media/xqcao/DC3C013E62AED233/ubuntuFile/wikipedia.txt");
		FileChannel fc = fs.getChannel();
		ByteBuffer buffer = ByteBuffer.allocate(900*1024*1024);//split with 900m
		int i = 0;
		while(true){
			buffer.clear();
			int flag = fc.read(buffer);
			if (flag == -1) {
				break;
			}
			buffer.flip();
			FileOutputStream fout = new FileOutputStream("/media/xqcao/DC3C013E62AED233/ubuntuFile/temp"+"wikipedia"+i+".txt");
			FileChannel fcout = fout.getChannel();
			fcout.write(buffer);
			i++;
		}
		long end = System.currentTimeMillis();
		System.out.println("time cost :"+(end - start));
	}

}
