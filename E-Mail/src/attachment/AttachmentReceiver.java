package attachment;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeUtility;
 
/**
 * This program demonstrates how to download e-mail messages and save
 * attachments into files on disk.
 *
 * @author www.codejava.net
 *
 */
public class AttachmentReceiver {
    private String saveDirectory;
 
    /**
     * Sets the directory where attached files will be stored.
     * @param dir absolute path of the directory
     */
    public void setSaveDirectory(String dir) {
        this.saveDirectory = dir;
        
        File targetDir = new File(saveDirectory);
        if (!targetDir.exists()) {
            targetDir.mkdirs();
        }
    }
 
    /**
     * Downloads new messages and saves attachments to disk if any.
     * @param host
     * @param port
     * @param userName
     * @param password
     */
    public void downloadSOCParsingFiles(String host, String port,
            String userName, String password, List<String> keywordList) {
        Properties properties = new Properties();
 
        Session session = setSessionProperty(host, port, properties);
 
        try {
            // connects to the message store
            Store store = session.getStore("pop3");
            store.connect(userName, password);
 
            // opens the inbox folder
            Folder folderInbox = store.getFolder("INBOX");
            folderInbox.open(Folder.READ_ONLY);
 
            // fetches new messages from server
            Message[] arrayMessages = folderInbox.getMessages();
            
            downloadAttachment(arrayMessages, keywordList);
 
            // disconnect
            folderInbox.close(false);
            store.close();
        } catch (NoSuchProviderException ex) {
            System.out.println("No provider for pop3.");
            ex.printStackTrace();
        } catch (MessagingException ex) {
            System.out.println("Could not connect to the message store");
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * @param arrayMessages
     * @throws MessagingException
     * @throws IOException
     */
    private void downloadAttachment(Message[] arrayMessages, List<String> keywordList) throws MessagingException, IOException {
        clearDirectory(saveDirectory);
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        
        for (int i = arrayMessages.length - 1; i >= 0; i--) {
            Message message = arrayMessages[i];
            Address[] fromAddress = message.getFrom();
            
            // Only Today
//            Date receivedDate = message.getReceivedDate();
            Date sentDate = message.getSentDate();
            System.out.println(sdf.format(sentDate));
            if (!sdf.format(sentDate).equals(sdf.format(new Date()))) {
                break;
            }
            
            String subject = message.getSubject();
            if (!isLike(keywordList, subject)) {
                continue;
            }
            
            String from = fromAddress[0].toString();
//            String sentDate = message.getSentDate().toString();
            
 
            String contentType = message.getContentType();
            String messageContent = "";
 
            // store attachment file name, separated by comma
            String attachFiles = "";
 
            if (contentType.contains("multipart")) {
                // content may contain attachments
                Multipart multiPart = (Multipart) message.getContent();
                int numberOfParts = multiPart.getCount();
                for (int partCount = 0; partCount < numberOfParts; partCount++) {
                    MimeBodyPart part = (MimeBodyPart) multiPart.getBodyPart(partCount);
                    if (Part.ATTACHMENT.equalsIgnoreCase(part.getDisposition())) {
                        // this part is attachment
                        String fileName = part.getFileName();
                        fileName = MimeUtility.decodeText(fileName); // 解決中文檔名問題
                        attachFiles += fileName + ", ";
                        part.saveFile(saveDirectory + File.separator + fileName);
                    } else {
                        // this part may be the message content
                        messageContent = part.getContent().toString();
                    }
                }
 
                if (attachFiles.length() > 1) {
                    attachFiles = attachFiles.substring(0, attachFiles.length() - 2);
                }
            } else if (contentType.contains("text/plain")
                    || contentType.contains("text/html")) {
                Object content = message.getContent();
                if (content != null) {
                    messageContent = content.toString();
                }
            }
 
            // print out details of each message
            System.out.println("Message #" + (i + 1) + ":");
//            System.out.println("\t From: " + from);
            System.out.println("\t Subject: " + subject);
            System.out.println("\t Sent Date: " + sentDate);
//            System.out.println("\t Received Date: " + receivedDate.toString());
//            System.out.println("\t Message: " + messageContent);
            System.out.println("\t Attachments: " + attachFiles);
        }
    }

    private void clearDirectory(String dir) {
        File toDelete = new File(dir);
        if (toDelete.isDirectory() && toDelete.exists()) {
            File[] files = toDelete.listFiles();
            for (File f : files) {
                f.delete();
            }
        }
    }
    /**
     * @param host
     * @param port
     * @param properties
     * @return
     */
    private Session setSessionProperty(String host, String port, Properties properties) {
        // server setting
        properties.put("mail.pop3.host", host);
        properties.put("mail.pop3.port", port);
 
        // SSL setting
//        properties.setProperty("mail.pop3.socketFactory.class",
//                "javax.net.ssl.SSLSocketFactory");
        properties.setProperty("mail.pop3.socketFactory.fallback", "false");
        properties.setProperty("mail.pop3.socketFactory.port",
                String.valueOf(port));
 
        Session session = Session.getDefaultInstance(properties);
        return session;
    }
    
    private boolean isLike(List<String> keywordList, String content) {
        for (String kw : keywordList) {
            if (content.contains(kw)) {
                return true;
            }
        }
        
        return false;
    }
 
    /**
     * Runs this program with Gmail POP3 server
     */
    public static void main(String[] args) {
        String host = "mail server";
        String port = "110";
        String userName = "xxx";
        String password = "xxx";
 
        String saveDirectory = "d:/Attachment";
 
        AttachmentReceiver receiver = new AttachmentReceiver();
        receiver.setSaveDirectory(saveDirectory);
        receiver.downloadSOCParsingFiles(host, port, userName, password, new ArrayList<String>());
 
    }
}