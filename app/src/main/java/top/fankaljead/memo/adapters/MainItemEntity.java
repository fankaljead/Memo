package top.fankaljead.memo.adapters;


import top.fankaljead.memo.adapters.base.ItemEntity;
import top.fankaljead.memo.adapters.base.ItemType;

/**
 *
 * @author NewBies
 * @date 2018/10/26
 */

public class MainItemEntity extends ItemEntity {

    public MainItemEntity(String time, String content){
        setField(MainItemField.ITEM_TYPE, ItemType.TEXT_AND_TITLE);
        setTime(time);
        setContent(content);
    }


    public void setTime(String time){
        setField(MainItemField.TIME, time);
    }

    public void setContent(String content){
        setField(MainItemField.TEXT, content);
    }
}
