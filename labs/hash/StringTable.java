package hash;

import java.util.Iterator;
import java.util.LinkedList;

//
// STRINGTABLE.JAVA
// A hash table mapping Strings to their positions in the the pattern sequence
// You get to fill in the methods for this part.
//
public class StringTable {
    
    private LinkedList<Record>[] buckets;
    private int nBuckets;

    //
    // number of records currently stored in table --
    // must be maintained by all operations
    //
    public int size;
    
    
    //
    // Create an empty table with nBuckets buckets
    //
    @SuppressWarnings("unchecked")
	public StringTable(int nBuckets)
    {
    	this.nBuckets = nBuckets;
    	buckets = new LinkedList[nBuckets];
	
    	// TODO - fill in the rest of this method to initialize your table
    	size = 0;
    	for(int i=0; i<nBuckets; i++)
    		buckets[i] = new LinkedList<Record>();
    }
    
    
    /**
     * insert - inserts a record to the StringTable
     *
     * @param r
     * @return true if the insertion was successful, or false if a
     *         record with the same key is already present in the table.
     */
    public boolean insert(Record r) 
    {  
    	// TODO - implement this method

    	String key = r.key;
    	//if key is already in table
    	if(find(key) != null)
    		return false;
    	else {
    		int hashCode = stringToHashCode(key);
	    	int index = toIndex(hashCode);
	    	buckets[index].add(r);
	    	size++;
	    	return true;
    	}
    }
    
    
    /**
     * find - finds the record with a key matching the input.
     *
     * @param key
     * @return the record matching this key, or null if it does not exist.
     */
    public Record find(String key) 
    {
    	// TODO - implement this method
    	int hashCode = stringToHashCode(key);
    	int index = toIndex(hashCode);
    	Record target = null;
    	for(Record r : buckets[index])
    		if(key.contentEquals(r.key)) {
    			target = r;
    			break;
    		}
    	if(target == null)
    		return null;
    	else
    		return target;
    }
    
    
    /**
     * remove - finds a record in the StringTable with the given key
     * and removes the record if it exists.
     *
     * @param key
     */
    public void remove(String key) 
    {
    	// TODO - implement this method
    	if(find(key) == null)
    		return;
    	else {
    		int hashCode = stringToHashCode(key);
	    	int index = toIndex(hashCode);
	    	Iterator<Record> itr = buckets[index].iterator();
	    	while(itr.hasNext()) {
	    		if(itr.next().key.equals(key)) {
	    			itr.remove();
	    			break;
	    		}	
	    	}
	    	size--;
    	}
    	
    }
    

    /**
     * toIndex - convert a string's hashcode to a table index
     *
     * As part of your hashing computation, you need to convert the
     * hashcode of a key string (computed using the provided function
     * stringToHashCode) to a bucket index in the hash table.
     *
     * You should use a multiplicative hashing strategy to convert
     * hashcodes to indices.  If you want to use the fixed-point
     * computation with bit shifts, you may assume that nBuckets is a
     * power of 2 and compute its log at construction time.
     * Otherwise, you can use the floating-point computation.
     */
    private int toIndex(int hashcode)
    {
    	// Fill in your own hash function here
    	hashcode = Math.abs(hashcode);
    	double A = (Math.sqrt(5) - 1.0) / 2.0;
    	int index = (int)Math.floor(((hashcode*A) % 1.0) * nBuckets);
    	return index;
    }
    
    
    /**
     * stringToHashCode
     * Converts a String key into an integer that serves as input to
     * hash functions.  This mapping is based on the idea of integer
     * multiplicative hashing, where we do multiplies for successive
     * characters of the key (adding in the position to distinguish
     * permutations of the key from each other).
     *
     * @param string to hash
     * @returns hashcode
     */
    int stringToHashCode(String key)
    {
    	int A = 1952786893;
	
    	int v = A;
    	for (int j = 0; j < key.length(); j++)
	    {
    		char c = key.charAt(j);
    		v = A * (v + (int) c + j) >> 16;
	    }
	
    	return v;
    }

    /**
     * Use this function to print out your table for debugging
     * purposes.
     */
    public String toString() 
    {
    	StringBuilder sb = new StringBuilder();
	
    	for(int i = 0; i < nBuckets; i++) 
	    {
    		sb.append(i+ "  ");
    		if (buckets[i] == null) 
		    {
    			sb.append("\n");
    			continue;
		    }
    		for (Record r : buckets[i]) 
		    {
    			sb.append(r.key + "  ");
		    }
    		sb.append("\n");
	    }
    	return sb.toString();
    }
}
