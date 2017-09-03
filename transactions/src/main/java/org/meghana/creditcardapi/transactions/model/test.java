package org.meghana.creditcardapi.transactions.model;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class test{
	
	public static void main(String[] args) {
		String timeStamp = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss").format(Calendar.getInstance().getTime());
		String str = timeStamp.replaceAll("[-:]", "");
		System.out.println(new transcationType().create);
	}
}