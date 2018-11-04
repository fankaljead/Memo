package top.fankaljead.memo.adapters;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import top.fankaljead.memo.R;
import top.fankaljead.memo.adapters.base.ItemEntity;
import top.fankaljead.memo.adapters.base.ItemType;


/**
 *
 * @author NewBies
 * @date 2018/10/26
 */

public class AppAdapter extends BaseMultiItemQuickAdapter<ItemEntity, BaseViewHolder> {

    public AppAdapter(List<ItemEntity> data) {
        super(data);
        addItemType(ItemType.TEXT_AND_TITLE, R.layout.activity_main);
    }

    @Override
    protected void convert(BaseViewHolder helper, ItemEntity item) {
        switch (helper.getItemViewType()){
            case ItemType.TEXT_AND_TITLE:
                final String title = item.getField(MainItemField.TITLE);
                final String time = item.getField(MainItemField.TIME);
                final String content = item.getField(MainItemField.TEXT);
                helper.setText(R.id.title, title);
                helper.setText(R.id.time, time);
                if(content.length() > 7){
                    helper.setText(R.id.content, content.substring(0,7) + "...");
                }
                else {
                    helper.setText(R.id.content, content);
                }
                break;
            default:
                break;
        }

    }
}
