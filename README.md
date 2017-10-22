# emoji util forked from vdurmont/emoji-java

## 改动
1. 删除其余方法，只提供检测字符串是否包含emoji，以及去除所有emoji
2. org.json替换为fastjson

##EmojiParser

EmojiParser#removeAllEmojis(String): removes all the emojis from the String
EmojiParser#containsEmojis(String): check the input string contains emojis or not 
  