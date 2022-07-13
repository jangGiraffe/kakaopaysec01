package com.kakaopaysec.algorithm;

import java.util.Scanner;

//2. 배열 회전
public class ArrayRotation {
	public static void main(String[] args) {
		//변수 셋팅
		Scanner scanner = new Scanner(System.in);		
		int x=0;
		int y=0;
		String [] tmpArray;
		int [][] array=null;
		
		
		//변수 입력(가로열)
		System.out.print(">가로 열\t\t\t : ");
		y = Integer.parseInt(scanner.next());
		//변수 입력(세로열)
		System.out.print(">세로 열\t\t\t : ");
		x = Integer.parseInt(scanner.next());
		//배열 데이터 입력
		System.out.print(">배열 데이터 (,) 로 구분 \t : ");
		tmpArray = scanner.next().split(",");
		array = new int[x][y];
		for(int i=0;i<x;i++) {
			for (int j=0;j<y;j++) {
				array[i][j]=Integer.parseInt(tmpArray[i*y+j]);
			}
		}
		
		//배열 회전 수행
		System.out.print(">회전 명령어 \t : ");
		tmpArray = scanner.next().split("");
		for(int i=0;i<tmpArray.length;i++) {
			switch(tmpArray[i]){
			case "L" :
				array = turnLeft(array);
				break;
			case "R" : 
				array = turnRight(array);
				break;
			case "T" :
				array = flipArray(array);
				break;
			}
		}
		//정답 출력
		System.out.print(">출력할 배열 위치: ");
		tmpArray = scanner.next().split(",");
		
		x = Integer.parseInt(tmpArray[1])-1;
		y = Integer.parseInt(tmpArray[0])-1;

		System.out.println("출력 : " + array[x][y]);		
	}
	
	public static int [][] turnRight(int [][] array){
		int x = array[0].length;
		int y = array.length;
		int[][] array2 = new int[x][y];
		
		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				array2[i][j] = array[y - 1 - j][i];
			}
		}
		return array2;
	}
	
	public static int [][] turnLeft(int [][] array){
		int x = array[0].length;
		int y = array.length;
		int[][] array2 = new int[x][y];
		
		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				array2[i][j] = array[j][x-1-i];
			}
		}
		return array2;
	}
	
	public static int [][] flipArray(int [][]array){
		int x = array.length;
		int y = array[0].length;
		
		int[][] array2 = new int[x][y];
				
		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				array2[i][j] = array[i][y-1-j];
			}
		}
		return array2;
	}
	
	public static void printArray(int [][]array){
		System.out.println("---------------");
		for(int i=0;i<array.length;i++) {
			for(int j=0;j<array[i].length;j++) {
				System.out.print(array[i][j]);
			}
			System.out.println();
		}
	}
}
