package com.kakaopaysec.algorithm;

import java.util.Scanner;

//1. 배열탐색
public class ArrayNavigation {

	
	
	public static void main(String[] args) {
		//변수 셋팅
		Scanner scanner = new Scanner(System.in);		
		int x=0;
		int y=0;
		String [] arrayData;
		int [][] array=null;
		
		//변수 입력(가로열)
		System.out.print(">가로 열\t\t\t : ");
		x = Integer.parseInt(scanner.next());
		//변수 입력(세로열)
		System.out.print(">세로 열\t\t\t : ");
		y = Integer.parseInt(scanner.next());
		//배열 데이터 입력
		System.out.print(">배열 데이터 (,) 로 구분 \t : ");
		arrayData = scanner.next().split(",");
		array = new int[x][y];
		for(int i=0;i<x;i++) {
			for (int j=0;j<y;j++) {
				array[i][j]=Integer.parseInt(arrayData[i*y+j]);
			}
		}
		
		System.out.println(">정답");
		
		//배열탐색 및 출력
		Coordinate coordinate = new Coordinate(0, 0, true,false);
		while(!coordinate.isEnd) {
			if(!(coordinate.getX()==0 && coordinate.getY()==0)) {
				System.out.print(",");	
			}
			System.out.print(array[coordinate.getX()][coordinate.getY()]);
			
			coordinate = searchRoad(x,y,coordinate);
		}
	}
	
	static Coordinate searchRoad(int maxX,int maxY, Coordinate coordinate) {
		
		int x = coordinate.getX();
		int y = coordinate.getY();
		boolean isUp = coordinate.getIsUp();
		boolean moveX = false;
		boolean moveY = false;
		
		if(coordinate.getIsUp()) {
			x--;
			y++;
		}else {
			x++;
			y--;
		}
		
		if(coordinate.getIsUp()) {
			moveX = (x>=0);
			moveY = (y<maxY);
		}else {
			moveX = (x<maxX);
			moveY = (y>=0);
		}
		
		if(moveX&&moveY) {
			if(coordinate.getIsUp()) {
				coordinate.setX(coordinate.getX()-1);
				coordinate.setY(coordinate.getY()+1);
			}else {
				coordinate.setX(coordinate.getX()+1);
				coordinate.setY(coordinate.getY()-1);
			}
		}else if(moveX) {
			coordinate.setX(coordinate.getX()+1);
			coordinate.setIsUp(!coordinate.getIsUp());
		}else if(moveY) {
			coordinate.setY(coordinate.getY()+1);
			coordinate.setIsUp(!coordinate.getIsUp());
		}else {
			if(x==-1&&coordinate.getIsUp()) {
				coordinate.setX(coordinate.getX()+1);
				coordinate.setIsUp(!coordinate.getIsUp());
			}else if(y==-1&&!coordinate.getIsUp()){
				coordinate.setY(coordinate.getY()+1);
				coordinate.setIsUp(!coordinate.getIsUp());
			}			
		}
		
		if(coordinate.getX()==(maxX)|| coordinate.getY()==(maxY)) {
			coordinate.setEnd(true);
		}
		return coordinate;
	}
	
	static class Coordinate{
		private int x;
		private int y;
		private boolean isUp;
		private boolean isEnd;
		
		
		public Coordinate() {
			super();
		}



		public Coordinate(int x, int y, boolean isUp,boolean isEnd) {
			super();
			this.x = x;
			this.y = y;
			this.isUp = isUp;
			this.isEnd = isEnd;
		}



		public boolean isEnd() {
			return isEnd;
		}



		public void setEnd(boolean isEnd) {
			this.isEnd = isEnd;
		}


		public int getX() {
			return x;
		}



		public void setX(int x) {
			this.x = x;
		}



		public int getY() {
			return y;
		}



		public void setY(int y) {
			this.y = y;
		}



		public boolean getIsUp() {
			return isUp;
		}



		public void setIsUp(boolean isUp) {
			this.isUp = isUp;
		}



		@Override
		public String toString() {
			return "Coordinate [x=" + x + ", y=" + y + ", isUp=" + isUp + ", isEnd=" + isEnd + "]";
		}
		
		
		
		
	}
	

}
