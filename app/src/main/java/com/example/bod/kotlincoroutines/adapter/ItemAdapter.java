package com.example.bod.kotlincoroutines.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.bod.kotlincoroutines.R;

/**
 * @ClassName: ItemAdapter
 * @Description:
 * @CreateDate: 2019/10/8
 */
public class ItemAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public ItemAdapter() {
        super(R.layout.item_adapter);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {

    }
}
