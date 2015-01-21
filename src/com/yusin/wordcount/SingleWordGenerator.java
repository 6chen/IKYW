package com.yusin.wordcount;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SingleWordGenerator {
	
	//정규식에 적용할 패턴을 정의함
	private String PATTERN = "([a-z]|[A-Z]|\')+";
	
	public void fileToSingleWord(File srcFile, File tarFile) throws IOException {
		// 파일을 읽기 위한 BufferedReader를 생성
		BufferedReader bufReader = new BufferedReader(new FileReader(srcFile));

		//Map를 생성하여 결과를 "Key, Value" 형태로 저장해 둠. Key는 낱 단어이고, Value는 낱 단어가 나타나는 횟수다.
		Map<String, Integer> cntMap = new HashMap<String, Integer>();
		
		// 파일을 쓰기 위한 PrintWriter를 생성
		PrintWriter pw = new PrintWriter(tarFile);

		// BufferedReader안에 readLine 메서드를 사용할 때 리턴 값을 받기 위해 String 형 line 변수를 선언
		String line;

		// 현재 OS의 줄바꿈 기호를 얻어옴. 일단 적어 두고 나중에 지울 수도 있다.
		// String lineSeparator = System.getProperty("line.separator");

		while ((line = bufReader.readLine()) != null) {
			if (!line.equals("")) { // 텍스트 파일 안에 빈 줄을 지움
				
				//패턴을 정의하여 line에거 적용시킴
				Pattern pattern = Pattern.compile(PATTERN);
				Matcher matcher = pattern.matcher(line);
				
				while(matcher.find()){//만약에 정의한 패턴에 어울리는 것 있으면 낱 단어를 읽음
					//읽어온 단어를 전체 소문자로 전환함
					String wordMatched = matcher.group().toLowerCase();
					//자른 결과인 낱 단어가 Map 안에 존재한지 판단하여 존재하면 value를 증가시킴, 존재하지 않으면 새로운 Entry를 추가함.
					boolean exists = cntMap.containsKey(wordMatched);
					if (exists) {
						cntMap.replace(wordMatched, cntMap.get(wordMatched) + 1);
					} else {
						cntMap.put(wordMatched, 1);
					}
				}
			}
		}
		
		//Map안에 있는 모든 결과를 출력하기 위해 먼저 Entry Set를 생성한다.
		Set<Entry<String, Integer>> cntMapEntrySet = cntMap.entrySet();
		for (Entry<String, Integer> entry : cntMapEntrySet) {
			//낱 단어 및 개수를 파일 안으로 한 줄씩 적어주고 버퍼를 flush함
			pw.println(entry.getKey() + " : " + entry.getValue());
			pw.flush();
		}

		// PrintWriter를 사용한 후에 꼭 닫아줌
		pw.close();
		
		// BufferedReader를 사용한 후에 꼭 닫아줌
		bufReader.close();
	}
}
