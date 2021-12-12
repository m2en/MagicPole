# MagicPole

![image](./image/description.png)

> ⚠
>
> 記事自体はまだ出来ていません。

[Minecraftサーバ開発・運営 Advent Calendar 2021](https://qiita.com/advent-calendar/2021/minecraft-server-dev) - 16日目の記事で作ったSpigotプラグインです。

カスタムアイテム『MagicPole』とそのカスタムレシピ・魔法を追加します。

## 実装

```md
+ コマンドではなく専用のクラフトレシピを用意する
+ 魔法の詠唱には10秒のディレイを用意する
+ 魔法を使うにはマナ、もとい満腹度を要求する
+ 魔法を詠唱すると **HPブースト・スピード** が付くようにする
```
