# ドメイン（Domain）とは

- ドメイン知識を表現するためのオブジェクト

## 設計パターン

### ドメインモデルを表現する（ドメインオブジェクト）

- 値オブジェクト
- エンティティ
- ドメインイベント（？）
  - エンティティとは違うやり方で、外部と集約を連携する方法
  - 実際に起きた出来事を表現する（ex. チケットを割り当てた、メッセージを受け取った）

### ドメインオブジェクトを使用する

- リポジトリ
- ファクトリー
- ドメインサービス

### その他のパターン

- enumによる値オブジェクトの表現
- 仕様オブジェクト
- ファーストクラスコレクション

## システムの複雑さについて
- システムの振る舞いを予測する難易度は「自由度」で決まる
- 