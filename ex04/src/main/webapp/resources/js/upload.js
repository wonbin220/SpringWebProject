function checkImageType(fileName){
	// 정규표현식을 이용해서 파일의 확장자가 존재하는지를 검사
	var pattern = /jpg|gif|png|jpeg/i;
	 // i: 대,소문자의 구분 없음.
	return fileName.match(pattern);

}

function getFileInfo(fullName){//파일 이름 줄여주는 기능. 첨부파일의 이름이 UUID와  첨부파일의 이름이 결합될때 
								//'_'가 사용되는 것을 이용해서 원래의 파일 이름만을 추출
	var fileName,imgsrc, getLink;
		//fileName: 경로나 UUID가 제외된 파일의 이름, imgsrc:화면상에 보여주는 썸네일 혹은 파일이미지의 경로, getLink: 원본 파일을 볼수있는 링크
	var fileLink;
	
	if(checkImageType(fullName)){
		imgsrc = "/displayFile?fileName="+fullName;
		fileLink = fullName.substr(14);
		
		var front = fullName.substr(0,12); // /2015/07/01/   //'/년/월/일' 경로를 추출
		var end = fullName.substr(14); //파일 이름 앞의 's_'를 제거하는 용도.
		
		getLink = "/displayFile?fileName="+front + end;
		
	}else{
		imgsrc ="/resources/dist/img/file.png";
		fileLink = fullName.substr(12);
		getLink = "/displayFile?fileName="+fullName;
	}
	fileName = fileLink.substr(fileLink.indexOf("_")+1);
	
	return  {fileName:fileName, imgsrc:imgsrc, getLink:getLink, fullName:fullName};
	
}

