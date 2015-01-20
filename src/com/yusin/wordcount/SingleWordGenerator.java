package com.yusin.wordcount;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class SingleWordGenerator {
	public void fileToSingleWord(File file) throws IOException {
		// 파일을 읽기 위한 BufferedReader를 생성
		BufferedReader bufReader = new BufferedReader(new FileReader(file));

		//파일을 쓰기 위한 PrintWriter를 생성
		PrintWriter pw = new PrintWriter("E:\\JavaIO\\test-utf8-result.txt");
		
		// BufferedReader안에 readLine 메서드를 사용할 때 리턴 값을 받기 위해 String 형 line 변수를 선언
		String line;

		// 현재 OS의 줄바꿈 기호를 얻어옴
		// String lineSeparator = System.getProperty("line.separator");

		while ((line = bufReader.readLine()) != null) {
			if (!line.equals("")) { // 텍스트 파일 안에 빈 줄을 지움
				
				String[] words = line.split(" ");
				for (String word : words) {
					System.out.println(word);
					
					//잘라진 단어를 쓰기 대상인 파일 안으로 한 줄씩 적어주고 버퍼를 flush함
					pw.println(word);
					pw.flush();
				}
				
			}
		}

		// BufferedReader를 사용한 후에 꼭 닫아줌
		bufReader.close();
		
		//PrintWriter를 사용한 후에 꼭 닫아줌
		pw.close();
	}
}
