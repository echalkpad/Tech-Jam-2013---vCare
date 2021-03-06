package com.techjam.parser;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class MedicationSupplementParser extends Parser{
	
	public String[] ignorelist = {"Work Phone Number"};
	
	public MedicationSupplementParser(String[] listObject , String type){
		this.listObject = listObject;
		this.type = type;
	}

	public String parse(Scanner s) {
		
		while (s.hasNext()){
			String k = s.nextLine();
			if (isComplete(k)){
				return k;
			}
			if(k.indexOf(":") > 1){
				if (k.startsWith("Phone Number:")){
					parselist.add(new KeyPair("Phone Number", k.substring(13)));
				}else if (k.startsWith("Pharmacy Name:")){
					Pattern p = Pattern.compile("Pharmacy Name: (.*) Pharmacy Phone: (.*)");
					Matcher matcher = p.matcher(k);
					matcher.find();
					parselist.add(new KeyPair("Pharmacy Name", matcher.group(1)));					
					parselist.add(new KeyPair("Pharmacy Phone", matcher.group(2)));
				}else if (k.startsWith("Start Date:")){
					Pattern p = Pattern.compile("Start Date: (.*) Stop Date:(.*)");
					Matcher matcher = p.matcher(k);
					matcher.find();
					parselist.add(new KeyPair("Start Date", matcher.group(1)));					
					parselist.add(new KeyPair("Stop Date", matcher.group(2)));
				}else{
					String[] p = k.split(":");
					if (p.length > 1){
						parselist.add(new KeyPair(p[0], p[1]));
					}
				}
			}
			//System.out.println(type + k );
		}
		return "";
 	}

}
