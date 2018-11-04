package top.fankaljead.memo.adapters.base;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.LinkedHashMap;

/**
 *
 * @author NewBies
 * @date 2018/10/26
 */

public class ItemEntity implements MultiItemEntity {

    private final ReferenceQueue<LinkedHashMap<Object, Object>> ITEM_QUEUE = new ReferenceQueue<>();
    private final LinkedHashMap<Object, Object> FIELDS = new LinkedHashMap<>();
    private final SoftReference<LinkedHashMap<Object, Object>> FIELDS_REFERENCE = new SoftReference<>(FIELDS,ITEM_QUEUE);

    @Override
    public int getItemType() {
        return (int)FIELDS.get(ItemField.ITEM_TYPE);
    }

    @SuppressWarnings("unchecked")
    public final <T> T getField(Object key){
        return (T)FIELDS_REFERENCE.get().get(key);
    }

    public final LinkedHashMap<?, ?> getFields(){
        return FIELDS_REFERENCE.get();
    }

    public final ItemEntity setField(Object key, Object value){
        FIELDS_REFERENCE.get().put(key, value);
        return this;
    }
}
