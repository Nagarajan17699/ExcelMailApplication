
package MailServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import MailService.ExcelMail;
import MailService.sendMailto;

/**
 *
 * @author Nagarajan
 */
public class Excelread extends HttpServlet {

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try{
        String subject = request.getAttribute("subject").toString();
        String message = request.getAttribute("message").toString();
        String filepath = request.getAttribute("file").toString();
          //System.out.println(request.getAttribute("file"));
            ExcelMail em = new ExcelMail();
            sendMailto sm = new sendMailto();
        //System.out.println("Filepath is:"+filepath+" "+subject+" "+message);
        List<String> li = em.readFile(filepath);
       // sm.send(message, subject, s)
       //System.out.println("The size of list:"+li.size());
        for(String s:li)
        {
           System.out.println(sm.send(message, subject, s));
        }
        
        response.sendRedirect("success.jsp");
        }catch(Exception e){
            System.out.println(e);
        }
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
