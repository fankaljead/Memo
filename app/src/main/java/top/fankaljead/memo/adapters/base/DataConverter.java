package top.fankaljead.memo.adapters.base;


import java.util.ArrayList;

import top.fankaljead.memo.utils.StringUtil;

/**
 *
 * @author NewBies
 * @date 2018/10/26
 */

public abstract class DataConverter {

    protected final ArrayList<ItemEntity> ENTITLES = new ArrayList<>();
    private String jsonData = null;

    public abstract ArrayList<ItemEntity> convert();

    public DataConverter setJsonData(String json){
        this.jsonData = json;
        return this;
    }

    protected String getJsonData(){
        if(StringUtil.isNull(this.jsonData)){
            throw new NullPointerException("json 数据为空");
        }
        return this.jsonData;
    }
}
