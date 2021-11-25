package com.ukom.view;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

/**
 * Created by Administrator on 2018/5/18 0020.
 *
 *
 */
public class StickyRadioGroup implements CompoundButton.OnCheckedChangeListener {
    private static final String TAG = "StickyRadioGroup";

    private ViewGroup parentView;

    private int checkedId = View.NO_ID;
    private OnCheckedChangedListener mOnCheckedChangedListener;

    private int firstButtonId = View.NO_ID;

    public StickyRadioGroup(){}
    public StickyRadioGroup(OnCheckedChangedListener listener){
        this.mOnCheckedChangedListener = listener;
    }

    public void setOnCheckedChangeListener(OnCheckedChangedListener listener){
        this.mOnCheckedChangedListener = listener;
    }

    public void stickTo(ViewGroup parent){
        parentView = parent;
        stickToChildButtons(parent);

        //没有预先被选中的单选框
        if (checkedId == View.NO_ID){
            if (firstButtonId != View.NO_ID){
                //默认选中第一个
                Log.d(TAG, "stickTo: no checked CompoundButton in advance, so check the first one");
                check(firstButtonId);
            } else {
                //没有可选择的单选框
                Log.w(TAG, "stickTo: no child CompoundButton");
            }
        }
    }

    private void stickToChildButtons(ViewGroup parent){
        final int count = parent.getChildCount();
        View child;
        int id;
        for (int i = 0; i < count; i++) {
            child = parent.getChildAt(i);

            if (child instanceof CompoundButton){
                id = child.getId();
                if (id == View.NO_ID){
                    id = View.generateViewId();
                    child.setId(id);
                }

                if (firstButtonId == View.NO_ID){
                    firstButtonId = id;
                }

                if (((CompoundButton) child).isChecked()){
                    if(checkedId == View.NO_ID){
                        setCheckedId(id);
                    } else {
                        ((CompoundButton) child).setChecked(false);
                    }
                }

                ((CompoundButton) child).setOnCheckedChangeListener(this);

            } else if (child instanceof ViewGroup){
                stickToChildButtons((ViewGroup) child);
            }

        }
    }

    public void check(int id) {
        setCheckedStateForChild(id, true);
    }

    private void setCheckedStateForChild(int viewId, boolean checked) {
        View child = parentView.findViewById(viewId);
        if (child != null && child instanceof CompoundButton) {
            ((CompoundButton) child).setChecked(checked);
        } else {
            Log.w(TAG, "setCheckedStateForChild: try to set checked state for an " +
                    "non-existed child view , or child view is not a CompoundButton");
        }
    }

    private void setCheckedId(int id) {
        if (id != checkedId) {
            //对原来被选中的Button 取消选中
            if (checkedId != View.NO_ID){
                setCheckedStateForChild(checkedId, false);
            }

            checkedId = id;

            if (mOnCheckedChangedListener != null){
                mOnCheckedChangedListener.onCheckedChanged(this, checkedId);
            }

        }
    }

    public CompoundButton getCheckedButton(){
        return parentView.findViewById(checkedId);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked){
            setCheckedId(buttonView.getId());
        }
    }

    public interface OnCheckedChangedListener {
        void onCheckedChanged(StickyRadioGroup group, int checkedId);
    }

}
