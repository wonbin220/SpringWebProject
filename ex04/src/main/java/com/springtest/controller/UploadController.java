package com.springtest.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.springtest.util.MediaUtils;
import com.springtest.util.UploadFileUtils;

@Controller
public class UploadController {
	
	@Resource(name="uploadPath")
	private String uploadPath;
	
	private static final Logger logger = LoggerFactory.getLogger(UploadController.class);

	@RequestMapping(value="uploadForm", method = RequestMethod.GET)
	public void uploadForm() {
	}
	
	@RequestMapping(value="uploadForm", method = RequestMethod.POST)
	public String uploadForm(MultipartFile file, Model model)throws Exception {
		
		logger.info("originalName: " + file.getOriginalFilename());
		logger.info("size: " + file.getSize());
		logger.info("contentType :" + file.getContentType());
		
		String savedName = uploadFile(file.getOriginalFilename(), file.getBytes());
							
		model.addAttribute("savedName", savedName);
	
		return "uploadResult";
	}

	private String uploadFile(String originalName, byte[] fileData)throws Exception{
							//원본 파일과 파일데이터를 byte[]로 변환한 정보를 파라미터로 처리해서 실제로 파일을 업로드
		UUID uid = UUID.randomUUID();
		//UUID는 중복되지 않는 고유한 키 값을 설정할때 사용, 파일업로드에서 주의할 점은 동일한 경로에 동일한 이름의 파일을 업로드하는 것
		//UUID와 같이 (거의)고유한 값을 생성해서 처리하면 이 문제를 피할 수 있음.
		String savedName = uid.toString() + "_" + originalName;
		//ex)41412abee-aefaesf3-4d3-5434_파일.jpg  => UUID값_파일이름.jpg
		File target = new File(uploadPath, savedName);
					
		FileCopyUtils.copy(fileData, target);
		//실제 파일 처리는 스프링에서 제공하는 FileCopyUtils(파일데이터를 파일로 처리하거나, 복사하는 등이 작업에 유용하게 사용됨)
		//byte[]나 InputStream이 존재하는 경우 유용하게 사용됨
		return savedName;
	}
	
	@RequestMapping(value="uploadAjax", method = RequestMethod.GET)
	public void uploadAjax() {
	}
	
/*	@ResponseBody		//produces : 한국어를 정상적으로 전송하기 위한 간단한 설정
	@RequestMapping(value="uploadAjax", 
					method = RequestMethod.POST,
					produces="text/plain;charset=UTF-8")		
	public ResponseEntity<String> uploadAjax(MultipartFile file)throws Exception {
		
		logger.info("originalName: " + file.getOriginalFilename());
		logger.info("size: " + file.getSize());
		logger.info("contentType : " + file.getContentType());
		
		return new ResponseEntity<>(file.getOriginalFilename(), HttpStatus.CREATED);
	}			// HttpStatus.CREATED: 원하는 리소스가 정상적으로 생성되었다는 상태코드, HttpStatus.OK도 가능
*/
	
	//위 메소드와 다른차이는 UploadFileUtils와 MediaUtil에서 파일 업로드에 대한 대부분의 처리가 재구성 됨.
	@ResponseBody		
	@RequestMapping(value="uploadAjax", 
					method = RequestMethod.POST,
					produces="text/plain;charset=UTF-8")		
	public ResponseEntity<String> uploadAjax(MultipartFile file)throws Exception {
		
		logger.info("originalName: " + file.getOriginalFilename());
	
		return new ResponseEntity<>(UploadFileUtils.uploadFile(uploadPath, file.getOriginalFilename(), file.getBytes()),HttpStatus.CREATED);
		//리턴값은 내부적으로 UploadFileUtils의 uploadFile()을 사용하도록 수정
	}
	
	@ResponseBody
	@RequestMapping("displayFile") //displayFile()은 파라미터로 브라우저에서 전송받기를 원하는 파일의 이름을 받음. '/년/월/일/'의 형태.
	public ResponseEntity<byte[]> displayFile(String fileName)throws Exception{
		
		InputStream in = null;
		ResponseEntity<byte[]> entity = null;
		
		logger.info("FILE NAME : " + fileName);
		
		try {
			String formatName = fileName.substring(fileName.lastIndexOf(".")+1);
			
			MediaType mType = MediaUtils.getMediaType(formatName);//가장 먼저 하는 작업은 파일이름에서 확장자를 추출. 
			
			HttpHeaders headers = new HttpHeaders();
			
			in = new FileInputStream(uploadPath + fileName);
			
			if (mType != null) { 
				headers.setContentType(mType);//이미지 타입인경우  적절한 MIME타입을 지정
			}else {//이미지가 아닌경우 MIME타입을 다운로드용으로 사용되는 'application/octet-stream'으로 지정, 
				   //브라우저는 이 MIME타입을 보고, 사용자에게 자동으로 다운로드 창을 열어줌
				fileName = fileName.substring(fileName.indexOf("_")+1);
				headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
				headers.add("Content-Disposition", "attachment; filename=\""
				+new String(fileName.getBytes("UTF-8"), "ISO-8859-1")+"\"");//다운로드할때 사용자에게 보이는 파일의 이름이르로 한글 처리를 해서 전송 
			}																//한글 파일인경우 다운로드하면 파일의 이름이 깨져서 나오기때문에 인코딩처리가 필요
			
				entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in)//실제로 데이터를 읽는 부분은 commons라이브러리 기능을 활용해서
						, headers					//대상 파일에서 데이터를 읽어내는 IOUtils.toByteArray()
						, HttpStatus.CREATED);
				
		}catch(Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
		}finally {
			in.close();
		}
		return entity;
		//리턴타입은 ResponseEntity<byte[]>로 작성 결과는 셀지로 파일의 데이터가 됨. @ResponseBody를 이용해서 byte[] 데이터가 그대로 전송될것을 명시
		// 정상적으로 작동하면 'displayFile?fileName=/년/월/일/파일명'을 호출해서 확인가능
	}
}
