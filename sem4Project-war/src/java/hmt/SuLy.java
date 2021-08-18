/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hmt;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author hmtua
 */
public class SuLy {

    public static List days(String dateStart, String dateEnd) {
        LocalDate start = LocalDate.parse(dateStart);
        LocalDate end = LocalDate.parse(dateEnd);
        List<String> totalDates = new ArrayList<>();
        while (!start.isAfter(end)) {
            totalDates.add(start.toString().substring(8, 10).replaceAll("\\s", ""));
            start = start.plusDays(1);
        }
        return totalDates;
    }

    public String dateTruocDo(String date) {
        String thang = null, ngay = null, nam;
        ngay = date.substring(8, 10);
        thang = date.substring(5, 7);
        nam = date.substring(0, 4);
        int a, b, c;
        a = Integer.parseInt(ngay);
        b = Integer.parseInt(thang);
        c = Integer.parseInt(nam);
        a = 29;
        b--;
        if (b < 1) {
            c--;
            b = 1;
        }
        ngay = String.valueOf(a);
        thang = String.valueOf(b);
        nam = String.valueOf(c);
        String string = nam + "-" + thang + "-" + ngay;
        LocalDate convertedDate = LocalDate.parse(string, DateTimeFormatter.ofPattern("yyyy-M-d"));
        convertedDate = convertedDate.withDayOfMonth(convertedDate.getMonth().length(convertedDate.isLeapYear()));
        return convertedDate.toString();
    }

    public String datePhiaSau(String date) {
        LocalDate end = YearMonth.now().atEndOfMonth();
        String thang = null, ngay = null, nam;
        ngay = date.substring(8, 10);
        thang = date.substring(5, 7);
        nam = date.substring(0, 4);
        int a, b, c;
        a = Integer.parseInt(ngay);
        b = Integer.parseInt(thang);
        c = Integer.parseInt(nam);

        String dateEnd = end.toString().substring(8, 10);
        int dateEndInt = Integer.parseInt(dateEnd);
        a++;
        if (a > dateEndInt) {
            b++;
            a = 1;
            if (b > 12) {
                c++;
                b = 1;
            }
        }

        ngay = String.valueOf(a);
        if (ngay.contains("0") == false) {
            ngay = "0" + ngay;
        }
        thang = String.valueOf(b);
        if (thang.contains("0") == false) {
            thang = "0" + thang;
        }
        nam = String.valueOf(c);
        String string = nam + "-" + thang + "-" + ngay;
        return string;
    }

    public static void sendMail(String emailNhan, String emailGui, String pass, String Mony, String CongThanhToan, String date, String MaGiaoDich) throws AddressException, MessagingException, UnsupportedEncodingException {
        final String fromEmail = emailGui;
        // Mat khai email cua ban
        final String password = pass;
        // dia chi email nguoi nhan
        final String toEmail = emailNhan;
        final String subject = "Java Example Test";
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
        props.put("mail.smtp.port", "587"); //TLS Port
        props.put("mail.smtp.auth", "true"); //enable authentication
        props.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS
        Session session1 = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        });
        MimeMessage message = new MimeMessage(session1);
        message.setFrom(new InternetAddress(fromEmail, "Bugs"));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
        message.setSubject(subject);
        message.setSubject("Bungs");
        String htmlContent = "<div class=\"\"><div class=\"aHl\"></div><div id=\":q4\" tabindex=\"-1\"></div><div id=\":ox\" class=\"ii gt\"><div id=\":oy\" class=\"a3s aiL msg-6019542432575260588\"><u></u>\n"
                + "            <div class=\"m_-6019542432575260588body\" style=\"margin:0;padding:0;width:100%;background-color:#f3f3f3\">\n"
                + "                <span style=\"display:none;font-size:0;max-height:0;width:0;line-height:0\"></span>\n"
                + "                <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n"
                + "                    <tbody><tr>\n"
                + "                            <td align=\"center\" class=\"m_-6019542432575260588minwidth\" style=\"min-width:512px;background-color:#f3f3f3\">\n"
                + "                                <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n"
                + "                                    <tbody><tr>\n"
                + "                                            <td>\n"
                + "                                                <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n"
                + "                                                    <tbody><tr>\n"
                + "                                                            <td align=\"center\">\n"
                + "                                                                <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"m_-6019542432575260588w100\" width=\"512\">\n"
                + "                                                                    <tbody><tr>\n"
                + "                                                                            <td align=\"center\" class=\"m_-6019542432575260588hide\" style=\"padding-top:10px;padding-bottom:15px\">\n"
                + "                                                                                <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"95%\">\n"
                + "                                                                                    <tbody><tr>\n"
                + "                                                                                        </tr>\n"
                + "                                                                                    </tbody></table>\n"
                + "                                                                            </td>\n"
                + "                                                                        </tr>\n"
                + "\n"
                + "                                                                        <tr>\n"
                + "                                                                            <td align=\"center\" style=\"background-color: red\">\n"
                + "                                                                                <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n"
                + "                                                                                    <tbody>\n"
                + "                                                                                        <tr>\n"
                + "                                                                                            <td style=\"border-top:3px solid red;border-radius:4px 4px 0 0;background-color: red\"></td>\n"
                + "                                                                                        </tr>\n"
                + "                                                                                    </tbody>\n"
                + "                                                                                </table>\n"
                + "                                                                            </td>\n"
                + "                                                                        </tr>\n"
                + "\n"
                + "\n"
                + "                                                                        <tr>\n"
                + "                                                                            <td align=\"center\" class=\"m_-6019542432575260588padt10m m_-6019542432575260588padb10m\" style=\"background-color:#fdfdfe;padding-top:15px;padding-bottom:15px\">\n"
                + "                                                                                <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"90%\">\n"
                + "                                                                                    <tbody><tr>\n"
                + "                                                                                            <td align=\"left\">\n"
                + "                                                                                                <div style=\"display:block;border:0;font-size:40px;font-weight:bold;font-family:sans-serif;color:#222222\">\n"
                + "                                                                                                    <b> <samp>Bung</samp><samp style=\"color: red\">s</samp></b>\n"
                + "                                                                                                </div>\n"
                + "                                                                                            </td>\n"
                + "                                                                                        </tr>\n"
                + "                                                                                    </tbody></table>\n"
                + "                                                                            </td>\n"
                + "                                                                        </tr>\n"
                + "\n"
                + "                                                                        <tr>\n"
                + "                                                                            <td align=\"center\" style=\"background-color:#f5f5f6\">\n"
                + "                                                                                <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n"
                + "                                                                                    <tbody>\n"
                + "                                                                                        <tr>\n"
                + "                                                                                            <td style=\"border-top:1px solid #f5f5f6\"></td>\n"
                + "                                                                                        </tr>\n"
                + "                                                                                    </tbody>\n"
                + "                                                                                </table>\n"
                + "                                                                            </td>\n"
                + "                                                                        </tr>\n"
                + "\n"
                + "\n"
                + "                                                                        <tr>\n"
                + "                                                                            <td align=\"center\" style=\"background-color:white;padding-top:25px;padding-bottom:0\">\n"
                + "                                                                                <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"90%\">\n"
                + "                                                                                    <tbody><tr>\n"
                + "                                                                                            <td>\n"
                + "                                                                                                <h1 class=\"m_-6019542432575260588h1m\" style=\"font-size:22px;line-height:28px;letter-spacing:-.20px;margin:10px 0 16px 0;font-family:Helvetica Neue,Arial,sans-serif;color:red;text-align:left\">\n"
                + "                                                                                                    Electronic Bill</h1>\n"
                + "                                                                                            </td>\n"
                + "                                                                                        </tr>\n"
                + "\n"
                + "                                                                                        <tr>\n"
                + "                                                                                            <td>\n"
                + "                                                                                                <p class=\"m_-6019542432575260588h2m\" style=\"margin:0 0 15px 0;font-size:14px;font-family:Helvetica Neue,Arial,sans-serif;color:#3c4043;text-align:left;line-height:24px\">\n"
                + "                                                                                                    Hi,<br>\n"
                + "                                                                                                    Thank you very much for using our service.\n"
                + "                                                                                                </p>\n"
                + "                                                                                            </td>\n"
                + "                                                                                        </tr>\n"
                + "                                                                                    </tbody></table>\n"
                + "                                                                            </td>\n"
                + "                                                                        </tr>\n"
                + "\n"
                + "\n"
                + "                                                                        <tr>\n"
                + "                                                                            <td align=\"center\" style=\"background-color:white;padding-top:15px;padding-bottom:10px\">\n"
                + "                                                                                <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"90%\">\n"
                + "                                                                                    <tbody><tr>\n"
                + "                                                                                            <td style=\"font-size:16px;font-family:Helvetica Neue,Arial,sans-serif;color:#969696;text-align:center\">\n"
                + "                                                                                                Payment amount\n"
                + "                                                                                            </td>\n"
                + "                                                                                        </tr>\n"
                + "\n"
                + "                                                                                        <tr>\n"
                + "                                                                                            <td style=\"padding-top:5px;font-size:28px;font-family:Helvetica Neue,Arial,sans-serif;color:#3c4043;text-align:center;line-height:1.2em;font-weight:500\">\n"
                + "                                                                                                " + Mony + "\n"
                + "                                                                                            </td>\n"
                + "                                                                                        </tr>\n"
                + "                                                                                    </tbody></table>\n"
                + "                                                                            </td>\n"
                + "                                                                        </tr>\n"
                + "\n"
                + "\n"
                + "                                                                        <tr>\n"
                + "                                                                            <td align=\"center\" style=\"background-color:white;padding-top:10px;padding-bottom:10px\">\n"
                + "                                                                                <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"90%\">\n"
                + "                                                                                    <tbody><tr>\n"
                + "                                                                                            <td style=\"font-size:13px;font-family:Helvetica Neue,Arial,sans-serif;color:#969696;text-align:left;font-weight:bold;padding-bottom:5px\">\n"
                + "                                                                                                INFORMATION OF BILLING\n"
                + "                                                                                            </td>\n"
                + "                                                                                        </tr>\n"
                + "\n"
                + "                                                                                        <tr>\n"
                + "                                                                                            <td align=\"center\" style=\"background-color:white\">\n"
                + "                                                                                                <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n"
                + "                                                                                                    <tbody>\n"
                + "                                                                                                        <tr>\n"
                + "                                                                                                            <td style=\"border-top:1px solid #ececec\"></td>\n"
                + "                                                                                                        </tr>\n"
                + "                                                                                                    </tbody>\n"
                + "                                                                                                </table>\n"
                + "                                                                                            </td>\n"
                + "                                                                                        </tr>\n"
                + "                                                                                    </tbody></table>\n"
                + "                                                                            </td>\n"
                + "                                                                        </tr>\n"
                + "\n"
                + "                                                                        <tr>\n"
                + "                                                                            <td align=\"center\" style=\"background-color:white;padding-top:0;padding-bottom:20px\">\n"
                + "                                                                                <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"90%\">\n"
                + "                                                                                    <tbody><tr>\n"
                + "                                                                                            <td class=\"m_-6019542432575260588h2m\" style=\"padding-top:5px;padding-bottom:10px;font-size:14px;font-family:Helvetica Neue,Arial,sans-serif;color:#3c4043;text-align:left;line-height:1.55em;vertical-align:top\" width=\"70%\">\n"
                + "                                                                                                <div style=\"color:#737373;margin:0px;font-size:12px;line-height:24px;font-weight:normal\">\n"
                + "                                                                                                    Service\n"
                + "                                                                                                </div>\n"
                + "\n"
                + "                                                                                                <div style=\"color:#3c4043;display:block;font-family:sans-serif;font-size:14px;font-weight:bold;line-height:24px;margin:0px;padding:0px;text-align:left;text-decoration:none;padding-right:5px\">\n"
                + "                                                                                                    Bungs Joint Stock Company <span class=\"il\">Viet</span> Nam\n"
                + "                                                                                                </div>\n"
                + "\n"
                + "                                                                                            </td>\n"
                + "\n"
                + "                                                                                            <td class=\"m_-6019542432575260588h2m\" style=\"padding-top:5px;font-size:14px;font-family:Helvetica Neue,Arial,sans-serif;color:#3c4043;text-align:left;line-height:1.55em;vertical-align:top\" width=\"30%\">\n"
                + "                                                                                                <div style=\"color:#737373;margin:0px;font-size:12px;line-height:24px;font-weight:normal\">\n"
                + "                                                                                                    Payment gateways\n"
                + "                                                                                                </div>\n"
                + "\n"
                + "                                                                                                <div style=\"color:#3c4043;display:block;font-family:sans-serif;font-size:14px;font-weight:normal;line-height:18px;margin:0px;padding:0px;text-align:left;text-decoration:none;padding-right:5px\">\n"
                + "                                                                                                    " + CongThanhToan + "\n"
                + "                                                                                                </div>\n"
                + "                                                                                            </td>\n"
                + "                                                                                        </tr>\n"
                + "\n"
                + "                                                                                        <tr>\n"
                + "                                                                                            <td class=\"m_-6019542432575260588h2m\" style=\"padding-top:5px;padding-bottom:10px;font-size:14px;font-family:Helvetica Neue,Arial,sans-serif;color:#3c4043;text-align:left;line-height:1.55em;vertical-align:top\" width=\"70%\">\n"
                + "                                                                                                <div style=\"color:#737373;margin:0px;font-size:12px;line-height:24px;font-weight:normal\">\n"
                + "                                                                                                    Date\n"
                + "                                                                                                </div>\n"
                + "\n"
                + "                                                                                                <div style=\"color:#3c4043;display:block;font-family:sans-serif;font-size:14px;font-weight:normal;line-height:18px;margin:0px;padding:0px;text-align:left;text-decoration:none;padding-right:5px\">\n"
                + "                                                                                                    " + date + "\n"
                + "                                                                                                </div>\n"
                + "                                                                                            </td>\n"
                + "\n"
                + "                                                                                            <td class=\"m_-6019542432575260588h2m\" style=\"padding-top:5px;padding-bottom:10px;font-size:14px;font-family:Helvetica Neue,Arial,sans-serif;color:#3c4043;text-align:left;line-height:1.55em;vertical-align:top\" width=\"30%\">\n"
                + "\n"
                + "                                                                                                <div style=\"color:#737373;margin:0px;font-size:12px;line-height:24px;font-weight:normal\">\n"
                + "                                                                                                    Trading code\n"
                + "                                                                                                </div>\n"
                + "\n"
                + "                                                                                                <div style=\"color:#3c4043;display:block;font-family:sans-serif;font-size:14px;font-weight:normal;line-height:18px;margin:0px;padding:0px;text-align:left;text-decoration:none;padding-right:5px\">\n"
                + "                                                                                                    " + MaGiaoDich + "\n"
                + "                                                                                                </div>\n"
                + "                                                                                            </td>\n"
                + "                                                                                        </tr>\n"
                + "                                                                                    </tbody></table>\n"
                + "                                                                            </td>\n"
                + "                                                                        </tr>\n"
                + "\n"
                + "\n"
                + "                                                                        <tr>\n"
                + "                                                                            <td align=\"center\" style=\"background-color:white;padding-top:10px;padding-bottom:10px\">\n"
                + "                                                                                <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"90%\">\n"
                + "                                                                                    <tbody><tr>\n"
                + "                                                                                            <td style=\"font-size:13px;font-family:Helvetica Neue,Arial,sans-serif;color:#969696;text-align:left;font-weight:bold;padding-bottom:5px\">\n"
                + "                                                                                                TRANSACTION DETAILS \n"
                + "                                                                                            </td>\n"
                + "                                                                                        </tr>\n"
                + "\n"
                + "                                                                                        <tr>\n"
                + "                                                                                            <td align=\"center\" style=\"background-color:white\">\n"
                + "                                                                                                <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n"
                + "                                                                                                    <tbody>\n"
                + "                                                                                                        <tr>\n"
                + "                                                                                                            <td style=\"border-top:1px solid #ececec\"></td>\n"
                + "                                                                                                        </tr>\n"
                + "                                                                                                    </tbody>\n"
                + "                                                                                                </table>\n"
                + "                                                                                            </td>\n"
                + "                                                                                        </tr>\n"
                + "                                                                                    </tbody></table>\n"
                + "                                                                            </td>\n"
                + "                                                                        </tr>\n"
                + "\n"
                + "\n"
                + "                                                                        <tr>\n"
                + "                                                                            <td align=\"center\" style=\"background-color:white;padding-top:0;padding-bottom:20px\">\n"
                + "                                                                                <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"90%\">\n"
                + "                                                                                    <tbody><tr>\n"
                + "                                                                                            <td style=\"padding-top:5px;padding-bottom:5px;font-size:14px;font-family:Helvetica Neue,Arial,sans-serif;color:#3c4043;text-align:left;line-height:1.55em\" width=\"70%\">\n"
                + "                                                                                                <div style=\"color:#3c4043;margin:0px;font-size:12px;line-height:22px;font-weight:normal;font-size:15px;padding-right:10px\">\n"
                + "                                                                                                    Mony\n"
                + "                                                                                                </div>\n"
                + "                                                                                            </td>\n"
                + "                                                                                            <td style=\"padding-top:5px;padding-bottom:5px;font-size:14px;font-family:Helvetica Neue,Arial,sans-serif;color:#3c4043;text-align:left;line-height:1.55em\" width=\"30%\">\n"
                + "                                                                                                <div style=\"color:#3c4043;margin:0px;font-size:12px;line-height:22px;font-weight:normal;font-size:15px\">\n"
                + "                                                                                                    " + Mony + "\n"
                + "                                                                                                </div>\n"
                + "                                                                                            </td>\n"
                + "                                                                                        </tr>\n"
                + "\n"
                + "                                                                                        <tr>\n"
                + "                                                                                            <td style=\"padding-top:5px;padding-bottom:5px;font-size:14px;font-family:Helvetica Neue,Arial,sans-serif;color:#3c4043;text-align:left;line-height:1.55em\" width=\"70%\">\n"
                + "                                                                                                <div style=\"color:#3c4043;margin:0px;font-size:12px;line-height:20px;font-weight:normal;font-size:15px;padding-right:10px\">\n"
                + "                                                                                                    Transaction fee\n"
                + "                                                                                                </div>\n"
                + "                                                                                            </td>\n"
                + "                                                                                            <td style=\"padding-top:5px;padding-bottom:5px;font-size:14px;font-family:Helvetica Neue,Arial,sans-serif;color:#3c4043;text-align:left;line-height:1.55em\" width=\"30%\">\n"
                + "                                                                                                <div style=\"color:#3c4043;margin:0px;font-size:12px;line-height:22px;font-weight:normal;font-size:15px\">\n"
                + "                                                                                                    0\n"
                + "                                                                                                </div>\n"
                + "                                                                                            </td>\n"
                + "                                                                                        </tr>\n"
                + "\n"
                + "\n"
                + "                                                                                        <tr>\n"
                + "                                                                                            <td style=\"padding-top:5px;padding-bottom:5px;font-size:14px;font-family:Helvetica Neue,Arial,sans-serif;color:#3c4043;text-align:left;line-height:1.55em\" width=\"70%\">\n"
                + "                                                                                                <div style=\"color:#3c4043;margin:0px;font-size:12px;line-height:20px;font-weight:normal;font-size:15px;padding-right:10px\">\n"
                + "                                                                                                    Gift card\n"
                + "                                                                                                </div>\n"
                + "                                                                                            </td>\n"
                + "                                                                                            <td style=\"padding-top:5px;padding-bottom:5px;font-size:14px;font-family:Helvetica Neue,Arial,sans-serif;color:#3c4043;text-align:left;line-height:1.55em\" width=\"30%\">\n"
                + "                                                                                                <div style=\"color:#3c4043;margin:0px;font-size:12px;line-height:22px;font-weight:normal;font-size:15px\">\n"
                + "                                                                                                    0\n"
                + "                                                                                                </div>\n"
                + "                                                                                            </td>\n"
                + "                                                                                        </tr>\n"
                + "\n"
                + "\n"
                + "                                                                                        <tr>\n"
                + "                                                                                            <td style=\"padding-top:5px;padding-bottom:5px;font-size:14px;font-family:Helvetica Neue,Arial,sans-serif;color:#3c4043;text-align:left;line-height:1.55em\" width=\"70%\">\n"
                + "                                                                                                <div style=\"color:#3c4043;margin:0px;font-size:12px;line-height:20px;font-weight:normal;font-size:15px;padding-right:10px\">\n"
                + "                                                                                                    Discount\n"
                + "                                                                                                </div>\n"
                + "                                                                                            </td>\n"
                + "                                                                                            <td style=\"padding-top:5px;padding-bottom:5px;font-size:14px;font-family:Helvetica Neue,Arial,sans-serif;color:#3c4043;text-align:left;line-height:1.55em\" width=\"30%\">\n"
                + "                                                                                                <div style=\"color:#3c4043;margin:0px;font-size:12px;line-height:22px;font-weight:normal;font-size:15px\">\n"
                + "                                                                                                    0\n"
                + "                                                                                                </div>\n"
                + "                                                                                            </td>\n"
                + "                                                                                        </tr>\n"
                + "\n"
                + "\n"
                + "                                                                                        <tr>\n"
                + "                                                                                            <td style=\"padding-top:5px;padding-bottom:5px;font-size:14px;font-family:Helvetica Neue,Arial,sans-serif;color:#3c4043;text-align:left;line-height:1.55em\" width=\"70%\">\n"
                + "                                                                                                <div style=\"color:#3c4043;margin:0px;font-size:12px;line-height:20px;font-weight:normal;font-size:15px;padding-right:10px\">\n"
                + "                                                                                                    Reward Points\n"
                + "                                                                                                </div>\n"
                + "                                                                                            </td>\n"
                + "                                                                                            <td style=\"padding-top:5px;padding-bottom:5px;font-size:14px;font-family:Helvetica Neue,Arial,sans-serif;color:#3c4043;text-align:left;line-height:1.55em\" width=\"30%\">\n"
                + "                                                                                                <div style=\"color:#3c4043;margin:0px;font-size:12px;line-height:22px;font-weight:normal;font-size:15px\">\n"
                + "                                                                                                    0\n"
                + "                                                                                                </div>\n"
                + "                                                                                            </td>\n"
                + "                                                                                        </tr>\n"
                + "\n"
                + "\n"
                + "                                                                                        <tr>\n"
                + "                                                                                            <td style=\"padding-top:5px;padding-bottom:5px;font-size:14px;font-family:Helvetica Neue,Arial,sans-serif;color:#3c4043;text-align:left;line-height:1.55em\" width=\"70%\">\n"
                + "                                                                                                <div style=\"color:#3c4043;margin:0px;font-size:12px;line-height:22px;font-weight:bold;font-size:15px;padding-right:10px\">\n"
                + "                                                                                                    Total\n"
                + "                                                                                                </div>\n"
                + "                                                                                            </td>\n"
                + "                                                                                            <td style=\"padding-top:5px;padding-bottom:5px;font-size:14px;font-family:Helvetica Neue,Arial,sans-serif;color:#3c4043;text-align:left;line-height:1.55em\" width=\"30%\">\n"
                + "                                                                                                <div style=\"color:#3c4043;margin:0px;font-size:12px;line-height:22px;font-weight:bold;font-size:15px\">\n"
                + "                                                                                                    " + Mony + "\n"
                + "                                                                                                </div>\n"
                + "                                                                                            </td>\n"
                + "                                                                                        </tr>\n"
                + "                                                                                    </tbody></table>\n"
                + "                                                                            </td>\n"
                + "                                                                        </tr>\n"
                + "\n"
                + "\n"
                + "                                                                        <tr>\n"
                + "                                                                            <td align=\"center\" style=\"background-color:white;padding-top:0;padding-bottom:20px\">\n"
                + "                                                                                <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"90%\">\n"
                + "                                                                                    <tbody><tr>\n"
                + "                                                                                            <td class=\"m_-6019542432575260588h2m\" style=\"padding-top:5px;font-size:14px;font-family:Helvetica Neue,Arial,sans-serif;color:#3c4043;text-align:left;line-height:1.55em\"></td>\n"
                + "                                                                                        </tr>\n"
                + "                                                                                    </tbody></table>\n"
                + "                                                                            </td>\n"
                + "                                                                        </tr>\n"
                + "\n"
                + "\n"
                + "                                                                        <tr>\n"
                + "                                                                            <td align=\"center\" style=\"background-color:white;padding-top:0\">\n"
                + "                                                                                <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"90%\">\n"
                + "                                                                                    <tbody><tr>\n"
                + "                                                                                            <td>\n"
                + "                                                                                                <p class=\"m_-6019542432575260588h2m\" style=\"margin:0 0 25px 0;font-size:14px;font-style:italic;font-family:Helvetica Neue,Arial,sans-serif;color:#3c4043;text-align:left;line-height:24px\">\n"
                + "                                                                                                    <strong style=\"color:#f1330a\">Note:</strong>\n"
                + "                                                                                                    If you have any problems, please contact customer service for the best support. <br/>Thanks!\n"
                + "                                                                                                </p>\n"
                + "                                                                                            </td>\n"
                + "                                                                                        </tr>\n"
                + "                                                                                    </tbody></table>\n"
                + "                                                                            </td>\n"
                + "                                                                        </tr>\n"
                + "\n"
                + "\n"
                + "                                                                    </tbody></table>\n"
                + "                                                            </td>\n"
                + "                                                        </tr>\n"
                + "                                                    </tbody></table>\n"
                + "\n"
                + "\n"
                + "\n"
                + "\n"
                + "                                                <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n"
                + "                                                    <tbody><tr>\n"
                + "                                                            <td align=\"center\">\n"
                + "                                                                <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"m_-6019542432575260588w100\" width=\"512\">\n"
                + "\n"
                + "                                                                    <tbody><tr>\n"
                + "                                                                            <td align=\"center\" style=\"background-color:red;padding-top:20px;padding-bottom:20px\">\n"
                + "                                                                                <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"90%\">\n"
                + "                                                                                    <tbody><tr>\n"
                + "                                                                                            <td>\n"
                + "                                                                                                <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n"
                + "                                                                                                    <tbody>\n"
                + "                                                                                                        <tr>\n"
                + "                                                                                                            <td class=\"m_-6019542432575260588h2m m_-6019542432575260588stack-column-center\" style=\"padding:0;margin:0\">\n"
                + "                                                                                                                <div style=\"color:#f3e6ec;display:block;font-family:sans-serif;font-size:13px;font-weight:700;line-height:19px;margin:0px;padding:0px;text-align:center\">\n"
                + "                                                                                                                    Service Joint Stock Company Bugs \n"
                + "                                                                                                                    <br>\n"
                + "                                                                                                                    Online (Music)\n"
                + "                                                                                                                </div>\n"
                + "\n"
                + "                                                                                                                <div style=\"margin:5px 0 0 0;color:#f3e6ec;display:block;font-family:sans-serif;font-size:12px;font-weight:normal;line-height:20px;text-align:center\">\n"
                + "                                                                                                                    1 Ly Tu Trong, Ninh Kieu, Can Tho, Viet Nam \n"
                + "                                                                                                                </div>\n"
                + "                                                                                                            </td>\n"
                + "                                                                                                        </tr>\n"
                + "                                                                                                    </tbody>\n"
                + "                                                                                                </table>\n"
                + "                                                                                                <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n"
                + "                                                                                                    <tbody>\n"
                + "                                                                                                        <tr>\n"
                + "                                                                                                            <td class=\"m_-6019542432575260588h2m\" style=\"padding:0\">\n"
                + "                                                                                                                <div style=\"color:#737373;margin:10px 0;font-size:12px;line-height:22px;text-transform:uppercase;text-align:center;font-weight:bold\">\n"
                + "                                                                                                                    <a href=\"\" style=\"Margin:0;color:#9b9b9b;font-family:Roboto,Helvetica,sans-serif;font-weight:400;line-height:1.3;margin:0;padding:0;text-align:left\" target=\"_blank\" data-saferedirecturl=\"https://www.google.com/url?q=https://www.facebook.com/vimomo/&amp;source=gmail&amp;ust=1626593990274000&amp;usg=AFQjCNFVagOnbjtljRYzkjju06jVZrdo6g\">\n"
                + "                                                                                                                        <img height=\"26\" src=\"https://ci5.googleusercontent.com/proxy/sHQrymeM5KQ-F86v_-vxy9A48n8xUk_pHIj5y5doeQtxm6q7cB6Qd7psf-NcB-vPIWhAvnNrFDkpdJngemkbh_Cj8Uc5uCanGfO_W7iIJtGNASgVdruzlif4eMOc09K4DkxJQiqRPPBiYA=s0-d-e1-ft#https://static.mservice.io/images/s/momo-upload-api-191105150846-637085633269386698.png\" style=\"border:none;clear:both;display:inline-block;height:26px;max-width:100%;outline:0;text-decoration:none;vertical-align:middle;width:26px\" title=\"facebook\" width=\"26\" alt=\"\" class=\"CToWUd\">\n"
                + "                                                                                                                    </a>&nbsp;&nbsp;\n"
                + "\n"
                + "                                                                                                                    <a href=\"\" style=\"Margin:0;color:#9b9b9b;font-family:Roboto,Helvetica,sans-serif;font-weight:400;line-height:1.3;margin:0;padding:0;text-align:left\" target=\"_blank\" data-saferedirecturl=\"https://www.google.com/url?q=https://momo.vn/&amp;source=gmail&amp;ust=1626593990274000&amp;usg=AFQjCNEhrKroAZUGofkU2lmyFegcgotKHw\">\n"
                + "                                                                                                                        <img height=\"26\" src=\"https://ci4.googleusercontent.com/proxy/oORNn-LtrHgsiNysIssG4R2mAgOEV-mbe9v9I8tuWR4eBXMY7l-WyhPjxQGr7jYaxqsZsc_K60pwu3_RxJvGdpNDB_LKkYbCm78bcevmHe1OTfnG06uv_6r8uOud0DFbLa7qHKLxlIcz8A=s0-d-e1-ft#https://static.mservice.io/images/s/momo-upload-api-191105150920-637085633600093600.png\" style=\"border:none;clear:both;display:inline-block;height:26px;max-width:100%;outline:0;text-decoration:none;vertical-align:middle;width:26px\" title=\"momo\" width=\"26\" alt=\"\" class=\"CToWUd\">\n"
                + "                                                                                                                    </a>&nbsp;&nbsp;\n"
                + "\n"
                + "                                                                                                                    <a href=\"hmtuana18122@cusc.ctu.edu.vn\" style=\"Margin:0;color:#9b9b9b;font-family:Roboto,Helvetica,sans-serif;font-weight:400;line-height:1.3;margin:0;padding:0;text-align:left\" target=\"_blank\">\n"
                + "                                                                                                                        <img height=\"26\" src=\"https://ci4.googleusercontent.com/proxy/TwzLO-58hn4IBTemaQnEL_-eYcHvphCO6iZNExO9wrbLMCiX2_tm5pw2YOHwkCqcUb2rh5CHfgG_3t-qEBDno6Cpf9SRhcYQnFs199rd9Y5kl_t8Xbn1QkWbIYUxJh-zAEdZRhSR3zLwRA=s0-d-e1-ft#https://static.mservice.io/images/s/momo-upload-api-191105150941-637085633813746314.png\" style=\"border:none;clear:both;display:inline-block;height:26px;max-width:100%;outline:0;text-decoration:none;vertical-align:middle;width:26px\" title=\"email\" width=\"26\" alt=\"\" class=\"CToWUd\">\n"
                + "                                                                                                                    </a>&nbsp;&nbsp;\n"
                + "\n"
                + "                                                                                                                    <a href=\"\" style=\"Margin:0;color:#9b9b9b;font-family:Roboto,Helvetica,sans-serif;font-weight:400;line-height:1.3;margin:0;padding:0;text-align:left\" target=\"_blank\" data-saferedirecturl=\"https://www.google.com/url?q=https://www.youtube.com/channel/UCKHHW-qL2JoZqcSNm1jPlqw&amp;source=gmail&amp;ust=1626593990274000&amp;usg=AFQjCNHPovszM4SV46hIdICFox6tK_VibQ\">\n"
                + "                                                                                                                        <img height=\"26\" src=\"https://ci5.googleusercontent.com/proxy/bSmZZW4ot-BNb3kpBfVqJz8J7wjeQ7b5p9fX_KyVGuSbN9zDiQ0kzd2tdE80HTb5gFRbUHShGlgYitbj9_Rze9NZV449sDx8ptVr3pmPNZ3uTSIgdv4YfPd-egCdSR-L0fuqIz2IqmDGGw=s0-d-e1-ft#https://static.mservice.io/images/s/momo-upload-api-191105151010-637085634100025706.png\" style=\"border:none;clear:both;display:inline-block;height:26px;max-width:100%;outline:0;text-decoration:none;vertical-align:middle;width:26px\" title=\"youtube\" width=\"26\" alt=\"\" class=\"CToWUd\">\n"
                + "                                                                                                                    </a>\n"
                + "                                                                                                                </div>\n"
                + "                                                                                                            </td>\n"
                + "                                                                                                        </tr>\n"
                + "                                                                                                    </tbody>\n"
                + "                                                                                                </table>\n"
                + "                                                                                            </td>\n"
                + "                                                                                        </tr>\n"
                + "\n"
                + "\n"
                + "                                                                                        <tr>\n"
                + "                                                                                            <td align=\"center\" style=\"padding:10px 0 10px\">\n"
                + "                                                                                                <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n"
                + "                                                                                                    <tbody>\n"
                + "                                                                                                        <tr>\n"
                + "                                                                                                            <td style=\"border-top:1px dashed #bf6191\"></td>\n"
                + "                                                                                                        </tr>\n"
                + "                                                                                                    </tbody>\n"
                + "                                                                                                </table>\n"
                + "                                                                                            </td>\n"
                + "                                                                                        </tr>\n"
                + "                                                                                        <tr>\n"
                + "\n"
                + "                                                                                            <td>\n"
                + "                                                                                                <div style=\"color:#e5c6d5;display:block;font-family:sans-serif;font-size:11px;font-weight:normal;text-align:center;line-height:17px;margin:0px;padding:0px\">\n"
                + "                                                                                                    You are got email from account <strong>Admin Bug</strong>\n"
                + "                                                                                                </div>\n"
                + "                                                                                            </td>\n"
                + "                                                                                        </tr>\n"
                + "                                                                                    </tbody></table>\n"
                + "                                                                            </td>\n"
                + "                                                                        </tr>\n"
                + "                                                                        <tr>\n"
                + "                                                                            <td align=\"center\" style=\"background-color:#f5f5f6\">\n"
                + "                                                                                <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n"
                + "                                                                                    <tbody>\n"
                + "                                                                                        <tr>\n"
                + "                                                                                            <td style=\"border-top:1px solid #f5f5f6\"></td>\n"
                + "                                                                                        </tr>\n"
                + "                                                                                    </tbody>\n"
                + "                                                                                </table>\n"
                + "                                                                            </td>\n"
                + "                                                                        </tr>\n"
                + "                                                                    </tbody></table>\n"
                + "                                                            </td>\n"
                + "                                                        </tr>\n"
                + "                                                    </tbody></table>\n"
                + "                                            </td>\n"
                + "                                        </tr>\n"
                + "                                    </tbody></table>\n"
                + "                            </td>\n"
                + "                        </tr>\n"
                + "                    </tbody></table>\n"
                + "\n"
                + "                <div class=\"m_-6019542432575260588gmailfix\" style=\"white-space:nowrap;font:15px courier;line-height:0\">\n"
                + "                    &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;\n"
                + "                    &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;\n"
                + "                </div>\n"
                + "                <table cellpadding=\"0\" cellspacing=\"0\" style=\"border:0px;padding:0px;margin:0px;display:none;float:left\">\n"
                + "                    <tbody><tr>\n"
                + "                            <td height=\"1\" style=\"font-size:1px;line-height:1px;padding:0px\">\n"
                + "                                <br>\n"
                + "                            </td>\n"
                + "                        </tr>\n"
                + "                    </tbody></table><div class=\"yj6qo\"></div><div class=\"adL\">\n"
                + "\n"
                + "                </div></div><div class=\"adL\">\n"
                + "\n"
                + "            </div></div></div><div id=\":ox\" class=\"ii gt\"><div id=\":oy\" class=\"a3s aiL msg-6019542432575260588\"><u></u>";
        message.setContent(htmlContent, "text/html");
        Transport.send(message);
        System.out.println("Gui mail thanh cong");
    }

    public static String traRaTrangThai(String a, String b) {
        String tt = null;
        if (a.endsWith("1") && b.equals("1")) {
            tt = "Paid";
        } if (a.endsWith("3")) {
            tt = "Canceled";
        }  if (a.endsWith("1") && b.equals("null")) {
            tt = "Unpaid";
        }
        return tt;
    }

    public static List taoListTuNote(String note) {
        List list = new ArrayList();
        if (note.equals("null")) {
            list = null;
        } else {
            note += "]";
            char[] c = note.toCharArray();
            String tam = "";
            for (int i = 0; i < c.length; i++) {
                tam += String.valueOf(c[i]);
                if (i < c.length - 1) {
                    if ((String.valueOf(c[i]).equals("]") && String.valueOf(c[i + 1]).equals("[")) || (String.valueOf(c[i]).equals("]") && String.valueOf(c[i + 1]).equals("]"))) {
                        list.add(tam);
                        tam = "";
                    }
                }
            }
        }

        return list;
    }

    public static String Createmd5(String text) {
        String md5 = null;
        if (null == text) {
            return null;
        }
        try {
            //tạo đối tương  MessageDigest cho MD5
            MessageDigest digest = MessageDigest.getInstance("MD5");
            //Caaph Nhật Cuỗi Đầu Vào
            digest.update(text.getBytes(), 0, text.length());
            //Chuyển đổi giá trị thông báo thông báo trong cơ số 16 (hex)
            md5 = new BigInteger(1, digest.digest()).toString(16);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return md5;
    }

    public static void main(String[] args) {
        taoListTuNote("[chua thanh toan nha /404/Fri Jul 30 01:02:22 ICT 2021/Canceled/Canceled][thanh cong /404/Fri Jul 30 01:03:09 ICT 2021/Paid/Paid][lkjlkj/404/Fri Jul 30 01:03:37 ICT 2021/Canceled/Canceled][ nha /404/Fri Jul 30 01:06:38 ICT 2021/Paid/Paid][dtfdgdfgxbccg/404/Fri Jul 30 01:11:53 ICT 2021/Canceled/Canceled][sadsa/404/Fri Jul 30 01:23:36 ICT 2021/Paid/Unpaid]");
    }

}
