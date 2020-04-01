package keySorting;

import java.io.FileWriter;
import java.io.RandomAccessFile;
import java.util.StringTokenizer;

public class KeyNodeIndex {
	
	int albumIDKey;
	long recPos;
	
	public KeyNodeIndex(int albumIDKey2, long recPos2) {
		albumIDKey = albumIDKey2;
		recPos = recPos2;
	}
	
	public int getAlbumIDKey() {
		return albumIDKey;
	}
	public void setAlbumIDKey(int albumIDKey) {
		this.albumIDKey = albumIDKey;
	}
	public long getRecPos() {
		return recPos;
	}
	public void setRecPos(long recPos) {
		this.recPos = recPos;
	}
	
    public static void sort(KeyNodeIndex[] kni) {
		
    	int n = kni.length;
        for (int i = 0; i < n-1; i++)
            for (int j = 0; j < n-i-1; j++)
                if (kni[j].getAlbumIDKey() > kni[j+1].getAlbumIDKey())
                {
                    // swap temp and arr[i]
                	KeyNodeIndex temp = new KeyNodeIndex(kni[j].getAlbumIDKey(),kni[j].recPos);
                    kni[j].setAlbumIDKey(kni[j+1].getAlbumIDKey());
                    kni[j].setRecPos(kni[j+1].getRecPos());
                    kni[j+1].setAlbumIDKey(temp.getAlbumIDKey());
                    kni[j+1].setRecPos(temp.getRecPos());
                }
		
	}
    
    public static void displayKeyNodeIndex(KeyNodeIndex[] kni) {
    	for (int i = 0; i<kni.length; i++) {
    		System.out.println(kni[i].getAlbumIDKey()+ "|" + kni[i].getRecPos());
    	}
    	
    }
    
    public static void rewriteRecordFile(KeyNodeIndex[] kni) {
    	long recPos = 0;
    	String rec = null;
    	for(int i=0;i<kni.length;i++) {
    	
    		try{
    	        RandomAccessFile recFile = new RandomAccessFile("/home/bytestorm/albumrec.txt", "rw");  
    	        recPos = kni[i].getRecPos();
    	        recFile.seek(recPos);  
    	        rec = recFile.readLine();  
    	        rec += "\n";
    	        recFile.close();
    		}catch(Exception e){System.out.println(e);}
    	        
    		try {
    			FileWriter fw = new FileWriter("/home/bytestorm/newalbumrec.txt", true);
    			fw.write(rec);
    			fw.close();   			
    		}catch(Exception e){System.out.println(e);}
    	       	
        }
    	
    }
    
    public static String readRecordFileForKey(long pos) {
    	String key = null;
    	
    	try{
	        RandomAccessFile recFile = new RandomAccessFile("/home/bytestorm/newalbumrec.txt", "rw");  
	        String rec = null;
	        recFile.seek(pos*51);  
	        rec = recFile.readLine();  
	        recFile.close();
	        //System.out.println("pos: "+pos*51);
	        //System.out.println("rec: "+rec);
	        StringTokenizer st1 = new StringTokenizer(rec,"|");
	        for (int i = 1; st1.hasMoreTokens(); i++) {
	        	key = st1.nextToken();
	        	st1.nextToken();
	        	st1.nextToken();
	        	st1.nextToken();
	        }
	    		    	
	    	//System.out.println("key: "+key);
	        
		}catch(Exception e){System.out.println(e);}
    	return key;
    }
    
    
    public static int binarySearchFile(int albumIDToSearch) {
    	int low = 0;
		int high = 0;
		int mid = 0;
		String keyFromRec = null;
    	try{
    		RandomAccessFile recFile = new RandomAccessFile("/home/bytestorm/newalbumrec.txt", "rw");
    		high = (int) (recFile.length()/MusicAlbumClass.getMAX())-1;
    		//System.out.println("high: "+high);
    		recFile.close();
    		while(low<=high) {
    			mid = (high+low)/2;
    			System.out.println("mid: " +mid);
    			keyFromRec = readRecordFileForKey(mid);
    			int intKeyToSearch = Integer.parseInt(keyFromRec);
    			//System.out.println("keyfromrec: "+keyFromRec);
    			//System.out.println("albumIDToSearch: "+albumIDToSearch);
    			if(intKeyToSearch==albumIDToSearch)
    				return mid;
    			else if(intKeyToSearch>albumIDToSearch)high=mid-1 ;
    			else low = mid+1;
    		}
    		
    		mid=-1; 
    		
    	}catch(Exception e){System.out.println(e);}
    	
    	return mid;
    	
    }
	
}
