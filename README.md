SCFRM
=====
1、项目在eclipse环境中，编码设置为utf-8.

2、项目按功能模块分包，比如基础设置模块，可以命名为basicSettings, 基础设置下的产品类别页面，可以放到basicSettings/productClass包下。

3、每一个页面所对应的包下应该包含以下3个类：Action：对应struts2配置， Service：对应业务逻辑， Bean：对应业务实体类。

4、jdk使用1.7版本，Tomcat使用7.0版本。


以上原则可以参考example包下的例子。
