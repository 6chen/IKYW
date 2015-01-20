package com.yusin.wordcount;

import java.io.File;
import java.io.IOException;

public class Main {
	public static void main(String[] args) throws IOException {
		/*
		 * 텍스트 파일의 인코딩 문제가 있어서 한글 OS에서 텍스트 파일이  디폴터로 utf-8을 설정되어 있고 
		 * 중국어 OS에서 텍스트 파일이 디폴터로 gbk로 설정되어 있다.
		 * 
		*/ 
		
		//파일 생성
		File srcFile = new File("E:\\JavaIO","test-utf8.txt");
		File tarFile = new File("E:\\JavaIO", "test-utf8-result.txt");
		
		//텍스트 파일을 낱 단어로 전환해 주는 생성기를 생성
		SingleWordGenerator swg = new SingleWordGenerator();
		
		//전환해야 하는 파일을 fileToSingleWord함수 안에 입력함
		swg.fileToSingleWord(srcFile, tarFile);
	}
}
