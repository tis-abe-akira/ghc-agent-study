# Proman - プロジェクト管理システム

Promanは、プロジェクトの登録、検索、管理を効率的に行うためのWebアプリケーションです。

## 機能概要

### 1. プロジェクト検索
- 複数の検索条件による柔軟な検索
- 検索条件の折りたたみ機能（アニメーション付き）
- ページネーション機能
- 検索結果の一覧表示

### 2. プロジェクト詳細
- プロジェクト情報の詳細表示
- 編集機能
- 削除機能（確認ダイアログ付き）

### 3. プロジェクト登録
- 新規プロジェクトの登録
- 入力内容の確認ダイアログ
- バリデーション機能

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
- モダンなReactの機能を活用（Hooks, Context等）
- TypeScriptによる型安全性の確保
- コンポーネントベースのアーキテクチャ
- レスポンシブデザイン
- アニメーションによるUX向上

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
- 検索条件の入力フォーム
- 検索結果の表示
- 折りたたみ可能な検索条件パネル
- ページネーション機能

### ProjectDetail
- プロジェクト情報の詳細表示
- 編集・削除機能
- 前画面への戻り機能

### ProjectRegistration
- 新規プロジェクトの登録フォーム
- 入力バリデーション
- 確認ダイアログ

### ConfirmationModal
- 汎用的な確認ダイアログ
- プロジェクトの登録/更新/削除時の確認
- カスタマイズ可能なメッセージとボタン
