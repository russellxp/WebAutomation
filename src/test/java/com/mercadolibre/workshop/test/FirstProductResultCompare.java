package com.mercadolibre.workshop.test;

import com.mercadolibre.workshop.driver.Driver;
import com.mercadolibre.workshop.helper.ProductResultHelper;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

import static com.mercadolibre.workshop.driver.Driver.driver;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;

public class FirstProductResultCompare {

    static Driver driverBase = new Driver();
    ProductResultHelper productResultHelper = new ProductResultHelper();

    @BeforeClass
    public static void setUp() {
        driverBase.initDriver();
    }

    @Test
    public void firstItemPriceShouldBeGreaterToExpectedPrice() throws InterruptedException {
        //preconditions
        String keyword = "gorra";
        int expectedProductPrice = 100;
        int obtainedProductPrice = 0;

        //Action
        obtainedProductPrice = productResultHelper.getFirstProductResultAmount(keyword);

        //Assertion - Verify the outcome
        //Assert.assertTrue((expectedProductPrice < obtainedProductPrice));

        assertThat(obtainedProductPrice, greaterThan(expectedProductPrice));
    }

    @Test
    public void selectProductConditionNewComparePreviousPriceResultsShouldBeEqualThanFirst() throws InterruptedException {

        int obtainedNewProductPrice = 0;
        String keyword = "gorra";
        int expectedPrice = 100;
        int obtainedProductPrice = 0;

        //Action
        obtainedProductPrice = productResultHelper.getFirstProductResultAmount(keyword);
        List<WebElement> optionList = driver.findElements(By.xpath("//span[@class='ui-search-filter-name']"));

        for (WebElement item : optionList) {
            if(item.getText().equalsIgnoreCase("Nuevo")) {
                Actions actions = new Actions(driver);
                actions.moveToElement(item).click().build().perform();
                //item.click();
                break;
            }
        }

        List<WebElement> newProductList = driver.findElements(By.cssSelector(".price-tag-fraction"));
        WebElement newProduct = newProductList.get(0);
        obtainedNewProductPrice =  Integer.parseInt(newProduct.getText());

        System.out.println("Primer precio obtenido" + obtainedProductPrice);
        System.out.println("Segundo precio obtenido" + obtainedNewProductPrice);

        //Assert.assertTrue(obtainedProductPrice == obtainedNewProductPrice);
        assertThat(obtainedProductPrice, equalTo(obtainedNewProductPrice));

    }

    @AfterClass
    public static void clean() {
        driverBase.exitDriver();
    }

}
