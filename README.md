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
