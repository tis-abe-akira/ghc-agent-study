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
    remarks: '重要案件のため優先対応',
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
    remarks: '月次の定例会あり',
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
    remarks: '',
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
    remarks: '週次進捗報告あり',
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
    remarks: '大規模プロジェクト',
  },
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
    rank: '',
  });

  // 検索結果の状態管理
  const [searchResults, setSearchResults] = useState<
    typeof dummyProjects | null
  >(null);

  // 入力変更のハンドラー
  const handleInputChange = (
    e: React.ChangeEvent<HTMLInputElement | HTMLSelectElement>
  ) => {
    const { name, value } = e.target;
    setSearchCriteria((prev) => ({
      ...prev,
      [name]: value,
    }));
  };

  // 検索実行のハンドラー
  const handleSearch = (e: React.FormEvent) => {
    e.preventDefault();

    // 検索条件が何も入力されていない場合は全データを返す
    const isEmptySearch = Object.values(searchCriteria).every(
      (value) => value === ''
    );

    if (isEmptySearch) {
      setSearchResults(dummyProjects);
      return;
    }

    // 検索条件に合致するデータをフィルタリング
    const results = dummyProjects.filter((project) => {
      // 入力された検索条件のみでフィルタリング
      return (
        (searchCriteria.businessUnit === '' ||
          project.businessUnit.includes(searchCriteria.businessUnit)) &&
        (searchCriteria.projectName === '' ||
          project.projectName.includes(searchCriteria.projectName)) &&
        (searchCriteria.projectType === '' ||
          project.projectType === searchCriteria.projectType) &&
        (searchCriteria.pm === '' || project.pm.includes(searchCriteria.pm)) &&
        (searchCriteria.pl === '' || project.pl.includes(searchCriteria.pl)) &&
        (searchCriteria.startDateFrom === '' ||
          new Date(project.startDate) >=
            new Date(searchCriteria.startDateFrom)) &&
        (searchCriteria.startDateTo === '' ||
          new Date(project.startDate) <=
            new Date(searchCriteria.startDateTo)) &&
        (searchCriteria.endDateFrom === '' ||
          new Date(project.endDate) >= new Date(searchCriteria.endDateFrom)) &&
        (searchCriteria.endDateTo === '' ||
          new Date(project.endDate) <= new Date(searchCriteria.endDateTo)) &&
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
      rank: '',
    });
    setSearchResults(null);
  };

  return (
    <div className='p-6'>
      <h2 className='text-2xl font-bold mb-6'>プロジェクト検索</h2>

      {/* 検索フォーム */}
      <form
        onSubmit={handleSearch}
        className='bg-white rounded-lg shadow p-6 mb-6'
      >
        <div className='grid grid-cols-1 md:grid-cols-2 gap-4'>
          {/* 事業部/部門 */}
          <div className='mb-4'>
            <label className='block text-gray-700 text-sm font-bold mb-2'>
              事業部/部門
            </label>
            <select
              name='businessUnit'
              value={searchCriteria.businessUnit}
              onChange={handleInputChange}
              className='w-full border rounded p-2'
            >
              <option value=''>選択してください</option>
              <option value='A事業本部'>A事業本部</option>
              <option value='B事業本部'>B事業本部</option>
              <option value='C事業本部'>C事業本部</option>
            </select>
          </div>

          {/* プロジェクト種別 */}
          <div className='mb-4'>
            <label className='block text-gray-700 text-sm font-bold mb-2'>
              PJ種別
            </label>
            <select
              name='projectType'
              value={searchCriteria.projectType}
              onChange={handleInputChange}
              className='w-full border rounded p-2'
            >
              <option value=''>選択してください</option>
              <option value='新規開発PJ'>新規開発PJ</option>
              <option value='保守開発PJ'>保守開発PJ</option>
              <option value='ERP導入支援'>ERP導入支援</option>
              <option value='保守PJ'>保守PJ</option>
            </select>
          </div>

          {/* プロジェクト名 */}
          <div className='mb-4'>
            <label className='block text-gray-700 text-sm font-bold mb-2'>
              PJ名
            </label>
            <input
              type='text'
              name='projectName'
              value={searchCriteria.projectName}
              onChange={handleInputChange}
              className='w-full border rounded p-2'
              placeholder='プロジェクト名を入力'
            />
          </div>

          {/* ランク */}
          <div className='mb-4'>
            <label className='block text-gray-700 text-sm font-bold mb-2'>
              ランク
            </label>
            <select
              name='rank'
              value={searchCriteria.rank}
              onChange={handleInputChange}
              className='w-full border rounded p-2'
            >
              <option value=''>選択してください</option>
              <option value='S'>S</option>
              <option value='A'>A</option>
              <option value='B'>B</option>
              <option value='C'>C</option>
              <option value='D'>D</option>
            </select>
          </div>

          {/* PM */}
          <div className='mb-4'>
            <label className='block text-gray-700 text-sm font-bold mb-2'>
              PM
            </label>
            <input
              type='text'
              name='pm'
              value={searchCriteria.pm}
              onChange={handleInputChange}
              className='w-full border rounded p-2'
              placeholder='PM名を入力'
            />
          </div>

          {/* PL */}
          <div className='mb-4'>
            <label className='block text-gray-700 text-sm font-bold mb-2'>
              PL
            </label>
            <input
              type='text'
              name='pl'
              value={searchCriteria.pl}
              onChange={handleInputChange}
              className='w-full border rounded p-2'
              placeholder='PL名を入力'
            />
          </div>

          {/* 開始日（From-To） */}
          <div className='mb-4'>
            <label className='block text-gray-700 text-sm font-bold mb-2'>
              開始日
            </label>
            <div className='flex items-center space-x-2'>
              <input
                type='date'
                name='startDateFrom'
                value={searchCriteria.startDateFrom}
                onChange={handleInputChange}
                className='w-full border rounded p-2'
              />
              <span>～</span>
              <input
                type='date'
                name='startDateTo'
                value={searchCriteria.startDateTo}
                onChange={handleInputChange}
                className='w-full border rounded p-2'
              />
            </div>
          </div>

          {/* 終了日（From-To） */}
          <div className='mb-4'>
            <label className='block text-gray-700 text-sm font-bold mb-2'>
              終了日
            </label>
            <div className='flex items-center space-x-2'>
              <input
                type='date'
                name='endDateFrom'
                value={searchCriteria.endDateFrom}
                onChange={handleInputChange}
                className='w-full border rounded p-2'
              />
              <span>～</span>
              <input
                type='date'
                name='endDateTo'
                value={searchCriteria.endDateTo}
                onChange={handleInputChange}
                className='w-full border rounded p-2'
              />
            </div>
          </div>
        </div>

        {/* 検索ボタンエリア */}
        <div className='flex justify-center gap-4 mt-6'>
          <button
            type='button'
            onClick={handleReset}
            className='bg-gray-500 text-white px-6 py-2 rounded hover:bg-gray-600'
          >
            クリア
          </button>
          <button
            type='submit'
            className='bg-blue-600 text-white px-6 py-2 rounded hover:bg-blue-700'
          >
            検索
          </button>
        </div>
      </form>

      {/* 検索結果エリア */}
      {searchResults && (
        <div className='mt-8'>
          <h3 className='text-xl font-bold mb-4'>
            検索結果（{searchResults.length}件）
          </h3>

          {searchResults.length > 0 ? (
            <div className='overflow-x-auto'>
              <table className='min-w-full bg-white border border-gray-200'>
                <thead className='bg-gray-100'>
                  <tr>
                    <th className='py-2 px-4 border-b border-r'>事業部/部門</th>
                    <th className='py-2 px-4 border-b border-r'>PJ名</th>
                    <th className='py-2 px-4 border-b border-r'>PJ種別</th>
                    <th className='py-2 px-4 border-b border-r'>PM</th>
                    <th className='py-2 px-4 border-b border-r'>PL</th>
                    <th className='py-2 px-4 border-b border-r'>開始日</th>
                    <th className='py-2 px-4 border-b border-r'>終了日</th>
                    <th className='py-2 px-4 border-b border-r'>売上高</th>
                    <th className='py-2 px-4 border-b border-r'>ランク</th>
                    <th className='py-2 px-4 border-b'>詳細</th>
                  </tr>
                </thead>
                <tbody>
                  {searchResults.map((project) => (
                    <tr key={project.id} className='hover:bg-gray-50'>
                      <td className='py-2 px-4 border-b border-r'>
                        {project.businessUnit}
                      </td>
                      <td className='py-2 px-4 border-b border-r'>
                        {project.projectName}
                      </td>
                      <td className='py-2 px-4 border-b border-r'>
                        {project.projectType}
                      </td>
                      <td className='py-2 px-4 border-b border-r'>
                        {project.pm}
                      </td>
                      <td className='py-2 px-4 border-b border-r'>
                        {project.pl}
                      </td>
                      <td className='py-2 px-4 border-b border-r'>
                        {project.startDate}
                      </td>
                      <td className='py-2 px-4 border-b border-r'>
                        {project.endDate}
                      </td>
                      <td className='py-2 px-4 border-b border-r text-right'>
                        {parseInt(project.sales).toLocaleString()}千円
                      </td>
                      <td className='py-2 px-4 border-b border-r text-center'>
                        <span
                          className={`inline-block px-2 py-1 rounded ${
                            project.rank === 'S'
                              ? 'bg-red-100 text-red-800'
                              : project.rank === 'A'
                              ? 'bg-orange-100 text-orange-800'
                              : project.rank === 'B'
                              ? 'bg-yellow-100 text-yellow-800'
                              : project.rank === 'C'
                              ? 'bg-blue-100 text-blue-800'
                              : 'bg-gray-100 text-gray-800'
                          }`}
                        >
                          {project.rank}
                        </span>
                      </td>
                      <td className='py-2 px-4 border-b text-center'>
                        <Link
                          to={`/projects/${project.id}`}
                          className='text-blue-600 hover:text-blue-800 hover:underline'
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
            <div className='bg-yellow-50 border-l-4 border-yellow-400 p-4'>
              <p>検索条件に一致するプロジェクトはありませんでした。</p>
            </div>
          )}
        </div>
      )}

      {/* トップに戻るボタン */}
      <div className='mt-8 flex justify-center'>
        <Link
          to='/'
          className='bg-gray-500 text-white px-6 py-2 rounded hover:bg-gray-600'
        >
          トップページに戻る
        </Link>
      </div>
    </div>
  );
};

export default ProjectSearch;
