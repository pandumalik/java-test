package ai.inmotion.javatest.util;

import ai.inmotion.javatest.model.GithubUser;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

import java.io.IOException;
import java.net.URL;
import java.util.stream.Stream;

public class PDFUtil {


    public void addTableHeader(PdfPTable table, String[] headerTitle) {
        Stream.of(headerTitle)
                .forEach(columnTitle -> {
                    PdfPCell header = new PdfPCell();
                    header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    header.setBorderWidth(2);
                    header.setPhrase(new Phrase(columnTitle));
                    table.addCell(header);
                });
    }

    public void addRows(PdfPTable table, GithubUser userData) throws IOException, BadElementException {
        URL url = new URL(userData.getAvatar_url());
        Image image = Image.getInstance(url);
        image.scaleToFit(PageSize.A4.getWidth() / 10, PageSize.A4.getWidth() / 10);
        PdfPCell imageCell = new PdfPCell(image);

        table.addCell(userData.getId());
        table.addCell(imageCell);
        table.addCell(userData.getLogin());
    }
}
