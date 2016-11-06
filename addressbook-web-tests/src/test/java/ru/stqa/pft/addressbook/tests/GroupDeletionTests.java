package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class GroupDeletionTests extends TestBase {

  @Test
  public void testGroupDeletion() {
    app.getNavigationHelper().gotoGroupPage();
   //  app.getGroupHelper().selectp(); //делала для себя, чтобы проверить ожидание
    app.getGroupHelper().selectGroup();
    app.getGroupHelper().deleteSelectedGroups();
    app.getGroupHelper().returnToGroupPage();
  }


}
