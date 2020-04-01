package keySorting;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.RandomAccessFile;
import java.util.Scanner;
import java.util.StringTokenizer; 

public class MusicAlbumClass {

	String albumName;
	int    albumID;
	String albumArtist;
	static int MAX = 50;
	
	public String getAlbumName() {
		return albumName;
	}
	public void setAlbumName(String albumName) {
		this.albumName = albumName;
	}
	public int getAlbumID() {
		return albumID;
	}
	public void setAlbumID(int albumID) {
		this.albumID = albumID;
	}
	public String getAlbumArtist() {
		return albumArtist;
	}
	public void setAlbumArtist(String albumArtist) {
		this.albumArtist = albumArtist;
	}
	
	public static int getMAX() {
		return MAX;
	}
	public static void setMAX(int mAX) {
		MAX = mAX;
	}
	
	MusicAlbumClass(){
		albumName = null;
		albumID = 0;
		albumArtist = null;
	}
	
	public void getAlbumDetails() {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter the Album ID: ");
		albumID = sc.nextInt();
		
		System.out.println("Enter the Album Name: ");
		albumName = sc.next();
		
		System.out.println("Enter the Album Artist: ");
		albumArtist = sc.next();
		
	
	}
	
	public long albumPack() {
		
		String packedRecord = albumID + "|" + albumName + "|" + albumArtist + "|";
		while(packedRecord.length()<50) {
			packedRecord += "@";
		}
		packedRecord += "\n";
		long recPos = 0;
		try{
	        RandomAccessFile recFile = new RandomAccessFile("/home/bytestorm/albumrec.txt", "rw");  
	        recPos = recFile.length();
	        recFile.seek(recPos);  
	        recFile.write(packedRecord.getBytes());  
	        recFile.close();
	       	}catch(Exception e){System.out.println(e);}
			
		return recPos;
		
	}
	
	public static void tokenizeRec(String rec) {
		StringTokenizer st1 = new StringTokenizer(rec,"|");
        
    	for (int i = 1; st1.hasMoreTokens(); i++) {
            System.out.println("Album ID: "+st1.nextToken());
    		System.out.println("Album Name: "+st1.nextToken());
            System.out.println("Album Artist: "+st1.nextToken());
            st1.nextToken();
            System.out.println("****************");
    	}
    	
	}
	
	public void albumUnpack() {
		
		try{
		FileReader fr=new FileReader("/home/bytestorm/albumrec.txt");    
		BufferedReader br=new BufferedReader(fr); 
		String recFromFile;    
		System.out.println("****************");
        while((recFromFile=br.readLine())!=null) {    
        System.out.println(recFromFile);
        
        tokenizeRec(recFromFile);        	      
        
        }fr.close();
        
		}catch(Exception e){System.out.println(e);}
	}
	
	public static void albumUnpackSingleRecord(int recNumber) {
		
		long pos = 0;
		if (recNumber == 0) pos = 0;
		else 
			pos = (recNumber * 51);
		try{
		RandomAccessFile recFile = new RandomAccessFile("/home/bytestorm/newalbumrec.txt", "rw");    
		recFile.seek(pos);  
		String recFromFile;    
		recFromFile=recFile.readLine();  
        //System.out.println(recFromFile);
        tokenizeRec(recFromFile);        	      
        recFile.close();
		} catch(Exception e){System.out.println(e);}
	}
	
}
