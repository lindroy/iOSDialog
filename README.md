# IDialog(iOSDialog)

![版本信息](https://img.shields.io/badge/iOSDialog-v1.0.0-blue)

------------
基于Kotlin+DialogFragment编写的iOS风格对话框。

## 目录
* [功能介绍](#功能介绍)
* [配置方法](#配置方法)
* [使用方法](#使用方法)
* [资源文件](#资源文件)
* [更新日志](#更新日志)

## 功能介绍
- [x] 提示对话框：默认有一个确认按钮和一个取消按钮
- [x] 列表对话框：与提示对话框相似，但具有多选项列表，默认有一个取消按钮
- [x] 底部列表对话框：具有多选项列表的底部对话框，默认有一个取消按钮
- [x] 在**iOS风格的基础上**自定义布局(自定义的布局会添加在标题信息和按钮布局之间)
- [x] 自定义布局对话框
- [x] 完全自定义对话框

### iOS风格对话框效果图
![iOS风格对话框](https://raw.githubusercontent.com/lindroy/iOSDialog/master/image1.png "iOS风格对话框")

### 自定义iOS风格对话框
![自定义iOS风格对话框](https://raw.githubusercontent.com/lindroy/iOSDialog/master/image2.png "自定义iOS风格对话框")

## 配置方法
1.在工程的build.gradle中配置：

```
    allprojects {
        repositories {
            ...
            maven { url 'https://www.jitpack.io' }
        }
    }
```
2.添加依赖：

```
    implementation 'com.github.lindroy:iOSDialog:latest-version'
```
`latest-version`改为徽章中的版本号。

3.初始化
初始化`IDialog`，建议在`Application`中全局初始化：

```kotlin
     IDialog.init(this)
```

## 使用方法
### 全局配置
为了避免每次使用时都要重复设置一些UI细节，`iOSDialog`提供了全局配置的方法，你可以在初始化时就配置所有对话框的共同细节。
```kotlin
       IDialog.init(instance) //初始化方法，必须！！！
       //以下方法是对所有对话框的配置
       .setBackgroundColor(Color.WHITE) //背景颜色
       .setCornerRadius(48F) //背景圆角半径（px）
       .setBackgroundAlpha(0.85F) //背景透明度
       //以下方法用于配置提示类对话框（IAlert和IAlertList）
       .setAlertWidthScale(0.7F) //提示对话框与屏幕宽度比
       .setAlertWidthPx(0) //提示对话框宽度，优先于setAlertWidthScale
       .setAlertAnimStyle(0) //动画样式
       .setAlertPaddingTop(80) //对话框顶部内边距（px）
       .setAlertPaddingBottom(80) //对话框标题与消息文字布局的底部内边距（px）
       .setAlertPaddingSides(60) //对话框标题与消息文字布局的两侧内边距（px）
       .setAlertPaddingTitleMsg(20) //对话框标题与消息文字之间的间距（px）
       .setAlertTitleStyle( //对话框标题样式
           textSize = 16F, //字体大小（sp）
           textColor = Color.BLACK,
           text = "提示",//标题文字
           gravity = Gravity.CENTER
       )
       .setAlertMsgStyle( //对话框消息文字样式
           textSize = 14F, //字体大小（sp）
           textColor = Color.BLACK,
           gravity = Gravity.CENTER
       )
       .setAlertButtonHeight(200) //对话框按钮高度（px）
       .setAlertPositiveButton( //确认按钮样式
           text = "OK",
           textSize = 14F, //字体大小（sp）
           textColor = Color.BLUE,
           gravity = Gravity.CENTER
       )
       .setAlertNegativeButton( //取消按钮样式
           text = "Cancel",
           textSize = 14F, //字体大小（sp）
           textColor = Color.RED,
           gravity = Gravity.CENTER
       )
       //以下方法用于配置IAlertListDialog
       .setAlertListItemStyle( //列表选项样式
           textSize = 14F, //字体大小（sp）
           textColor = Color.BLUE,
           height = 200, //高度（px）
           paddingSides = 60 //两侧的padding值（px）
       )
       //以下方法用于配置底部对话框（目前只有IBottomList）
       .setBottomWidthScale(0.95F) //底部对话框与屏幕宽度之比
       .setBottomWidthPx(0) //底部对话框的宽度，优先于setBottomWidthScale
       .setBottomAnimStyle(0) //动画样式
       .setBottomPaddingTop(80) //对话框顶部内边距（px）
       .setBottomPaddingBottom(80) //对话框标题与消息文字布局的底部内边距（px）
       .setBottomPaddingSides(60) //对话框标题与消息文字布局的两侧内边距（px）
       .setBottomPaddingTitleMsg(20) //对话框标题与消息文字之间的间距（px）
       //以下方法用于配置底部列表对话框（IBottomListDialog）
       .setBottomListTitleStyle( //对话框标题样式
           textSize = 16F, //字体大小（sp）
           textColor = Color.BLACK,
           text = "提示",//标题文字
           gravity = Gravity.CENTER
       )
       .setBottomListMsgStyle( //对话框说明文字样式
           textSize = 14F, //字体大小（sp）
           textColor = Color.BLACK,
           gravity = Gravity.CENTER
       )
       .setBottomListButton(  //取消按钮样式
           textSize = 16F, //字体大小（sp）
           textColor = Color.BLACK,
           text = "取消", //按钮文字
           height = 200 //高度（px）
       )
       .setBottomListItemStyle( //列表选项样式
           textSize = 14F, //字体大小（sp）
           textColor = Color.BLUE,
           height = 200, //高度（px）
           paddingSides = 60 //两侧的padding值（px）
       )
```
### 局部使用
`IDialog`是所有所有对话框的入口，通过它可以配置不同样式的对话框：
- `IDialog.alert(Activity|Fragment)`：一般的提示对话框
- `IDialog.alertList(Activity|Fragment)`：含有选项列表的对话框
- `IDialog.bottomList(Activity|Fragment)`：底部列表选项对话框
- `IDialog.custom(Activity|Fragment)`：自定义布局对话框

提示对话框：
```kotlin
    IDialog.alert(this)
        .setTitle("提示")
        .setMessage("确定要退出登录吗？")
        .setPositiveText("确定")
        .setNegButtonText("取消")
        .setOnPositiveClickListener {
            Toast.makeText(this, "确定", Toast.LENGTH_LONG).show()
        }
        .setOnNegativeClickListener {
            Toast.makeText(this, "取消", Toast.LENGTH_LONG).show()
        }
        .show()
```
列表对话框：
```kotlin
   IDialog.alertList(this)
       .setTitle("提示")
       .setMessage("请选择你喜欢的书籍")
       .addItem("西游记")
       .addItem("红楼梦")
       .addItem("水浒传")
       .addItem("三国演义")
       .setCancelText("取消")
       .setOnCancelClickListener {
           Toast.makeText(this, "取消", Toast.LENGTH_LONG).show()
       }
       .setItemClickedDismissible(true)
       .setOnItemClickListener { pos, s, textView, dialogInterface ->
           Toast.makeText(this, "你选择了$s", Toast.LENGTH_LONG).show()
       }
       .show()
```

底部列表对话框：
```kotlin
    IDialog.bottomList(this)
        .setTitle("提示")
        .setMessage("请选择你喜欢的书籍")
        .addItem("西游记")
        .addItem("红楼梦")
        .addItem("水浒传")
        .addItem("三国演义")
        .setCancelText("取消")
        .setOnCancelClickListener {
            Toast.makeText(mContext, "取消", Toast.LENGTH_LONG).show()
        }
        .setItemClickedDismissible(true)
        .setOnItemClickListener { pos, s, textView, dialogInterface ->
            Toast.makeText(mContext, "你选择了$s", Toast.LENGTH_LONG).show()
        }
        .show()
```
自定义布局的iOS对话框：


### 方法说明

`IDialog`具有如下共同的配置方法：

|  方法名称 | 作用  | 默认值  |  备注 |
| :------------: | :------------: | :------------: | :------------: |
| setView() | 添加自定义布局  | / |  参数类型为资源Id |
| setAnimStyle() | 设置动画  | R.style.IOSAlertDialogStyle | / |
| setCancelableOutside() | 点击外部是否使对话框消失  | true | / |
| setBackgroundColor() | 设置背景颜色  | Color.WHITE | / |
| setBackgroundColorRes() | 设置背景颜色 | 同上  | 参数类型为资源Id  |
| setBackgroundAlpha() | 设置透明度 | 0.85  | 范围为0~1.0 |
| setCornerRadius() | 设置圆角矩形的圆角半径  | 14dp |  单位为px |
| setTitle() | 设置标题文字 | / | / |
| setTitleSize() | 设置标题文字大小 | 16sp  | / |
| setTitleColor() | 设置标题文字颜色 | #303030 | / |
| setTitleColorRes() | 设置标题文字颜色 | 同上 | 参数类型为资源Id |
| setMessage() | 设置消息文字 | / | / |
| setMessageSize() | 设置消息文字大小 | 14sp  | / |
| setMessageColor() | 设置消息文字颜色 | #303030 | / |
| setMessageColorRes() | 设置消息文字颜色 | 同上 | 参数类型为资源Id |
| setKeepPortraitWidth() | 横屏时是否保持竖屏时的宽度 | true | / |
| setOnDismissListener() | 对话框消失监听 | / | / |
| setOnViewHandler() | 处理自定义布局上的控件 | / | / |

#### 提示对话框
```kotlin
    IDialog.alert(this)
        .setDismissible(true) //点击对话框上的按钮是否可以关闭对话框，默认为true
        .setButtonHeight(200) //设置“确定”按钮和“取消”按钮的高度（px）
        .setButtonHeightRes(0) //设置“确定”按钮和“取消”按钮的高度（参数为资源Id）
        .setPositiveText("确定") //设置“确认”按钮文字
        .setPositiveTextColor(Color.BLUE) //设置“确认”按钮颜色
        .setPositiveTextColorRes(0) //设置“确认”按钮颜色（参数为资源Id）
        .setPositiveTextSize(14F) //设置“确认”按钮文字大小（sp）
        .setPositiveTextSizeRes(0) //设置“确认”按钮文字大小（参数为资源Id）
        .setOnPositiveClickListener { dialog: DialogInterface ->
            //“确认”按钮点击事件
        }
        .setNegativeText("取消") //设置“取消”按钮文字
        .setNegativeTextColor(Color.BLUE) //设置“取消”按钮颜色
        .setNegativeTextColorRes(0) //设置“取消”按钮颜色（参数为资源Id）
        .setNegativeTextSize(14F) //设置“取消”按钮文字大小（sp）
        .setNegativeTextSizeRes(0) //设置“取消”按钮文字大小（参数为资源Id）
        .setOnNegativeClickListener { dialog: DialogInterface ->
            //“取消”按钮点击事件
        }
        .setShowNegativeButton(true) //是否显示“取消”按钮
        .show()
```

对于“确认”和“取消”两个按钮，也可以采用如下更简便的方法：

```kotlin
       IDialog.alert(this)
               ……
              .setPositiveButton(
                 text = "确认",
                 textColor = Color.BLUE,
                 textSize = 14F
               ) { dialog: DialogInterface ->
                        //点击事件
               }
               .show()
```

#### 列表对话框
```kotlin
    IDialog.alertList(this)
        .setCancelButtonHeight(200) //设置“取消”按钮高度（px）
        .setCancelButtonHeightRes(0) //设置“取消”按钮高度（参数为资源Id）
        .setShowCancelButton(true) //是否显示“取消”按钮
        .addItem( //添加一个选项
            "选项名称", //选项上的文字，必传
            textColor = Color.BLACK, //文字颜色，非必传
            textSize = 14F //文字大小，非必传
        )
        .addItems(listOf("A","B","C")) //添加一组选项（参数为List，元素类型为String）
        .setCancelText("取消") //设置“取消”按钮文字
        .setCancelTextColor(Color.BLUE) //设置“取消”按钮颜色
        .setCancelTextColorRes(0) //设置“取消”按钮颜色（参数为资源Id）
        .setCancelTextSize(14F) //设置“取消”按钮文字大小（sp）
        .setCancelTextSizeRes(0) //设置“取消”按钮文字大小（参数为资源Id）
        .setCancelButtonHeight(200) //设置“取消”按钮高度（px）
        .setCancelButtonHeightRes(0) //设置“取消”按钮高度（参数为资源Id）
        .setCancelClickedDismissible(true) //点击取消按钮后是否自动关闭对话框
        .setOnCancelClickListener { dialog: DialogInterface ->
            //“取消”按钮点击事件
        }
        .setItemHeight(200) //设置列表选项高度（px）
        .setItemHeightRes(0) //设置列表选项高度（参数为资源Id）
        .setItemClickedDismissible(true) //点击item后是否自动关闭对话框
        .setOnItemClickListener { position, text, itemView, dialog ->
            //列表Item点击事件
        }
        .show()
```

#### 底部列表对话框

```Kotlin
    IDialog.bottomList(this)
        .addItem( //添加一个选项
            "选项名称", //选项上的文字，必传
            textColor = Color.BLACK, //文字颜色，非必传
            textSize = 14F //文字大小，非必传
        )
        .addItems(listOf("A","B","C")) //添加一组选项（参数为List，元素类型为String）
        .setCancelText("取消") //设置“取消”按钮文字
        .setCancelTextColor(Color.BLUE) //设置“取消”按钮颜色
        .setCancelTextColorRes(0) //设置“取消”按钮颜色（参数为资源Id）
        .setCancelTextSize(14F) //设置“取消”按钮文字大小（sp）
        .setCancelTextSizeRes(0) //设置“取消”按钮文字大小（参数为资源Id）
        .setCancelButtonHeight(200) //设置“取消”按钮高度（px）
        .setCancelButtonHeightRes(0) //设置“取消”按钮高度（参数为资源Id）
        .setCanCelClickedDismissible(true) //点击取消按钮后是否自动关闭对话框
        .setOnCancelClickListener { dialog: DialogInterface ->
            //“取消”按钮点击事件
        }
        .setItemHeight(200) //设置列表选项高度（px）
        .setItemHeightRes(0) //设置列表选项高度（参数为资源Id）
        .setItemClickedDismissible(true) //点击列表选项后是否关闭对话框
        .setOnItemClickListener { position, text, itemView, dialog ->
            //列表选项点击事件
        }
        .show()
```
#### 自定义iOS风格对话框
先调用`setView()`方法传入布局，然后在`setOnViewHandler()`回调中处理布局上的控件。已提示对话框为例：
```kotlin
    IDialog.alert(this)
        .setView(R.layout.dialog_custom_view_ios) //传入自定义的布局
        .setTitle("提示")
        .setPositiveText(R.string.confirm)
        .setNegativeText(R.string.cancel)
        .setOnViewHandler { holder, dialogInterface ->
            //在这里处理布局控件
            holder.setOnClickListener(R.id.imageView) {
                Toast.makeText(mContext, "点击图标", Toast.LENGTH_LONG).show()
            }
        }
        .show()
```
#### 自定义布局对话框
对于项目中可能出现的非iOS风格对话框，可以先编写好布局，再以如下方式调用：
```kotlin
    IDialog.custom(this)
        .setView(R.layout.layout_custom) //传入自定义的布局
        .setOnViewHandler { holder, dialog ->
            //在这里处理布局控件
            holder.setOnClickListener(R.id.tvLater, R.id.tvContact) { view ->
                when (view.id) {
                    R.id.tvLater -> shortToast(R.string.text_later)
                    R.id.tvContact -> shortToast(R.string.text_contact_us)
                }
            }
        }
        .show()
```
#### 完全自定义对话框
如果某个对话框比较复杂且频繁使用，可以将其封装到一个类中，步骤如下：
1. 创建一个类继承于`BaseDialog`；
2. 实现`layoutId()`方法，传入布局Id；
3. 实现`onHandleView()`方法，在里面处理布局控件；
4. 编写静态入口方法。

```kotlin
//1. 创建一个类继承于`BaseDialog`
class ICustomDialog :BaseDialog<ICustomDialog>(){
    /**
     * 2. 实现`layoutId()`方法，传入布局Id
     * 子类继承需要创建的对话框布局Id
     */
    override fun layoutId(): Int= R.layout.layout_custom
 
    /**
     * 3. 实现`onHandleView()`方法，在里面处理布局控件
     * 返回true表示子类自己处理布局，setViewHandler方法无效
     */
    override fun onHandleView(dialogLayout: View): Boolean {
        tvLater.setOnClickListener {
            shortToast(R.string.text_later)
            dialog.dismiss()
        }
        tvContact.setOnClickListener {
            shortToast(R.string.text_contact_us)
            dialog.dismiss()
        }
        return true
    }
 
 
    /**
     * 4.编写静态入口方法
     */
    companion object{
        fun build(fm:FragmentManager) = ICustomDialog().apply { setFragmentManager(fm) }
 
        fun build(context: FragmentActivity) = build(context.supportFragmentManager)
 
        fun build(context: Fragment) = build(context.childFragmentManager)
    }
}
```
然后就可以像`IDialog`一样调用了：
```dart
    ICustomDialog.build(this)
        .show()
```
对于屏幕旋转后的数据保存，`IDialog`采用将参数序列化后保存，所以完全自定义对话框需要保存数据的话应放置在`onHandleView`方法中，否则易造成数据丢失。

## 资源文件
### colors.xml
```xml
<resources>
    <color name="ios_dialog_title_color">#000000</color>
    <color name="ios_dialog_list_item_text_color">#000000</color>
    <color name="ios_dialog_message_color">#000000</color>
    <color name="ios_dialog_positive_button_text_color">#2095f2</color>
    <color name="ios_dialog_negative_button_text_color">#2095f2</color>
    <color name="ios_dialog_bg_color_white">#ffffff</color>
    <color name="ios_dialog_divider_color">#cdcdcd</color>
</resources>
```
### dimens.xml
```xml
<?xml version="1.0" encoding="utf-8"?>
<resources>
    <!--以下用于全部对话框-->
    <dimen name="ios_dialog_corner_radius">14dp</dimen>
    <dimen name="ios_dialog_divider_size">0.5dp</dimen>
    <dimen name="ios_dialog_list_item_padding_top">8dp</dimen>
    <dimen name="ios_dialog_list_item_padding_sides">15dp</dimen>
    <dimen name="ios_dialog_list_item_height">44dp</dimen>
    <!--以上用于全部对话框-->
 
    <!--以下为提示对话框资源-->
    <dimen name="ios_alert_padding_top">15dp</dimen>
    <dimen name="ios_alert_padding_bottom">15dp</dimen>
    <dimen name="ios_alert_padding_sides">15dp</dimen>
    <dimen name="ios_alert_padding_title_msg">5dp</dimen>
    <dimen name="ios_alert_title_size">16sp</dimen>
    <dimen name="ios_alert_message_size">14sp</dimen>
    <dimen name="ios_alert_button_height">44dp</dimen>
    <dimen name="ios_alert_button_text_size">14sp</dimen>
    <dimen name="ios_alert_list_item_text_size">14sp</dimen>
    <!--以上为提示对话框资源-->
 
    <!--以下为底部对话框资源-->
    <dimen name="ios_bottom_padding_top">15dp</dimen>
    <dimen name="ios_bottom_padding_bottom">15dp</dimen>
    <dimen name="ios_bottom_padding_sides">15dp</dimen>
    <dimen name="ios_bottom_padding_title_msg">5dp</dimen>
    <dimen name="ios_bottom_title_size">16sp</dimen>
    <dimen name="ios_bottom_button_height">57dp</dimen>
    <dimen name="ios_bottom_message_size">14sp</dimen>
    <dimen name="ios_bottom_button_text_size">14sp</dimen>
    <dimen name="ios_bottom_button_list_margin">8dp</dimen>
    <dimen name="ios_bottom_button_screen_margin">8dp</dimen>
    <dimen name="ios_bottom_list_item_text_size">14sp</dimen>
    <!--以上为底部对话框资源-->
</resources>
```

### strings.xml
```xml
<resources>
    <string name="title_font_type">sans-serif-light</string>
    <string name="ios_dialog_positive_text">确定</string>
    <string name="ios_dialog_negative_text">取消</string>
</resources>
```
### styles.xml
```xml
<resources>
 
    <!--缩放动画 parent="android:Animation"-->
    <style name="IAlertDialogStyle" >
        <item name="android:windowEnterAnimation">@anim/ios_dialog_center_in</item>
        <item name="android:windowExitAnimation">@anim/ios_dialog_center_out</item>
    </style>
 
    <!--底部对话框动画-->
    <style name="IBottomDialogStyle">
        <item name="android:windowEnterAnimation">@anim/ios_bottom_list_slide_in</item>
        <item name="android:windowExitAnimation">@anim/ios_bottom_list_slide_out</item>
    </style>
 
    <!--对话框标题样式-->
    <style name="IDialogTitleStyle">
        <item name="android:gravity">center</item>
        <item name="android:layout_width">match_parent</item>
        <item name="android:layout_height">wrap_content</item>
        <item name="android:fontFamily">@string/title_font_type</item>
        <item name="android:textStyle">bold</item>
    </style>
 
    <!--对话框信息样式-->
    <style name="IDialogMessageStyle">
        <item name="android:gravity">center</item>
        <item name="android:layout_width">match_parent</item>
        <item name="android:layout_height">wrap_content</item>
    </style>
 
    <!--按钮样式-->
    <style name="IDialogButtonStyle">
        <item name="android:background">@android:color/transparent</item>
        <item name="android:fontFamily">@string/title_font_type</item>
        <item name="android:textStyle">bold</item>
        <item name="android:layout_width">wrap_content</item>
        <item name="android:layout_height">match_parent</item>
        <item name="android:foreground">?android:attr/selectableItemBackground</item>
    </style>
 
</resources>
```

## 更新日志
### v1.0.0（2021.05.24）
发布第一个正式版。