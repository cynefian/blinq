package com.gsd.sreenidhi.user.tickets;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@WebServlet("/uploadTicketAttachment")
@MultipartConfig(fileSizeThreshold=1024*1024*2, // 2MB
				maxFileSize=1024*1024*10,      // 10MB
				maxRequestSize=1024*1024*50)   // 50MB
public class TicketAttachmentUploadController extends HttpServlet {
	 /**
     * Name of the directory where uploaded files will be saved, relative to
     * the web application directory.
     */
    private static final String SAVE_DIR = "uploadFiles";
     
    /**
     * handles file upload
     */
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        // gets absolute path of the web application
        String appPath = request.getServletPath();
        // constructs path of the directory to save uploaded file
        String savePath = appPath + File.separator + SAVE_DIR;
         
        // creates the save directory if it does not exists
        File fileSaveDir = new File(savePath);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdir();
        }
        
        String str;
        StringBuilder jb = new StringBuilder();
        BufferedReader reader = request.getReader();
        while ((str = reader.readLine()) != null) {
        	jb.append(str);
        }
        request.getParameter("data").toString();
        
      /*  for (Part part : request.getParts()) {
            String fileName = extractFileName(part);
            // refines the fileName in case it is an absolute path
            fileName = new File(fileName).getName();
            part.write(savePath + File.separator + fileName);
        }
        request.setAttribute("message", "Upload has been done successfully!");*/
        
        
      /*  if(request.getPart("file")!=null) {
        	Part file = request.getPart("file");
            String filename = null;
            if(file!=null) {
            	 filename = getFilename(file);
                 InputStream filecontent = file.getInputStream();
                  // save file
                 
                 File saveFile = new File(savePath + File.separator + filename);
                 FileOutputStream fos = new FileOutputStream(saveFile);
                 
                 
                 OutputStream outputStream = new FileOutputStream(new File(saveFile.getAbsolutePath()));

         		int read = 0;
         		byte[] bytes = new byte[1024];
         	
         		while ((read = filecontent.read(bytes)) != -1) {
         			outputStream.write(bytes, 0, read);
         		}
         		outputStream.flush();
         		outputStream.close();
                 fos.close();
                 filecontent.close();
         
                 response.setContentType("text/plain");
                 response.setCharacterEncoding("UTF-8");
                 response.getWriter().write("File " + filename + " successfully uploaded");
              
            }
        }*/
        
       
		
        
      /*  for (Part part : request.getParts()) {
            String fileName = extractFileName(part);
            // refines the fileName in case it is an absolute path
            fileName = new File(fileName).getName();
            part.write(savePath + File.separator + fileName);
        }
        request.setAttribute("message", "Upload has been done successfully!");
        */
       /* getServletContext().getRequestDispatcher("/message.jsp").forward(
                request, response);*/
    }
    
    /**
     * Extracts file name from HTTP header content-disposition
     */
    private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length()-1);
            }
        }
        return "";
    }
    
    private static String getFilename(Part part) {
        for (String cd : part.getHeader("content-disposition").split(";")) {
            if (cd.trim().startsWith("filename")) {
                String filename = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
                return filename.substring(filename.lastIndexOf('/') + 1).substring(filename.lastIndexOf('\\') + 1); // MSIE fix.
            }
        }
        return null;
    }
    
}
