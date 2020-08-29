package cn.xpbootcamp.locker;

import org.junit.Assert;
import org.junit.Test;

public class LockerTest {
    final String p = "存入的包裹";

    @Test
    public void should_return_success_and_valid_bill_when_save_in_given_locker_have_space() {
        Locker locker = new Locker();
        StoreResults result = locker.saveIn(p);
        Assert.assertEquals(result.getStatus(), true);
        Assert.assertEquals(result.getType(), "Bill");
    }

    @Test
    public void should_return_fail_prompt_when_save_in_given_locker_no_space() {
        Locker locker = new Locker();

        for(int i = 0; i < Locker.LOCKER_MAX_SIZE; i ++) {
            locker.saveIn("Full of Locker " + i);
        }

        StoreResults result = locker.saveIn(p);
        Assert.assertEquals(result.getStatus(), false);
        Assert.assertEquals(result.getType(), "Message");
        Assert.assertEquals(result.getContent(), "存包失败，储物柜没有空间了");
    }

    @Test
    public void should_return_customer_item_by_bill_when_take_out_given_bill_valid() {
        Locker locker = new Locker();
        StoreResults saveResult = locker.saveIn(p);
        StoreResults takeResult = locker.takeOut(saveResult.getContent());

        Assert.assertEquals(takeResult.getStatus(), true);
        Assert.assertEquals(takeResult.getType(), "CustomerItem");
        Assert.assertEquals(takeResult.getContent(), p);
    }


    @Test
    public void should_return_fail_prompt_when_take_out_given_duplicate_bill() {
        Locker locker = new Locker();
        StoreResults saveResult = locker.saveIn(p);
        StoreResults firstTakeResult = locker.takeOut(saveResult.getContent());
        StoreResults secondTakeResult = locker.takeOut(saveResult.getContent());

        Assert.assertEquals(secondTakeResult.getStatus(), false);
        Assert.assertEquals(secondTakeResult.getType(), "Message");
        Assert.assertEquals(secondTakeResult.getContent(), "重复使用的票据");
    }

    @Test
    public void should_return_fail_prompt_when_take_out_given_forged_bill() {
        Locker locker = new Locker();
        StoreResults saveResult = locker.saveIn(p);
        StoreResults takeResult = locker.takeOut("通过伪造票据取包");

        Assert.assertEquals(takeResult.getStatus(), false);
        Assert.assertEquals(takeResult.getType(), "Message");
        Assert.assertEquals(takeResult.getContent(), "伪造的票据");
    }
}
