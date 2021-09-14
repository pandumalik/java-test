package ai.inmotion.javatest.service;

import ai.inmotion.javatest.model.GithubUser;
import ai.inmotion.javatest.util.PDFUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

@Service
public class UserService {
    private final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

    public byte[] getUserToPdf() throws IOException, DocumentException {
        PDFUtil pdfUtil = new PDFUtil();

        URL url = new URL("https://api.github.com/users?per_page=50");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Accept", "application/vnd.github.v3+json");
        InputStream response = con.getInputStream();
        ObjectMapper objectMapper = new ObjectMapper();
        List<GithubUser> listUser = objectMapper.readValue(response, new TypeReference<List<GithubUser>>() {
        });

        Document document = new Document();
        PdfWriter.getInstance(document, byteArrayOutputStream);

        document.open();

        PdfPTable table = new PdfPTable(3);
        pdfUtil.addTableHeader(table, new String[]{"User ID", "User Avatar", "User Login"});
        listUser.forEach(user -> {
            try {
                pdfUtil.addRows(table, user);
            } catch (IOException | BadElementException e) {
                e.printStackTrace();
            }
        });

        document.add(table);
        document.close();
        return byteArrayOutputStream.toByteArray();
    }

    public byte[] getSimplePdf() throws IOException, DocumentException {
        URL url = new URL("https://api.github.com/users?per_page=50");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Accept", "application/vnd.github.v3+json");
        InputStream response = con.getInputStream();
        ObjectMapper objectMapper = new ObjectMapper();
        List<GithubUser> listUser = objectMapper.readValue(response, new TypeReference<List<GithubUser>>() {
        });
        Document document = new Document();
        PdfWriter.getInstance(document, byteArrayOutputStream);
        document.open();
        document.addTitle("GitHub User");
        listUser.forEach(user -> {
            Chunk userLogin = new Chunk(user.getLogin());
            try {
                document.add(userLogin);
                document.add(new Paragraph("\n"));
            } catch (DocumentException e) {
                e.printStackTrace();
            }
        });
        document.close();
        return byteArrayOutputStream.toByteArray();
    }

}
