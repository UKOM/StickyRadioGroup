StickyRadioGroup
====


StickyRadioGroup 是一个 Android 自带 RaidoGroup 的替代品。

理论上来说，RadioGroup 应该只负责管理一组 CompoundButton 的 checked 状态，它不应该局限于某个特定的布局。而 Android 自带的 RadioGroup 继承于 LinearLayout，使得它的使用范围非常局限。如果使用其他的布局来容纳一组 RaidoButton 等控件，就需要自己来管理 checked 状态的切换。

StickyRadioGroup 并不是一个布局控件，它只是为附着在布局控件上，代为管理布局中 CompoundButton 类型子控件的 checked 状态。

## 用法

```Java
ViewGroup layout = ...;

StickyRadioGroup radioGroup = new StickyRadioGroup(
        new StickyRadioGroup.OnCheckedChangedListener(){
    @Override
    public void onCheckedChanged(StickyRadioGroup group, int checkedId) {
        ...//just like RadioGroup
    }
});
radioGroup.stickTo(layout);
```

