## Android UI 开发
### 简介

本项目主要内容是Android的UI开发，主要使用Banner、Glide、XRecyclierView、CircleImageView、SmartRefreshLayout、CollapsingToolbarLayout、ViewPager2和MagicIndicator。

内容展示：

![avatar](./src/shooter.gif)

### 项目内容

主要实现了：

+ 应用主页

  + ActionBar 的沉浸式填充

  + Banner 实现循环播放
  + CardView 内容展示
  + XRecyclierView 实现列表展示，上拉更新内容

+ Tab浏览页面

  + 利用CollapsingToolbarLayout实现可折叠的ToolBar
  + 利用MagicIndicator和ViewPager实现自定义的Tab展示

+ 个人主页

  + 使用include和merge标签实现复杂页面分离
  + 实现个人主页展示

### 主要技术

| UI名称                  | 作用                 | 版本   |
| ----------------------- | -------------------- | ------ |
| Banner                  | 轮询图，循环播放     | 2.1.0  |
| Gilde                   | 图片加载库           | 4.12.0 |
| XRecyclierView          | 列表展示，上下拉刷新 | 1.6.0  |
| SmartRefreshLayout      | 智能下拉刷新布局     | 2.0.3  |
| CollapsingToolbarLayout | 可折叠工具栏         | -      |
| ViewPager2              | 内容循环展示         | 1.0.0  |
| MagicIndicator          | Tab页面指示器        | 1.7.0  |

