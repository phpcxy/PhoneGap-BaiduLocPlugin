PhoneGap-BaiduLocPlugin For Android
===================================

### Android百度定位插件，将原来的SDK改成PhoneGap插件供JS调用。

---

百度定位SDK请看[http://dev.baidu.com/wiki/geolocation/index.php?title=AndroidAPI](http://dev.baidu.com/wiki/geolocation/index.php?title=AndroidAPI)

使用方法
-------

-1 拷贝JAVA文件到你的项目文件里，即/src文件夹；
-2 拷贝BaiduLoc.js文件到你的www目录，并且在html页面引入该js；
-3 拷贝libs里面的百度定位SDK（locSDK_2.3.jar与armeabi文件夹），并且在项目里面进行Build Path；
-4 打开res/xml/config.xml（Phonegap 2.0以下是plugins.xml）添加：
<plugin name="BaiduLocPlugin" value="com.fulstore.plugin.BaiduLoc.BaiduLocPlugin"/>
-5 参考[http://dev.baidu.com/wiki/geolocation/index.php?title=AndroidAPI%E5%BC%80%E5%8F%91%E6%8C%87%E5%8D%972.3](这里)进行AndroidManifest.xml的设置：
在application标签中声明service组件
<service android:name="com.baidu.location.f" android:enabled="true" android:process=":remote" 
android:permission="android.permission.BAIDU_LOCATION_SERVICE">
    <intent-filter>
        <action android:name="com.baidu.location.service_v2.3"></action>
    </intent-filter>
</service>

声明使用权限
<permission android:name="android.permission.BAIDU_LOCATION_SERVICE"></permission>
<uses-permission android:name="android.permission.BAIDU_LOCATION_SERVICE"></uses-permission>
<uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION"></uses-permission>
<uses-permission android:name="android.permission.ACCESS_FINE_LOCATION"></uses-permission>
<uses-permission android:name="android.permission.ACCESS_WIFI_STATE"></uses-permission>
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"></uses-permission>
<uses-permission android:name="android.permission.CHANGE_WIFI_STATE"></uses-permission>
<uses-permission android:name="android.permission.READ_PHONE_STATE"></uses-permission>
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"></uses-permission>
<uses-permission android:name="android.permission.INTERNET" />
<uses-permission android:name="android.permission.MOUNT_UNMOUNT_FILESYSTEMS"></uses-permission>
<uses-permission android:name="android.permission.READ_LOGS"></uses-permission>

-6 javascript调用方法：
window.Location(success(pos),fail(err));


