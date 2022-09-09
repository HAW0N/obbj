package com.example.project.controller.upload;

import java.io.File;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UploadController {

	@Resource(name="upload_path")
	String upload_path;
	
	@RequestMapping("/upload/input.do")
		public String input(){
		return"upload/input";
	}
	@RequestMapping("/upload/upload.do")
	public ModelAndView upload(MultipartFile file, ModelAndView mav) throws Exception{
		String savedName = file.getOriginalFilename();
		savedName = uploadFile(savedName, file.getBytes());
		mav.setViewName("upload/upload_result");
		mav.addObject("saved_name", savedName);
		return mav;
	}
	
	public String uploadFile(String originalName, byte[] fileData) throws Exception {
		UUID uid = UUID.randomUUID();
		String savedName = uid.toString() + "_" + originalName;
		File target = new File(upload_path, savedName);
		FileCopyUtils.copy(fileData, target);
		return savedName;
	}
}

