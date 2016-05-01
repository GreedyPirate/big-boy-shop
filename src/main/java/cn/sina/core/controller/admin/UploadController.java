package cn.sina.core.controller.admin;

import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.fckeditor.response.UploadResponse;

import org.apache.commons.io.FilenameUtils;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import cn.sina.core.web.Constants;
import cn.sina.core.web.DateUtils;
import cn.sina.core.web.ResponseUtils;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

/**
 * 上传控制器
 * @author Jaynnay
 *
 */
@Controller
@RequestMapping(value = "/upload")
public class UploadController {
	
	/**
	 * 图片上传功能：用jersey保存在图片服务器上，并把相对路径保存到数据库中
	 * @param pic
	 */
	@RequestMapping(value = "/uploadPic.do")
	//value表示页面传递的参数必须是pic
	public void uploadPic(@RequestParam(required=true,value="pic") MultipartFile pic, HttpServletResponse response){
		//使用毫秒级的日期格式作为文件名，毫秒:SSS
		StringBuilder filename = new StringBuilder(DateUtils.dateFilename());
		
		//假设1毫秒内有很多文件同时上传，则用001的格式区分
		Random random = new Random();
		for (int i = 0; i < 3; i++) {
			filename.append(random.nextInt(10));
		}
		
		//获取文件的后缀名
		String subfix = FilenameUtils.getExtension(pic.getOriginalFilename());
		
		Client client = new Client();
		
		//这是图片服务器的访问路径，8088
		String path = "upload/"+ filename.toString()+"."+subfix;
		String url = Constants.IMAGE_SERVER_URL+path;
		WebResource resource = client.resource(url);
		
		//把要上传的图片读取到内存中
		try {
			resource.put(String.class, pic.getBytes());
			
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("url", url);
			jsonObject.put("path", path);
			
			ResponseUtils.renderJson(response, jsonObject.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/uploadFck.do")
	public void uploadFck(HttpServletRequest request , HttpServletResponse response){
		MultipartHttpServletRequest springRequest = (MultipartHttpServletRequest)request;
		
		Map<String, MultipartFile> fileMap = springRequest.getFileMap();
		//遍历上传文件
		if(fileMap != null && fileMap.size() > 0){
			Set<Entry<String,MultipartFile>> files =  fileMap.entrySet();
			
			for (Entry<String,MultipartFile> file : files) {
				MultipartFile pic = file.getValue();
				StringBuilder filename = new StringBuilder(DateUtils.dateFilename());
				
				//假设1毫秒内有很多文件同时上传，则用001的格式区分
				Random random = new Random();
				for (int i = 0; i < 3; i++) {
					filename.append(random.nextInt(10));
				}
				
				//获取文件的后缀名
				String subfix = FilenameUtils.getExtension(pic.getOriginalFilename());
				
				Client client = new Client();
				
				//这是图片服务器的访问路径，8088
				String path = "upload/"+ filename.toString()+"."+subfix;
				String url = Constants.IMAGE_SERVER_URL+path;
				WebResource resource = client.resource(url);
				
				//把要上传的图片读取到内存中
				try {
					resource.put(String.class, pic.getBytes());
					
					//把url发给fck
					UploadResponse ok = UploadResponse.getOK(url);
					response.getWriter().print(ok);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
