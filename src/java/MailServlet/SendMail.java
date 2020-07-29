/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MailServlet;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;

/**
 *
 * @author Nagarajan
 */
public class SendMail extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        List<String> li = new ArrayList<>();
        String filename = "";
        if (ServletFileUpload.isMultipartContent(request)) {
            DiskFileItemFactory factory = new DiskFileItemFactory();
            ServletContext servletContext = this.getServletConfig().getServletContext();
            File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
            factory.setRepository(repository);
            ServletFileUpload upload = new ServletFileUpload(factory);

            //System.out.println();
            try {
                List<FileItem> items = upload.parseRequest(request);
                Iterator<FileItem> iterator = items.iterator();
                while (iterator.hasNext()) {
                    FileItem item = iterator.next();
                    System.out.println();
                    if (!item.isFormField()) {
                        String fileName = item.getName();
                        System.out.println(fileName);
                        String root = "D:\\Netbeans Project\\ServletUploads";
                        //System.out.println(root);
                        File path = new File(root + "/uploads");
                        if (!path.exists()) {
                            boolean status = path.mkdirs();
                        }

                        File uploadedFile = new File(path + "/" + fileName);
                        filename = uploadedFile.getAbsolutePath();
                        item.write(uploadedFile);
                    }
                    else
                    {
                        li.add(item.getString());
                    }
                }
            } catch (FileUploadException e) {
                e.printStackTrace();
                out.println("Upload a Valid file");
            } catch (Exception e) {
                e.printStackTrace();
                out.println("Upload a Valid file");
            }
            
            request.setAttribute("subject", li.get(0));
            request.setAttribute("message", li.get(1));
            request.setAttribute("file", filename);
            //System.out.println("FileName in sendmail is:" + filename+" "+li.get(0));
            request.getRequestDispatcher("Excelread").forward(request,response);
        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
