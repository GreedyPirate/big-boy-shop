package cn.sina.core.service.freemarker;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Map;

import javax.servlet.ServletContext;

import org.springframework.web.context.ServletContextAware;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class StaticPageServiceImp implements StaticPageService,ServletContextAware{

	private Configuration configuration;
	private FreeMarkerConfigurer freeMarkerConfigurer;
	private ServletContext servletContext;
	

	public void setFreeMarkerConfigurer(FreeMarkerConfigurer freeMarkerConfigurer) {
		this.configuration = freeMarkerConfigurer.getConfiguration();
	}

	/**
	 * 因为需要realpath
	 */
	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}
	
	private String getPath(String url){
		return servletContext.getRealPath(url);
	}

	@Override
	public void productDetail(Map<String,Object> root, Integer productId) {
		Writer out = null;
		try {
			Template template = configuration.getTemplate("productDetail.html");
			//用商品id作为静态页文件名
			String detailPath = this.getPath("/html/product/"+ productId + ".html");
			
			File outFile = new File(detailPath);
			//先创建目录
			File parentDir = outFile.getParentFile();
			if(!parentDir.exists()){
				parentDir.mkdirs();
			}
			
			out = new OutputStreamWriter(new FileOutputStream(outFile), "UTF-8");
			template.process(root, out);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TemplateException e) {
			e.printStackTrace();
		}finally{
			if(out!=null){
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}




}
