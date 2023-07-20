package utils;

import DAO.StockDAO;
import DAO.UsersDAO;
import entity.Account;
import entity.Product;
import entity.Users;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;
import java.util.Random;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author tranh
 */
public class EmailContext {

    static String from = "hieptvhe173252@gmail.com";
    static String password = "djcsapucpjtdcvue";
//    static String password = "TEST";

    public static void sendAccountInformation(String username, ArrayList<Account> accountList) {
        UsersDAO usersDAO = new UsersDAO();
        Users user = usersDAO.searchUserByUsername(username);
        String to = user.getEmail();
        String subject = "[ZSHOP] YOUR ORDERED ACCOUNT INFORMATION";

        String mailContent = "<table style=\"font-family:'Open Sans',sans-serif\" width=\"100%\" border=\"0\">\n"
                + "        <tbody>\n"
                + "            <tr>\n"
                + "                <td style=\"word-break:break-word;padding:28px 33px 25px;font-family:'Open Sans',sans-serif\"\n"
                + "                    align=\"left\">\n"
                + "                    <div>\n"
                + "                        <p style=\"font-size: 24px;line-height:200%;\">\n"
                + "                            <strong>THANKS FOR ORDERED AN ACCOUNT FROM ZSHOP.COM</strong>\n"
                + "                        </p>\n"
                + "                        <p style=\"font-size:14px;line-height:200%;\">\n"
                + "                            Hi <strong>" + username + "</strong>, we would like to send you the following account information: </p>\n";

        for (Account account : accountList) {
            int accountID = account.getAccountID();
            String productID = account.getProductID();
            String emailAcc = account.getEmail();
            String passwordAcc = account.getPassword();
            Timestamp dueDate = account.getDueDate();
            StockDAO stockDAO = new StockDAO();
            Product product = stockDAO.searchProductByID(productID);
            String productName = product.getTitle();
            mailContent += "<p style=\"font-size:14px;line-height:200%;\">\n"
                    + "                            Product name: <strong>" + productName + "</strong></p>\n"
                    + "                        <p style=\"font-size:14px;line-height:200%;\">\n"
                    + "                            Account ID: <strong>" + accountID + "</strong>\n"
                    + "                        </p>\n"
                    + "                        <p style=\"font-size:14px;line-height:200%;\">\n"
                    + "                            Account email: <strong>" + emailAcc + "</strong>\n"
                    + "                        </p>\n"
                    + "                        <p style=\"font-size:14px;line-height:200%;\">\n"
                    + "                            Account password: <strong>" + passwordAcc + "</strong>\n"
                    + "                        </p>\n"
                    + "                        <p style=\"font-size:14px;line-height:200%;\">\n"
                    + "                            Expiry date: <strong>" + dueDate + "</strong>\n"
                    + "                        </p>\n"
                    + "                        ==================================\n";
        }

        mailContent += "<p style=\"font-size:14px;line-height:200%;\">\n"
                + "                            Any questions please contact: <a href=\"mailto:hieptvhe173252@gmail.com\"\n"
                + "                                target=\"_blank\">hieptvhe173252@gmail.com</a> to\n"
                + "                            be answered.</p>\n"
                + "                        <p style=\"font-size:14px;line-height:200%;\">\n"
                + "                            Thank you for shopping at Zshop.com.</p>\n"
                + "                    </div>\n"
                + "                </td>\n"
                + "            </tr>\n"
                + "        </tbody>\n"
                + "    </table>";

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.prot", "465");

        // Create Authenticator
        Authenticator auth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        };

        Session session = Session.getInstance(props, auth);
        MimeMessage msg = new MimeMessage(session);
        try {
            msg.addHeader("Content-type", "text/HTML; charset = UTF-8");
            msg.setFrom(new InternetAddress(from));
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));
            msg.setSubject(subject);
            msg.setSentDate(new Date());
            msg.setContent(mailContent, "text/html;charset=UTF-8");
            Transport.send(msg);
        } catch (MessagingException e) {
        }
    }

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final String NUMBERS = "0123456789";

    public static String generateRandomVerificationCode(int length) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(NUMBERS.length());
            char randomChar = NUMBERS.charAt(randomIndex);
            sb.append(randomChar);
        }
        return sb.toString();
    }

    public static boolean sendVerificationCode(String username, String code) {
        UsersDAO usersDAO = new UsersDAO();
        Users user = usersDAO.searchUserByUsername(username);
        String to = user.getEmail();
        String subject = "[ZSHOP] Verification code";

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.prot", "465");

        // Create Authenticator
        Authenticator auth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        };

        Session session = Session.getInstance(props, auth);
        MimeMessage msg = new MimeMessage(session);
        try {
            msg.addHeader("Content-type", "text/HTML; charset = UTF-8");
            msg.setFrom(new InternetAddress(from));
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));
            msg.setSubject(subject);
            msg.setSentDate(new Date());
            msg.setContent("<table style=\"font-family:'Open Sans',sans-serif\" width=\"100%\" border=\"0\">\n"
                    + "        <tbody>\n"
                    + "            <tr>\n"
                    + "                <td style=\"word-break:break-word;padding:28px 33px 25px;font-family:'Open Sans',sans-serif\"\n"
                    + "                    align=\"left\">\n"
                    + "                    <div>\n"
                    + "                        <p style=\"font-size:14px;line-height:200%;\">\n"
                    + "                            Hi <strong>" + username + "</strong>, here is your verification code: </p>\n"
                    + "                        <p style=\"font-size:14px;line-height:200%;\">\n"
                    + "                            <strong>" + code + "</strong></p>\n"
                    + "                        <p style=\"font-size:14px;line-height:200%;\">\n"
                    + "                            Any questions please contact: <a href=\"mailto:hieptvhe173252@gmail.com\"\n"
                    + "                                target=\"_blank\">hieptvhe173252@gmail.com</a> to\n"
                    + "                            be answered.</p>\n"
                    + "                    </div>\n"
                    + "                </td>\n"
                    + "            </tr>\n"
                    + "        </tbody>\n"
                    + "    </table>", "text/html;charset=UTF-8");
            Transport.send(msg);
            return true;
        } catch (MessagingException e) {
        }
        return false;
    }

    public static boolean sendVerificationCodeToEmail(String email, String code) {
        String subject = "[ZSHOP] Verification code";

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.prot", "465");

        // Create Authenticator
        Authenticator auth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        };

        Session session = Session.getInstance(props, auth);
        MimeMessage msg = new MimeMessage(session);
        try {
            msg.addHeader("Content-type", "text/HTML; charset = UTF-8");
            msg.setFrom(new InternetAddress(from));
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email, false));
            msg.setSubject(subject);
            msg.setSentDate(new Date());
            msg.setContent("<table style=\"font-family:'Open Sans',sans-serif\" width=\"100%\" border=\"0\">\n"
                    + "        <tbody>\n"
                    + "            <tr>\n"
                    + "                <td style=\"word-break:break-word;padding:28px 33px 25px;font-family:'Open Sans',sans-serif\"\n"
                    + "                    align=\"left\">\n"
                    + "                    <div>\n"
                    + "                        <p style=\"font-size:14px;line-height:200%;\">\n"
                    + "                            Hi <strong>" + email + "</strong>, here is your verification code: </p>\n"
                    + "                        <p style=\"font-size:14px;line-height:200%;\">\n"
                    + "                            <strong>" + code + "</strong></p>\n"
                    + "                        <p style=\"font-size:14px;line-height:200%;\">\n"
                    + "                            Any questions please contact: <a href=\"mailto:hieptvhe173252@gmail.com\"\n"
                    + "                                target=\"_blank\">hieptvhe173252@gmail.com</a> to\n"
                    + "                            be answered.</p>\n"
                    + "                    </div>\n"
                    + "                </td>\n"
                    + "            </tr>\n"
                    + "        </tbody>\n"
                    + "    </table>", "text/html;charset=UTF-8");
            Transport.send(msg);
            return true;
        } catch (MessagingException e) {
        }
        return false;
    }

    public static void main(String[] args) {
        EmailContext context = new EmailContext();
        StockDAO stockDAO = new StockDAO();
        String captcha = generateRandomVerificationCode(6);
        System.out.println(captcha);
    }

}
