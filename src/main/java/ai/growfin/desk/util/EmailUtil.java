package ai.growfin.desk.util;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;

import java.io.IOException;

public class EmailUtil {
    public static Boolean sendEmailToCustomer(Long ticketId, String customerEmailId) throws IOException {
        Email from = new Email("yogesh@sinecycle.com");
        String subject = "New response added to ticket id " + ticketId;
        Email to = new Email(customerEmailId);
        Content content = new Content("text/plain", "Response has been added to ticket");
        Mail mail = new Mail(from, subject, to, content);

        SendGrid sg = new SendGrid(System.getenv("SENDGRID_API_KEY"));
        Request request = new Request();
        Response response;
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            response = sg.api(request);
            System.out.println(response.getStatusCode());
            System.out.println(response.getBody());
            System.out.println(response.getHeaders());
        } catch (IOException ex) {
            throw ex;
        }
        return response.getStatusCode() == 200;
    }
}
