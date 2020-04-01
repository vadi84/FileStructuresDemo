package keySorting;

import java.util.Scanner;

public class KeySortDemo {
	
	public static void main(String[] args) {
		
		MusicAlbumClass ma = new MusicAlbumClass();
		
		Scanner sc = new Scanner(System.in);
		int n = 0;
		int albumIDToSearch = 0;
		int posInFile = 0;
		
		
		System.out.println("Enter the number of time you want to run the program");
		n = sc.nextInt();
		
		KeyNodeIndex[] kni = new KeyNodeIndex[n];
		
		for(int i=0; i < n ; i++) {
			kni[i]=new KeyNodeIndex(0,0);
		}
		
		for(int i=0; i<n; i++) {
			ma.getAlbumDetails();
			long pos = ma.albumPack();
			kni[i].albumIDKey = ma.getAlbumID();
			kni[i].recPos = pos;
		}	
			
		ma.albumUnpack();
		
		KeyNodeIndex.sort(kni);
		
		System.out.println("the key node index contains:");
		KeyNodeIndex.displayKeyNodeIndex(kni);
		
		System.out.println("will sort the record file");
		KeyNodeIndex.rewriteRecordFile(kni);
		System.out.println("done sorting the record file");
		
		System.out.println("enter the album ID to search in new record file");
		albumIDToSearch = sc.nextInt();
		
		posInFile = KeyNodeIndex.binarySearchFile(albumIDToSearch);
		
		if(posInFile >=0) {
			System.out.println("The album id is present in the file");
			MusicAlbumClass.albumUnpackSingleRecord(posInFile);
		}
		else
			System.out.println("The album id is not present in the file");
			
		
		
		sc.close();
	}
	
	
	
}
