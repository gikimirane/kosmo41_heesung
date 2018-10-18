package com.study.spring;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FileuploadController {
	
	//���� ���ε� ��
	@RequestMapping("/uploadForm")
	public String uploadForm() {
		return "FileUpload/uploadForm";
	}
	
	//������ ��������� ��������
	@RequestMapping("/uploadPath")
	public void uploadPath(HttpServletResponse resp, HttpServletRequest req) throws IOException{
		
		//��Ʈ�ѷ����� �����l ������ ��� ��������
		String path = req.getSession().getServletContext().getRealPath("/resources/upload");
		
		//��Ʈ�ѷ����� ���� ����ϴ� ���
		resp.setContentType("text/html; charset=UTF-8");
		PrintWriter print = resp.getWriter();
		print.println("/upload������ ������ ���");
		print.print(path);
	}
	
	//���� ��� ����
	@RequestMapping("/uploadList")
	public String uploadList(HttpServletResponse resp, Model model, HttpServletRequest req) throws IOException{
		
		//������ ������ ��� ������
		String saveDirectory = req.getSession().getServletContext().getRealPath("/resources/upload");
		
		//File ��ü ����
		File file = new File(saveDirectory);
		
		//���ϸ�� ������
		File[] files = file.listFiles();
		
		Map<String, Integer> fileMap = new HashMap<String, Integer>();
		
		for(File f : files) {
			//Key:���ϸ� , value : ����ũ��
			fileMap.put(f.getName(), (int)Math.ceil(f.length()/1024.0));
		}
		
		model.addAttribute("fileMap", fileMap);
		return "FileUpload/uploadList";
	}
	
	// ���� �ٿ�ε�	
		@RequestMapping("/download")	
		public ModelAndView download(HttpServletRequest req, 
	            HttpServletResponse resp) throws Exception {
		    		
			System.out.println("FundingController�� callDownloadȣ�� ����");
		    
			String fileName = req.getParameter("fileName");
			String oriFileName = req.getParameter("oriFileName");
		    
		    String saveDirectory = 
		    		req.getSession().getServletContext().getRealPath("/resources/upload");
		    
		    File downloadFile = new File(saveDirectory+"/"+fileName);
		    
		    if(!downloadFile.canRead()){
		        throw new Exception("File can't read(������ ã�� �� �����ϴ�)");
		    }
		    
		    ModelAndView mv = new ModelAndView();
		    mv.setViewName("fileDownloadView");
		  	mv.addObject("downloadFile", downloadFile);
		  	mv.addObject("oriFileName", oriFileName);
		  		 
		  	return mv;
		  	//return new ModelAndView("��", "�Ӽ���", "��");
		}	
		
		// ���� ���ε� ó��
		@RequestMapping("/uploadAction")
		public String uploadAction(HttpServletRequest req, Model model){
			
			System.out.println("���Ͼ��ε� ������");
			
			//������ ��������� ��������
			String path = 
				req.getSession().getServletContext().getRealPath("/resources/upload");
			
			//��� ������ ������ �����ϱ� ���� MapŸ���� ����
			Map returnObj = new HashMap();
			
			try{
				/*
				 * ���Ͼ��ε� ���� MultipartHttpServletRequest��ü ����
				 * ��ü ������ ���ÿ� ���Ͼ��ε� �Ϸ��. ������ ������ Multipart�� 
				 * ��°�� �޾Ƽ� ó���Ѵ�.
				 */
				MultipartHttpServletRequest mhsr = (MultipartHttpServletRequest) req;
				
				//���ε����� file�Ӽ� �ʵ��� �̸��� ��� ����
				Iterator itr = mhsr.getFileNames();
				
				MultipartFile mfile = null;			
				String fileName = "";		
				
				//���� �ϳ��� ������ �����ϱ� ���� ListŸ���� ����(�������ϸ�, ��������ϸ� ��)
				List resultList = new ArrayList();
							
				//�����ޱ� : ����
				String title = mhsr.getParameter("title");
					
				File directory = new File(path);
				//���ε��� ���丮�� �ִ��� Ȯ����
				if(!directory.isDirectory()){
					//���丮�� ���ٸ� ������
					directory.mkdirs();
				}
				
				//���ε����� file�Ӽ��� �ʵ��� ������ŭ �ݺ�
				while(itr.hasNext()){
					
					//userfile1, userfile2....��µ�
					fileName = (String)itr.next();
					//System.out.println(fileName);	
					
					//������ ���ε�� �ӽ����ϸ� ������
					mfile = mhsr.getFile(fileName);
					//System.out.println(mfile);//CommonsMultipartFile@1366c0b ������
					
					//�ѱ۱������� ó���� ���ε�� ���ϸ��� �����´�.
					String originalName = 
						new String(mfile.getOriginalFilename().getBytes(),"UTF-8");
					
					//���ϸ��� �����̶�� while���� ó������ ���ư���.
					if("".equals(originalName)){
						continue;
					}
					//System.out.println("originalName:"+originalName);

					//���ϸ��� Ȯ���� ��������
					String ext = originalName.substring(originalName.lastIndexOf('.'));
					
					//���ϸ��� UUID�� �����Ȱ����� ������.
					String saveFileName = getUuid() + ext;
					
					//������ ��ο� ��������
					File serverFullName = new File(path + File.separator + saveFileName);
					
					//���ε��� ������ ������ ���Ͽ� �����Ѵ�.
					mfile.transferTo(serverFullName);
					
					Map file = new HashMap();
					file.put("originalName", originalName);//�������ϸ�
					file.put("saveFileName", saveFileName);//��������ϸ�
					file.put("serverFullName", serverFullName);//������ ����� ��ü��� �� ���ϸ�
					file.put("title", title);//Ÿ��Ʋ
					
					//�� ������ ������ ���� Map�� List�� ����
					resultList.add(file);
				}
				returnObj.put("files", resultList);
			}
			catch(UnsupportedEncodingException e){
				e.printStackTrace();
			}
			catch(IllegalStateException e){
				e.printStackTrace();
			}
			catch(IOException e){
				e.printStackTrace();
			}
			
			model.addAttribute("returnObj", returnObj);	
			
			return "FileUpload/uploadAction";
		}
		
		// uuid ������ �޼ҵ� ����
		/*
		 * UUID(Universally unique identifier), ���� ���� �ĺ���.
		 * UUID.randomUUID().toString() ���� �����ϸ� 
		 * b7389ffc-eca7-40cc-b09b-d46cfdc095bd
		 * �� ���� 4���� �����°� 32���� ���ڷ� �̷���� ���ڿ��� ��ȯ�Ѵ�.
		 */
		public static String getUuid(){
			String uuid = UUID.randomUUID().toString();
			//System.out.println(uuid);		
			uuid = uuid.replaceAll("-", "");
			//System.out.println("������UUID:"+ uuid);
			return uuid;
		}	
	

}
