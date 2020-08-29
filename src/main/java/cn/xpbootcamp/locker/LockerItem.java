package cn.xpbootcamp.locker;

import java.util.Date;

public class LockerItem {
    private String storeNumber;
    private boolean saved;
    private String customerItem;

    public LockerItem(String customerItem) {
      this.storeNumber = "LockerItem_" + new Date().getTime();
      this.customerItem = customerItem;
    }

    public String takeOut() {
      String currentCustomerItem = this.customerItem;

      this.saved = false;
      this.storeNumber = "";
      this.customerItem = "";

      return currentCustomerItem;
    }

    public String getLockerId() {
      return this.storeNumber;
    }
}
