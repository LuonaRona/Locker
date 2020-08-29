package cn.xpbootcamp.locker;

import java.util.ArrayList;

public class Locker {
    final static int LOCKER_MAX_SIZE = 50;
    final ArrayList<LockerItem> list = new ArrayList<LockerItem>();
    final ArrayList<String> usedAlreadyBill = new ArrayList<String>();

    public StoreResults saveIn(String p) {
        if (list.size() < LOCKER_MAX_SIZE) {
            LockerItem currentSavedItem = new LockerItem(p);
            list.add(currentSavedItem);
            return new StoreResults(true, "Bill", currentSavedItem.getLockerId());
        } else {
            return new StoreResults(false, "Message", "存包失败，储物柜没有空间了");
        }
    }

    public StoreResults takeOut(String bill) {
        boolean contains = false;
        String customerItem = "";

        for (LockerItem lockerItem : list) {
            if (lockerItem.getLockerId().equals(bill)) {
                contains = true;
                customerItem = lockerItem.takeOut();
            }
        }

        if (contains) {
            usedAlreadyBill.add(bill);
            return new StoreResults(true, "CustomerItem", customerItem);
        } else if (usedAlreadyBill.contains(bill)) {
            return  new StoreResults(false, "Message", "重复使用的票据");
        } else {
            return  new StoreResults(false, "Message", "伪造的票据");
        }
    }

    
}
