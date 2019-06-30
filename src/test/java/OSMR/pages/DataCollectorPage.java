package OSMR.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.thucydides.core.annotations.DefaultUrl;
import org.openqa.selenium.WebElement;

@DefaultUrl("https://www.contextures.com/xlSampleData01.html")
public class DataCollectorPage {

    @FindBy(xpath = "/html/body/div/div/div[1]/table")
    private WebElement firstColumnHeader;

    @FindBy(xpath = "/html/body/div/div/div[1]/table")
    private WebElement secondColumnHeader;

    @FindBy(xpath = "/html/body/div/div/div[1]/table")
    private WebElement thirdColumnHeader;

    @FindBy(xpath = "/html/body/div/div/div[1]/table")
    private WebElement fourthColumnHeader;

    @FindBy(xpath = "/html/body/div/div/div[1]/table")
    private WebElement fifthColumnHeader;

    @FindBy(xpath = "/html/body/div/div/div[1]/table")
    private WebElement sixthColumnHeader;

    @FindBy(xpath = "/html/body/div/div/div[1]/table")
    private WebElement seventhColumnHeader;

    @FindBy(xpath = "/html/body/div/div/div[1]/table/tbody")
    private WebElement tableBody;

    public String getNumberOfTableRows(){
        return tableBody.getSize().toString();
    }

    public String getFirstColumnHeaderValue(){
        return firstColumnHeader.getText();
    }

    public String getSecondColumnHeaderValue(){
        return secondColumnHeader.getText();
    }

    public String getThirdColumnHeaderValue(){
        return thirdColumnHeader.getText();
    }

    public String getFourthColumnHeaderValue(){
        return fourthColumnHeader.getText();
    }

    public String getFifthColumnHeaderValue(){
        return fifthColumnHeader.getText();
    }

    public String getSixthColumnHeaderValue(){
        return sixthColumnHeader.getText();
    }

    public String getSeventhColumnHeaderValue(){
        return seventhColumnHeader.getText();
    }


}
