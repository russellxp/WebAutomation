package com.mercadolibre.workshop.helper;

import com.mercadolibre.workshop.driver.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ProductResultHelper extends Driver {

    public int getFirstProductResultAmount(String keyword) {
        driver.findElement(By.id("cb1-edit")).sendKeys(keyword);
        driver.findElement(By.cssSelector(".nav-search-btn")).click();

        List<WebElement> productList = driver.findElements(By.cssSelector(".price-tag-fraction"));
        WebElement firstProduct = productList.get(0);
        return Integer.parseInt(firstProduct.getText());
    }
}


//Principio de responsabilidad única
//Single responsibility
//O
//L
//I
//D