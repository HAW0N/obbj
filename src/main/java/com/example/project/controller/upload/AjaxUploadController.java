package com.example.project.controller.upload;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.example.project.service.board.BoardService;
import com.example.project.util.UploadFileUtils;

@Controller
public class AjaxUploadController{
	@Inject
	BoardService boardService;	
	
	@Resource(name="upload_path")
	String upload_path;//파일업로드디렉토리
	
	@RequestMapping("/upload/ajax_form")
	public String upload_form(){
	return "/upload/ajax_form";
	}
	
	@RequestMapping(value = "/upload/ajax_upload", produces = "text/plain;charset=utf-8")
	
	@ResponseBody
	public ResponseEntity<String> ajax_upload(MultipartFile file)throws Exception{
	String filename = UploadFileUtils.uploadFile(upload_path,file.getOriginalFilename(),file.getBytes());
	return new ResponseEntity<String>(filename, HttpStatus.OK);
	}
	
	@ResponseBody
	@RequestMapping("/upload/display_file")
	public ResponseEntity<byte[]>display_file(String file_name)throws Exception{
	InputStream in=null;//스트림
	ResponseEntity<byte[]> entity=null;
	try{
	HttpHeaders headers=new HttpHeaders();
	in=new FileInputStream(file_name);
	file_name = file_name.substring(file_name.indexOf("_")+1);//uuid를제외한파일이름
	headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);//컨텐트타입
	headers.add("Content-Disposition", "attachment; filename=\""+file_name+"\"");//첨부파일정보
	entity=new ResponseEntity<byte[]>(IOUtils.toByteArray(in),headers,HttpStatus.OK);
	}catch(Exception e){
	e.printStackTrace();
	entity=new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
	}finally{
	if(in!=null)
	in.close();
	}
	return entity;
	}
	
	@ResponseBody
	@RequestMapping(value="/upload/delete_file",method=RequestMethod.POST)
	public ResponseEntity<String> delete_file(String file_name){
	new File(file_name.replace("/", File.separator)).delete();
	boardService.delete_attach(file_name);
	return new ResponseEntity<String>("deleted", HttpStatus.OK);
	}
}