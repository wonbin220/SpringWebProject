package com.springtest.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.imgscalr.Scalr;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.FileCopyUtils;
			//UploadFileUtils는 static으로 구성된 클래스 메소드들만을 가지기 때문에 그 자체로 테스트 가능
public class UploadFileUtils {
			//스프링의 FileCopyUitls와 유사하게 static 기능을 호출해서 파일을 업로드 할 수 있도록 설계할것.
	//파일업로드하기 위해 필요한 3개 : 파일의 저장경로(uploadPath), 원본 파일의 이름(originalName), 파일데이터(byte[])
	private static final Logger logger = LoggerFactory.getLogger(UploadFileUtils.class);
	
	public static String uploadFile(String uploadPath
								  , String originalName
								  , byte[] fileData)throws Exception{
						//위 3개를 파라미터로 전송하기위해 uploadFile()함수 생성
	//진행루트
		// UUID를 이용한 고유한값 생성 -> UUID와 결합한 업로드 파일이름 생성 -> 파일이 저장될 '/년/월/일'정보 생성
		// -> 업로드 기본경로(uploadPath)와 '/년/월/일' 폴더 생성 -> 기본경로 + 파일경로 + 파일 이름으로 파일 저장
		
		UUID uid = UUID.randomUUID();
		
		String savedName = uid.toString() + "_" + originalName;
		
		String savedPath = calcPath(uploadPath); // 저장될 경로 계싼
		
		File target = new File(uploadPath + savedPath, savedName);
		
		FileCopyUtils.copy(fileData, target); // 원본 파일 저장하는 부분
		
		String formatName = originalName.substring(originalName.lastIndexOf(".")+1);//원본 파일의 확장자.
		
		String uploadedFileName = null;
		
		if(MediaUtils.getMediaType(formatName) != null) { //formatName를 이용해서 이미지 파일인 경우와 그렇지 않은 경우를 나누어 처리
			uploadedFileName = makeThumbnail(uploadPath, savedPath, savedName);//이미지타입인경우 썸네일을 생성
		}else {
			uploadedFileName = makeIcon(uploadPath, savedPath, savedName);//아닌 경우 makeIcon 통해 결과를 만들어냄
		}
		return uploadedFileName;
	}
	
	private static String makeIcon(String uploadPath, String path, String fileName)throws Exception{
		// 경로 처리를 하는 문자열의 치환용도에 불과함
		String iconName = uploadPath + path + File.separator + fileName;
		
		return iconName.substring(uploadPath.length()).replace(File.separatorChar, '/');
	}
	
	private static String calcPath(String uploadPath) {
		// '/년/월/일' 폴더 생성 부분. 
		Calendar cal = Calendar.getInstance();
		
		String yearPath = File.separator+cal.get(Calendar.YEAR);
		
		String monthPath = yearPath + File.separator 
				+ new DecimalFormat("00").format(cal.get(Calendar.MONTH)+1);
		
		String datePath = monthPath + File.separator 
				+  new DecimalFormat("00").format(cal.get(Calendar.DATE));
		
		makeDir(uploadPath, yearPath, monthPath, datePath);//현재 시스템의 날짜에 맞는 데이터를 얻어내, 기본 경로와
												//함께 makeDir()에 전달되어서, 폴더가 생성
		logger.info(datePath);
		
		return datePath; // 리턴값은 최종 결과 폴더를 반환, 내부적으로 폴더를 생성해주는 기능이 필요하기 때문에 
	}					//기본적인 경로(uploadPath)를 파라미터로 전달 받음
	
	private static void makeDir(String uploadPath, String... paths) {
		
		if(new File(uploadPath + paths[paths.length -1]).exists()) {
		return;
	
	}
	for (String path : paths) {
		
		File dirPath = new File(uploadPath + path);
		
		if(! dirPath.exists()) {
			dirPath.mkdir();
			}
		}
	}
										//기본경로, '년/월/일' 폴더(path), 현재 업로드 된 파일 이름
	private static String makeThumbnail(String uploadPath, String path, String fileName)throws Exception{
			
		BufferedImage sourceImg = ImageIO.read(new File(uploadPath + path, fileName));
		//BufferedImage : 실제이미지가 아닌 메모리상의 이미지를 의미하는 객체, 원본파일을 파일을 메모리상으로 로딩하고, 정해진 크기에 맞게 작은 이미지 파일에 원본이미지를 복사
		BufferedImage destImg = Scalr.resize(sourceImg, Scalr.Method.AUTOMATIC, Scalr.Mode.FIT_TO_HEIGHT, 100);
		//FIT_TO_HEIGHT : 썸네일 이미지 파일의 높이를 뒤에 지정된 100px로 동일하게 만들어주는 역할
		String thumbnailName = uploadPath + path + File.separator + "s_" + fileName;
		// 썸네일 이미지의 파일명에 UUID값이 사용된 이후에 반드시 's_'로 시작하도록 설정. 동일한 이름의 파일이지만 's_'로 시작하면 썸네일이미지, 's_'가 없을경우 원본아피일의 이름
		File newFile = new File(thumbnailName);
		String formatName = fileName.substring(fileName.lastIndexOf(".")+1);
		
		ImageIO.write(destImg, formatName.toUpperCase(), newFile);
		return thumbnailName.substring(uploadPath.length()).replace(File.separatorChar,'/');
			//문자열을 치환하는 이유는 브라우저에서 윈도우경로로 사용하는 '￦'문자가 정상적인 경로로 인식되지 않기 때문에 '/'로 치환해줌
		}
	
	
}