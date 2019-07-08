/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.Cycle;

import java.util.Map;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.Session;
import static org.hibernate.criterion.Expression.sql;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import web.session.ORMUtil;

/**
 *
 * @author CR108002
 */
public class SendMails implements Job, SessionAware {

    Session vdk;
    Map session;

    public SendMails() {
        vdk = ORMUtil.getSesionCMS().openSession();
    }

    @Override
    public void setSession(Map session) {
        this.session = session;
    }

    public Map getSession() {
        return session;
    }

    public void execute(JobExecutionContext context)
            throws JobExecutionException {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                String email = "";
                //  ArrayList<AdmDtoCorreosPendientes> arr = new ArrayList<>();
                // arr = AdmConsultas.getCorreosPendientes(o2c);
                // System.out.println("Cantidad "+arr.size());
                // for (int i = 0; i < arr.size(); i++) {
                //     AdmDtoCorreosPendientes co = arr.get(i);
                String anwerResponse = sendMail(email);

                /*   Transaction tn = null;
                    try {
                        tn = o2c.beginTransaction();
                        AdmConsultas.modificarCorreoPendiente(o2c, co.getId(), respuestaEnvio.contains("O2C_Error") ? respuestaEnvio : "ENVIADO", respuestaEnvio.contains("O2C_Error") ? "PENDIENTE" : "ENVIADO");

                        FacDtoFacturaEstados e = new FacDtoFacturaEstados();
                        e = FacConsultas.getFacturaEstado(o2c, co.getClave());
                        e.setEnvioCorreo(respuestaEnvio.contains("O2C_Error") ? "PENDIENTE" : "ENVIADO");                        
                        e.setModificado(Fechas.ya());
                        e.setModificadoPor("SISTEMA");
                        FacConsultas.modificarEstadosFactura(o2c, e);
                        AdmConsultas.bitacora(o2c, "SISTEMA", "Intento de envio de correo estado: " + (respuestaEnvio.contains("O2C_Error") ? "PENDIENTE" : "ENVIADO"));

                        tn.commit();
                    } catch (HibernateException x) {
                        if (tn != null) {
                            tn.rollback();
                        }
                    }
                    System.out.println("Respuesta Envio: " + respuestaEnvio);
                }*/
                vdk.close();
            }
        }
        );
        t.start();
    }

    public String sendMail(String email) {
        String message = "Sended";
        try {
            /*
            Transaction tn = null;
            try {
                tn = o2c.beginTransaction();
                AdmConsultas.modificarIntentoCorreoPendiente(o2c, obj.getId());
                tn.commit();
            } catch (HibernateException x) {
                if (tn != null) {
                    tn.rollback();
                }
            }*/

            HtmlEmail mail = new HtmlEmail();

//             (587 or 2587) 
            //mail.setSSLOnConnect(true);
      
            mail.setSmtpPort(587);
            mail.setStartTLSEnabled(true);
            mail.setHostName("email-smtp.us-east-1.amazonaws.com");           
        //    mail.setHostName("smtp.mail.us-east-1.awsapps.com");

            mail.setAuthentication("AKIA5IWSWKB4PUTALS66", "BM8bBcvVe7gnt60WvejtducQ6lPlI5uCuaaXTXDTn8OR");
            //mail.setAuthentication("no-reply", "lar@22Qh21");

            mail.addHeader("X-Priority", "1");

            // String correos[] = new String[obj.getDestinatarios().split(";").length];
            //   correos = obj.getDestinatarios().split(";");
            //  for (int i = 0; i < correos.length; i++) {
            //      correo.addTo(correos[i]);
            //  }
              System.out.println(email);
            mail.addTo(email);

             mail.setFrom("no-reply@masonryorderdesk.ca", "Masonry");
           // mail.setFrom("no-reply@masonryorderdesk.awsapps.com", "Masonry");
            // correo.setSubject(obj.getAsunto());
            mail.setSubject("Prueba");
            mail.setCharset("UTF-8");

            //correo.setHtmlMsg(obj.getCuerpo());
            mail.setHtmlMsg("<p>Testing mail</p>");

            message = mail.send();
            System.out.println("Verifying mails sended" + message);
        } catch (EmailException ex) {
            message = "E-mail sending process error: " + ex.getMessage();
            //Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "Error de correo", x);
        } finally {
            return message;
        }
    }
}
