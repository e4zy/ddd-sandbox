# リポジトリ（Repository）とは

- データを永続化（保存）し、再構築（復元）するための処理を行うオブジェクト。
- DBにデータを保存し、DBから取得したデータをドメインオブジェクトにマッピングを行う。
  - 上記の操作は必ずリポジトリを経由して行う必要がある。

## ルール

- リポジトリを経由してデータ保存・復元する際の単位は必ず集約単位とする。（整合性の担保）
  - 集約内の一部のオブジェクトのみの操作は許可しない。
- リポジトリはListのように扱う。
  - 【NG】ユーザーを新規登録する, ユーザーを退会させる
    - 新規登録や退会という情報はドメイン知識であるため、リポジトリが知っているのはおかしい
  - 【OK】新規登録状態のユーザーをaddする, 退会状態のユーザーをaddする

## メリット

- 特定のデータストアに基づく処理をドメイン層からリポジトリ層に分離することで、ドメイン知識のみに集中することができる。
- ドメイン層が永続化処理を気にする必要がなくなる。
  - データストア自体の差し替えも可能になる。