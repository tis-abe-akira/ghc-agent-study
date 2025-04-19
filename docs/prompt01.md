# このプロンプトの説明

frontendをCopilot Agentで作成

## プロンプト内容

tis-abe-akira: 現状は プロジェクト登録画面で登録を押すと確認画面が工事中と表示されます。
 それに対して確認画面に、前画面で入力した内容を表示し、 オーケー なら「登録」。 元に戻るなら「戻る」という機能を具備したボタンを配置してください。

GitHub Copilot: ConfirmationModalコンポーネントを修正して、前の画面で入力した内容を表示し、「登録」と「戻る」ボタンを配置する実装をします。まず、コンポーネントの現状を確認し、必要な修正を加えていきます。ProjectRegistrationコンポーネントを確認して、どのようなデータが確認モーダルに渡されるかを調べてみましょう。ProjectRegistrationコンポーネントを確認しました。このコンポーネントからConfirmationModalに渡されているデータを確認できました。

ProjectRegistrationでは、以下のような構造のformDataが作成され、確認モーダルに渡されています：

```
{
  businessUnit: '事業部名',
  projectName: 'プロジェクト名',
  projectType: 'プロジェクト種別',
  sales: '売上高',
  pm: 'PM名',
  pl: 'PL名',
  startDate: '開始日',
  endDate: '終了日',
  rank: 'ランク',
  remarks: '備考'
}
```

また、ConfirmationModalには「入力内容の確認」というタイトルと「以下の内容で登録してよろしいですか？」というメッセージも渡されています。

では、ConfirmationModalコンポーネントを修正して、前画面で入力した内容を表示し、「登録」と「戻る」ボタンを実装します。

````
interface ConfirmationModalProps {
  isOpen: boolean;
  onClose: () => void;
  onConfirm: () => void;
  title: string;
  message: string;
  confirmText: string;
  cancelText: string;
  projectData?: {
    businessUnit: string;
    projectName: string;
    projectType: string;
    sales: string;
    pm: string;
    pl: string;
    startDate: string;
    endDate: string;
    rank: string;
    remarks: string;
  };
}

const ConfirmationModal = ({ 
  isOpen, 
  onClose, 
  onConfirm,
  title,
  message,
  confirmText,
  cancelText,
  projectData 
}: ConfirmationModalProps) => {
  if (!isOpen) return null;

  return (
    <div className="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center z-50">
      <div className="bg-white rounded-lg p-6 w-full max-w-2xl">
        <h3 className="text-xl font-bold mb-4">{title}</h3>
        
        <p className="mb-4">{message}</p>

        {projectData && (
          <div className="bg-gray-50 p-4 rounded-lg mb-6">
            <div className="grid grid-cols-2 gap-4">
              <div>
                <p className="text-sm text-gray-500">事業部/部門</p>
                <p className="font-medium">{projectData.businessUnit}</p>
              </div>
              <div>
                <p className="text-sm text-gray-500">PJ種別</p>
                <p className="font-medium">{projectData.projectType}</p>
              </div>
              <div className="col-span-2">
                <p className="text-sm text-gray-500">PJ名</p>
                <p className="font-medium">{projectData.projectName}</p>
              </div>
              <div>
                <p className="text-sm text-gray-500">PM</p>
                <p className="font-medium">{projectData.pm}</p>
              </div>
              <div>
                <p className="text-sm text-gray-500">PL</p>
                <p className="font-medium">{projectData.pl}</p>
              </div>
              <div>
                <p className="text-sm text-gray-500">開始日</p>
                <p className="font-medium">{projectData.startDate}</p>
              </div>
              <div>
                <p className="text-sm text-gray-500">終了日</p>
                <p className="font-medium">{projectData.endDate}</p>
              </div>
              <div>
                <p className="text-sm text-gray-500">売上高(千円)</p>
                <p className="font-medium">{projectData.sales}</p>
              </div>
              <div>
                <p className="text-sm text-gray-500">ランク</p>
                <p className="font-medium">{projectData.rank}</p>
              </div>
              {projectData.remarks && (
                <div className="col-span-2">
                  <p className="text-sm text-gray-500">備考</p>
                  <p className="font-medium">{projectData.remarks}</p>
                </div>
              )}
            </div>
          </div>
        )}

        <div className="flex justify-center gap-4 mt-6">
          <button
            onClick={onClose}
            className="px-4 py-2 bg-gray-500 text-white rounded hover:bg-gray-600"
          >
            {cancelText || '戻る'}
          </button>
          <button
            onClick={onConfirm}
            className="px-4 py-2 bg-blue-600 text-white rounded hover:bg-blue-700"
          >
            {confirmText || '登録'}
          </button>
        </div>
      </div>
    </div>
  );
};

export default ConfirmationModal;
````

Made changes.

ConfirmationModalコンポーネントを修正しました。新しい実装では以下の機能が追加されています：

1. 前の画面（ProjectRegistration）から渡されたプロジェクトデータが確認モーダル内に表示されるようになりました
2. データは見やすい形式で表示され、各項目にラベルが付いています
3. 「登録」ボタンと「戻る」ボタンが配置され、それぞれ以下の動作をします：
   - 「戻る」ボタン：onClose関数を呼び出し、モーダルを閉じて入力画面に戻ります
   - 「登録」ボタン：onConfirm関数を呼び出し、データを登録して処理を完了します

モーダルのデザインはTailwind CSSを使用して整えており、プロジェクトの情報がグリッドレイアウトで表示されるようになっています。項目ごとにラベルとデータが表示され、ユーザーが入力内容を確認しやすいようになっています。

これで、プロジェクト登録画面で「登録」ボタンを押した後に確認モーダルが表示され、入力内容を確認した上で登録を進めるか、入力画面に戻るかを選択できるようになりました。

tis-abe-akira: 現状、トップ画面から「プロジェクト検索」を押下すると、工事中画面が出ます。
これに対して、
検索条件を入力し、それに合致したプロジェクトを一覧表示するようにしたいです。
なお、一覧に表示する内容は本来はバックエンドからAPI経由で取得しますが、そこは次のイテレーションで実装しますので、今回は適当なダミーデータを返すようにしてください。

検索条件：
入力した項目のうち、どれかを入力する。

一覧表示項目：
入力項目を並べる

将来拡張機能（今回は実装不要です）：
一覧から明細を選択すると、詳細画面に遷移出来る。
詳細画面からは、データの削除や編集ができる。

GitHub Copilot: プロジェクト検索画面を実装して、検索条件に合致したプロジェクトを一覧表示できるようにします。まずは現在の状態を確認してから、必要な修正を加えていきます。現在のProjectSearch.tsxを確認しました。現在は「工事中」と表示されるだけのシンプルなコンポーネントになっています。これを、検索条件を入力できるフォームと検索結果を一覧表示できるように修正します。

次に、プロジェクトデータの構造を理解するために、ProjectRegistration.tsxを確認してみましょう。登録画面のコードから、プロジェクトのデータ構造を確認できました。以下のフィールドがあります：

- businessUnit（事業部/部門）
- projectName（プロジェクト名）
- projectType（プロジェクト種別）
- sales（売上高）
- pm（プロジェクトマネージャー）
- pl（プロジェクトリーダー）
- startDate（開始日）
- endDate（終了日）
- rank（ランク）
- remarks（備考）

この構造をもとに、プロジェクト検索画面を実装します。検索条件として上記の項目が入力できるようにし、結果をテーブルで表示する機能を追加します。

````
import { useState } from 'react';
import { Link } from 'react-router-dom';

// ダミーデータを作成
const dummyProjects = [
  {
    id: '1',
    businessUnit: 'A事業本部',
    projectName: '基幹システム刷新',
    projectType: '新規開発PJ',
    sales: '120000',
    pm: '田中太郎',
    pl: '鈴木一郎',
    startDate: '2025-01-15',
    endDate: '2025-09-30',
    rank: 'S',
    remarks: '重要案件のため優先対応'
  },
  {
    id: '2',
    businessUnit: 'B事業本部',
    projectName: 'ECサイト保守',
    projectType: '保守PJ',
    sales: '45000',
    pm: '佐藤次郎',
    pl: '高橋三郎',
    startDate: '2025-02-01',
    endDate: '2026-01-31',
    rank: 'B',
    remarks: '月次の定例会あり'
  },
  {
    id: '3',
    businessUnit: 'C事業本部',
    projectName: 'クラウド移行',
    projectType: '新規開発PJ',
    sales: '80000',
    pm: '伊藤花子',
    pl: '山本和子',
    startDate: '2025-03-10',
    endDate: '2025-10-31',
    rank: 'A',
    remarks: ''
  },
  {
    id: '4',
    businessUnit: 'A事業本部',
    projectName: 'モバイルアプリ開発',
    projectType: '新規開発PJ',
    sales: '65000',
    pm: '中村修',
    pl: '小林誠',
    startDate: '2025-04-01',
    endDate: '2025-12-15',
    rank: 'A',
    remarks: '週次進捗報告あり'
  },
  {
    id: '5',
    businessUnit: 'B事業本部',
    projectName: 'ERPシステム導入',
    projectType: 'ERP導入支援',
    sales: '200000',
    pm: '加藤雄一',
    pl: '松田健太',
    startDate: '2025-01-10',
    endDate: '2026-03-31',
    rank: 'S',
    remarks: '大規模プロジェクト'
  }
];

const ProjectSearch = () => {
  // 検索条件の状態管理
  const [searchCriteria, setSearchCriteria] = useState({
    businessUnit: '',
    projectName: '',
    projectType: '',
    pm: '',
    pl: '',
    startDateFrom: '',
    startDateTo: '',
    endDateFrom: '',
    endDateTo: '',
    rank: ''
  });

  // 検索結果の状態管理
  const [searchResults, setSearchResults] = useState<typeof dummyProjects | null>(null);
  
  // 入力変更のハンドラー
  const handleInputChange = (e: React.ChangeEvent<HTMLInputElement | HTMLSelectElement>) => {
    const { name, value } = e.target;
    setSearchCriteria(prev => ({
      ...prev,
      [name]: value
    }));
  };

  // 検索実行のハンドラー
  const handleSearch = (e: React.FormEvent) => {
    e.preventDefault();
    
    // 検索条件が何も入力されていない場合は全データを返す
    const isEmptySearch = Object.values(searchCriteria).every(value => value === '');
    
    if (isEmptySearch) {
      setSearchResults(dummyProjects);
      return;
    }
    
    // 検索条件に合致するデータをフィルタリング
    const results = dummyProjects.filter(project => {
      // 入力された検索条件のみでフィルタリング
      return (
        (searchCriteria.businessUnit === '' || project.businessUnit.includes(searchCriteria.businessUnit)) &&
        (searchCriteria.projectName === '' || project.projectName.includes(searchCriteria.projectName)) &&
        (searchCriteria.projectType === '' || project.projectType === searchCriteria.projectType) &&
        (searchCriteria.pm === '' || project.pm.includes(searchCriteria.pm)) &&
        (searchCriteria.pl === '' || project.pl.includes(searchCriteria.pl)) &&
        (searchCriteria.startDateFrom === '' || new Date(project.startDate) >= new Date(searchCriteria.startDateFrom)) &&
        (searchCriteria.startDateTo === '' || new Date(project.startDate) <= new Date(searchCriteria.startDateTo)) &&
        (searchCriteria.endDateFrom === '' || new Date(project.endDate) >= new Date(searchCriteria.endDateFrom)) &&
        (searchCriteria.endDateTo === '' || new Date(project.endDate) <= new Date(searchCriteria.endDateTo)) &&
        (searchCriteria.rank === '' || project.rank === searchCriteria.rank)
      );
    });
    
    setSearchResults(results);
  };

  // 検索条件のリセット
  const handleReset = () => {
    setSearchCriteria({
      businessUnit: '',
      projectName: '',
      projectType: '',
      pm: '',
      pl: '',
      startDateFrom: '',
      startDateTo: '',
      endDateFrom: '',
      endDateTo: '',
      rank: ''
    });
    setSearchResults(null);
  };

  return (
    <div className="p-6">
      <h2 className="text-2xl font-bold mb-6">プロジェクト検索</h2>
      
      {/* 検索フォーム */}
      <form onSubmit={handleSearch} className="bg-white rounded-lg shadow p-6 mb-6">
        <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
          {/* 事業部/部門 */}
          <div className="mb-4">
            <label className="block text-gray-700 text-sm font-bold mb-2">
              事業部/部門
            </label>
            <select
              name="businessUnit"
              value={searchCriteria.businessUnit}
              onChange={handleInputChange}
              className="w-full border rounded p-2"
            >
              <option value="">選択してください</option>
              <option value="A事業本部">A事業本部</option>
              <option value="B事業本部">B事業本部</option>
              <option value="C事業本部">C事業本部</option>
            </select>
          </div>

          {/* プロジェクト種別 */}
          <div className="mb-4">
            <label className="block text-gray-700 text-sm font-bold mb-2">
              PJ種別
            </label>
            <select
              name="projectType"
              value={searchCriteria.projectType}
              onChange={handleInputChange}
              className="w-full border rounded p-2"
            >
              <option value="">選択してください</option>
              <option value="新規開発PJ">新規開発PJ</option>
              <option value="保守開発PJ">保守開発PJ</option>
              <option value="ERP導入支援">ERP導入支援</option>
              <option value="保守PJ">保守PJ</option>
            </select>
          </div>

          {/* プロジェクト名 */}
          <div className="mb-4">
            <label className="block text-gray-700 text-sm font-bold mb-2">
              PJ名
            </label>
            <input
              type="text"
              name="projectName"
              value={searchCriteria.projectName}
              onChange={handleInputChange}
              className="w-full border rounded p-2"
              placeholder="プロジェクト名を入力"
            />
          </div>

          {/* ランク */}
          <div className="mb-4">
            <label className="block text-gray-700 text-sm font-bold mb-2">
              ランク
            </label>
            <select
              name="rank"
              value={searchCriteria.rank}
              onChange={handleInputChange}
              className="w-full border rounded p-2"
            >
              <option value="">選択してください</option>
              <option value="S">S</option>
              <option value="A">A</option>
              <option value="B">B</option>
              <option value="C">C</option>
              <option value="D">D</option>
            </select>
          </div>

          {/* PM */}
          <div className="mb-4">
            <label className="block text-gray-700 text-sm font-bold mb-2">
              PM
            </label>
            <input
              type="text"
              name="pm"
              value={searchCriteria.pm}
              onChange={handleInputChange}
              className="w-full border rounded p-2"
              placeholder="PM名を入力"
            />
          </div>

          {/* PL */}
          <div className="mb-4">
            <label className="block text-gray-700 text-sm font-bold mb-2">
              PL
            </label>
            <input
              type="text"
              name="pl"
              value={searchCriteria.pl}
              onChange={handleInputChange}
              className="w-full border rounded p-2"
              placeholder="PL名を入力"
            />
          </div>

          {/* 開始日（From-To） */}
          <div className="mb-4">
            <label className="block text-gray-700 text-sm font-bold mb-2">
              開始日
            </label>
            <div className="flex items-center space-x-2">
              <input
                type="date"
                name="startDateFrom"
                value={searchCriteria.startDateFrom}
                onChange={handleInputChange}
                className="w-full border rounded p-2"
              />
              <span>～</span>
              <input
                type="date"
                name="startDateTo"
                value={searchCriteria.startDateTo}
                onChange={handleInputChange}
                className="w-full border rounded p-2"
              />
            </div>
          </div>

          {/* 終了日（From-To） */}
          <div className="mb-4">
            <label className="block text-gray-700 text-sm font-bold mb-2">
              終了日
            </label>
            <div className="flex items-center space-x-2">
              <input
                type="date"
                name="endDateFrom"
                value={searchCriteria.endDateFrom}
                onChange={handleInputChange}
                className="w-full border rounded p-2"
              />
              <span>～</span>
              <input
                type="date"
                name="endDateTo"
                value={searchCriteria.endDateTo}
                onChange={handleInputChange}
                className="w-full border rounded p-2"
              />
            </div>
          </div>
        </div>

        {/* 検索ボタンエリア */}
        <div className="flex justify-center gap-4 mt-6">
          <button
            type="button"
            onClick={handleReset}
            className="bg-gray-500 text-white px-6 py-2 rounded hover:bg-gray-600"
          >
            クリア
          </button>
          <button
            type="submit"
            className="bg-blue-600 text-white px-6 py-2 rounded hover:bg-blue-700"
          >
            検索
          </button>
        </div>
      </form>

      {/* 検索結果エリア */}
      {searchResults && (
        <div className="mt-8">
          <h3 className="text-xl font-bold mb-4">検索結果（{searchResults.length}件）</h3>
          
          {searchResults.length > 0 ? (
            <div className="overflow-x-auto">
              <table className="min-w-full bg-white border border-gray-200">
                <thead className="bg-gray-100">
                  <tr>
                    <th className="py-2 px-4 border-b border-r">事業部/部門</th>
                    <th className="py-2 px-4 border-b border-r">PJ名</th>
                    <th className="py-2 px-4 border-b border-r">PJ種別</th>
                    <th className="py-2 px-4 border-b border-r">PM</th>
                    <th className="py-2 px-4 border-b border-r">PL</th>
                    <th className="py-2 px-4 border-b border-r">開始日</th>
                    <th className="py-2 px-4 border-b border-r">終了日</th>
                    <th className="py-2 px-4 border-b border-r">売上高</th>
                    <th className="py-2 px-4 border-b border-r">ランク</th>
                    <th className="py-2 px-4 border-b">詳細</th>
                  </tr>
                </thead>
                <tbody>
                  {searchResults.map((project) => (
                    <tr key={project.id} className="hover:bg-gray-50">
                      <td className="py-2 px-4 border-b border-r">{project.businessUnit}</td>
                      <td className="py-2 px-4 border-b border-r">{project.projectName}</td>
                      <td className="py-2 px-4 border-b border-r">{project.projectType}</td>
                      <td className="py-2 px-4 border-b border-r">{project.pm}</td>
                      <td className="py-2 px-4 border-b border-r">{project.pl}</td>
                      <td className="py-2 px-4 border-b border-r">{project.startDate}</td>
                      <td className="py-2 px-4 border-b border-r">{project.endDate}</td>
                      <td className="py-2 px-4 border-b border-r text-right">
                        {parseInt(project.sales).toLocaleString()}千円
                      </td>
                      <td className="py-2 px-4 border-b border-r text-center">
                        <span className={`inline-block px-2 py-1 rounded ${
                          project.rank === 'S' ? 'bg-red-100 text-red-800' :
                          project.rank === 'A' ? 'bg-orange-100 text-orange-800' :
                          project.rank === 'B' ? 'bg-yellow-100 text-yellow-800' :
                          project.rank === 'C' ? 'bg-blue-100 text-blue-800' :
                          'bg-gray-100 text-gray-800'
                        }`}>
                          {project.rank}
                        </span>
                      </td>
                      <td className="py-2 px-4 border-b text-center">
                        <Link
                          to={`/projects/${project.id}`}
                          className="text-blue-600 hover:text-blue-800 hover:underline"
                        >
                          詳細
                        </Link>
                      </td>
                    </tr>
                  ))}
                </tbody>
              </table>
            </div>
          ) : (
            <div className="bg-yellow-50 border-l-4 border-yellow-400 p-4">
              <p>検索条件に一致するプロジェクトはありませんでした。</p>
            </div>
          )}
        </div>
      )}

      {/* トップに戻るボタン */}
      <div className="mt-8 flex justify-center">
        <Link
          to="/"
          className="bg-gray-500 text-white px-6 py-2 rounded hover:bg-gray-600"
        >
          トップページに戻る
        </Link>
      </div>
    </div>
  );
};

export default ProjectSearch;
````

Made changes.

プロジェクト検索画面を実装しました。検索機能とダミーデータを使用した検索結果の表示ができるようになっています。

実装した機能の概要：

1. **検索フォーム**
   - 事業部/部門、プロジェクト種別、プロジェクト名、ランク、PMやPL名などの検索条件を入力できます
   - 開始日と終了日は期間指定（From-To）で検索できます
   - 「検索」ボタンを押すと条件に合致するプロジェクトが表示されます
   - 「クリア」ボタンで入力条件をリセットできます

2. **検索結果一覧表示**
   - 検索条件に合致したプロジェクトが表形式で表示されます
   - 事業部/部門、プロジェクト名、プロジェクト種別、PM、PL、開始日、終了日、売上高、ランクなどの情報が一目で分かります
   - ランクはSからDまで色分けされ視覚的に確認しやすくなっています
   - 各プロジェクトの「詳細」リンクから、個別のプロジェクト詳細画面に遷移できるようにしてあります（実際の遷移先は次のイテレーションで実装予定）

3. **ダミーデータ**
   - バックエンド実装の前段階として5件のダミープロジェクトデータを用意しました
   - 様々な事業部や種別、日付のデータがあるので、検索機能のテストができます

検索ロジックは入力された条件のみを考慮し、何も入力せずに検索した場合は全件表示されるようになっています。また、検索条件に一致するプロジェクトがない場合はその旨のメッセージが表示されます。

画面はレスポンシブデザインとなっており、スマートフォンからもアクセスしやすいレイアウトになっています。将来的な拡張として、一覧からプロジェクト詳細画面への遷移や、データの編集・削除機能の追加も考慮した設計としています。
