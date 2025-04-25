# Proman - プロジェクト管理システム

Promanは、プロジェクトの登録、検索、管理を効率的に行うためのWebアプリケーションです。

## プロジェクト構成

本プロジェクトは以下の2つのサブプロジェクトで構成されています：

### Frontend
- React/TypeScriptベースのWebフロントエンド
- プロジェクト情報の表示、登録、検索などのUI機能を提供
- 詳細は[frontend/README.md](frontend/README.md)を参照

### Backend
- Nablarchベースのバックエンドサーバー
- RESTful APIによるデータ管理機能を提供
- 詳細は[backend/README.md](backend/README.md)を参照

## システム概要図

```
[ブラウザ] --- HTTP/REST ---> [Frontend Server] --- HTTP/REST ---> [Backend Server] --- JDBC ---> [Database]
   ユーザー          (React/TypeScript)          (Nablarch)                             (H2DB)
```

## 開発環境のセットアップ

1. バックエンドのセットアップ
```bash
cd backend
mvn install
```

2. フロントエンドのセットアップ
```bash
cd frontend
npm install
```

## アプリケーションの起動

1. バックエンドの起動
```bash
cd backend
mvn exec:java -Dexec.mainClass=com.nablarch.example.ProjectRuntime
```

2. フロントエンドの起動
```bash
cd frontend
npm run dev
```

フロントエンド、バックエンドの詳細な設定や実行方法については、各ディレクトリ配下のREADMEを参照してください。

## GitHub Copilot エージェントとの対話実験記録

プロジェクト開発におけるGitHub Copilotの活用実験の記録です：

1. [フロントエンド実装支援](docs/prompt01.md)
   - プロジェクト登録画面の確認ダイアログ実装
   - プロジェクト検索画面の実装

2. [Nablarchアーキテクチャの理解](docs/prompt02.md)
   - Nablarchの責務配置の解説
   - Example実装の分析とパッケージ構造の理解

3. [REST APIの実装支援](docs/prompt03.md)
   - パスパラメータを使用したAPIエンドポイントの実装方法

4. [バリデーション機能の実装](docs/prompt04.md)
   - 登録機能の実装例
   - Nablarch ValidationとBean Validationの比較

5. [エンティティの取り扱い](docs/prompt05.md)
   - エンティティ間の関連
   - エンティティに関するAPIエンドポイントの実装

6. [ログ機能の活用](docs/prompt06.md)
   - Nablarchのログ機能の概要
   - 各種アダプタの説明

7. [ビジネスロジックの共通化](docs/prompt07.md)
   - コンポーネントへの切り出し方
   - 検索ワードの最適化実験

8. [AI検索の改善実験](docs/cline_task_apr-25-2025_2-59-01-pm.md)
   - Clineを使用した検索の改善
   - キーワード変更による検索結果の向上
