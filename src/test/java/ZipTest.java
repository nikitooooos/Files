import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static com.codeborne.pdftest.PDF.containsExactText;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ZipTest {

    ClassLoader cl = ZipTest.class.getClassLoader();

    @Test
    void testCsvInJava() throws Exception {
        try (InputStream stream = cl.getResourceAsStream("test.zip");
             ZipInputStream zis = new ZipInputStream(stream)) {

            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                final String name = entry.getName();
                if (name.contains(".csv")) {
                    CSVReader csvReader = new CSVReader(new InputStreamReader(zis));
                    List<String[]> content = csvReader.readAll();
                    assertEquals(2, content.size());

                    final String[] firstRow = content.get(0);
                    final String[] secondRow = content.get(1);

                    Assertions.assertArrayEquals(new String[]{"Name", " Nikita"}, firstRow);
                    Assertions.assertArrayEquals(new String[]{"Second name", " Postnikov"}, secondRow);
                }
            }
        }
    }

    @Test
    void testPdfInJava() throws Exception {
        try (InputStream stream = cl.getResourceAsStream("test.zip");
             ZipInputStream zis = new ZipInputStream(stream)) {
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                final String name = entry.getName();
                if (name.contains(".pdf")) {
                    PDF pdf = new PDF(zis);
                    assertThat(pdf, containsExactText("Hello World"));
                }
            }
        }
    }

    @Test
    void testXlsxInJava () throws Exception {
        try (InputStream stream = cl.getResourceAsStream("test.zip");
        ZipInputStream zis = new ZipInputStream(stream)) {
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                final String name = entry.getName();
                if (name.contains(".xlsx")) {
                    XLS xls = new XLS(zis);
                    Assertions.assertEquals("Выписка", xls.excel
                            .getSheet(String.valueOf(0))
                            .getRow(0));
                }
            }
        }
    }

}