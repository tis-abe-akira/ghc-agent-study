# Proman - プロジェクト管理システム

Promanは、プロジェクトの登録、検索、管理を効率的に行うためのWebアプリケーションです。

## 機能概要

### 1. プロジェクト検索
- 複数の検索条件による柔軟な検索
- 検索結果の一覧表示
- 検索条件のリセット機能

### 2. プロジェクト詳細
- プロジェクト情報の詳細表示（現在開発中）
- 前画面への戻り機能

### 3. プロジェクト登録
- 新規プロジェクトの登録
- 入力内容の確認ダイアログ
- 必須項目のバリデーション機能

## 実行方法

1. 依存パッケージのインストール
```bash
npm install
```

2. 開発サーバーの起動
```bash
npm run dev
```

3. ビルド
```bash
npm run build
```

4. ビルドしたアプリケーションのプレビュー
```bash
npm run preview
```

## 技術スタック

### フロントエンド
- React 18
- TypeScript
- React Router DOM
- Tailwind CSS（スタイリング）

### 開発環境
- Vite（ビルドツール）
- ESLint（コード品質管理）
- TypeScript（型システム）

### 特徴
- モダンなReactの機能を活用（Hooks, useState等）
- TypeScriptによる型安全性の確保
- コンポーネントベースのアーキテクチャ
- レスポンシブデザイン

## ディレクトリ構造

```
proman/
├── src/                    # ソースコード
│   ├── components/         # Reactコンポーネント
│   │   ├── TopPage.tsx           # トップページ
│   │   ├── ProjectSearch.tsx     # プロジェクト検索
│   │   ├── ProjectDetail.tsx     # プロジェクト詳細
│   │   ├── ProjectEdit.tsx       # プロジェクト編集
│   │   ├── ProjectRegistration.tsx # プロジェクト登録
│   │   └── ConfirmationModal.tsx  # 確認ダイアログ
│   ├── assets/            # 静的アセット
│   ├── App.tsx           # メインアプリケーション
│   └── main.tsx         # エントリーポイント
├── public/              # 公開ファイル
├── index.html          # HTMLテンプレート
├── package.json       # プロジェクト設定
├── tsconfig.json     # TypeScript設定
├── vite.config.ts   # Vite設定
└── tailwind.config.js # Tailwind CSS設定
```

## 主要コンポーネント

### ProjectSearch
- 検索条件の入力フォーム（事業部、プロジェクト名、種別など）
- 検索結果の表示（テーブル形式）
- 検索条件のリセット機能
- 検索結果からプロジェクト詳細への遷移

### ProjectDetail
- プロジェクト情報の詳細表示（現在開発中）
- トップページへの戻り機能

### ProjectRegistration
- 新規プロジェクトの登録フォーム
- 必須項目の入力バリデーション
- 確認ダイアログによる登録内容の確認
- キャンセル機能

### ConfirmationModal
- 登録内容確認用のモーダルダイアログ
- プロジェクト登録時の確認
- カスタマイズ可能なメッセージとボタン

