import { Link } from 'react-router-dom';

const ProjectDetail = () => {
  return (
    <div className="p-6 flex flex-col items-center justify-center">
      <h2 className="text-2xl font-bold mb-6">プロジェクト詳細</h2>
      
      <div className="bg-yellow-100 border-l-4 border-yellow-500 p-6 mb-6 w-full max-w-lg">
        <div className="flex items-center">
          <svg className="w-8 h-8 text-yellow-500 mr-4" fill="none" stroke="currentColor" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg">
            <path strokeLinecap="round" strokeLinejoin="round" strokeWidth="2" d="M12 9v2m0 4h.01m-6.938 4h13.856c1.54 0 2.502-1.667 1.732-3L13.732 4c-.77-1.333-2.694-1.333-3.464 0L3.34 16c-.77 1.333.192 3 1.732 3z"></path>
          </svg>
          <h3 className="text-xl font-bold text-yellow-700">工事中！</h3>
        </div>
        <p className="mt-3 text-yellow-700">
          このページは現在開発中です。近日公開予定です。
        </p>
      </div>
      
      <Link 
        to="/" 
        className="bg-blue-600 text-white px-6 py-2 rounded hover:bg-blue-700"
      >
        トップページに戻る
      </Link>
    </div>
  );
};

export default ProjectDetail;
