package OSMR.tests;

import OSMR.pages.DataCollectorPage;
import OSMR.steps.DataCollectorSteps;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import org.junit.Test;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

//import static jdk.internal.org.jline.utils.AttributedStringBuilder.append;


@RunWith(SerenityRunner.class)
public class DataCollectorTest {

    @Managed(uniqueSession = true)
    public WebDriver webdriver;

    @Steps
    DataCollectorSteps steps;
    DataCollectorPage page;

    static private String[] columns = {"OrderDate", "Region", "Rep", "Item", "Units", "UnitCost", "Total"};
    static private StringBuilder newColumns = new StringBuilder();
    static private ArrayList <String> newerColumns = new ArrayList<>();

    @Test
    public void getData(){
        System.out.println(steps.getNumberOfTableRows());

//        StringBuilder builder = new StringBuilder();
//        for(int i = 0; i < 7; i++){
//            String first = page.getFifthColumnHeaderValue();
//            newerColumns.add(first);
//            newColumns.append(page.getFifthColumnHeaderValue());
//        }

    }


    @Test
    public void collectData() throws IOException, InvalidFormatException {


        String workingDirectory = System.getProperty("user.dir");
        System.out.println(workingDirectory);
        String resourceFilePath = workingDirectory + "\\SpreadSheets"; // \\" + System.currentTimeMillis() + ".xlsx"

        Workbook workbook = new XSSFWorkbook();
        CreationHelper creationHelper = workbook.getCreationHelper();

        Sheet sheet = workbook.createSheet("Collected Data");

        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerFont.setFontHeightInPoints((short) 14);
        headerFont.setColor(IndexedColors.GREEN.getIndex());

        CellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFont(headerFont);

        // Cell Style for formatting Date
        CellStyle dateCellStyle = workbook.createCellStyle();
        dateCellStyle.setDataFormat(creationHelper.createDataFormat().getFormat("dd-MM-yyy"));

        Row headerRow = sheet.createRow(0);

        for (int i = 0; i < columns.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellStyle(headerCellStyle);
            cell.setCellValue(columns[i]);
        }



        // Resize all columns to fit the content size
        for (int i = 0; i < columns.length; i++) {
            sheet.autoSizeColumn(i);
        }

        // Write the output to a file
        FileOutputStream fileOut = new FileOutputStream("poi-generated-file.xlsx");
        workbook.write(fileOut);
        fileOut.close();


        workbook.close();


    }
}
