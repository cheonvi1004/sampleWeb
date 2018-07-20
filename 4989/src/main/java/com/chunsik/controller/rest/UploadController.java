package com.chunsik.controller.rest;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.chunsik.model.ParamModel;
import com.chunsik.service.PostService;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class UploadController {

	@Autowired
	private MessageSourceAccessor messageSource;

	@Autowired
	private PostService postService;

	@Value("${image.upload.path}")
	private String uploadPath;

	@Value("${image.upload.uri}")
	String uploadUri;

	@Value("${image.upload.maxSize}")
	int uploadMaxSize;

//	@PreAuthorize("hasAuthority('USER') || hasAuthority('ADMIN')")
	@PostMapping("/image/upload")
	@SneakyThrows
	public String imageUpload(@RequestPart MultipartFile upload, @RequestParam("CKEditorFuncNum") String callBack,
			HttpServletRequest request) {
		Map<String, Object> validResultMap = imageFileValidCheck(upload);
		if (!(boolean) validResultMap.get("isValid")) {
			return setCallBackScript4UploadFile(String.valueOf(validResultMap.get("message")));
		}

		File destinationFile;
		String destinationFileName;
		do {
			String sourceFileNameExtension = FilenameUtils.getExtension(upload.getOriginalFilename()).toLowerCase();
			destinationFileName = RandomStringUtils.randomAlphanumeric(32) + "." + sourceFileNameExtension;
			destinationFile = new File(uploadPath.concat(destinationFileName));
		} while (destinationFile.exists());
		destinationFile.getParentFile().mkdirs();
		upload.transferTo(destinationFile);

		return setCallBackScript4UploadFile(callBack, request.getScheme().concat("://").concat(request.getServerName())
				.concat(uploadUri).concat(destinationFileName), messageSource.getMessage("SUCC_UPLOAD_IMAGE"));
	}

//	@PreAuthorize("hasAuthority('USER') || hasAuthority('ADMIN')")
	@PostMapping("/article/upload")
	public String articleUpload(@Valid ParamModel paramModel, BindingResult result) {
		if (!result.hasErrors()) {
			log.info("result : {}", postService.saveArtice(paramModel));
			return "SUCC";
		}
		return "ERR";
	}

	private Map<String, Object> imageFileValidCheck(MultipartFile upload) {
		Map<String, Object> validCheckMap = new HashMap<String, Object>();
		validCheckMap.put("isValid", false);

		// file null check
		if (upload == null) {
			validCheckMap.put("message", messageSource.getMessage("ERR_UPLOAD_IMAGE_NULL"));
			return validCheckMap;
		}
		// file extension check
		Pattern p = Pattern.compile("\\.(jpg|jpeg|png|JPG|JPEG|PNG)$", Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(upload.getOriginalFilename());
		if (!m.find()) {
			validCheckMap.put("message", messageSource.getMessage("ERR_UPLOAD_IMAGE_EXT"));
			return validCheckMap;

		}
		// file size check (5Mb under..)
		if (upload.getSize() > uploadMaxSize) {
			validCheckMap.put("message", messageSource.getMessage("ERR_UPLOAD_IMAGE_SIZE"));
			return validCheckMap;
		}

		validCheckMap.put("isValid", true);
		return validCheckMap;
	}

	private String setCallBackScript4UploadFile(String callBack, String imgUrl, String message) {
		StringBuffer sb = new StringBuffer();
		sb.append("<script type='text/javascript'>window.parent.CKEDITOR.tools.callFunction(");
		sb.append(callBack);
		sb.append(",'");
		sb.append(imgUrl);
		sb.append("','");
		sb.append(message);
		sb.append("')</script>");
		return sb.toString();
	}

	private String setCallBackScript4UploadFile(String message) {
		StringBuffer sb = new StringBuffer();
		sb.append("<script type='text/javascript'>alert('");
		sb.append(message);
		sb.append("'); </script>");
		return sb.toString();
	}

}
