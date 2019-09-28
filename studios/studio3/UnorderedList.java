package studio3;

import java.util.LinkedList;
import java.util.List;

public class UnorderedList<T extends Comparable<T>> implements PriorityQueue<T> {

	public LinkedList<T> list;
	
	public UnorderedList() {
		list = new LinkedList<T>();
	}
	
	@Override
	public boolean isEmpty() {
		//
		// FIXME
		//
		return this.list.isEmpty();
	}

	@Override
	public void insert(T thing) {
		//
		// FIXME
		//
		this.list.add(thing);
	}

	@Override
	public T extractMin() {
		//
		// FIXME
		//
		int min = 0;
		for(int i=0; i<list.size(); i++) {
			if(list.get(min).compareTo(list.get(i))>0)
				min = i;
		}
		return list.remove(min);
	}

}
