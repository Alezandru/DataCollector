package OSMR.steps;

import OSMR.pages.DataCollectorPage;
import net.thucydides.core.annotations.Step;
import org.fluentlenium.core.annotation.Page;

public class DataCollectorSteps {

    @Page
    DataCollectorPage page;

    @Step
    public String getNumberOfTableRows(){
        return page.getNumberOfTableRows();
    }
}
