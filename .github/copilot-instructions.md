# Nablarchアプリケーション開発ガイドライン

## 1. Nablarchフレームワークの概要

Nablarchは、業務アプリケーション開発のためのJavaアプリケーションフレームワークです。本リポジトリでは、Nablarchを使用したRESTful APIバックエンドアプリケーションを提供しています。

このガイドラインは、アプリケーションの設計ポリシーと責務配置を理解し、一貫性のあるコードを書くための指針です。

## 2. アプリケーションの基本構造と責務配置

Nablarchアプリケーションは以下の主要な層で構成されています：

### 2.1 基本レイヤー構造

- **アクション層**（Action）：HTTPリクエストを受け付け、処理を制御
- **フォーム層**（Form）：リクエストデータのマッピングとバリデーション
- **サービス層**（Service）：業務ロジックの実装
- **データアクセス層**：データ永続化の処理
- **エンティティ層**（Entity）：データベースのテーブル構造を表現

### 2.2 補完的なパッケージ構造

- **code**：コード値の定義（列挙型）
- **domain**：ドメインルールとバリデーション定義
- **dto**：レイヤー間のデータ転送オブジェクト
- **form**：HTTPリクエストのパラメータ受付とバリデーション
- **error**：エラー処理関連

## 3. 各パッケージの役割と使い分け

### 3.1 コードパッケージ（code）

**役割**：アプリケーション内で使用する定数や列挙型（Enum）を集約

```java
public interface CodeEnum extends WithValue<String> {
    String getLabel();  // 表示用ラベル
    String getValue();  // コード値
}

// 実装例
public enum ProjectType implements CodeEnum {
    NEW("1", "新規開発"),
    MAINTENANCE("2", "保守");
    // ...
}
```

### 3.2 ドメインパッケージ（domain）

**役割**：業務ルールの基本単位としてのドメイン定義とバリデーションルールを集約

```java
public class RestExampleDomain {
    // ID
    @NumberRange(min = 0)
    @Digits(integer = 9)
    private String id;

    // プロジェクト名
    @Length(max = 64)
    @SystemChar(charsetDef = "全角文字")
    private String projectName;
    // ...
}
```

### 3.3 DTOパッケージ（dto）

**役割**：レイヤー間のデータ転送や、APIのレスポンス/リクエスト構造を定義

```java
public class ProjectSearchDto implements Serializable {
    private Integer clientId;     // 適切なデータ型で定義
    private String projectName;
    // getter/setter...
}
```

### 3.4 フォームパッケージ（form）

**役割**：HTTPリクエストパラメータのバインドと入力値検証

```java
public class ProjectSearchForm implements Serializable {
    @Domain("id")               // ドメイン定義を参照
    private String clientId;    // 文字列として受け取り
    
    @Domain("projectName")
    private String projectName;
    // getter/setter...
}
```

## 4. 処理フローと使い分けの意図

### 4.1 基本的な処理の流れ

1. **HTTPリクエスト** → **Form**（文字列として受け取り、バリデーション）
2. **Form** → **DTO**（変換処理：文字列から適切な型へ）
3. **DTO** → **Service/Domain層**（ビジネスロジック）
4. **DTO** → **Response**（クライアントへの応答）

### 4.2 Form と DTO の使い分け

- **Form**：外部からの入力を受け取り、バリデーションを行う
  - すべての値を文字列（String）で保持
  - `@Domain` アノテーションによるバリデーション
  
- **DTO**：内部処理のためのデータ構造を提供
  - 適切なデータ型（Integer, Date など）で値を保持
  - バリデーションアノテーションなし
  - ビジネスロジックで扱いやすい構造

## 5. 開発ガイドライン

### 5.1 コード作成の原則

- 新しいコード値を追加する場合は、`CodeEnum`を実装した列挙型を作成
- バリデーションルールは`domain`パッケージで一元的に定義
- 外部入力のマッピングには必ず`Form`クラスを使用
- レイヤー間のデータ受け渡しには`DTO`を使用

### 5.2 バリデーションの扱い

- 入力値のバリデーションは`Form`クラスで行う
- ドメインルールに基づくバリデーションは`@Domain`アノテーションで参照
- 複合的なバリデーションロジックはドメイン層で実装

### 5.3 REST APIの設計

- URIはリソース指向で設計
- HTTPメソッドを適切に使い分ける（GET, POST, PUT, DELETE）
- レスポンスには適切なHTTPステータスコードを設定

## 6. アンチパターン

- Formクラスをビジネスロジック内で直接使用しない
- DTOクラスにバリデーションロジックを含めない
- Action層に複雑なビジネスロジックを実装しない
- コード値を文字列リテラルでハードコードしない

## 7. テスト方針

- 単体テストでは各レイヤーを適切にモック化
- 統合テストではリクエスト～レスポンスの一連の流れをテスト
- テストデータはテスト用のSQLファイルで管理

以上のガイドラインに従うことで、保守性が高く、一貫性のあるNablarchアプリケーションの開発が可能になります。
