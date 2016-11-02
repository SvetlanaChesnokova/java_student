package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

/**
 * Created by Светлана on 03.11.2016.
 */
public class AddNewDeletionTests extends TestBase {

  @Test
  public void testAddNewDeletion(){
    app.getNavigationHelper().gotoGroupPage();
    app.getGroupHelper().initAddNewHome();
    app.getGroupHelper().selectGroup();
    app.getGroupHelper().initAddNewDelete();
    app.getGroupHelper().initAddNewAlert();

  }
}
