# emoji检测工具

fork from [emoji-java](https://github.com/vdurmont/emoji-java)

## 主要改动

1. 去除多余方法
2. org.json换成fastjson
## EmojiParser

## 使用方式
工程就5个类和一个emoji.json文件，可直接引入项目中使用。

对外提供俩个方法
- EmojiParser#removeAllEmojis(String): 删除字符串中的所有emoji
- EmojiParser#containsEmojis(String): 检查字符串中是否有emoji 
  