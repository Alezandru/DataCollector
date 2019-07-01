package OSMR.tests;

import OSMR.steps.DataCollectorSteps;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import org.junit.Before;
import org.junit.Test;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.FileOutputStream;
import java.io.IOException;


@RunWith(SerenityRunner.class)
public class DataCollectorTest {

    @Steps
    DataCollectorSteps steps;

    @Managed(uniqueSession = true)
    WebDriver driver;

    @Before
    public void setUp() {
        driver.manage().window().maximize();
        steps.pageIsOpen();
    }


    @Test
    public void collectData() throws IOException {
//        int numberOfResultPages = Integer.valueOf(driver.findElement(By.xpath("")).getText());
        int columnsCount = driver.findElements(By.xpath("/html/body/div/div/div[1]/table/tbody/tr[1]/td")).size();
        int rowCount = driver.findElements(By.xpath("/html/body/div/div/div[1]/table/tbody/tr")).size();
        System.out.println("Total number of columns: " + columnsCount);
        System.out.println("Total number of rows: " + rowCount);

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Collected Data");

        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerFont.setFontHeightInPoints((short) 14);
        headerFont.setColor(IndexedColors.GREEN.getIndex());
        CellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFont(headerFont);

//        for (int z = 1; z <= numberOfResultPages; z++) {

            for (int i = 0; i <= rowCount; i++) {

                if (i == 0) {
                    Row row = sheet.createRow(i);

                    for (int j = 1; j <= columnsCount; j++) {
                        Cell cell = row.createCell(j - 1);
                        cell.setCellStyle(headerCellStyle);
                        cell.setCellValue(driver.findElement(By.xpath("/html/body/div/div/div[1]/table/tbody/tr[1]/td[" + j + "]/p")).getText());
                        System.out.println("Added cell " + j + " in row" + (i + 1));
                    }

                    System.out.println("FINISHED ADDING HEADER ROW" + "\n");
                    continue;
                }

                Row row = sheet.createRow(i);

                for (int k = 1; k <= columnsCount; k++) {
                    Cell cell = row.createCell(k - 1);
                    cell.setCellValue(driver.findElement(By.xpath("/html/body/div/div/div[1]/table/tbody/tr[" + i + "]/td[" + k + "]/p")).getText());
                    System.out.println("Added cell " + k + " in row" + (i + 1));
                }

                System.out.println("FINISHED ADDING ROW: " + (i + 1) + "\n");
            }

//            driver.findElement(By.xpath("")).click();
//            System.out.println("FINISHED ADDING PAGE: " + z + "\n");
//        }

        for (int i = 0; i < columnsCount; i++) {
            sheet.autoSizeColumn(i);
        }

        FileOutputStream fileOut = new FileOutputStream("data-collector-file-" + System.currentTimeMillis() + ".xlsx");
        workbook.write(fileOut);
        fileOut.close();
        workbook.close();

    }
}
