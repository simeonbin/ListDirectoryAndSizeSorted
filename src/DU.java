
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Simeon
 */
public class DU {
    
public static void main(String[] args) {
    
		System.out.print("Enter a file or a directory: ");
		java.util.Scanner input = new java.util.Scanner(System.in);
		String s = input.nextLine(); 
               
               File[] filesAndDirectories = new File(s).listFiles();
               Map<String, Long> fileAndDirectoryMap = new HashMap<>(100);
               System.out.println();
                for (File directorie : filesAndDirectories) {
                    try {

                    System.out.print(directorie.getAbsolutePath() + " ");
                    String DP = directorie.getAbsolutePath();
                    long DS = directorySize(directorie.getCanonicalFile());
                    System.out.println( DS/1024 + "KB");
                    fileAndDirectoryMap.put(DP, DS);

                    }
                    catch (IOException ex) {
                    System.out.println(ex.toString());
                    }

                }
                System.out.println();
                System.out.println("---- NOW SORT'EM UP PLEASE ----");
                System.out.println();

                Map<String, Long> sortedFileAndDirectoryMap = sortByValue(fileAndDirectoryMap);

                for ( Map.Entry<String, Long> entry : sortedFileAndDirectoryMap.entrySet() ) {

                    System.out.println(entry.getKey() + "  " + entry.getValue()/1024 + "KB" );

                }
        
    }
  
    public static long directorySize(File file) throws java.io.FileNotFoundException {
            
		if (!file.exists())
			throw new java.io.FileNotFoundException(file + " not found");
		if (file.isFile()) {
			return file.length();
		}
		else {
			File[] files = file.listFiles();
			long size = 0;
                    for (File file1 : files) {
                        size += directorySize(file1);
                    }

			return size;
		}
	}
        
        
    public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
    return map.entrySet()
              .stream()
              .sorted(Map.Entry.comparingByValue(Collections.reverseOrder()))
              .collect(Collectors.toMap(
                Map.Entry::getKey, 
                Map.Entry::getValue, 
                (e1, e2) -> e1, 
                LinkedHashMap::new
              ));
}
        
}

    

