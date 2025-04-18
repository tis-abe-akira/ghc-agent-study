import { Link } from 'react-router-dom';

const TopPage = () => {
  return (
    <div>
      <h2 className="text-2xl font-bold mb-6">全社のプロジェクトを収集</h2>
      <div className="bg-white rounded-lg shadow p-6">
        <h3 className="text-xl font-semibold mb-4">プロジェクト管理</h3>
        <ul className="space-y-2">
          <li>
            <Link 
              to="/project/register" 
              className="text-blue-600 hover:text-blue-800 hover:underline"
            >
              プロジェクト登録
            </Link>
          </li>
          <li>
            <Link 
              to="/project/search" 
              className="text-blue-600 hover:text-blue-800 hover:underline"
            >
              プロジェクト検索
            </Link>
          </li>
        </ul>
      </div>
    </div>
  );
};

export default TopPage;
