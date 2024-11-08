## イベント履歴式ドメインモデル

- 通常のドメインモデルとは『集約の永続化方法』が異なる
- 『イベントソーシング』を使って集約の状態を管理する

### イベントソーシング

- 集約の状態が変化したことを業務イベントで表現し、その履歴を永続化する
  - 逆に現在の状態は永続化しない
- 集約がこれまで辿ってきた状態の変遷に着目する
  - 状態が複雑な予約ステータスを表現するのによさそう感
- 一連のイベントを永続化したデータベースを『イベントストア』と呼ぶ

- ここまでの話を聞く限りあんまり扱える気がしない

### イベントストア

- 最低限必要な機能は以下の2つ
  - イベントを追記する
  - 特定のエンティティに対するイベントをすべて取り出す

### メリット

- 履歴から、過去のあらゆる時点の状態を再現できる
  - 分析、不具合調査などに活用できる
- 履歴からシステムの使われ方や特性を知ることができる
- 監査ログとしての役割

- ここまでする必要ない気もしてくる

### デメリット

- 学習コストが高い
- 設計変更コストが高い（データ構造を簡単に変えることができない）
- 設計自体が複雑になる

```
{
"lead-id": 12,
"event-id": 0,
"event-type": "新規登録",
"last-name": "小林",
"first-name": "浩美",
"phone-number": "555-2951",
"timestamp": "2020-05-20T09:52:55.95Z"
},
{
"lead-id": 12,
"event-id": 1,
"event-type": "架電",
"timestamp": "2020-05-20T12:32:08.24Z"
},
{
"lead-id": 12,
"event-id": 2,
"event-type": "商談予定設定",
"followup-on": "2020-05-27T12:00:00.00Z",
"timestamp": "2020-05-20T12:32:08.24Z"
}
```