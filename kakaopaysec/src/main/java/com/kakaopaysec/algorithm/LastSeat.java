package com.kakaopaysec.algorithm;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;

//3. 마지막 남는 자리
public class LastSeat {
	public static void main(String[] args) {
		
		//변수 셋팅
		Scanner scanner = new Scanner(System.in);	
		String lastSeat="";
		int moveD;
		int peopleNum;
		int tmp = 0;
		
		//변수 입력(사람 수)
		System.out.print("> 사람 수  \t:");
		peopleNum = Integer.parseInt(scanner.next());
		
		//변수 입력(이동 거리)
		System.out.print("> 이동 거리  \t:");
		moveD = Integer.parseInt(scanner.next());
		
		if(peopleNum<10) {
			for(int i=1;i<=peopleNum;i++) {
				lastSeat += i;
			}
			
			//마지막 남는 자리 찾기
			for(int i=0;i<peopleNum-1;i++) {
				tmp = moveD%(peopleNum-i);
				if(tmp==0) tmp=peopleNum-i;			
				lastSeat= lastSeat.substring(tmp,lastSeat.length())+lastSeat.substring(0,tmp-1);
			}
		}else {
			List<String> seatList = new LinkedList<String>();
			int index=0;
			for(int i=0;i<peopleNum;i++) {
				seatList.add(i+"");
			}
			ListIterator<String> iterator = seatList.listIterator();
			//마지막 남는 자리 찾기
			for(int i=0;i<peopleNum-1;i++) {
				for(int j=0;j<moveD;j++) {
					if(iterator.hasNext()) {
						index = iterator.nextIndex();
						iterator.next();
					}
					else {
						iterator = seatList.listIterator();
						index = iterator.nextIndex();
						iterator.next();
					}
				}
				lastSeat = (String) seatList.get(index);
				seatList.remove(index);
				iterator = seatList.listIterator(index);
			}
			tmp = Integer.parseInt(seatList.get(0))+1;
			lastSeat = tmp+"";
		}
		//결과 출력
		System.out.println("> 출력  \t\t:" + lastSeat);
		
	}
}