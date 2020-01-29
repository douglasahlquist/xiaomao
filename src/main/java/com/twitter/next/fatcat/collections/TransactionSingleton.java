package com.twitter.next.fatcat.collections;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.twitter.next.fatcat.entities.CTransaction;

public class TransactionSingleton {

	private TransactionCollection map = null;

	private static TransactionSingleton instance = null;

	private TransactionSingleton() {
		map = new TransactionCollection();
	}

	public static TransactionSingleton getInstance() {
		if (instance == null) {
			instance = new TransactionSingleton();
		}
		return instance;
	}

	public CTransaction get(String id) {
		return map.get(id);
	}

	public void put(String id, CTransaction trx) {
		map.put(id, trx);
	}

	public void save() {

	}

	public void read() {

	}

	public int getCount() {
		return map.size();
	}
	
	public List<CTransaction> getAllTransactions(){
		
		List<CTransaction> list = new ArrayList<CTransaction> ();
		
		Set<String> set = map.keySet();
		Iterator<String> it = set.iterator();
		while(it.hasNext()) {
				CTransaction c = map.get(it.hasNext());
				list.add(c);
		}
	
	    return list;	
	}
 
}
